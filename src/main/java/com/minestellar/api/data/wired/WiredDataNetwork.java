/**
 * Copyright (c) 20/05/15 Davide Cossu & Matthew Albrecht.
 * <p/>
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 * <p/>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, see <http://www.gnu.org/licenses>.
 */

package com.minestellar.api.data.wired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class WiredDataNetwork{

    public static ArrayList<WiredDataPacket> packetList;

    /** @see WiredDataNetwork#getLock() */
    private static Object lock = new Object();

    public WiredDataNetwork(){
        packetList = new ArrayList<WiredDataPacket>();
    }

    /**
     * Adds a packet to the queue
     *
     * @param packet The packet to add
     */

    public static void addPacket(WiredDataPacket packet){
        synchronized(getLock()){
            System.out.println("Adding: " + packet);
            boolean contains = false;
            for(WiredDataPacket next : packetList){
                if(packet.equals(next)){ //We check if the packet is already in the list. In that case we won't put it in again
                    contains = true;
                    break;
                }
            }
            if(! contains){
                packetList.listIterator().add(packet); //Needs to use the Iterator otherwise it'll throw java.util.ConcurrentModificationException
            }
        }
    }

    /**
     * Clears the queue of packets
     */

    public static void clearQueue(){
        packetList.clear();
    }

    /**
     * Initialises the timer
     *
     * @see WiredNetworkTask
     */

    public void initTimer(){
        Timer timer = new Timer("MinestellarWiredNetwork");
        timer.scheduleAtFixedRate(new WiredNetworkTask(), 10*1000, 2*1000);
    }

    /**
     * Method that returns the lock for synchronization with this class
     * <p>You probably won't use this</p>
     *
     * @return The lock for this class
     */

    public synchronized static Object getLock(){
        return lock;
    }

    /**
     * In this timerTask we'll notify the designed tileEntities
     * <p>Receiving/Sending a packet is actually done here</p>
     */

    public class WiredNetworkTask extends TimerTask{

        @Override
        public void run(){
            synchronized(WiredDataNetwork.getLock()){
                Iterator<WiredDataPacket> iterator = packetList.iterator();
                while(iterator.hasNext()){
                    WiredDataPacket packet = iterator.next();
                    System.out.println("Are connected?: " + packet.areReceiverAndSenderConnected());
//                    if(packet.areReceiverAndSenderConnected()){ //They must be connected, otherwise the packet won't be sent
                        if(packet.getReceiver() != null){
                            System.out.println("Are connected?: " + packet.areReceiverAndSenderConnected());
                            packet.getReceiver().receiveWiredPacket(packet); //Here is where it notifies the receiver
                            iterator.remove(); //We remove the packet because we don't want a backlog
                        }
//                    }
                }
            }
        }

    }

}
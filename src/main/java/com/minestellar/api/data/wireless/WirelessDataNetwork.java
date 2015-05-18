/**
 * Copyright (c) 14/05/15 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.api.data.wireless;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class represents the whole data network. If you want your {@code TileEntity} to be able to send and/or receive a packet, use this class.
 */

public class WirelessDataNetwork{

    public static ArrayList<WirelessDataPacket> packetList;

    /** @see WirelessDataNetwork#getLock() */
    private static Object lock = new Object();

    public WirelessDataNetwork(){
        packetList = new ArrayList<WirelessDataPacket>();
    }

    /**
     * Adds a packet to the queue
     *
     * @param packet The packet to add
     */

    public static void addPacket(WirelessDataPacket packet){ // This is currently not really working. It's throwing java.util.ConcurrentModificationException
        synchronized(getLock()){
            boolean contains = false;
            for(WirelessDataPacket next : packetList){
                if(packet.equals(next)){
                    contains = true;
                    break;
                }
            }
            if(! contains){
                packetList.listIterator().add(packet);
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
     * Method that returns the lock for synchronization with this class
     * <p>You probably won't use this</p>
     *
     * @return The lock for this class
     */

    public synchronized static Object getLock(){
        return lock;
    }

    /**
     * Initialises the timer
     *
     * @see WirelessNetworkTask
     */

    public void initTimer(){
        Timer timer = new Timer("MinestellarWirelessNetwork");
        timer.scheduleAtFixedRate(new WirelessNetworkTask(), 10*1000, 2*1000);
    }

    /**
     * In this timerTask we'll notify the designed tileEntities
     * <p>Receiving/Sending a packet is actually done here</p>
     */

    public class WirelessNetworkTask extends TimerTask{

        @Override
        public void run(){
            synchronized(WirelessDataNetwork.getLock()){
                for(WirelessDataPacket packet : WirelessDataNetwork.packetList){
                    System.out.println("\n" + packet);
                    packet.getReceiver().receiveWirelessPacket(packet);
                    packetList.remove(packet);
                }
            }
        }

    }

}
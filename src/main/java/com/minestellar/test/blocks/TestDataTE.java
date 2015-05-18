/**
 * Copyright (c) 15/05/15 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.test.blocks;

import com.minestellar.api.data.block.DataTileEntity;
import com.minestellar.api.data.wired.WiredDataPacket;
import com.minestellar.api.data.wireless.WirelessDataNetwork;
import com.minestellar.api.data.wireless.WirelessDataPacket;

/**
 * Example class for the {@link com.minestellar.api.data.block.IDataConnection} implementation
 */

public class TestDataTE extends DataTileEntity{

    private WirelessDataPacket packet, packet1;

    private boolean firstTime = true, firstSent = true;

    @Override
    public void updateEntity(){
        if(firstTime){ //You need to do this otherwise it'll have tons of similar packets in the network queue
            firstTime = false;
            packet = new WirelessDataPacket(worldObj, xCoord, yCoord, zCoord, xCoord, yCoord, zCoord);
            packet1 = new WirelessDataPacket(worldObj, xCoord, yCoord, zCoord, xCoord, yCoord, zCoord);
        }
        super.updateEntity();
    }

    @Override
    public void receiveWiredPacket(WiredDataPacket packet){}

    @Override
    public void receiveWirelessPacket(WirelessDataPacket packet){
        System.out.println("\n" + packet);
    }

    @Override
    public void sendWirelessPacket(){
        packet.setAttribute("2", "Test");
        packet1.setAttribute("Test", "TestAttribute");
        if(firstSent){ //You don't necessarily need to do this. You can leave it alone but it'll send new packets every 1/20th of a second
            firstSent = false;
            WirelessDataNetwork.addPacket(packet);
            WirelessDataNetwork.addPacket(packet1);
        }
    }

    @Override
    public WiredDataPacket sendWiredPacket(){
        return null;
    }
}

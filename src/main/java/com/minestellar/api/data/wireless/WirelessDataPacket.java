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

import com.minestellar.api.data.block.DataTileEntity;
import com.minestellar.api.data.block.IDataConnection;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.Hashtable;
import java.util.Map;

/**
 * This class represents a packet of data sent from Object A to Object B
 */

public class WirelessDataPacket{

    private int senderX, senderY, senderZ, receiverX, receiverY, receiverZ;
    /**
     * The map that stores all the different data. To every identifier (String) corresponds a different Object
     */
    public Map<String, Object> dataMap;

    private World world;

    public WirelessDataPacket(World world, int senderX, int senderY, int senderZ,
                              int receiverX, int receiverY, int receiverZ){
        this.senderX = senderX;
        this.senderY = senderY;
        this.senderZ = senderZ;
        this.receiverX = receiverX;
        this.receiverY = receiverY;
        this.receiverZ = receiverZ;
        this.dataMap = new Hashtable<String, Object>();
        this.world = world;
    }

    public WirelessDataPacket(World world, Vec3 senderCoordinates, Vec3 receiverCoordinates){
        this(world, (int)senderCoordinates.xCoord, (int)senderCoordinates.zCoord, (int)senderCoordinates.zCoord,
                (int)receiverCoordinates.xCoord, (int)receiverCoordinates.yCoord, (int)receiverCoordinates.zCoord);
    }

    /**
     * Gets the sender of the packet
     *
     * @return The block from which the packet is sent
     */

    public DataTileEntity getSender(){
        return world.getTileEntity(senderX, senderY, senderZ) instanceof IDataConnection
                ? (DataTileEntity)world.getTileEntity(senderX, senderY, senderZ)
                : null;
    }

    /**
     * Gets the receiver of the packet
     *
     * @return The block that should receive the packet
     */

    public DataTileEntity getReceiver(){
        return world.getTileEntity(receiverX, receiverY, receiverZ) instanceof IDataConnection
                ? (DataTileEntity)world.getTileEntity(receiverX, receiverY, receiverZ)
                : null;
    }

    /**
     * Adds the given object to the given identifier
     *
     * @param identifier The identifier to which the given object corresponds
     * @param object The object to add the the map
     */

    public void setAttribute(String identifier, Object object){
        dataMap.put(identifier, object);
    }

    /**
     * Returns the object associated to the given identifier
     * @param identifier The identifier
     * @return The object that corresponds to the given identifier
     */

    public Object getAttribute(String identifier){
        return dataMap.get(identifier);
    }

    @Override
    public String toString(){
        return "["+senderX+" "+senderY+" "+senderZ+"]/"
        +"["+receiverX+" "+receiverY+" "+receiverZ+"]";
    }
}
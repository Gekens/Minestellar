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

package com.minestellar.api.data.wired;

import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.minestellar.api.data.block.DataTileEntity;
import com.minestellar.api.data.block.IDataConnection;

import java.util.Hashtable;
import java.util.Map;

public class WiredDataPacket{

    private int senderX, senderY, senderZ, receiverX, receiverY, receiverZ;
    /**
     * The map that stores all the different data. To every identifier (String) corresponds a different Object
     */
    private Map<String, Object> dataMap;

    private World world;

    public WiredDataPacket(World world, int senderX, int senderY, int senderZ,
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
     * Checks if the receiver is connected via {@code DataWireTileEntity} to the sender
     *
     * @see com.minestellar.api.data.block.wire.DataWireTileEntity
     *
     * @return Whether or not the receiver is connected to the sender
     */

    public boolean areReceiverAndSenderConnected(){ //It's obvious that it's not even trying to work.
        int index = 6;
        int x = senderX;
        int y = senderY;
        int z = senderZ;
        ForgeDirection direction;
        DataTileEntity data;

        while(true){
            direction = ForgeDirection.getOrientation(index);
            data = getTileEntity(x+direction.offsetX, y+direction.offsetY, z+direction.offsetZ);

            if(data != null){
                if(x == receiverX && y == receiverY && z == receiverZ){
                    return true;
                }
                x += direction.offsetX;
                y += direction.offsetY;
                z += direction.offsetZ;
            }else{
                index--;
            }
        }
    }

    /**
     * Gets and casts the tileEntity at the given coordinates. Just saves space
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @param z The z coordinate
     *
     * @return The cast TileEntity
     */

    public DataTileEntity getTileEntity(int x, int y, int z){
        if(world.blockExists(x, y, z)){
            if(world.getTileEntity(x, y, z) != null){
                if(world.getTileEntity(x, y, z) instanceof DataTileEntity){
                    return (DataTileEntity) world.getTileEntity(x, y, z);
                }
            }
        }
        return null;
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
     *
     * @param identifier The identifier
     *
     * @return The object that corresponds to the given identifier
     */

    public Object getAttribute(String identifier){
        return dataMap.get(identifier);
    }

}

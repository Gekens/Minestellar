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

package com.minestellar.api.data.block.wire;

import net.minecraftforge.common.util.ForgeDirection;

import com.minestellar.api.blocks.TileEntityWire;
import com.minestellar.api.data.block.IDataConnection;
import com.minestellar.api.data.wired.WiredDataPacket;
import com.minestellar.api.data.wireless.WirelessDataPacket;
import com.minestellar.core.blocks.tile.TileEntityCable;

public class DataWireTileEntity extends TileEntityWire implements IDataConnection{

    public DataWireTileEntity(){
        super(0);
    }

    @Override
    public void updateEntity(){
        super.updateCableConnections();
        this.updateBlockConnections();
    }

    /**
     * Connects the cable to the <code>IDataConnection<code> blocks
     */
    public void updateBlockConnections() {
        if (this.worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof IDataConnection) {
            if (this.worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof DataWireTileEntity) {
                if (this.worldObj.getTileEntity(xCoord, yCoord + 1, zCoord).getBlockMetadata() == this.getBlockMetadata()) {
                    connections[0] = ForgeDirection.UP;
                } else {
                    connections[0] = null;
                }
            } else {
                connections[0] = ForgeDirection.UP;
            }
        } else {
            connections[0] = null;
        }

        if (this.worldObj.getTileEntity(xCoord, yCoord - 1, zCoord) instanceof IDataConnection) {
            if (this.worldObj.getTileEntity(xCoord, yCoord - 1, zCoord) instanceof TileEntityCable) {
                if (this.worldObj.getTileEntity(xCoord, yCoord - 1, zCoord).getBlockMetadata() == this.getBlockMetadata()) {
                    connections[1] = ForgeDirection.DOWN;
                } else {
                    connections[1] = null;
                }
            } else {
                connections[1] = ForgeDirection.DOWN;
            }
        } else {
            connections[1] = null;
        }

        if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord - 1) instanceof IDataConnection) {
            if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord - 1) instanceof TileEntityCable) {
                if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord - 1).getBlockMetadata() == this.getBlockMetadata()) {
                    connections[2] = ForgeDirection.NORTH;
                } else {
                    connections[2] = null;
                }
            } else {
                connections[2] = ForgeDirection.NORTH;
            }
        } else {
            connections[2] = null;
        }

        if (this.worldObj.getTileEntity(xCoord + 1, yCoord, zCoord) instanceof IDataConnection) {
            if (this.worldObj.getTileEntity(xCoord + 1, yCoord, zCoord) instanceof TileEntityCable) {
                if (this.worldObj.getTileEntity(xCoord + 1, yCoord, zCoord).getBlockMetadata() == this.getBlockMetadata()) {
                    connections[3] = ForgeDirection.EAST;
                } else {
                    connections[3] = null;
                }
            } else {
                connections[3] = ForgeDirection.EAST;
            }
        } else {
            connections[3] = null;
        }

        if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord + 1) instanceof IDataConnection) {
            if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord + 1) instanceof TileEntityCable) {
                if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord + 1).getBlockMetadata() == this.getBlockMetadata()) {
                    connections[4] = ForgeDirection.SOUTH;
                } else {
                    connections[4] = null;
                }
            } else {
                connections[4] = ForgeDirection.SOUTH;
            }
        } else {
            connections[4] = null;
        }

        if (this.worldObj.getTileEntity(xCoord - 1, yCoord, zCoord) instanceof IDataConnection) {
            if (this.worldObj.getTileEntity(xCoord - 1, yCoord, zCoord) instanceof TileEntityCable) {
                if (this.worldObj.getTileEntity(xCoord - 1, yCoord, zCoord).getBlockMetadata() == this.getBlockMetadata()) {
                    connections[5] = ForgeDirection.WEST;
                } else {
                    connections[5] = null;
                }
            } else {
                connections[5] = ForgeDirection.WEST;
            }
        } else {
            connections[5] = null;
        }
    }

    @Override
    public boolean canDataConnect(){
        return true;
    }

    @Override
    public void receiveWirelessPacket(WirelessDataPacket packet){

    }

    @Override
    public void receiveWiredPacket(WiredDataPacket packet){

    }

    @Override
    public void sendWirelessPacket(){

    }

    @Override
    public void sendWiredPacket(){

    }
}
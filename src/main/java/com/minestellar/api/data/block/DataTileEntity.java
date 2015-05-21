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

package com.minestellar.api.data.block;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import com.minestellar.api.data.wired.WiredDataPacket;
import com.minestellar.api.data.wireless.WirelessDataPacket;

/**
 * Extend this class if you want any of your tileEntities to be connectable to the network
 */

public abstract class DataTileEntity extends TileEntity implements IDataConnection{

    public DataTileEntity(){}

    @Override
    public void updateEntity(){
        super.updateEntity();
        //Makes sure that the methods are called
        this.sendWiredPacket();
        this.sendWirelessPacket();
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt){
        super.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt){
        super.readFromNBT(nbt);
    }

    // IDataConnection Implementation

    @Override
    public boolean canDataConnect(){ return true; }

    @Override
    public abstract void receiveWiredPacket(WiredDataPacket packet);

    @Override
    public abstract void receiveWirelessPacket(WirelessDataPacket packet);

    @Override
    public abstract void sendWirelessPacket();

    @Override
    public abstract void sendWiredPacket();
}
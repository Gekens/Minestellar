/**
 * Copyright (c) 26/apr/2015 Davide Cossu & Matthew Albrecht.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, see <http://www.gnu.org/licenses>.
 */

package com.minestellar.core.blocks.tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import com.minestellar.core.network.NetworkHandler;
import com.minestellar.core.network.message.MessageAntennaOnline;
import com.minestellar.core.util.MinestellarLog;
import cpw.mods.fml.common.Optional;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

@Optional.Interface(iface = "cofh.api.energy.IEnergyHandler", modid = "CoFHCore")
public class TileEntityRadioAntenna extends TileEntity implements IEnergyHandler{

    private EnergyStorage storage;

    public TileEntityRadioAntenna() {
        storage = new EnergyStorage(32000);
    }

    @Override
    public void updateEntity() {

        MinestellarLog.info("Stored: " + storage.getEnergyStored());

        if(storage.getEnergyStored() > 0){
            NetworkHandler.sendToServer(new MessageAntennaOnline(true, xCoord, yCoord, zCoord));
        }else{
            NetworkHandler.sendToServer(new MessageAntennaOnline(false, xCoord, yCoord, zCoord));
        }

        /**
         * This is to receive the energy
         */

        for (int i = 0; i < 6; i++){
            // ForgeDirection is a useful helper class for handling directions.
            int targetX = xCoord + ForgeDirection.getOrientation(i).offsetX;
            int targetY = yCoord + ForgeDirection.getOrientation(i).offsetY;
            int targetZ = zCoord + ForgeDirection.getOrientation(i).offsetZ;

            TileEntity tile = worldObj.getTileEntity(targetX, targetY, targetZ);
            if(tile instanceof IEnergyHandler){
                int maxReceive = storage.getMaxReceive();
                int maxAvailable = storage.receiveEnergy(maxReceive, true);
                int energyTransferred = ((IEnergyHandler) tile).extractEnergy(ForgeDirection.getOrientation(i), maxAvailable, false);
                storage.receiveEnergy(energyTransferred, false);
            }
        }

        storage.extractEnergy(10, false);

    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        storage.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        storage.writeToNBT(nbt);
    }

    /**
     * RF IMPLEMENTATION
     */
    @Optional.Method(modid = "CoFHCore")
    @Override
    public boolean canConnectEnergy(ForgeDirection direction) {
        return true;
    }

    @Optional.Method(modid = "CoFHCore")
    @Override
    public int extractEnergy(ForgeDirection direction, int maxExtract, boolean simulate) {
        return 0;
    }

    @Optional.Method(modid = "CoFHCore")
    @Override
    public int getEnergyStored(ForgeDirection direction) {
        return storage.getEnergyStored();
    }

    @Optional.Method(modid = "CoFHCore")
    @Override
    public int getMaxEnergyStored(ForgeDirection direction) {
        return storage.getMaxEnergyStored();
    }

    @Optional.Method(modid = "CoFHCore")
    @Override
    public int receiveEnergy(ForgeDirection direction, int maxReceive, boolean simulate) {
        return storage.receiveEnergy(maxReceive, simulate);
    }
}
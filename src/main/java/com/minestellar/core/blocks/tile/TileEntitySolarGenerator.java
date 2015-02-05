/**
 * Copyright (c) 05/feb/2015 Davide Cossu & Matthew Albrecht.
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

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

import com.minestellar.core.blocks.machines.SolarGenerator;
import com.minestellar.core.util.MinestellarLog;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.Optional.Method;

@Optional.Interface(iface = "cofh.api.energy.IEnergyHandler", modid = "CoFHCore")
public class TileEntitySolarGenerator extends TileEntity implements IEnergyHandler{

	private EnergyStorage storage;

	public TileEntitySolarGenerator(){
		storage = new EnergyStorage(150000);
	}

	@Override
	public void updateEntity(){
		if(!this.worldObj.provider.hasNoSky){
			if (storage.getEnergyStored() > 0){
				int stored = storage.getEnergyStored();
				storage.setEnergyStored(stored += this.getSolarLight(this.worldObj, this.xCoord, this.yCoord, this.zCoord));
				MinestellarLog.info("Energy: " + storage.getEnergyStored());
			}
		}
	}
	
	public int getSolarLight(World world, int x, int y, int z){
		int i = world.getBlockMetadata(x, y, z);
		int j = world.getSavedLightValue(EnumSkyBlock.Sky, x, y, z) - world.skylightSubtracted;
		float f = world.getCelestialAngleRadians(1.0F);
		if (f < 3.141593F) {
			f += (0.0F - f) * 0.2F;
		} else {
			f += (6.283186F - f) * 0.2F;
		}
		j = Math.round(j * MathHelper.cos(f));
		if (j < 0) {
			j = 0;
		}
		if (j > 15) {
			j = 15;
		}
		return j;
	}

	// RF IMPLEMENTATION

	@Method(modid = "CoFHCore")
	@Override
	public boolean canConnectEnergy(ForgeDirection direction) {
		return true;
	}

	@Method(modid = "CoFHCore")
	@Override
	public int extractEnergy(ForgeDirection direction, int maxExtract, boolean simulate) {
		return storage.extractEnergy(storage.getMaxExtract(), simulate);
	}

	@Method(modid = "CoFHCore")
	@Override
	public int getEnergyStored(ForgeDirection direction) {
		return storage.getEnergyStored();
	}

	@Method(modid = "CoFHCore")
	@Override
	public int getMaxEnergyStored(ForgeDirection direction) {
		return storage.getMaxEnergyStored();
	}

	@Method(modid = "CoFHCore")
	@Override
	public int receiveEnergy(ForgeDirection direction, int maxReceive, boolean simulate) {
		return 0;
	}

}
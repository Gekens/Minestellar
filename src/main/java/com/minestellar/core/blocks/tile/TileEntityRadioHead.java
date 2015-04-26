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

import java.util.Timer;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

public class TileEntityRadioHead extends TileEntity{
	
	private Timer checkerTimer;
	
	public TileEntityRadioHead(){
		checkerTimer = new Timer();
		checkerTimer.scheduleAtFixedRate(new TileEntityRadioHeadTask(this), 100, 10 * 1000);
	}
	
	@Override
	public void updateEntity(){
		if(this.getBlockMetadata() == 1){ // If the head is surrounded
			//Just for now, we'll have custom ones in the future
			worldObj.spawnParticle("snowshovel", xCoord+0.5D - 2, yCoord+2, zCoord+0.5D - 2, 0.25D, -0.01D, 0.25D);
			worldObj.spawnParticle("snowshovel", xCoord+0.5D + 2, yCoord+2, zCoord+0.5D - 2, -0.25D, -0.01D, 0.25D);
			worldObj.spawnParticle("snowshovel", xCoord+0.5D - 2, yCoord+2, zCoord+0.5D + 2, 0.25D, -0.01D, -0.25D);
			worldObj.spawnParticle("snowshovel", xCoord+0.5D + 2, yCoord+2, zCoord+0.5D + 2, -0.25D, -0.01D, -0.25D);
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
	}
	
}
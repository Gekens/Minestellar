/**
 * Copyright (c) 31/dic/2014 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.core.blocks.tileEntities;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.Optional.Method;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyHandler;

@Optional.Interface(iface = "cofh.api.energy.IEnergyHandler", modid = "CoFHCore")
public class TileEntityCable extends TileEntity implements IEnergyHandler
{

	/**
	 * UP, DOWN, NORTH, EAST, SOUTH, WEST
	 */
	public ForgeDirection[] connections = new ForgeDirection[6];

	private EnergyStorage storage = new EnergyStorage(360);

	public TileEntityCable(){

	}

	@Override
	public void updateEntity(){
		this.updateConnections();
	}

	public void updateConnections(){
		if(this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof TileEntityCable) connections[0] = ForgeDirection.UP;
		else connections[0] = null;

		if(this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) instanceof TileEntityCable) connections[1] = ForgeDirection.DOWN;
		else connections[1] = null;

		if(this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1) instanceof TileEntityCable) connections[2] = ForgeDirection.NORTH;
		else connections[2] = null;

		if(this.worldObj.getTileEntity(xCoord+1, yCoord, zCoord) instanceof TileEntityCable) connections[3] = ForgeDirection.EAST;
		else connections[3] = null;

		if(this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1) instanceof TileEntityCable) connections[4] = ForgeDirection.SOUTH;
		else connections[4] = null;

		if(this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord) instanceof TileEntityCable) connections[5] = ForgeDirection.WEST;
		else connections[5] = null;
	}

	public boolean onlyOneOpposite(ForgeDirection[] directions){
		ForgeDirection mainDirection = null;

		boolean isOpposite = false;

		for(int i = 0; i < directions.length; i++)
		{
			if(mainDirection == null && directions[i] != null) mainDirection = directions[i];

			if(directions[i] != null && mainDirection != directions[i])
			{
				if(!isOpposite(mainDirection, directions[i])) return false;	
				else isOpposite = true;
			}
		}

		return isOpposite;
	}

	public boolean isOpposite(ForgeDirection firstDirection, ForgeDirection secondDirection)
	{
		if((firstDirection.equals(ForgeDirection.NORTH) && secondDirection.equals(ForgeDirection.SOUTH)) || (firstDirection.equals(ForgeDirection.SOUTH) && secondDirection.equals(ForgeDirection.NORTH))) return true;
		if((firstDirection.equals(ForgeDirection.EAST) && secondDirection.equals(ForgeDirection.WEST)) || (firstDirection.equals(ForgeDirection.WEST) && secondDirection.equals(ForgeDirection.EAST))) return true;
		if((firstDirection.equals(ForgeDirection.UP) && secondDirection.equals(ForgeDirection.DOWN)) || (firstDirection.equals(ForgeDirection.DOWN) && secondDirection.equals(ForgeDirection.UP))) return true;

		return false;
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

	//RF IMPLEMENTATION

	@Method(modid = "CoFHCore")
	@Override
	public boolean canConnectEnergy(ForgeDirection direction) {

		if(direction.equals(ForgeDirection.UP)){
			if((worldObj.getTileEntity(xCoord, yCoord+1, zCoord) != null) && (worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof IEnergyConnection)){
				connections[0] = ForgeDirection.UP;
				return true;
			}
		}else{
			connections[0] = null;
		}
		if(direction.equals(ForgeDirection.DOWN)){
			if((worldObj.getTileEntity(xCoord, yCoord-1, zCoord) != null) && (worldObj.getTileEntity(xCoord, yCoord-1, zCoord) instanceof IEnergyConnection)){
				return true;
			}
		}
		if(direction.equals(ForgeDirection.EAST)){
			if((worldObj.getTileEntity(xCoord+1, yCoord, zCoord) != null) && (worldObj.getTileEntity(xCoord+1, yCoord, zCoord) instanceof IEnergyConnection)){
				return true;
			}
		}
		if(direction.equals(ForgeDirection.WEST)){
			if((worldObj.getTileEntity(xCoord-1, yCoord, zCoord) != null) && (worldObj.getTileEntity(xCoord-1, yCoord, zCoord) instanceof IEnergyConnection)){
				return true;
			}
		}
		if(direction.equals(ForgeDirection.NORTH)){
			if((worldObj.getTileEntity(xCoord, yCoord, zCoord-1) != null) && (worldObj.getTileEntity(xCoord, yCoord, zCoord-1) instanceof IEnergyConnection)){
				return true;
			}
		}
		if(direction.equals(ForgeDirection.SOUTH)){
			if((worldObj.getTileEntity(xCoord, yCoord, zCoord+1) != null) && (worldObj.getTileEntity(xCoord, yCoord, zCoord+1) instanceof IEnergyConnection)){
				
				return true;
			}
		}

		return false;
	}

	@Method(modid = "CoFHCore")
	@Override
	public int extractEnergy(ForgeDirection direction, int maxExtract, boolean simulate) {
		return storage.extractEnergy(maxExtract, simulate);
	}

	@Method(modid = "CoFHCore")
	@Override
	public int getEnergyStored(ForgeDirection direction) {
		return storage.getMaxEnergyStored();
	}

	@Method(modid = "CoFHCore")
	@Override
	public int getMaxEnergyStored(ForgeDirection direction) {
		return storage.getEnergyStored();
	}

	@Method(modid = "CoFHCore")
	@Override
	public int receiveEnergy(ForgeDirection direction, int maxReceive, boolean simulate) {
		return storage.receiveEnergy(maxReceive, simulate);
	}

}

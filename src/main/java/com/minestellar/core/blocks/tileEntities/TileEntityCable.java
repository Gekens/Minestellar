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
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyStorage;

@Optional.Interface(iface = "cofh.api.energy.IEnergyHandler", modid = "CoFHCore")
public class TileEntityCable extends TileEntity implements IEnergyHandler
{

	/**
	 * UP, DOWN, NORTH, EAST, SOUTH, WEST
	 */
	public ForgeDirection[] connections = new ForgeDirection[6];

	private EnergyStorage storage = new EnergyStorage(3600);

	public TileEntityCable(int meta){
		this.blockMetadata = meta;
	}

	@Override
	public void updateEntity(){
		this.updateCableConnections();
		this.updateBlockConnections();
		if(storage.getEnergyStored() > 0){
			for(int i = 0; i < 6; i++){

				//ForgeDirection is a useful helper class for handling directions.
				int targetX = xCoord + ForgeDirection.getOrientation(i).offsetX;
				int targetY = yCoord + ForgeDirection.getOrientation(i).offsetY;
				int targetZ = zCoord + ForgeDirection.getOrientation(i).offsetZ;

				TileEntity tile = worldObj.getTileEntity(targetX, targetY, targetZ);
				if(tile instanceof IEnergyHandler){
					//System.out.println("instanceof IEnergyHandler");
					System.out.println("Stored: " + storage.getEnergyStored() + " Max: " + storage.getMaxEnergyStored());
					int maxExtract = storage.getMaxExtract(); //Gets the maximum amount of energy that can be extracted from this tile in one tick.
					int maxAvailable = storage.extractEnergy(maxExtract, true); //Simulates removing "maxExtract" to find out how much energy is actually available.
					int energyTransferred = ((IEnergyHandler) tile).receiveEnergy(ForgeDirection.getOrientation(i), maxAvailable, false); //Sends "maxAvailable" to the target tile and records how much energy was accepted. 

					storage.extractEnergy(energyTransferred, false);//Extract the energy transferred from the internal storage.
				}
			}
		}
	}

	public void updateCableConnections(){
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

	public void updateBlockConnections(){
		if(this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof IEnergyProvider) connections[0] = ForgeDirection.UP;
		else connections[0] = null;

		if(this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) instanceof IEnergyProvider) connections[1] = ForgeDirection.DOWN;
		else connections[1] = null;

		if(this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1) instanceof IEnergyProvider) connections[2] = ForgeDirection.NORTH;
		else connections[2] = null;

		if(this.worldObj.getTileEntity(xCoord+1, yCoord, zCoord) instanceof IEnergyProvider) connections[3] = ForgeDirection.EAST;
		else connections[3] = null;

		if(this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1) instanceof IEnergyProvider) connections[4] = ForgeDirection.SOUTH;
		else connections[4] = null;

		if(this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord) instanceof IEnergyProvider) connections[5] = ForgeDirection.WEST;
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
	
	@Override
	public int getBlockMetadata(){
		return blockMetadata;
	}

	//RF IMPLEMENTATION

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
		return this.storage.receiveEnergy(maxReceive, simulate);
	}

}

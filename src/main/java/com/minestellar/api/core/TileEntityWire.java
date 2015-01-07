/**
 * Copyright (c) 07/gen/2015 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.api.core;

import com.minestellar.core.blocks.tile.TileEntityPipe;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityWire extends TileEntity implements IWire{

	/**
	 * UP, DOWN, NORTH, EAST, SOUTH, WEST
	 */
	public ForgeDirection[] connections = new ForgeDirection[6];
	
	public TileEntityWire(int meta){
		this.blockMetadata = meta;
	}
	
	@Override
	public void updateEntity(){
		this.updateCableConnections();
	}
	
	@Override
	public void updateCableConnections(){

		if ((this.worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof TileEntityWire) && this.worldObj.getTileEntity(xCoord, yCoord + 1, zCoord).getBlockMetadata() == this.getBlockMetadata())
			connections[0] = ForgeDirection.UP;
		else
			connections[0] = null;

		if ((this.worldObj.getTileEntity(xCoord, yCoord - 1, zCoord) instanceof TileEntityWire) && this.worldObj.getTileEntity(xCoord, yCoord - 1, zCoord).getBlockMetadata() == this.getBlockMetadata())
			connections[1] = ForgeDirection.DOWN;
		else
			connections[1] = null;

		if ((this.worldObj.getTileEntity(xCoord, yCoord, zCoord - 1) instanceof TileEntityWire) && this.worldObj.getTileEntity(xCoord, yCoord, zCoord - 1).getBlockMetadata() == this.getBlockMetadata())
			connections[2] = ForgeDirection.NORTH;
		else
			connections[2] = null;

		if ((this.worldObj.getTileEntity(xCoord + 1, yCoord, zCoord) instanceof TileEntityWire) && this.worldObj.getTileEntity(xCoord + 1, yCoord, zCoord).getBlockMetadata() == this.getBlockMetadata())
			connections[3] = ForgeDirection.EAST;
		else
			connections[3] = null;

		if ((this.worldObj.getTileEntity(xCoord, yCoord, zCoord + 1) instanceof TileEntityWire) && this.worldObj.getTileEntity(xCoord, yCoord, zCoord + 1).getBlockMetadata() == this.getBlockMetadata())
			connections[4] = ForgeDirection.SOUTH;
		else
			connections[4] = null;

		if ((this.worldObj.getTileEntity(xCoord - 1, yCoord, zCoord) instanceof TileEntityWire) && this.worldObj.getTileEntity(xCoord - 1, yCoord, zCoord).getBlockMetadata() == this.getBlockMetadata())
			connections[5] = ForgeDirection.WEST;
		else
			connections[5] = null;

	}

	@Override
	public boolean onlyOneOpposite(ForgeDirection[] directions){
		ForgeDirection mainDirection = null;

		boolean isOpposite = false;

		for (int i = 0; i < directions.length; i++) {
			if (mainDirection == null && directions[i] != null)
				mainDirection = directions[i];

			if (directions[i] != null && mainDirection != directions[i]) {
				if (!isOpposite(mainDirection, directions[i]))
					return false;
				else
					isOpposite = true;
			}
		}

		return isOpposite;
	}

	@Override
	public boolean isOpposite(ForgeDirection firstDirection, ForgeDirection secondDirection){
		if ((firstDirection.equals(ForgeDirection.NORTH) && secondDirection.equals(ForgeDirection.SOUTH)) || (firstDirection.equals(ForgeDirection.SOUTH) && secondDirection.equals(ForgeDirection.NORTH)))
			return true;
		if ((firstDirection.equals(ForgeDirection.EAST) && secondDirection.equals(ForgeDirection.WEST)) || (firstDirection.equals(ForgeDirection.WEST) && secondDirection.equals(ForgeDirection.EAST)))
			return true;
		if ((firstDirection.equals(ForgeDirection.UP) && secondDirection.equals(ForgeDirection.DOWN)) || (firstDirection.equals(ForgeDirection.DOWN) && secondDirection.equals(ForgeDirection.UP)))
			return true;

		return false;
	}

}
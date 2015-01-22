/**
 * Copyright (c) 04/January/2015 Davide Cossu & Matthew Albrecht.
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

import java.util.Random;

import mekanism.api.gas.Gas;
import mekanism.api.gas.GasStack;
import mekanism.api.gas.GasTank;
import mekanism.api.gas.IGasHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import com.minestellar.api.core.TileEntityWire;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.Optional.Method;

@Optional.Interface(iface = "mekanism.api.gas.IGasHandler", modid = "Mekanism")
public class TileEntityPipe extends TileEntityWire implements IGasHandler {

	GasTank gasTank;

	/** How fast this tank can output gas. */
	public int output;

	public int currentGasAmount;

	public TileEntityPipe(int meta) {
		super(meta);
		this.blockMetadata = meta;

		switch (meta) {
		case 0:
			gasTank = new GasTank(300);
			output = 300;
			break;
		case 1:
			gasTank = new GasTank(600);
			output = 600;
			break;
		case 2:
			gasTank = new GasTank(1000);
			output = 1000;
			break;
		default:
			break;
		}

	}

	/**
	 * Called many times per second, it checks if it can transfer gas to any adjacent to our block   
	 */

	@Override
	public void updateEntity() {
		this.updateBlockConnections();
		super.updateCableConnections();

		if(!worldObj.isRemote && gasTank.getGas() != null){
			if(gasTank.getGas().getGas() != null){
				for(int i = 0; i < 6; i++) {
					try{ // This is necessary, I don't know why I keep getting an NPE at the line below

						GasStack toSend = new GasStack(gasTank.getGas().getGas(), Math.min(gasTank.getStored(), output));

						// ForgeDirection is a useful helper class for handling directions.
						int targetX = xCoord + ForgeDirection.getOrientation(i).offsetX;
						int targetY = yCoord + ForgeDirection.getOrientation(i).offsetY;
						int targetZ = zCoord + ForgeDirection.getOrientation(i).offsetZ;

						TileEntity tile = worldObj.getTileEntity(targetX, targetY, targetZ);

						if (tile instanceof IGasHandler) {
							if(((IGasHandler)tile).canReceiveGas(ForgeDirection.getOrientation(i).getOpposite(), gasTank.getGas().getGas()))
								gasTank.draw(((IGasHandler)tile).receiveGas(ForgeDirection.getOrientation(i), toSend), true);
						}
					}catch(Exception e){}

				}
			}
			
			if(!worldObj.isRemote){
				int newGasAmount = gasTank.getStored();

				if(newGasAmount != this.currentGasAmount){
					markDirty();
					this.currentGasAmount = newGasAmount;
				}
			}

		}
	}

	/**
	 * Connects the cable to the <code>IGasHandler<code> blocks
	 */

	public void updateBlockConnections() {

		if (this.worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof IGasHandler) {
			if (this.worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof TileEntityPipe) {
				if (this.worldObj.getTileEntity(xCoord, yCoord + 1, zCoord).getBlockMetadata() == this.getBlockMetadata()) {
					connections[0] = ForgeDirection.UP;
				}
				else
					connections[0] = null;
			}
			else {
				connections[0] = ForgeDirection.UP;
			}
		}
		else
			connections[0] = null;

		if (this.worldObj.getTileEntity(xCoord, yCoord - 1, zCoord) instanceof IGasHandler) {
			if (this.worldObj.getTileEntity(xCoord, yCoord - 1, zCoord) instanceof TileEntityPipe) {
				if (this.worldObj.getTileEntity(xCoord, yCoord - 1, zCoord).getBlockMetadata() == this.getBlockMetadata()) {
					connections[1] = ForgeDirection.DOWN;
				}
				else
					connections[1] = null;
			}
			else {
				connections[1] = ForgeDirection.DOWN;
			}
		}
		else
			connections[1] = null;

		if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord - 1) instanceof IGasHandler) {
			if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord - 1) instanceof TileEntityPipe) {
				if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord - 1).getBlockMetadata() == this.getBlockMetadata()) {
					connections[2] = ForgeDirection.NORTH;
				}
				else
					connections[2] = null;
			}
			else {
				connections[2] = ForgeDirection.NORTH;
			}
		}
		else
			connections[2] = null;

		if (this.worldObj.getTileEntity(xCoord + 1, yCoord, zCoord) instanceof IGasHandler) {
			if (this.worldObj.getTileEntity(xCoord + 1, yCoord, zCoord) instanceof TileEntityPipe) {
				if (this.worldObj.getTileEntity(xCoord + 1, yCoord, zCoord).getBlockMetadata() == this.getBlockMetadata()) {
					connections[3] = ForgeDirection.EAST;
				}
				else
					connections[3] = null;
			}
			else {
				connections[3] = ForgeDirection.EAST;
			}
		}
		else
			connections[3] = null;

		if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord + 1) instanceof IGasHandler) {
			if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord + 1) instanceof TileEntityPipe) {
				if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord + 1).getBlockMetadata() == this.getBlockMetadata()) {
					connections[4] = ForgeDirection.SOUTH;
				}
				else
					connections[4] = null;
			}
			else {
				connections[4] = ForgeDirection.SOUTH;
			}
		}
		else
			connections[4] = null;

		if (this.worldObj.getTileEntity(xCoord - 1, yCoord, zCoord) instanceof IGasHandler) {
			if (this.worldObj.getTileEntity(xCoord - 1, yCoord, zCoord) instanceof TileEntityPipe) {
				if (this.worldObj.getTileEntity(xCoord - 1, yCoord, zCoord).getBlockMetadata() == this.getBlockMetadata()) {
					connections[5] = ForgeDirection.WEST;
				}
				else
					connections[5] = null;
			}
			else {
				connections[5] = ForgeDirection.WEST;
			}
		}
		else
			connections[5] = null;
	}

	// MEKANISM GAS IMPLEMENTATION

	@Method(modid = "Mekanism")
	@Override
	public boolean canDrawGas(ForgeDirection side, Gas type)
	{
		return gasTank.canDraw(type);
	}

	@Method(modid = "Mekanism")
	@Override
	public boolean canReceiveGas(ForgeDirection direction, Gas gas) {
		return true;
	}

	@Method(modid = "Mekanism")
	@Override
	public GasStack drawGas(ForgeDirection side, int amount){
		return gasTank.draw(amount, true);
	}

	@Method(modid = "Mekanism")
	@Override
	public int receiveGas(ForgeDirection side, GasStack stack){
		return gasTank.receive(stack, true);
	}

}

/**
 * Copyright (c) 22/gen/2015 Davide Cossu & Matthew Albrecht.
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

import mekanism.api.gas.Gas;
import mekanism.api.gas.GasStack;
import mekanism.api.gas.GasTank;
import mekanism.api.gas.IGasHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.Optional.Method;

@Optional.Interface(iface = "mekanism.api.gas.IGasHandler", modid = "Mekanism")
public class TileEntityOxygenCollector extends TileEntity implements IGasHandler{

	private GasTank gasTank;

	private int currentGasAmount, natureBlocks;

	public TileEntityOxygenCollector(){
		gasTank = new GasTank(15000);
		gasTank.setGas(new GasStack(new Gas("oxygen"), 0));
	}

	@Override
	public void updateEntity(){
		natureBlocks = getNatureBlocks(5);

		//System.out.println("Blocks: " + natureBlocks);

		if(!worldObj.isRemote && gasTank.getGas() != null){
			if(gasTank.getGas().getGas() != null){
				if(gasTank.stored.amount < gasTank.getMaxGas()){
					gasTank.stored.amount += 5 * natureBlocks; // Augments the stored gas amount based on the amount of nature blocks

					System.out.println("Amount: " + gasTank.stored.amount + " Mult: " + 5 * natureBlocks);
				}
			}
		}
	}

	/**
	 * Gets the amount of "nature" blocks
	 * 
	 * @param maxDistanceAway The max radius
	 * @return the number of nature blocks
	 */

	private int getNatureBlocks(int maxDistanceAway){

		int xMov = 0 - maxDistanceAway;
		int yMov = maxDistanceAway;
		int zMov = 0 - maxDistanceAway;

		int natureBlocks = 0;

		while(true){
			final Block currentBlock = worldObj.getBlock(this.xCoord + xMov, this.yCoord + yMov, this.zCoord + zMov);

			if(currentBlock == Blocks.leaves || currentBlock == Blocks.leaves2 || currentBlock == Blocks.grass || currentBlock == Blocks.cactus){
				natureBlocks++;
			}

			if (zMov == maxDistanceAway && xMov == maxDistanceAway && yMov == -maxDistanceAway){
				return natureBlocks;
			}

			if (zMov == maxDistanceAway && xMov == maxDistanceAway){
				yMov--;
				xMov = 0 - maxDistanceAway;
				zMov = 0 - maxDistanceAway;
				continue;
			}

			if (xMov == maxDistanceAway){
				zMov++;
				xMov = 0 - maxDistanceAway;
				continue;
			}

			xMov++;

		}

	}

	// MEKANISM IMPLEMENTATION

	@Method(modid = "Mekanism")
	@Override
	public int receiveGas(ForgeDirection side, GasStack stack) {
		return 0;
	}

	@Method(modid = "Mekanism")
	@Override
	public GasStack drawGas(ForgeDirection side, int amount) {
		return gasTank.draw(amount, true);
	}

	@Method(modid = "Mekanism")
	@Override
	public boolean canReceiveGas(ForgeDirection side, Gas type) {
		return false;
	}

	@Method(modid = "Mekanism")
	@Override
	public boolean canDrawGas(ForgeDirection side, Gas type) {
		return true;
	}

}
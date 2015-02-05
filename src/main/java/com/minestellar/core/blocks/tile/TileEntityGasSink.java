/**
 * Copyright (c) 27/gen/2015 Davide Cossu & Matthew Albrecht.
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

import com.minestellar.core.model.ModelGasSink;
import com.minestellar.core.render.tile.TileEntityRenderGasSink;
import com.minestellar.core.util.MinestellarLog;
import com.minestellar.core.util.MinestellarUtil;

import cpw.mods.fml.common.Optional.Method;
import mekanism.api.gas.Gas;
import mekanism.api.gas.GasStack;
import mekanism.api.gas.GasTank;
import mekanism.api.gas.IGasHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityGasSink extends TileEntity implements IGasHandler{

	private GasTank gasTank;

	private int currentGasAmount;

	public TileEntityGasSink(int meta){
		gasTank = new GasTank(1500);
		this.blockMetadata = meta;
	}

	@Override
	public void updateEntity(){
		if(!worldObj.isRemote && gasTank.getGas() != null){
			if(gasTank.getGas().getGas() != null){
				if(gasTank.stored.amount < gasTank.getMaxGas()){
					gasTank.stored.amount -= 1;
					MinestellarLog.info("Ammount: " + gasTank.stored.amount);
				}
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

	// MEKANISM IMPLEMENTATION

	@Method(modid = "Mekanism")
	@Override
	public boolean canDrawGas(ForgeDirection side, Gas type){
		return true;
	}

	@Method(modid = "Mekanism")
	@Override
	public boolean canReceiveGas(ForgeDirection direction, Gas gas) {
		return direction == ForgeDirection.EAST;
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
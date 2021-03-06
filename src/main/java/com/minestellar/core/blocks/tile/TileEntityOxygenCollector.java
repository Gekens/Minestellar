/**
 * Copyright (c) 22/Feb/2015 Davide Cossu & Matthew Albrecht.
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

import com.minestellar.core.particles.EntitySparkleFX;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.Optional.Method;
import mekanism.api.gas.Gas;
import mekanism.api.gas.GasStack;
import mekanism.api.gas.GasTank;
import mekanism.api.gas.IGasHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

@Optional.Interface(iface = "mekanism.api.gas.IGasHandler", modid = "Mekanism")
public class TileEntityOxygenCollector extends TileEntity implements IGasHandler {
	private GasTank gasTank;
	private int currentGasAmount;

    public TileEntityOxygenCollector() {
		gasTank = new GasTank(15000);
		gasTank.setGas(new GasStack(new Gas("oxygen"), 0));
	}

	@Override
	public void updateEntity() {
        ArrayList<Block> blocks = getNatureBlocks(5);
        ArrayList<Vec3> coords = getNatureBlocksCoords(5);

		if (!worldObj.isRemote && gasTank.getGas() != null) {
			if (gasTank.getGas().getGas() != null) {
				if (gasTank.stored.amount < gasTank.getMaxGas()) {

                    if(Math.random() <= 0.20){ // Yay for cool particles!
                        for(Vec3 coord : coords){
                            Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySparkleFX(worldObj, coord.xCoord+0.5F, coord.yCoord+0.5F, coord.zCoord+0.5F, 2).setArrivalCoords(xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F).setColor(0.0F, 1.0F, 0.0F));
                        }
                    }

                    for(Block block : blocks){
                        if(block == Blocks.leaves || block == Blocks.leaves2){
                            gasTank.stored.amount += 5; // Augments the stored gas amount based on the nature of the nature blocks
                        }else if(block == Blocks.cactus){
                            gasTank.stored.amount += 1; // Augments the stored gas amount based on the nature of the nature blocks
                        }else if(block == Blocks.grass){
                            gasTank.stored.amount += 2; // Augments the stored gas amount based on the nature of the nature blocks
                        }else if(block == Blocks.log || block == Blocks.log2){
                            gasTank.stored.amount += 1; // Augments the stored gas amount based on the nature of the nature blocks
                        }
                    }
				}
			}
		}

		if (!worldObj.isRemote) {
			int newGasAmount = gasTank.getStored();

			if (newGasAmount != this.currentGasAmount) {
				markDirty();
				this.currentGasAmount = newGasAmount;
			}
		}

	}

	/**
	 * Gets a list of the nature blocks in the around area
	 * 
	 * @param maxDistanceAway The max radius
	 * @return The list of blocks in the area
	 */
	private ArrayList<Block> getNatureBlocks(int maxDistanceAway) {
		int xMov = 0 - maxDistanceAway;
		int yMov = maxDistanceAway;
		int zMov = 0 - maxDistanceAway;
		ArrayList<Block> list = new ArrayList<Block>();

		while (true) {
			final Block currentBlock = worldObj.getBlock(this.xCoord + xMov, this.yCoord + yMov, this.zCoord + zMov);

			if (currentBlock == Blocks.leaves || currentBlock == Blocks.leaves2 || currentBlock == Blocks.grass || currentBlock == Blocks.cactus || currentBlock == Blocks.log || currentBlock == Blocks.log2) {
				list.add(currentBlock);
			}

			if (zMov == maxDistanceAway && xMov == maxDistanceAway && yMov == -maxDistanceAway) {
				return list;
			}

			if (zMov == maxDistanceAway && xMov == maxDistanceAway) {
				yMov--;
				xMov = 0 - maxDistanceAway;
				zMov = 0 - maxDistanceAway;
				continue;
			}

			if (xMov == maxDistanceAway) {
				zMov++;
				xMov = 0 - maxDistanceAway;
				continue;
			}

			xMov++;
		}
	}

	/**
	 * Gets the coordinates of the nature blocks
	 */
	private ArrayList<Vec3> getNatureBlocksCoords(int maxDistanceAway) {
		int xMov = 0 - maxDistanceAway;
		int yMov = maxDistanceAway;
		int zMov = 0 - maxDistanceAway;
		ArrayList<Vec3> list = new ArrayList<Vec3>();

		while (true) {
			final Block currentBlock = worldObj.getBlock(this.xCoord + xMov, this.yCoord + yMov, this.zCoord + zMov);

			if (currentBlock == Blocks.leaves || currentBlock == Blocks.leaves2 || currentBlock == Blocks.grass || currentBlock == Blocks.cactus || currentBlock == Blocks.log || currentBlock == Blocks.log2) {
				list.add(Vec3.createVectorHelper(this.xCoord + xMov, this.yCoord + yMov, this.zCoord + zMov));
			}

			if (zMov == maxDistanceAway && xMov == maxDistanceAway && yMov == -maxDistanceAway) {
				return list;
			}

			if (zMov == maxDistanceAway && xMov == maxDistanceAway) {
				yMov--;
				xMov = 0 - maxDistanceAway;
				zMov = 0 - maxDistanceAway;
				continue;
			}

			if (xMov == maxDistanceAway) {
				zMov++;
				xMov = 0 - maxDistanceAway;
				continue;
			}

			xMov++;
		}
	}

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        gasTank.read(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        gasTank.write(nbt);
    }

	/**
	 * MEKANISM IMPLEMENTATION
	 */
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
		return side == ForgeDirection.EAST;
	}
}

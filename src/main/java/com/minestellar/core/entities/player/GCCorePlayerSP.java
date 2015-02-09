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

package com.minestellar.core.entities.player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Session;
import net.minecraft.world.World;

import com.minestellar.moon.blocks.MoonBlocks;
import com.minestellar.moon.world.WorldProviderMoon;

public class GCCorePlayerSP extends EntityPlayerSP {
	public GCCorePlayerSP(Minecraft par1Minecraft, World par2World, Session par3Session, int par4NetClientHandler) {
		super(par1Minecraft, par2World, par3Session, par4NetClientHandler);
	}

	private int lastStep;

	// Client-only methods
	@Override
	public void onUpdate() {
		super.onUpdate();
		this.updateStep();
	}
	
	private void updateStep() {
		if (this.worldObj != null && this.worldObj.provider instanceof WorldProviderMoon && !this.isAirBorne && this.ridingEntity == null) {
			if (this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ)) == MoonBlocks.moonBasicBlocks) {
				if (this.worldObj.getBlockMetadata(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ)) == 5) {
					int meta = -1;

					final int i = 1 + MathHelper.floor_double(this.rotationYaw * 8.0F / 360.0F + 0.5D) & 7;

					switch (this.lastStep) {
					case 1:
						switch (i) {
						case 0:
							meta = 2;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 1:
							meta = 4;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 2:
							meta = 2;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 3:
							meta = 2;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 4:
							meta = 2;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 5:
							meta = 2;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 6:
							meta = 2;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 7:
							meta = 2;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						}

						this.lastStep = 2;
						break;

					case 2:
						switch (i) {
						case 0:
							meta = 1;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 1:
							meta = 1;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 2:
							meta = 4;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 3:
							meta = 4;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 4:
							meta = 1;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 5:
							meta = 3;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 6:
							meta = 2;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 7:
							meta = 4;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						}

						this.lastStep = 1;
						this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
						break;

					default:
						this.lastStep = 1;
						break;
					}
				}
			}
		}
	}
}

/**
 * Copyright (c) 05/gen/2015 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.moon.entity;

import com.minestellar.moon.util.ConfigManagerMoon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityFootstep extends Entity{

	public final EntityPlayer thePlayer;
	public int fadeOutTimer = 40;
	public float scale = 1F;

	public EntityFootstep(EntityPlayer entityPlayer) {
		super(entityPlayer.getEntityWorld());
		this.thePlayer = entityPlayer;
		this.lastTickPosX = this.prevPosX = this.posX = entityPlayer.posX;
		this.lastTickPosY = this.prevPosY = this.posY = entityPlayer.posY;
		this.lastTickPosZ = this.prevPosZ = this.posZ = entityPlayer.posZ;
		this.ignoreFrustumCheck = true;
	}

	@Override
	public void onUpdate() {
		//If player moves away, being fading out
		if (this.getDistanceToEntity(this.thePlayer) > 1.4 || this.fadeOutTimer < 40) {
			this.fadeOutTimer--;
		}
		else if (this.scale < 1.5F) {
			this.scale += 0.001F;
		}
		
		if (this.fadeOutTimer < 0) {
			this.setDead();
		}
	}

	@Override
	public boolean shouldRenderInPass(int pass) {
		return pass == 1;
	}

	@Override
	public void setDead() {
		super.setDead();
	}

	@Override
	protected void entityInit() {}

	@Override
	protected void readEntityFromNBT(NBTTagCompound var1) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound var1) {}

}
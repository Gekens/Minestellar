/**
 * Copyright (c) 30/apr/2015 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.core.particles;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_GREATER;
import static org.lwjgl.opengl.GL11.glAlphaFunc;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDepthMask;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class EntityLightningFX extends EntityFX {
	private int seconds;
	private float[] xCoords, yCoords, zCoords;
	private float endX, endY, endZ;

	/**
	 * @param sections The number of different sections. The higher it is, the more lines there will be.
	 */

	public EntityLightningFX(World world, double x, double y, double z, int sections, int seconds) {
		super(world, x, y, z);
		this.seconds = seconds;
		xCoords = new float[sections];
		yCoords = new float[sections];
		zCoords = new float[sections];

		for (int i = 0; i < sections; i++) {
			xCoords[i] = (float) (rand.nextFloat() + x);
			yCoords[i] = rand.nextFloat();
			zCoords[i] = (float) (rand.nextFloat() + z);
		}

		noClip = true;
	}

	@Override
	public void renderParticle(Tessellator tessellator, float partialTicks, float par3, float par4, float par5, float par6, float par7) {
		glDisable(GL11.GL_TEXTURE_2D);
		glDisable(GL11.GL_LIGHTING);
		glEnable(GL11.GL_BLEND);
		glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		glDepthMask(false);
		glAlphaFunc(GL_GREATER, 0.003921569F);
		glColor4f(particleRed, particleGreen, particleBlue, 0.8F);

		tessellator.startDrawing(3);
		float x = (float) (prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
		float y = (float) (prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
		float z = (float) (prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);
		
		{
			for (int i = 0; i < xCoords.length && i < yCoords.length && i < zCoords.length; i++) {
				tessellator.addVertex(xCoords[i] + x, yCoords[i] + y, zCoords[i] + z);
			}
		}
		
		tessellator.draw();
		
		glEnable(GL11.GL_TEXTURE_2D);
		glEnable(GL11.GL_LIGHTING);
		glDisable(GL_BLEND);
		glDepthMask(true);
		glAlphaFunc(GL_GREATER, 0.1F);
	}

	@Override
	public void moveEntity(double motionX, double motionY, double motionZ) {
		super.moveEntity(motionX, motionY, motionZ);
	}

	@Override
	public void onUpdate() {
		setMaxAge(seconds * 20);
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge) {
			this.setDead();
		}

		this.motionX = calculateVelocityX(seconds * 20);
		this.motionY = calculateVelocityY(seconds * 20);
		this.motionZ = calculateVelocityZ(seconds * 20);

		this.moveEntity(this.motionX, this.motionY, this.motionZ);
	}

	/**
	 * Sets the coordinates of the point at which the particle should arrive.
	 */
	public EntityLightningFX setArrivalCoords(float x, float y, float z) {
		this.endX = x;
		this.endY = y;
		this.endZ = z;
		
		return this;
	}

	/**
	 * Calculates the <code>x</code> component of the velocity needed to get to the end position.
	 * 
	 * @param time Time in in-game ticks.
	 */
	public float calculateVelocityX(int time) {
		return (float) ((this.endX - this.posX) / (time));
	}

	/**
	 * Calculates the <code>y</code> component of the velocity needed to get to the end position.
	 * 
	 * @param time Time in in-game ticks.
	 */
	public float calculateVelocityY(int time) {
		float angle = (float) (Math.atan((this.endY - this.posY) / (this.endX - this.posX)));
		
		return (float) (calculateVelocityX(time) * Math.tan(angle));
	}

	/**
	 * Calculates the <code>z</code> component of the velocity needed to get to the end position.
	 * 
	 * @param time Time in in-game ticks.
	 */
	public float calculateVelocityZ(int time) {
		return (float) ((this.endZ - this.posZ) / (time));
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	public int getFXLayer() {
		return 3;
	}

	public EntityLightningFX setMaxAge(int maxAge) {
		particleMaxAge = maxAge;
		
		return this;
	}

	public EntityLightningFX setGravity(float gravity) {
		particleGravity = gravity;
		
		return this;
	}

	public EntityLightningFX setScale(float scale) {
		particleScale = scale;
		
		return this;
	}

	public EntityLightningFX setColor(float r, float g, float b) {
		setRBGColorF(r, g, b);
		
		return this;
	}
}

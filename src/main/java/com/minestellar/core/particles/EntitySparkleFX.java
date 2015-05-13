/**
 * Copyright (c) 29/apr/2015 Davide Cossu & Matthew Albrecht.
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

import com.minestellar.core.MinestellarCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class EntitySparkleFX extends EntityFX {
	private static final ResourceLocation texture = new ResourceLocation(MinestellarCore.TEXTURE_PREFIX + "textures/fx/sparkle.png");
	private int seconds;
	private float endX, endY, endZ;

	public EntitySparkleFX(World world, double x, double y, double z, int seconds) {
		super(world, x, y, z);
		this.seconds = seconds;
		noClip = true;
	}

	@Override
	public void renderParticle(Tessellator tessellator, float partialTicks, float par3, float par4, float par5, float par6, float par7) {
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		glDisable(GL11.GL_LIGHTING);
		glEnable(GL11.GL_BLEND);
		glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		glDepthMask(false);
		glAlphaFunc(GL_GREATER, 0.003921569F);
		
		{
			tessellator.startDrawingQuads();
			glColor4f(particleRed, particleGreen, particleBlue, 1);
			double scale = 0.1 * particleScale;
			float x = (float) (prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
			float y = (float) (prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
			float z = (float) (prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);

			{
				tessellator.addVertexWithUV(x - par3 * scale - par6 * scale, y - par4 * scale, z - par5 * scale - par7 * scale, 0, 0);
				tessellator.addVertexWithUV(x - par3 * scale + par6 * scale, y + par4 * scale, z - par5 * scale + par7 * scale, 1, 0);
				tessellator.addVertexWithUV(x + par3 * scale + par6 * scale, y + par4 * scale, z + par5 * scale + par7 * scale, 1, 1);
				tessellator.addVertexWithUV(x + par3 * scale - par6 * scale, y - par4 * scale, z + par5 * scale - par7 * scale, 0, 1);
			}
			
			tessellator.draw();
		}

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
		setMaxAge(seconds * 20 * 2);
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
	public EntitySparkleFX setArrivalCoords(float x, float y, float z) {
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
	public int getFXLayer() {
		return 3;
	}

	public EntitySparkleFX setMaxAge(int maxAge) {
		particleMaxAge = maxAge;
		
		return this;
	}

	public EntitySparkleFX setGravity(float gravity) {
		particleGravity = gravity;
		
		return this;
	}

	public EntitySparkleFX setScale(float scale) {
		particleScale = scale;
		
		return this;
	}

	public EntitySparkleFX setColor(float r, float g, float b) {
		setRBGColorF(r, g, b);
		
		return this;
	}
}

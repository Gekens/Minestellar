/**
 * Copyright (c) 02/05/15 Davide Cossu & Matthew Aplbrecht.
 * <p/>
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 * <p/>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * <p/>
 * You should have received a clone of the GNU General Public License along with
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

import com.minestellar.api.vector.Vector3;

public class EntityLightningBeamFX extends EntityFX {
	// Minecraft.getMinecraft().effectRenderer.addEffect(new EntityLightningBeamFX(worldObj, xCoord, yCoord, zCoord).setColor(1F, 0.2F, 0.7F).setArrivalCoords(new Vector3(xCoord+12, 5, zCoord-1), new Vector3(xCoord-9, 4, zCoord+1), new Vector3(xCoord-15, 10, zCoord+6)));
	private Vector3[] stopCoordinates;

	public EntityLightningBeamFX(World world, double x, double y, double z) {
		super(world, x, y, z);

		setGravity(0);
		setMaxAge(1);

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

		tessellator.setBrightness(0);
		tessellator.startDrawing(3);

		float x = (float) (prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
		float y = (float) (prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
		float z = (float) (prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);

		{
			tessellator.addVertex(x, y, z);

			for (Vector3 stopCoordinate : stopCoordinates) {
				tessellator.addVertex(stopCoordinate.x + x, stopCoordinate.y + y, stopCoordinate.z + z);
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
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge) {
			this.setDead();
		}
	}

	/**
	 * Sets the coordinates of the point at which the particle should arrive. <b>NOTE: for the {@code y} axis, don't use {@code xCoord +- y}, use just
	 * {@code y}</b>
	 */
	public EntityLightningBeamFX setArrivalCoords(Vector3... stopCoordinates) {
		this.stopCoordinates = stopCoordinates;
		
		return this;
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

	public EntityLightningBeamFX setMaxAge(int maxAge) {
		particleMaxAge = maxAge;
		
		return this;
	}

	public EntityLightningBeamFX setGravity(float gravity) {
		particleGravity = gravity;
		
		return this;
	}

	public EntityLightningBeamFX setScale(float scale) {
		particleScale = scale;
		
		return this;
	}

	public EntityLightningBeamFX setColor(float r, float g, float b) {
		setRBGColorF(r, g, b);
		
		return this;
	}
}

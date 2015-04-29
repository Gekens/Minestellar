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

import static org.lwjgl.opengl.GL11.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.minestellar.core.MinestellarCore;

public class EntitySparkleFX extends EntityFX{

	private static final ResourceLocation texture = new ResourceLocation(MinestellarCore.TEXTURE_PREFIX + "textures/fx/sparkle.png");

	//TODO: Calculate the needed velocity between 2 or more given points
	
	public EntitySparkleFX(World world, double x, double y, double z){
		super(world, x, y, z);
		noClip = true;
		setMaxAge(100);
		setGravity(-0.2F);
	}

	@Override
	public void renderParticle(Tessellator tessellator, float partialTicks, float par3, float par4, float par5, float par6, float par7){
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);

		glDepthMask(false);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glAlphaFunc(GL_GREATER, 0.003921569F);
		{
			tessellator.startDrawingQuads();

			tessellator.setBrightness(getBrightnessForRender(partialTicks));
			tessellator.setColorRGBA_F(particleRed, particleGreen, particleBlue, 1);
			double scale = 0.1*particleScale;
			float x = (float)(prevPosX+(posX-prevPosX)*partialTicks-interpPosX);
			float y = (float)(prevPosY+(posY-prevPosY)*partialTicks-interpPosY);
			float z = (float)(prevPosZ+(posZ-prevPosZ)*partialTicks-interpPosZ);
			{
				tessellator.addVertexWithUV(x-par3*scale-par6*scale, y-par4*scale, z-par5*scale-par7*scale, 0, 0);
				tessellator.addVertexWithUV(x-par3*scale+par6*scale, y+par4*scale, z-par5*scale+par7*scale, 1, 0);
				tessellator.addVertexWithUV(x+par3*scale+par6*scale, y+par4*scale, z+par5*scale+par7*scale, 1, 1);
				tessellator.addVertexWithUV(x+par3*scale-par6*scale, y-par4*scale, z+par5*scale-par7*scale, 0, 1);
			}
			tessellator.draw();

		}
		glDisable(GL_BLEND);
		glDepthMask(true);
		glAlphaFunc(GL_GREATER, 0.1F);
	}

	@Override
	public void moveEntity(double p_70091_1_, double p_70091_3_, double p_70091_5_) {
		super.moveEntity(p_70091_1_, p_70091_3_, p_70091_5_);
	}
	
	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if(this.particleAge++ >= this.particleMaxAge){
			this.setDead();
		}

		this.motionY -= 0.04D * (double)this.particleGravity;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9800000190734863D;
		this.motionY *= 0.9800000190734863D;
		this.motionZ *= 0.9800000190734863D;

		if(this.onGround){
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}
	}

	@Override
	public int getFXLayer(){
		return 3;
	}

	public EntitySparkleFX setMaxAge(int maxAge){
		particleMaxAge = maxAge;
		return this;
	}

	public EntitySparkleFX setGravity(float gravity){
		particleGravity = gravity;
		return this;
	}

	public EntitySparkleFX setScale(float scale){
		particleScale = scale;
		return this;
	}
	
	public EntitySparkleFX setColor(float r, float g, float b){
		setRBGColorF(r, g, b);
		return this;
	}

}
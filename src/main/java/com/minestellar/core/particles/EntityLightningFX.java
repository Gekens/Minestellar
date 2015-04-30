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

import static org.lwjgl.opengl.GL11.*;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.minestellar.core.util.MinestellarLog;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class EntityLightningFX extends EntityFX{
	
	private int sections;
	private float[] xCoords, yCoords, zCoords;
	
	//TODO: Calculate the needed velocity between 2 or more given points
	
	public EntityLightningFX(World world, double x, double y, double z, int sections){
		super(world, x, y, z);
		
		this.sections = sections;
		
		xCoords = new float[sections];
		yCoords = new float[sections];
		zCoords = new float[sections];
		
		for(int i = 0; i < sections; i++){
			xCoords[i] = (float) (rand.nextFloat()+x);
			yCoords[i] = (float) (rand.nextFloat());
			zCoords[i] = (float) (rand.nextFloat()+z);
		}
		
		setGravity(-20F);
		setMaxAge(100);
		noClip = true;
	}

	@Override
	public void renderParticle(Tessellator tessellator, float partialTicks, float par3, float par4, float par5, float par6, float par7){

		GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        glDepthMask(false);
		glAlphaFunc(GL_GREATER, 0.003921569F);
		
		//tessellator.setColorRGBA_F(particleRed, particleGreen, particleBlue, 1);
		GL11.glColor4f(particleRed, particleGreen, particleBlue, 1);
		
		tessellator.startDrawing(3);
		float x = (float)(prevPosX+(posX-prevPosX)*partialTicks-interpPosX);
		float y = (float)(prevPosY+(posY-prevPosY)*partialTicks-interpPosY);
		float z = (float)(prevPosZ+(posZ-prevPosZ)*partialTicks-interpPosZ);
		{
			for(int i = 0; i < xCoords.length && i < yCoords.length && i < zCoords.length; i++){
				//MinestellarLog.info("xCoords[i] = " + xCoords[i] + " yCoords[i] = " + yCoords[i] + " zCoords[i] = " + zCoords[i]);
				tessellator.addVertex(xCoords[i]+x, yCoords[i]+y, zCoords[i]+z);
			}
		}
		tessellator.draw();
		
        glEnable(GL11.GL_TEXTURE_2D);
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
	public boolean canBeCollidedWith() {
		return false;
	}
	
	@Override
	public boolean canBePushed() {
		return false;
	}
	
	@Override
	public int getFXLayer(){
		return 3;
	}

	public EntityLightningFX setMaxAge(int maxAge){
		particleMaxAge = maxAge;
		return this;
	}

	public EntityLightningFX setGravity(float gravity){
		particleGravity = gravity;
		return this;
	}

	public EntityLightningFX setScale(float scale){
		particleScale = scale;
		return this;
	}

	public EntityLightningFX setColor(float r, float g, float b){
		setRBGColorF(r, g, b);
		return this;
	}

}
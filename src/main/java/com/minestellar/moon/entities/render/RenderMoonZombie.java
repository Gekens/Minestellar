/**
 * Copyright (c) 31/dic/2014 Davide Cossu & Mattew Albrecht.
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

package com.minestellar.moon.entities.render;

import com.minestellar.moon.MoonCore;
import com.minestellar.moon.entities.EntityMoonZombie;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMoonZombie extends RenderLiving{

	private static final ResourceLocation moonZombie = new ResourceLocation(MoonCore.TEXTURE_PREFIX + "textures/model/moonZombie.png");

	public RenderMoonZombie(){
		super(new ModelZombie(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return moonZombie;
	}

	public void renderMoonZombie(EntityMoonZombie par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.mainModel = new ModelZombie();

		super.doRender(par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderMoonZombie((EntityMoonZombie)par1Entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderMoonZombie((EntityMoonZombie)par1EntityLiving, par2, par4, par6, par8, par9);
	}

	public void renderPlayer(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderMoonZombie((EntityMoonZombie)par1EntityLivingBase, par2, par4, par6, par8, par9);
	}
	
}
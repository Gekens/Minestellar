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

package com.minestellar.moon.render.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.minestellar.moon.MinestellarMoon;
import com.minestellar.moon.entity.EntityFootstep;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityFootstepRender extends Render{

	private final ResourceLocation footstepTexture = new ResourceLocation(MinestellarMoon.TEXTURE_PREFIX + "textures/model/entity/footstep.png");

	public EntityFootstepRender() {
		this.shadowSize = 0F;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float var8, float partialTickTime) {
		EntityFootstep footstep = (EntityFootstep) entity;
		this.bindTexture(this.footstepTexture);
		//GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		//GL11.glScalef(footstep.scale, 1F, footstep.scale);
		GL11.glTranslated(x, y - (footstep.thePlayer == Minecraft.getMinecraft().thePlayer ? footstep.thePlayer.height: 0.19) + 0.2, z - 0.5F);
		GL11.glRotatef(90F, 1F, 0F, 0F);
		Tessellator tessellator = Tessellator.instance;
		if (footstep.ticksExisted < 5) GL11.glColor4f(0F, 0F, 0F, footstep.ticksExisted / 5F);
		if (footstep.fadeOutTimer != 40) GL11.glColor4f(0F, 0F, 0F, footstep.fadeOutTimer / 40F);

		//if(footstep.fadeOutTimer != 0) GL11.glColor4f(0F, 0F, 0F, 1/footstep.fadeOutTimer);

		tessellator.startDrawingQuads();

		tessellator.addVertexWithUV(0.5D, 0D, 0D, 1, 1);
		tessellator.addVertexWithUV(-0.5D, 0D, 0D, 0, 1);
		tessellator.addVertexWithUV(-0.5D, 1, 0D, 0, 0);
		tessellator.addVertexWithUV(0.5D, 1, 0D, 1, 0);

		tessellator.draw();
		
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glPopMatrix();
		//GL11.glEnable(GL11.GL_LIGHTING);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return this.footstepTexture;
	}

}

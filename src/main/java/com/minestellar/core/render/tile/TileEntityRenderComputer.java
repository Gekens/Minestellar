/**
 * Copyright (c) 06/07/15 Davide Cossu & Matthew Albrecht.
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
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, see <http://www.gnu.org/licenses>.
 */

package com.minestellar.core.render.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.model.ModelComputer;

public class TileEntityRenderComputer extends TileEntitySpecialRenderer{

    public static ModelComputer model;

    public TileEntityRenderComputer(){
        model = new ModelComputer();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double translationX, double translationY, double translationZ, float f){
        this.bindTexture(new ResourceLocation(MinestellarCore.TEXTURE_PREFIX + "textures/model/tile/computer.png"));
        GL11.glPushMatrix();
        GL11.glTranslatef((float) translationX + 0.5F, (float) translationY + 1.5F, (float) translationZ + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
        model.renderModel(0.0625F);
        GL11.glPopMatrix();
    }
}
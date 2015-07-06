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

package com.minestellar.core.render.item;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import com.minestellar.core.blocks.tile.TileEntityComputer;

public class ComputerItemRender implements IItemRenderer{
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data){
        switch(type){
            case ENTITY:
                GL11.glPushMatrix();
                GL11.glScaled(2, 2, 0);
                GL11.glTranslated(-0.5, 0, -0.5);
                TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityComputer(), 0.0D, 0.0D, 0.0D, 0.0F);
                GL11.glTranslated(0.5, 0, 0.5);
                GL11.glPopMatrix();
                break;
            case EQUIPPED_FIRST_PERSON:
                GL11.glPushMatrix();
                GL11.glTranslated(0, 0.5, 0);
                TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityComputer(), 0.0D, 0.0D, 0.0D, 0.0F);
                GL11.glPopMatrix();
                break;
            case INVENTORY:
                GL11.glPushMatrix();
                TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityComputer(), 0.0D, 0.0D, 0.0D, 0.0F);
                GL11.glPopMatrix();
                break;
        }
    }
}
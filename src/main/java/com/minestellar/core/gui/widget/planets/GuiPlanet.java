/**
 * Copyright (c) 26/feb/2015 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.core.gui.widget.planets;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.gui.widget.GuiDraw;
import com.minestellar.core.gui.widget.GuiWidget;

public class GuiPlanet extends GuiWidget{

	private String name;
	private boolean isEnabled = true;
	private boolean isSelected = false;

	private ResourceLocation texture;

	public GuiPlanet(int x, int y, String name){
		super(x, y, 8, 8);
		this.name = name;
		this.texture = new ResourceLocation(MinestellarCore.TEXTURE_PREFIX + "textures/gui/planets/" + name + ".png");
	}

	@Override
	public void mouseClicked(int x, int y, int button){
		if(isEnabled && pointInside(x, y)){
			setSelected(!isSelected());
			System.out.println(isSelected());
		}
	}

	@Override
	public void draw(int mousex, int mousey, float frame){
		super.draw(mousex, mousey, frame);
		renderEngine.bindTexture(texture);
		GL11.glColor4f(1, 1, 1, 1);
		if(isSelected()){
			drawSelectedBox();
		}

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		{
			tessellator.addVertexWithUV(x, y, zLevel, 0, 0);
			tessellator.addVertexWithUV(x+8, y, zLevel, 8, 0);
			tessellator.addVertexWithUV(x, y+8, zLevel, 8, 8);
			tessellator.addVertexWithUV(x+8, y+8, zLevel, 0, 8);

			tessellator.addVertexWithUV(x+8, y+8, zLevel, 0, 8);
			tessellator.addVertexWithUV(x, y+8, zLevel, 8, 8);
			tessellator.addVertexWithUV(x+8, y, zLevel, 8, 0);
			tessellator.addVertexWithUV(x, y, zLevel, 0, 0);
		}
		tessellator.draw();
	}

	/**
	 * Draws the <i>selected box</i>
	 */
	
	public void drawSelectedBox(){
		int away = 2;
		GuiDraw.drawRect(x-away, y-away, this.width+away*2, this.height+away*2, 0xDD006666);
	}
	
	public boolean isEnabled(){
		return isEnabled;
	}

	public void setEnabled(boolean b){
		isEnabled = b;
	}	

	public boolean isSelected(){
		return isSelected;
	}

	public void setSelected(boolean b){
		isSelected = b;
	}	
	
	public void setTexture(ResourceLocation texture){
		this.texture = texture;
	}

	public ResourceLocation getTexture(){
		return this.texture;
	}

}
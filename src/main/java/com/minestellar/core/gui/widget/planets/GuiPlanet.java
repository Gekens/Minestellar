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

import java.util.Iterator;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.gui.ComputerGui;
import com.minestellar.core.gui.widget.GuiDraw;
import com.minestellar.core.gui.widget.GuiScreenWidget;
import com.minestellar.core.gui.widget.GuiSideBarWidget;
import com.minestellar.core.gui.widget.GuiWidget;

/**
 * The <i>planet</i> element for the {@link ComputerGui}
 */

public class GuiPlanet extends GuiWidget{

	private String name;
	private boolean isEnabled = true;
	private boolean isSelected = false;
	private boolean isSelectable = true;

	private ResourceLocation texture;

	private GuiScreenWidget parent;

	public GuiPlanet(int x, int y, String name){
		super(x, y, 8, 8);
		this.name = name;
		this.texture = new ResourceLocation(MinestellarCore.TEXTURE_PREFIX + "textures/gui/planets/" + name + ".png");
	}

	@Override
	public void mouseClicked(int x, int y, int button){
		if(isEnabled() && isSelectable() && pointInside(x, y)){
			if(parentScreen instanceof GuiScreenWidget){
				parent = (GuiScreenWidget)parentScreen;
				if(parent instanceof ComputerGui){
					parent = (ComputerGui)parent;
					for(Iterator iterator = parent.widgets.iterator(); iterator.hasNext();){
						GuiWidget widget = (GuiWidget)iterator.next();
						if(widget instanceof GuiPlanet){
							if(widget != this){
								((GuiPlanet) widget).setSelected(false);
								((ComputerGui) parent).setSelectedPlanet(null);
								((ComputerGui) parent).setDraw(false);
							}
						}else{
							continue;
						}
					}
					setSelected(!isSelected());
					System.out.println(name);
					if(isSelected()){
						((ComputerGui) parent).setSelectedPlanet(this);
						((ComputerGui) parent).setDraw(true);
					}else{
						((ComputerGui) parent).setSelectedPlanet(null);
						((ComputerGui) parent).setDraw(false);
					}
				}
			}
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
		
		drawTexturedModalRect(x, y, 0, 0, 8, 8);
	}

	/**
	 * Draws the <i>selected box</i>
	 */

	public void drawSelectedBox(){
		int away = 2;
		GuiDraw.drawRect(x-away, y-away, this.width+away*2, this.height+away*2, 0xDD006666);
	}

	/**
	 * To make sure this is <i>visible</i>
	 */

	public boolean isEnabled(){
		return isEnabled;
	}

	public void setEnabled(boolean b){
		isEnabled = b;
	}	

	/**
	 * To draw the background and the informations
	 * @see GuiSideBarWidget
	 */

	public boolean isSelected(){
		return isSelected;
	}

	public void setSelected(boolean b){
		isSelected = b;
	}	

	/**
	 * Used to make sure that only one planet is selected at time, no more than one
	 */

	public boolean isSelectable(){
		return isSelectable;
	}

	public void setSelectable(boolean b){
		isSelectable = b;
	}
	
	public String getName(){
		return name;
	}

	public void setTexture(ResourceLocation texture){
		this.texture = texture;
	}

	public ResourceLocation getTexture(){
		return this.texture;
	}

}
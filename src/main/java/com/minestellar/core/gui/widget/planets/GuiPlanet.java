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

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.gui.ComputerGui;
import com.minestellar.core.gui.widget.GuiDraw;
import com.minestellar.core.gui.widget.GuiScreenWidget;
import com.minestellar.core.gui.widget.GuiSideBarWidget;
import com.minestellar.core.gui.widget.GuiWidget;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

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
		if(isEnabled() && pointInside(x, y)){
			if(parentScreen instanceof GuiScreenWidget){
				parent = (GuiScreenWidget)parentScreen;
				if(parent instanceof ComputerGui){
					ComputerGui gui = (ComputerGui)parent;
					for(GuiPlanet planet1 : gui.planets){
						try{
							if(planet1 != this){
								planet1.setSelected(false);
								gui.setSelectedPlanet(null);
								gui.setDraw(false);
							}
						}catch(Exception ignored){
						}
					}
					setSelected(!isSelected());
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

		drawTexturedModalRect(x, y, 0, 0, width/2, height/2);
		drawTexturedModalRect(x+width/2, y, 4, 0, width/2, height/2);
		drawTexturedModalRect(x, y+height/2, 0, 4, width/2, height/2);
		drawTexturedModalRect(x+width/2, y+height/2, 4, 4, width/2, height/2);
	}

	public void setCoords(int x, int y){
		this.x = x; this.y = y;
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

	public String getName(){
		return name;
	}

	public void setTexture(ResourceLocation texture){
		this.texture = texture;
	}

	public ResourceLocation getTexture(){
		return this.texture;
	}
	
	@Override
	public String toString() {
		return this.name + " " + this.x + " " + this.y;
	}

}
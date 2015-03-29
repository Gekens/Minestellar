/**
 * Copyright (c) 25/feb/2015 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.core.gui;

import java.util.ArrayList;

import net.minecraft.client.resources.I18n;

import org.lwjgl.opengl.GL11;

import com.minestellar.core.blocks.machines.Computer;
import com.minestellar.core.blocks.tile.TileEntityComputer;
import com.minestellar.core.gui.widget.GuiDraw;
import com.minestellar.core.gui.widget.GuiMSButton;
import com.minestellar.core.gui.widget.GuiScreenWidget;
import com.minestellar.core.gui.widget.GuiSideBarWidget;
import com.minestellar.core.gui.widget.planets.GuiPlanet;

/**
 * GuiScreen for the {@link Computer} and {@link TileEntityComputer}
 */

public class ComputerGui extends GuiScreenWidget{

	public int screenWidth, screenHeight;
	public ArrayList<GuiPlanet> planets = new ArrayList<GuiPlanet>();
	private boolean doesDraw = false;

	
	private GuiMSButton testButton;
	private GuiPlanet selectedPlanet, sun, earth, moon;
	public GuiSideBarWidget planetInfoTop, planetInfoLeft, planetInfoBottom, planetInfoRight;

	public ComputerGui() {
		super(GuiDraw.displaySize().width, GuiDraw.displaySize().height); // 0,0 is in the top left corner
		this.screenWidth = GuiDraw.displaySize().width;
		this.screenHeight = GuiDraw.displaySize().height;
	}

	@Override
	public void updateScreen(){
		super.updateScreen();
		if(this.selectedPlanet != null){
			if(doesDraw){
				setDraw(false);
				add(planetInfoLeft = new GuiSideBarWidget(screenWidth, screenHeight, 200, screenHeight, 1).setColors(0xAA555555, 0xAA000000).setTitle(selectedPlanet.getName())
						.setContent("dimension", I18n.format("data." + selectedPlanet.getName() + ".dimension")).setContent("gravity", I18n.format("data." + selectedPlanet.getName() + ".gravity")));
			}
		}
		if(this.selectedPlanet == null && !this.doesDraw){
			removeSidebars();
		}
	}

	@Override
	public void addWidgets(){
		add(sun = new GuiPlanet(100, 100, "sun"));
		add(earth = new GuiPlanet(200, 100, "earth"));
		add(moon = new GuiPlanet(300, 100, "moon"));
		planets.add(earth);
		planets.add(moon);
		planets.add(sun);
	}

	@Override
	public void drawBackground(){
		drawDefaultBackground();
	}

	@Override
	public void drawForeground(){
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);

		//Stuff

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

	@Override
	public void actionPerformed(String ident, Object... params){
		switch(ident){
		case "test":
			System.out.println("test");
			break;
		}
	}

	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}

	/**
	 * Sets the <code>doesDraw</code> variable to the parameter 
	 * <p><code>doesDraw</code> is used to draw only one time the {@link GuiSideBarWidget}</p>
	 * 
	 * @param b The boolean
	 * @see GuiSideBarWidget
	 */
	
	public void setDraw(boolean b){
		this.doesDraw = b;
	}

	/**
	 * Sets the selected planet to draw the {@link GuiSideBarWidget}
	 * 
	 * @see GuiSideBarWidget
	 */
	
	public void setSelectedPlanet(GuiPlanet planet){
		this.selectedPlanet = planet;
	}
	
	/**
	 * Returns the current selected planet
	 * 
	 * @return The current selected planet
	 */
	
	public GuiPlanet getSelectedPlanet() {
		return selectedPlanet;
	}
	
	public void removeSidebars(){
		widgets.remove(planetInfoBottom);
		widgets.remove(planetInfoLeft);
		widgets.remove(planetInfoRight);
		widgets.remove(planetInfoTop);
		planetInfoBottom = null;
		planetInfoLeft = null;
		planetInfoRight = null;
		planetInfoTop = null;
	}

}
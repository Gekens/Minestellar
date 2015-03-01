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

import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.gui.widget.GuiDraw;
import com.minestellar.core.gui.widget.GuiMSButton;
import com.minestellar.core.gui.widget.GuiScreenWidget;
import com.minestellar.core.gui.widget.GuiWidget;
import com.minestellar.core.gui.widget.planets.GuiPlanet;

public class ComputerGui extends GuiScreenWidget{

	private GuiMSButton testButton;

	private GuiPlanet sun, earth, moon;
	
	public ComputerGui() {
		super(GuiDraw.displaySize().width, GuiDraw.displaySize().height); // 0,0 is in the top left corner
	}
	
	@Override
	public void updateScreen(){
		super.updateScreen();
	}
	
	@Override
	public void addWidgets(){
		add(testButton = new GuiMSButton(0, 0, 30, 20, "test").setActionCommand("test"));
		add(sun = new GuiPlanet(100, 100, "sun"));
		add(earth = new GuiPlanet(200, 100, "earth"));
		add(moon = new GuiPlanet(300, 100, "moon"));
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
	
}
/**
 * Copyright (c) 04/mar/2015 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.core.gui.widget;

import java.util.HashMap;
import java.util.Iterator;

import net.minecraft.client.resources.I18n;

/**
 * SideBar element used for planet's information <p>Don't forget to use <i>setColors(int, int)</i></p>
 */

public class GuiSideBarWidget extends GuiWidget{

	private int side, frameTime = 0, screenWidth, screenHeight, color1, color2;
	private String title;
	private HashMap<String, String> map = new HashMap<String, String>();

	/**
	 * SideBar element used for planet's information <p>Use <i>setColors(int, int)</i></p>
	 * 
	 * @param side 0=top 1=left 2=bottom 3=right
	 * @see GuiSideBarWidget#setColors(int, int)
	 */

	public GuiSideBarWidget(int screenWidth, int screenHeight, int width, int height, int side){
		super(0, 0, width, height);
		this.side = side;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}

	@Override
	public void draw(int mousex, int mousey, float frame){
		switch(side){
		case 0:
			if(frameTime <= height){
				frameTime += 1;
			}else{
				drawTitle();
				drawContent();
			}
			GuiDraw.drawGradientRect(0, 0, width, frameTime, color1, color2);
			break;
		case 1:
			if(frameTime <= width){
				frameTime += 1;
			}else{
				drawTitle();
				drawContent();
			}
			GuiDraw.drawGradientRect(0, 0, frameTime, height, color1, color2);
			break;
		case 2:
			if(frameTime <= height){
				frameTime += 1;
			}else{
				drawTitle();
				drawContent();
			}
			GuiDraw.drawGradientRect(0, screenHeight-frameTime, width, height, color1, color2);
			break;
		case 3:
			if(frameTime <= width){
				frameTime += 1;
			}else{
				drawTitle();
				drawContent();
			}
			GuiDraw.drawGradientRect(screenWidth-frameTime, 0, width, height, color1, color2);
			break;
		}
	}

	@Override
	public void update(){
		drawTitle();
		drawContent();
	}
	
	/**
	 * The colors are in this format <code>0xAARRGGBB</code>, A is <code>alpha</code>, R is <code>red</code>, G is <code>green</code> and B is <code>blue</code>
	 */

	public GuiSideBarWidget setColors(int color1, int color2){
		this.color1 = color1;
		this.color2 = color2;
		return this;
	}

	/**
	 * Sets the title of the SideBar widget
	 */

	public GuiSideBarWidget setTitle(String title){
		this.title = title;
		return this;
	}

	/**
	 * Draws the title
	 */

	public GuiSideBarWidget drawTitle(){
		switch(side){
		case 0:
			GuiDraw.drawCentered(this.title, width/2, y+10, 0xFFFFFFFF);
			break;
		case 1:
			GuiDraw.drawCentered(this.title, width/2, y+10, 0xFFFFFFFF);
			break;
		case 2:
			GuiDraw.drawCentered(this.title, width/2, height+height/2, 0xFFFFFFFF);
			break;
		case 3:
			GuiDraw.drawCentered(this.title, screenWidth-width/2, y+10, 0xFFFFFFFF);
			break;
		}
		return this;
	}

	/**
	 * Sets the content of the SideBar
	 */

	public GuiSideBarWidget setContent(String contest, String content){
		map.put(contest, content);
		return this;
	}

	/**
	 * Draws the content
	 */

	public void drawContent(){
		try{
			switch(side){
			case 0:
				GuiDraw.drawString("Dimension: ", 10, y+40, 0xFFFFFFFF);
				GuiDraw.drawString(I18n.format(map.get("dimension")), 15+GuiDraw.getStringWidth("Dimension: "), y+40, 0xFFFFFFFF);
				break;
			case 1:
				GuiDraw.drawString("Dimension: ", 10, y+40, 0xFFFFFFFF);
				GuiDraw.drawCentered(I18n.format(map.get("dimension")), GuiDraw.getStringWidth(I18n.format(map.get("dimension")))+GuiDraw.getStringWidth("Dimension: "), y+40, 0xFFFFFFFF);
				
				GuiDraw.drawString("Gravity: ", 10, y+80, 0xFFFFFFFF);
				GuiDraw.drawCentered(I18n.format(map.get("gravity")), GuiDraw.getStringWidth(I18n.format(map.get("gravity")))+GuiDraw.getStringWidth("Gravity: "), y+80, 0xFFFFFFFF);
				break;
			case 2:
				GuiDraw.drawString("Dimension: ", 10, y+40, 0xFFFFFFFF);
				GuiDraw.drawCentered(I18n.format(map.get("dimension")), 15+GuiDraw.getStringWidth("Dimension: "), height+height/2+40, 0xFFFFFFFF);
				break;
			case 3:
				GuiDraw.drawString("Dimension: ", 10, y+40, 0xFFFFFFFF);
				GuiDraw.drawCentered(I18n.format(map.get("dimension")), screenWidth-GuiDraw.getStringWidth("Dimension: "), y+40, 0xFFFFFFFF);
				break;
			}
		}catch(Exception e){}
	}

}
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

public class GuiSideBarWidget extends GuiWidget{

	private int side, frameTime = 0, screenWidth, screenHeight, color1, color2;

	/**
	 * @param side 0=top 1=left 2=bottom 3=right
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
			}
			GuiDraw.drawGradientRect(0, 0, width, frameTime, color1, color2);
			break;
		case 1:
			if(frameTime <= width){
				frameTime += 1;
			}
			GuiDraw.drawGradientRect(0, 0, frameTime, height, color1, color2);
			break;
		case 2:
			if(frameTime <= height){
				frameTime += 1;
			}
			GuiDraw.drawGradientRect(0, screenHeight-frameTime, width, height, color1, color2);
			break;
		case 3:
			if(frameTime <= width){
				frameTime += 1;
			}
			GuiDraw.drawGradientRect(screenWidth-frameTime, 0, width, height, color1, color2);
			break;
		}
	}
	
	/**
	 * The colors are in this format <code>0xAARRGGBB</code>, A is <code>alpha</code>, R is <code>red</code>, G is <code>green</code> and B is <code>blue</code>
	 */
	
	public GuiSideBarWidget setColors(int color1, int color2){
		this.color1 = color1;
		this.color2 = color2;
		return this;
	}

}
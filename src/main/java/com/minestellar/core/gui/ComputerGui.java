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

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import org.lwjgl.opengl.GL11;

import com.minestellar.core.blocks.machines.Computer;
import com.minestellar.core.blocks.tile.TileEntityComputer;
import com.minestellar.core.gui.widget.GuiDraw;
import com.minestellar.core.gui.widget.GuiScreenWidget;
import com.minestellar.core.gui.widget.GuiSideBarWidget;
import com.minestellar.core.gui.widget.GuiWidget;
import com.minestellar.core.gui.widget.planets.GuiPlanet;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * GuiScreen for the {@link Computer} and {@link TileEntityComputer}
 */

public class ComputerGui extends GuiScreenWidget {

    public int screenWidth, screenHeight, spaceX, spaceY, spaceWidth, spaceHeight, earthA, earthB;
    private boolean doesDraw = false, first = true;

    private static ArrayList<String> knownPlanets = new ArrayList<String>();

    public ArrayList<GuiPlanet> planets = new ArrayList<GuiPlanet>();
    public ArrayList<Point2D.Double> earthCoordsArray = new ArrayList<Point2D.Double>();
    public ArrayList<Point2D.Double> moonCoordsArray = new ArrayList<Point2D.Double>();
    public ArrayList<Point2D.Double> mercuryCoordsArray = new ArrayList<Point2D.Double>();
    public ArrayList<Point2D.Double> venusCoordsArray = new ArrayList<Point2D.Double>();

    private PlanetMover earthMover, venusMover, moonMover;

    private byte temp;

    public static GuiPlanet selectedPlanet, sun, earth, moon, venus;
    public GuiSideBarWidget planetInfoTop, planetInfoLeft, planetInfoBottom, planetInfoRight;

    /**
     * @param knownPlanets The planets known by the player. It's used to display only those
     */

    public ComputerGui(ArrayList<String> knownPlanets) {
        super(GuiDraw.displaySize().width, GuiDraw.displaySize().height); // 0,0 is in the top left corner
        ComputerGui.knownPlanets = knownPlanets;
        this.screenWidth = GuiDraw.displaySize().width;
        this.screenHeight = GuiDraw.displaySize().height;
        this.spaceX = this.spaceY = 10;
        this.spaceWidth = this.screenWidth - this.spaceX * 2;
        this.spaceHeight = this.screenHeight - this.spaceY * 2;
        this.earthA = 152 / 2;
        this.earthB = 147 / 2;
        GuiDraw.fillEllipseCoordsArray(earthA, earthB, earthCoordsArray);
        GuiDraw.fillEllipseCoordsArray(384 / 152, 383 / 147, moonCoordsArray);
        GuiDraw.fillEllipseCoordsArray(108 / 2, 107 / 2, venusCoordsArray);
        GuiDraw.fillEllipseCoordsArray(57 / 2, 56 / 2, mercuryCoordsArray);
        temp = 0;
    }

    @Override
    public void updateScreen() {
        super.updateScreen();

        if(first){
            first = false;
            earthMover.run();
            venusMover.run();
            moonMover.run();
        }

        if(selectedPlanet != null){
            if(doesDraw) {
                setDraw(false);
                add(planetInfoLeft = new GuiSideBarWidget(screenWidth, screenHeight, 200, screenHeight, 1).setColors(0xAA555555, 0xAA000000).setTitle(selectedPlanet.getName()).setContent("dimension", I18n.format("data." + selectedPlanet.getName() + ".dimension")).setContent("gravity", I18n.format("data." + selectedPlanet.getName() + ".gravity")));
            }
        }

        if(selectedPlanet == null && !this.doesDraw){
            setDraw(true);
            removeSidebars();
        }

        if(temp >= 50){
            temp = 0;
            earthMover.run();
            venusMover.run();
            moonMover.run();
        }

        setWorldAndResolution(FMLClientHandler.instance().getClient(), screenWidth, screenHeight);

        temp += 1;
    }

    @Override
    public void addWidgets(){
        add(sun = new GuiPlanet(getMid(screenWidth) - (int) Math.sqrt(earthA ^ 2 - earthB ^ 2), getMid(screenHeight) - 4, "sun"));

        if(knownPlanets.contains("moon")){
            add(moon = new GuiPlanet(0, 0, "moon"));
            moon.setSize(0, 0, 4, 4);
            planets.add(moon);
            moonMover = new PlanetMover(moon, Planet.MOON);
        }
        if(knownPlanets.contains("earth")){
            add(earth = new GuiPlanet(0, 0, "earth"));
            planets.add(earth);
            earthMover = new PlanetMover(earth, Planet.EARTH);
        }
        if(knownPlanets.contains("venus")){
            add(venus = new GuiPlanet(10, 10, "venus"));
            planets.add(venus);
            venusMover = new PlanetMover(venus, Planet.VENUS);
        }

    }

    @Override
    public void drawBackground() {
        drawDefaultBackground();
        GuiDraw.drawRect(spaceX, spaceY, spaceWidth, spaceHeight, 0xFF000000);
    }

    @Override
    public void drawForeground() {
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        {
            GL11.glPushMatrix();
            {
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glDepthMask(false);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glDisable(GL11.GL_TEXTURE_2D);

                // Earth
                GL11.glColor4d(0, 0, 1, 1);
                Tessellator tess = Tessellator.instance;
                tess.startDrawing(GL11.GL_LINES);

                for(Point2D.Double anEarthCoordsArray : earthCoordsArray){
                    tess.addVertex(getMid(screenWidth) + 4 + anEarthCoordsArray.x, getMid(screenHeight) + 4 + anEarthCoordsArray.y, 0.0D);
                }

                tess.draw();

                if(knownPlanets.contains("moon")){
                    GL11.glColor4d(0.89, 0.89, 0.89, 1);
                    tess.startDrawing(GL11.GL_LINES);

                    for(Point2D.Double aMoonCoordsArray : moonCoordsArray){
                        tess.addVertex(earth.x + 4 + aMoonCoordsArray.x, earth.y + 4 + aMoonCoordsArray.y, 0.0D);
                    }

                    tess.draw();
                }

                if(knownPlanets.contains("venus")){
                    GL11.glColor4d(0.84, 0.63, 0.29, 1);
                    tess.startDrawing(GL11.GL_LINES);

                    for(Point2D.Double aVenusCoordsArray : venusCoordsArray){
                        tess.addVertex(getMid(screenWidth) + 4 + aVenusCoordsArray.x, getMid(screenHeight) + 4 + aVenusCoordsArray.y, 0.0D);
                    }

                    tess.draw();
                }

            }

            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDepthMask(true);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
        }
        GL11.glPopMatrix();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    @Override
    public void actionPerformed(String ident, Object... params) {
        if(ident.equals("test")){
            System.out.println(ident);
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    /**
     * Sets the <code>doesDraw</code> variable to the parameter
     *
     * <p><code>doesDraw</code> is used to draw only one time the {@link GuiSideBarWidget}
     *
     * @param b The boolean
     * @see GuiSideBarWidget
     */

    public void setDraw(boolean b) {
        this.doesDraw = b;
    }

    /**
     * Sets the selected planet to draw the {@link GuiSideBarWidget}
     */

    public void setSelectedPlanet(GuiPlanet planet) {
        selectedPlanet = planet;
    }

    /**
     * Returns the current selected planet
     *
     * @return The current selected planet
     */

    public GuiPlanet getSelectedPlanet() {
        return selectedPlanet;
    }

    /**
     * Removes all the sidebars from the screen
     */

    public void removeSidebars() {
        Iterator<GuiWidget> iterator = widgets.listIterator();
        while(iterator.hasNext()){
            GuiWidget next = iterator.next();
            if(next == planetInfoBottom || next == planetInfoLeft || next == planetInfoRight || next == planetInfoTop){
                iterator.remove();
            }
        }
        planetInfoBottom = null;
        planetInfoLeft = null;
        planetInfoRight = null;
        planetInfoTop = null;
    }

}
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

import com.minestellar.core.Constants;
import com.minestellar.core.MinestellarCore;
import com.minestellar.core.blocks.machines.Computer;
import com.minestellar.core.blocks.tile.TileEntityComputer;
import com.minestellar.core.gui.widget.GuiDraw;
import com.minestellar.core.gui.widget.GuiScreenWidget;
import com.minestellar.core.gui.widget.GuiSideBarWidget;
import com.minestellar.core.gui.widget.GuiWidget;
import com.minestellar.core.gui.widget.planets.GuiPlanet;
import com.minestellar.core.handler.FileHandler;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * GuiScreen for the {@link Computer} and {@link TileEntityComputer}
 */

public class ComputerGui extends GuiScreenWidget{

    public int screenWidth, screenHeight, spaceX, spaceY, spaceWidth, spaceHeight, earthA, earthB;
    private boolean doesDraw = false;

    private static ArrayList<String> knownPlanets = new ArrayList<String>();

    public ArrayList<GuiPlanet> planets = new ArrayList<GuiPlanet>();
    public ArrayList<Point2D.Double> earthCoordsArray = new ArrayList<Point2D.Double>();
    public ArrayList<Point2D.Double> moonCoordsArray = new ArrayList<Point2D.Double>();
    public ArrayList<Point2D.Double> mercuryCoordsArray = new ArrayList<Point2D.Double>();
    public ArrayList<Point2D.Double> venusCoordsArray = new ArrayList<Point2D.Double>();

    private static PlanetMover earthMover, venusMover, moonMover;

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
    }

    @Override
    public void updateScreen() {
        super.updateScreen();

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

        setWorldAndResolution(FMLClientHandler.instance().getClient(), screenWidth, screenHeight);

    }

    @Override
    public void addWidgets(){
        add(sun = new GuiPlanet(getMid(screenWidth) - (int) Math.sqrt(earthA ^ 2 - earthB ^ 2), getMid(screenHeight) - 4, "sun"));

        String text = FileHandler.readFromFile(Constants.coordinatesFile, false);
        if(text != null){
            String[] split = text.split(" ");
            if(split.length >= 3){
                String textX = split[1];
                String textY = split[2];
                int x = Integer.parseInt(textX);
                int y = Integer.parseInt(textY);
                if(x > 0 && y > 0){
                    if(knownPlanets.contains("moon")){
                        add(moon = new GuiPlanet(x, y, "moon"));
                        moon.setSize(x, y, 4, 4);
                        planets.add(moon);
                        MinestellarCore.planetMovers.add(new PlanetMover(Planet.MOON));
                    }
                    if(knownPlanets.contains("earth")){
                        add(earth = new GuiPlanet(x, y, "earth"));
                        planets.add(earth);
                        MinestellarCore.planetMovers.add(new PlanetMover(Planet.EARTH));
                    }
                    if(knownPlanets.contains("venus")){
                        add(venus = new GuiPlanet(x, y, "venus"));
                        planets.add(venus);
                        MinestellarCore.planetMovers.add(new PlanetMover(Planet.VENUS));
                    }
                }else{
                    if(knownPlanets.contains("moon")){
                        add(moon = new GuiPlanet(x, y, "moon"));
                        moon.setSize(x, y, 4, 4);
                        planets.add(moon);
                        MinestellarCore.planetMovers.add(new PlanetMover(Planet.MOON));
                    }
                    if(knownPlanets.contains("earth")){
                        add(earth = new GuiPlanet(x, y, "earth"));
                        planets.add(earth);
                        MinestellarCore.planetMovers.add(new PlanetMover(Planet.EARTH));
                    }
                    if(knownPlanets.contains("venus")){
                        add(venus = new GuiPlanet(x, y, "venus"));
                        planets.add(venus);
                        MinestellarCore.planetMovers.add(new PlanetMover(Planet.VENUS));
                    }
                }
            }
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

    /**
     * Calculates the new planet coordinates
     */

    public static void movePlanets(){
//        if(earthMover != null){
//            earthMover.run();
//        }else{
//            earthMover = new PlanetMover(Planet.EARTH);
//        }
//
//        if(moonMover != null){
//            if(knownPlanets.contains("moon")) moonMover.run();
//        }else{
//            moonMover = new PlanetMover(Planet.MOON);
//        }
//
//        if(venusMover != null){
//            if(knownPlanets.contains("venus")) venusMover.run();
//        }else{
//            venusMover = new PlanetMover(Planet.VENUS);
//        }
        for(PlanetMover mover : MinestellarCore.planetMovers){
            mover.run();
        }
    }

    /**
     * Gets the planet object from the given variable name
     * <p>Unfortunately it cant be used in any static context</p>
     *
     * @param name The name of the variable to search
     * @return The object corresponding
     */

    public GuiPlanet getPlanetFromString(String name){
        GuiPlanet p = null;
        try{
            p = (GuiPlanet)this.getClass().getDeclaredField(name.toLowerCase()).get(this);
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }catch(NoSuchFieldException e){
            e.printStackTrace();
        }
        return p;
    }

    /**
     * Sets the coordinates of the planets
     */

    public static void setPlanetCoordinates(){
        String text = FileHandler.readFromFile(Constants.coordinatesFile, false);
        if(text != null){
            String[] split = text.split(" ");
            if(split.length >= 3){
                String name = split[0].toLowerCase();
                String x = split[1];
                String y = split[2];

                if(name.equals("earth") && earth != null){ //Unfortunately I can't use the ComputerGui#getPlanetFromString(String). Because I can't use static methods
                    earth.setCoords(Integer.parseInt(x), Integer.parseInt(y));
                }else if(name.equals("moon") && moon != null){
                    moon.setCoords(Integer.parseInt(x), Integer.parseInt(y));
                }else if(name.equals("venus") && venus != null){
                    venus.setCoords(Integer.parseInt(x), Integer.parseInt(y));
                }
            }
        }
    }

}
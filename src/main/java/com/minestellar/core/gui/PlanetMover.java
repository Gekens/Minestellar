/**
 * Copyright (c) 12/06/15 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.core.gui;

import com.minestellar.core.gui.widget.GuiDraw;
import com.minestellar.core.gui.widget.planets.GuiPlanet;

/**
 * Runnable that moves the planets
 */

public class PlanetMover implements Runnable{

    private final double incr = 2;
    private double x = 0., y = 0.;
    private boolean doTop = false, firstTime = true;

    private Planet planet;
    private GuiPlanet currentPlanet;

    public PlanetMover(GuiPlanet currentPlanet, Planet planet){
        this.currentPlanet = currentPlanet;
        this.planet = planet;
    }

    @Override
    public void run(){
        if(firstTime){
            firstTime = false;
            x = -planet.getA();
        }

        if(x == 0.){
            if(!doTop){
                x += incr;
            } else {
                x -= incr;
            }
        }

        if(planet.equals(Planet.MOON)){
            y = Math.sqrt(Math.abs((planet.getB() * planet.getB()) * (1 - ((ComputerGui.earth.x * ComputerGui.earth.x) / (planet.getA() * planet.getA())))));
        }else{
            y = Math.sqrt(Math.abs((planet.getB() * planet.getB()) * (1 - ((x * x) / (planet.getA() * planet.getA())))));
        }

        if((x == planet.getA() && !doTop) || (x == -planet.getA() && doTop)){
            doTop = !doTop;
        }

        if(doTop){
            y = -y;
            x -= incr;
        }else{
            x += incr;
        }

        currentPlanet.setCoords(GuiDraw.displaySize().width / 2 + 4 + (int) x, GuiDraw.displaySize().height / 2 + 4 + (int) y);
    }
}
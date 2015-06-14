/**
 * Copyright (c) 05/apr/2015 Davide Cossu & Matthew Albrecht.
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

/**
 * Enumeration containing all the required constants of the planet's orbits
 */
public enum Planet {
	EARTH(152. / 2., 147. / 2.),
    MOON(384. / 152., 383. / 147),
    VENUS(108. / 2., 107. / 2.);

	private double a, b;

	Planet(double a, double b) {
		this.a = a;
		this.b = b;
	}

	public double getA() {
		return this.a;
	}

	public double getB() {
		return this.b;
	}

    /**
     * Gets the {@link Planet} object corresponding to the specified String
     *
     * @param planet The string to compare
     * @return The Planet object
     */

    public Planet getPlanetFromString(String planet){
        if(planet.toLowerCase().equals("earth")){
            return EARTH;
        }else if(planet.toLowerCase().equals("moon")){
            return MOON;
        }else if(planet.toLowerCase().equals("venus")){
            return VENUS;
        }
        return null;
    }
}
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

public enum Planet{

	EARTH(152./2., 147./2.),
	MOON(384./152., 383./147);
	
	private double a, b;
	
	Planet(double a, double b){
		this.a = a;
		this.b = b;
	}
	
	public double getA(){return this.a;}
	public double getB(){return this.b;}
	
}
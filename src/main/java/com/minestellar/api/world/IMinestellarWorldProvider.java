/**
 * Copyright (c) 31/dic/2014 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.api.world;

/**
 * Implement this in your World Provider, this sets the basic info for your
 * world.
 */
public interface IMinestellarWorldProvider
{
	/**
	 * Gets the gravity levels on the world. 1 would be equivalent to overworld.
	 * DO NOT USE 0! Also is used in the calculation of fall damage.
	 * 
	 * @return (1.0 though 0.01)F
	 */
	public float getGravity();

	/**
	 * Gets the heat levels on the world. This is measured in Celsius.
	 * 
	 * @return (Degrees In Celsius)
	 */
	public float getHeatLevelsDay();
	public float getHeatLevelsNight();

	/**
	 * Tells weather the planet has a breathable atmosphere or not. True means
	 * it has a atmosphere and you can breath, false has no atmosphere. This
	 * also means it has gas.
	 * 
	 * @return (true or false)
	 */
	public boolean hasAtmosphere();

	/**
	 * How long the days are. 24000 is 1 minecraft day. Try now to use very
	 * short values, the sun will start glitching.
	 * 
	 * @return (Any NonDecimal Number)L
	 */
	long getDayLength();

	/**
	 * Gets the air pressure of the world. This is measured in Pounds.
	 * 
	 * @return (Any Number Greater Than 0)L
	 */
	public float getAirPressure();
}

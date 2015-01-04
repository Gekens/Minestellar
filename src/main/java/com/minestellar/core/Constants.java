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

package com.minestellar.core;

public class Constants
{
	public static final int LOCAL_MAJ_VERSION = 0;
	public static final int LOCAL_MIN_VERSION = 0;
	public static final int LOCAL_BUILD_VERSION = 1;
	public static final String VERSION = (LOCAL_MAJ_VERSION + "." + LOCAL_MIN_VERSION + "." + LOCAL_BUILD_VERSION);

	public static final String MOD_NAME = "Minestellar";

	public static final String CONFIG_CATEGORY_DIMENSIONS = "dimensions";
	public static final String CONFIG_CATEGORY_BIOMES = "biomes";
	public static final String CONFIG_CATEGORY_ENTITIES = "entities";
	public static final String CONFIG_CATEGORY_GENERAL = "general";
}

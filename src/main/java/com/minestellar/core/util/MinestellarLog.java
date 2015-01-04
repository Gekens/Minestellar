/**
 * Copyright (c) 04/January/2015 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.core.util;

import org.apache.logging.log4j.Level;

import com.minestellar.core.Constants;

import cpw.mods.fml.relauncher.FMLRelaunchLog;

public class MinestellarLog {
	public static void info(String message) {
		FMLRelaunchLog.log(Constants.MOD_NAME, Level.INFO, message);
	}

	public static void severe(String message) {
		FMLRelaunchLog.log(Constants.MOD_NAME, Level.ERROR, message);
	}
}
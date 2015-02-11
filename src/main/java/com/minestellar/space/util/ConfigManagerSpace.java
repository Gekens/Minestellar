/**
 * Copyright (c) 11/feb/2015 Davide Cossu.
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

package com.minestellar.space.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import com.minestellar.core.Constants;

import cpw.mods.fml.common.FMLLog;

public class ConfigManagerSpace {

	public static boolean loaded;

	static Configuration configuration;

	public ConfigManagerSpace(File file) {
		if (!ConfigManagerSpace.loaded) {
			ConfigManagerSpace.configuration = new Configuration(file);
			this.setDefaultValues();
		}
	}
	
	public static int idDimensionSpace;
	public static int idBiomeSpace;


	private void setDefaultValues() {
		try {
			ConfigManagerSpace.configuration.load();
			
			ConfigManagerSpace.idDimensionSpace = ConfigManagerSpace.configuration.get(Constants.CONFIGURATION_DIMENSIONS, "Space Dimension", -26).getInt(-26);
			ConfigManagerSpace.idBiomeSpace = ConfigManagerSpace.configuration.get(Constants.CONFIGURATION_BIOMES, "Space Biome", 226).getInt(226);
		}catch (final Exception e) {
			FMLLog.log(Level.ERROR, e, Constants.MOD_NAME + " Space Config has a problem loading it's configuration");
		}

		finally {
			ConfigManagerSpace.configuration.save();
			ConfigManagerSpace.loaded = true;
		}
	}
	
}
/**
 * Copyright (c) 22/Feb/2015 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.space.orbit.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import com.minestellar.core.Constants;

import cpw.mods.fml.common.FMLLog;

public class ConfigManagerOrbit {
	public static boolean loaded;

	static Configuration configuration;

	public ConfigManagerOrbit(File file) {
		if (!ConfigManagerOrbit.loaded) {
			ConfigManagerOrbit.configuration = new Configuration(file);
			this.setDefaultValues();
		}
	}

	public static int idDimensionOrbit;
	public static int idBiomeOrbit;

	private void setDefaultValues() {
		try {
			ConfigManagerOrbit.configuration.load();

			ConfigManagerOrbit.idDimensionOrbit = ConfigManagerOrbit.configuration.get(Constants.CONFIGURATION_DIMENSIONS, "Orbit Dimension", -28).getInt(-28);
			ConfigManagerOrbit.idBiomeOrbit = ConfigManagerOrbit.configuration.get(Constants.CONFIGURATION_BIOMES, "Orbit Biome", 228).getInt(228);
		} catch (final Exception e) {
			FMLLog.log(Level.ERROR, e, Constants.MOD_NAME + " Orbit Config has a problem loading it's configuration");
		} finally {
			ConfigManagerOrbit.configuration.save();
			ConfigManagerOrbit.loaded = true;
		}
	}

}
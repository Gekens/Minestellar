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

package com.minestellar.core.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import com.minestellar.core.Constants;

import cpw.mods.fml.common.FMLLog;

public class ConfigManagerCore
{
	public static boolean loaded;

	static Configuration configuration;

	public ConfigManagerCore(File file)
	{
		if (!ConfigManagerCore.loaded)
		{
			ConfigManagerCore.configuration = new Configuration(file);
			this.setDefaultValues();
		}
	}

	public static boolean idDayLength;
	public static double idStarMultiplier;
	public static boolean idVersionCheck;

	private void setDefaultValues()
	{
		try
		{
			ConfigManagerCore.configuration.load();

			ConfigManagerCore.idStarMultiplier = ConfigManagerCore.configuration.get(Constants.CONFIG_CATEGORY_GENERAL, "Star count multiplyer. Causes more lag when in space. (Default '1.0')", 1.0).getInt(1);
			ConfigManagerCore.idStarMultiplier = ConfigManagerCore.configuration.get(Constants.CONFIG_CATEGORY_GENERAL, "Star count multiplyer. Causes more lag when in space. (Default '1.0')", 1.0).getDouble(1.0);

			ConfigManagerCore.idDayLength = ConfigManagerCore.configuration.get(Constants.CONFIG_CATEGORY_GENERAL, "Realistic Day Length. (Default 'true')", true).getBoolean(true);
			ConfigManagerCore.idVersionCheck = ConfigManagerCore.configuration.get(Constants.CONFIG_CATEGORY_GENERAL, "Run a version check. May cause lag for the first 2 minutes of loading a world. (Default 'true')", true).getBoolean(true);
		}

		catch (final Exception e)
		{
			FMLLog.log(Level.ERROR, e, Constants.MOD_NAME + " Core Config has a problem loading it's configuration");
		}

		finally
		{
			ConfigManagerCore.configuration.save();
			ConfigManagerCore.loaded = true;
		}
	}
}

package com.minestellar.moon.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

public class ConfigManagerMoon
{
	public static boolean loaded;

	static Configuration configuration;

	public ConfigManagerMoon(File file)
	{
		if (!ConfigManagerMoon.loaded)
		{
			ConfigManagerMoon.configuration = new Configuration(file);
			this.setDefaultValues();
		}
	}

	public static int idDimensionMoon;
	public static int idBiomeMoon;

	private void setDefaultValues()
	{
		try
		{
			ConfigManagerMoon.configuration.load();

			ConfigManagerMoon.idDimensionMoon = ConfigManagerMoon.configuration.get(Configuration.CATEGORY_GENERAL, "Moon Dimension", -25).getInt(-25);
			ConfigManagerMoon.idBiomeMoon = ConfigManagerMoon.configuration.get(Configuration.CATEGORY_GENERAL, "Moon Biome", 225).getInt(225);
		}

		catch (final Exception e)
		{
			FMLLog.log(Level.ERROR, e, "Minestellar Moon Config has a problem loading it's configuration");
		}

		finally
		{
			ConfigManagerMoon.configuration.save();
			ConfigManagerMoon.loaded = true;
		}
	}
}

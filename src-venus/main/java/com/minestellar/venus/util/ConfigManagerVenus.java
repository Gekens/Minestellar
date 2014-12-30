package com.minestellar.venus.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

public class ConfigManagerVenus
{
	public static boolean loaded;

	static Configuration configuration;

	public ConfigManagerVenus(File file)
	{
		if (!ConfigManagerVenus.loaded)
		{
			ConfigManagerVenus.configuration = new Configuration(file);
			this.setDefaultValues();
		}
	}

	public static int idDimensionVenus;
	public static boolean idDayLength;
	public static int idBiomeVenus;

	private void setDefaultValues()
	{
		try
		{
			ConfigManagerVenus.configuration.load();

			ConfigManagerVenus.idDimensionVenus = ConfigManagerVenus.configuration.get(Configuration.CATEGORY_GENERAL, "Venus Dimension", -41).getInt(-41);
			ConfigManagerVenus.idDayLength = ConfigManagerVenus.configuration.get(Configuration.CATEGORY_GENERAL, "Venus Day Length Realistic", true).getBoolean(true);
			ConfigManagerVenus.idBiomeVenus = ConfigManagerVenus.configuration.get(Configuration.CATEGORY_GENERAL, "Venus Biome", 211).getInt(211);
		}

		catch (final Exception e)
		{
			FMLLog.log(Level.ERROR, e, "Minestellar Venus Config has a problem loading it's configuration");
		}

		finally
		{
			ConfigManagerVenus.configuration.save();
			ConfigManagerVenus.loaded = true;
		}
	}
}
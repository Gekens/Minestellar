package com.minestellar.moon.world;

import net.minecraftforge.common.DimensionManager;

import com.minestellar.moon.util.ConfigManagerMoon;

public class DimensionMoon
{
	/** Register dimensions. */
	public static void registerDimensions()
	{
		DimensionManager.registerDimension(ConfigManagerMoon.idDimensionMoon, ConfigManagerMoon.idDimensionMoon);
	}
	
	/** Register dimension world providers with the dimension manager. */
	public static void registerWorldProvider()
	{
		DimensionManager.registerProviderType(ConfigManagerMoon.idDimensionMoon, WorldProviderMoon.class, true);
	}
}
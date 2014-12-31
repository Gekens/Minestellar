package com.minestellar.venus.world;

import net.minecraftforge.common.DimensionManager;

import com.minestellar.venus.util.ConfigManagerVenus;

public class DimensionVenus
{
	/** Register dimensions. */
	public static void registerDimensions()
	{
		DimensionManager.registerDimension(ConfigManagerVenus.idDimensionVenus, ConfigManagerVenus.idDimensionVenus);
	}
	
	/** Register dimension world providers with the dimension manager. */
	public static void registerWorldProvider()
	{
		DimensionManager.registerProviderType(ConfigManagerVenus.idDimensionVenus, WorldProviderVenus.class, true);
	}
}
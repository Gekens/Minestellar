package mattparks.mods.space.venus.world;

import mattparks.mods.space.venus.util.ConfigManagerVenus;
import net.minecraftforge.common.DimensionManager;

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
package com.minestellar.moon.world.gen;

import net.minecraft.world.biome.BiomeGenBase;

import com.minestellar.api.prefab.wgen.MinestellarWorldChunkManager;

public class WorldChunkManagerMoon extends MinestellarWorldChunkManager
{
	@Override
	public BiomeGenBase getBiome()
	{
		return BiomeGenBaseMoon.moon;
	}
}
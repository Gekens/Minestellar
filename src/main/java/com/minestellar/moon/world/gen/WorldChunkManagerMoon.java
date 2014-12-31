package com.minestellar.moon.world.gen;

import net.minecraft.world.biome.BiomeGenBase;

import com.minestellar.api.world.gen.WorldChunkManagerMinestellar;

public class WorldChunkManagerMoon extends WorldChunkManagerMinestellar
{
	@Override
	public BiomeGenBase getBiome()
	{
		return BiomeGenBaseMoon.moon;
	}
}

package com.minestellar.venus.world.gen;

import net.minecraft.world.biome.BiomeGenBase;

import com.minestellar.api.world.gen.WorldChunkManagerMinestellar;

public class WorldChunkManagerVenus extends WorldChunkManagerMinestellar {
	@Override
	public BiomeGenBase getBiome() {
		return BiomeGenBaseVenus.venus;
	}
}

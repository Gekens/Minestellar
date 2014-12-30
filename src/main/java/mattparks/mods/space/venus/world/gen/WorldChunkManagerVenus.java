package mattparks.mods.space.venus.world.gen;

import net.minecraft.world.biome.BiomeGenBase;

import com.minestellar.api.world.gen.MinestellarWorldChunkManager;

public class WorldChunkManagerVenus extends MinestellarWorldChunkManager
{
	@Override
	public BiomeGenBase getBiome()
	{
		return BiomeGenBaseVenus.venus;
	}
}
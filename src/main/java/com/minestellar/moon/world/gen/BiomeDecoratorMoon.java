package com.minestellar.moon.world.gen;

import net.minecraft.world.World;

import com.minestellar.api.world.gen.BiomeDecoratorMinestellar;

public class BiomeDecoratorMoon extends BiomeDecoratorMinestellar
{
	// protected WorldGenerator oreExample;
	private World currentWorld;

	public BiomeDecoratorMoon()
	{
		// this.oreExample = new WorldGenMinableMeta(MoonBlocks.moonOreBlocks,
		// 8, 1, true, MoonBlocks.moonBasicBlocks, 2);
	}

	@Override
	public void decorate()
	{
		// this.generateOre(32, this.oreExample, 0, 128);
	}

	@Override
	protected void setCurrentWorld(World world)
	{
		this.currentWorld = world;
	}

	@Override
	protected World getCurrentWorld()
	{
		return this.currentWorld;
	}
}

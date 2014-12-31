package com.minestellar.moon.world.gen;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

import com.google.common.collect.Lists;
import com.minestellar.api.core.BlockMetaPair;
import com.minestellar.api.world.gen.BiomeDecoratorMinestellar;
import com.minestellar.api.world.gen.ChunkProviderMinestellar;
import com.minestellar.api.world.gen.MapGenBaseMeta;
import com.minestellar.moon.blocks.MoonBlocks;

public class ChunkProviderMoon extends ChunkProviderMinestellar
{
	private final BiomeDecoratorMoon marsBiomeDecorator = new BiomeDecoratorMoon();
	private final MapGenCaveMoon cavernGenerator = new MapGenCaveMoon();

	public ChunkProviderMoon(World par1World, long seed, boolean mapFeaturesEnabled)
	{
		super(par1World, seed, mapFeaturesEnabled);
	}

	@Override
	protected BiomeDecoratorMinestellar getBiomeGenerator()
	{
		return this.marsBiomeDecorator;
	}

	@Override
	protected BiomeGenBase[] getBiomesForGeneration()
	{
		return new BiomeGenBase[] { BiomeGenBaseMoon.moon };
	}

	@Override
	protected int getSeaLevel()
	{
		return 93;
	}

	@Override
	protected List<MapGenBaseMeta> getWorldGenerators()
	{
		List<MapGenBaseMeta> generators = Lists.newArrayList();
		generators.add(this.cavernGenerator);
		return generators;
	}

	@Override
	protected BiomeGenBase.SpawnListEntry[] getMonsters()
	{
		List<BiomeGenBase.SpawnListEntry> monsters = new ArrayList<BiomeGenBase.SpawnListEntry>();
		monsters.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 8, 2, 3));
		monsters.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 8, 2, 3));
		monsters.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 8, 2, 3));
		monsters.add(new BiomeGenBase.SpawnListEntry(EntityCreeper.class, 8, 2, 3));
		return monsters.toArray(new BiomeGenBase.SpawnListEntry[monsters.size()]);
	}

	@Override
	protected BiomeGenBase.SpawnListEntry[] getCreatures()
	{
		return new BiomeGenBase.SpawnListEntry[0];
	}

	@Override
	protected BlockMetaPair getGrassBlock()
	{
		return new BlockMetaPair(MoonBlocks.moonBasicBlocks, (byte) 0);
	}

	@Override
	protected BlockMetaPair getDirtBlock()
	{
		return new BlockMetaPair(MoonBlocks.moonBasicBlocks, (byte) 1);
	}

	@Override
	protected BlockMetaPair getStoneBlock()
	{
		return new BlockMetaPair(MoonBlocks.moonBasicBlocks, (byte) 5);
	}

	@Override
	public double getHeightModifier()
	{
		return 12;
	}

	@Override
	public double getSmallFeatureHeightModifier()
	{
		return 26;
	}

	@Override
	public double getMountainHeightModifier()
	{
		return 95;
	}

	@Override
	public double getValleyHeightModifier()
	{
		return 50;
	}

	@Override
	public int getCraterProbability()
	{
		return 2000;
	}

	@Override
	public void onChunkProvide(int cX, int cZ, Block[] blocks, byte[] metadata)
	{
	}

	@Override
	public void onPopulate(IChunkProvider provider, int cX, int cZ)
	{
	}
}

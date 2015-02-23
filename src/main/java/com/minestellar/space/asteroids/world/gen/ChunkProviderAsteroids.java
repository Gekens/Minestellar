/**
 * Copyright (c) 22/Feb/2015 Davide Cossu & Matthew Albrecht.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, see <http://www.gnu.org/licenses>.
 */

package com.minestellar.space.asteroids.world.gen;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

import com.google.common.collect.Lists;
import com.minestellar.api.core.BlockMetaPair;
import com.minestellar.api.world.gen.BiomeDecoratorMinestellar;
import com.minestellar.api.world.gen.ChunkProviderMinestellar;
import com.minestellar.api.world.gen.MapGenBaseMeta;

public class ChunkProviderAsteroids extends ChunkProviderMinestellar {
	private final BiomeDecoratorAsteroids spaceBiomeDecorator = new BiomeDecoratorAsteroids();

	public ChunkProviderAsteroids(World par1World, long seed, boolean mapFeaturesEnabled) {
		super(par1World, seed, mapFeaturesEnabled);
	}

	@Override
	protected BiomeDecoratorMinestellar getBiomeGenerator() {
		return this.spaceBiomeDecorator;
	}

	@Override
	protected BiomeGenBase[] getBiomesForGeneration() {
		return new BiomeGenBase[] {BiomeGenBaseAsteroids.asteroids};
	}

	@Override
	protected int getSeaLevel() {
		return 0;
	}

	@Override
	protected List<MapGenBaseMeta> getWorldGenerators() {
		List<MapGenBaseMeta> list = Lists.newArrayList();
		return list;
	}

	@Override
	protected SpawnListEntry[] getMonsters() {
		SpawnListEntry[] l = new SpawnListEntry[0];
		return l;
	}

	@Override
	protected SpawnListEntry[] getCreatures() {
		SpawnListEntry[] l = new SpawnListEntry[0];
		return l;
	}

	@Override
	protected BlockMetaPair getGrassBlock() {
		return null;
	}

	@Override
	protected BlockMetaPair getDirtBlock() {
		return null;
	}

	@Override
	protected BlockMetaPair getStoneBlock() {
		return null;
	}

	@Override
	public double getHeightModifier() {
		return 0;
	}

	@Override
	public double getSmallFeatureHeightModifier() {
		return 0;
	}

	@Override
	public double getMountainHeightModifier() {
		return 0;
	}

	@Override
	public double getValleyHeightModifier() {
		return 0;
	}

	@Override
	public int getCraterProbability() {
		return 0;
	}

	@Override
	public void onChunkProvide(int cX, int cZ, Block[] blocks, byte[] metadata) {
	}

	@Override
	public void onPopulate(IChunkProvider provider, int cX, int cZ) {
	}

	@Override
	public void replaceBlocksForBiome(int par1, int par2, Block[] arrayOfIDs, byte[] arrayOfMeta, BiomeGenBase[] par4ArrayOfBiomeGenBase) {
	}

	@Override
	public Chunk provideChunk(int par1, int par2) {
		final Chunk chunk = new Chunk(this.worldObj, new Block[] {}, new byte[] {}, par1, par2);
		return chunk;
	}
}
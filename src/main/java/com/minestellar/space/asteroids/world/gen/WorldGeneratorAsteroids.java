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

import java.util.Random;

import com.minestellar.space.asteroids.world.WorldProviderAsteroids;
import com.minestellar.space.asteroids.world.gen.structure.WorldGenStructureAsteroids;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorAsteroids implements IWorldGenerator {
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider instanceof WorldProviderAsteroids) {
			generateAsteroids(world, random, chunkX * 16, chunkZ * 16);
		}
	}

	private void generateAsteroids(World world, Random random, int x, int z) {
		//if (random.nextInt(30) >= 10) {
			int Xcoord1 = x + random.nextInt(16);
			int Ycoord1 = random.nextInt(256);
			int Zcoord1 = z + random.nextInt(16);

			(new WorldGenStructureAsteroids()).generate(world, random, Xcoord1, Ycoord1, Zcoord1);
		//}
	}
}
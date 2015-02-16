/**
 * Copyright (c) 12/feb/2015 Davide Cossu.
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

package com.minestellar.space.world.gen;

import java.util.Random;

import net.minecraft.world.World;

import com.minestellar.api.world.gen.BiomeDecoratorMinestellar;
import com.minestellar.space.world.gen.structure.WorldGenStructureAsteroid;

public class BiomeDecoratorSpace extends BiomeDecoratorMinestellar{

	private World currentWorld;

	private WorldGenStructureAsteroid generator;
	
	public BiomeDecoratorSpace() {
		generator = new WorldGenStructureAsteroid();
	}
	
	@Override
	protected void setCurrentWorld(World world) {
		this.currentWorld = world;
	}

	@Override
	protected World getCurrentWorld() {
		return this.currentWorld;
	}

	@Override
	protected void decorate() {
		//this.generateOre(1, generator, 30, 220);
		/*Random random = new Random();
		int x = this.chunkX + random.nextInt(16);
		int y = random.nextInt(256);
		int z = this.chunkZ + random.nextInt(16);
		generator.generate(this.getCurrentWorld(), rand, x, y, z);*/
	}

}

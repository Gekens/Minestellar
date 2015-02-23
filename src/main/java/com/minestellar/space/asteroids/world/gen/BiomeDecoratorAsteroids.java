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

import net.minecraft.world.World;

import com.minestellar.api.world.gen.BiomeDecoratorMinestellar;

public class BiomeDecoratorAsteroids extends BiomeDecoratorMinestellar {
	// protected WorldGenerator oreExample;
	private World currentWorld;

	public BiomeDecoratorAsteroids() {
		// this.oreExample = new WorldGenMinableMeta(MoonBlocks.moonOreBlocks, 8, 1, true, MoonBlocks.moonBasicBlocks, 2);
	}

	@Override
	public void decorate() {
		// this.generateOre(32, this.oreExample, 0, 128);
	}

	@Override
	protected void setCurrentWorld(World world) {
		this.currentWorld = world;
	}

	@Override
	protected World getCurrentWorld() {
		return this.currentWorld;
	}
}

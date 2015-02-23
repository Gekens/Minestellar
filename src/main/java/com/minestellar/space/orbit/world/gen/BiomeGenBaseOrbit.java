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

package com.minestellar.space.orbit.world.gen;

import com.minestellar.space.orbit.util.ConfigManagerOrbit;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBaseOrbit extends BiomeGenBase {
	public static final BiomeGenBase orbit = new BiomeGenBaseOrbit(ConfigManagerOrbit.idBiomeOrbit).setBiomeName("Orbit");

	public BiomeGenBaseOrbit(int var1) {
		super(var1);
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.rainfall = 0F;
	}

	@Override
	public BiomeGenBaseOrbit setColor(int var1) {
		return (BiomeGenBaseOrbit) super.setColor(var1);
	}

	@Override
	public float getSpawningChance() {
		return 0.0F;
	}
}
/**
 * Copyright (c) 11/feb/2015 Davide Cossu.
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

import com.minestellar.moon.world.gen.BiomeGenBaseMoon;
import com.minestellar.space.util.ConfigManagerSpace;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;

public class BiomeGenBaseSpace extends BiomeGenBase{
	public static final BiomeGenBase space = new BiomeGenBaseMoon(ConfigManagerSpace.idBiomeSpace).setBiomeName("Space");
	
	public BiomeGenBaseSpace(int var1) {
		super(var1);
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.rainfall = 0F;
	}
	
	@Override
	public BiomeGenBaseMoon setColor(int var1) {
		return (BiomeGenBaseMoon) super.setColor(var1);
	}

	@Override
	public float getSpawningChance() {
		return 0.0F;
	}
	
}
/**
 * Copyright (c) 31/dic/2014 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.moon.world.gen;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.biome.BiomeGenBase;

import com.minestellar.moon.util.ConfigManagerMoon;

public class BiomeGenBaseMoon extends BiomeGenBase
{
	public static final BiomeGenBase moon = new BiomeGenBaseMoon(ConfigManagerMoon.idBiomeMoon).setBiomeName("Moon");

	public BiomeGenBaseMoon(int var1)
	{
		super(var1);
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 5, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 3, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityCreeper.class, 2, 1, 1));
		this.rainfall = 0F;
	}

	@Override
	public BiomeGenBaseMoon setColor(int var1)
	{
		return (BiomeGenBaseMoon) super.setColor(var1);
	}

	@Override
	public float getSpawningChance()
	{
		return 0.01F;
	}
}

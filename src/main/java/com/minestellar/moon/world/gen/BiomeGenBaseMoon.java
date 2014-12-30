package com.minestellar.moon.world.gen;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.biome.BiomeGenBase;

import com.minestellar.moon.ConfigManagerMoon;

public class BiomeGenBaseMoon extends BiomeGenBase
{
	public static final BiomeGenBase europa = new BiomeGenBaseMoon(ConfigManagerMoon.idBiomeMoon).setBiomeName("Europa");

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
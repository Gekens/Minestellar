package com.minestellar.venus.world.gen;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.biome.BiomeGenBase;

import com.minestellar.venus.entities.EntityEvolvedBlaze;
import com.minestellar.venus.util.ConfigManagerVenus;

public class BiomeGenBaseVenus extends BiomeGenBase
{
	public static final BiomeGenBase venus = new BiomeGenBaseVenus(ConfigManagerVenus.idBiomeVenus).setBiomeName("Venus");

	public BiomeGenBaseVenus(int var1)
	{
		super(var1);
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 5, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 3, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityCreeper.class, 2, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedBlaze.class, 4, 1, 1));
		this.rainfall = 0F;
	}

	@Override
	public BiomeGenBaseVenus setColor(int var1)
	{
		return (BiomeGenBaseVenus) super.setColor(var1);
	}

	@Override
	public float getSpawningChance()
	{
		return 0.01F;
	}
}
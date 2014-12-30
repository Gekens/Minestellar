package com.minestellar.api.world.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;

import com.minestellar.api.event.EventWorldPopulate;

public abstract class MinestellarBiomeDecorator
{
	protected Random rand;

	protected int chunkX;
	protected int chunkZ;

	public void decorate(World world, Random random, int chunkX, int chunkZ)
	{
		if (this.getCurrentWorld() != null)
		{
			throw new RuntimeException("Already decorating!");
		}

		else
		{
			this.setCurrentWorld(world);
			this.rand = random;
			this.chunkX = chunkX;
			this.chunkZ = chunkZ;
			MinecraftForge.EVENT_BUS.post(new EventWorldPopulate.Pre(world, this.rand, this.chunkX, this.chunkZ));
			this.decorate();
			MinecraftForge.EVENT_BUS.post(new EventWorldPopulate.Post(world, this.rand, this.chunkX, this.chunkZ));
			this.setCurrentWorld(null);
			this.rand = null;
		}
	}

	protected abstract void setCurrentWorld(World world);

	protected abstract World getCurrentWorld();

	protected void generateOre(int amountPerChunk, WorldGenerator worldGenerator, int minY, int maxY)
	{
		World currentWorld = this.getCurrentWorld();
		for (int var5 = 0; var5 < amountPerChunk; ++var5)
		{
			final int var6 = this.chunkX + this.rand.nextInt(16);
			final int var7 = this.rand.nextInt(maxY - minY) + minY;
			final int var8 = this.chunkZ + this.rand.nextInt(16);
			worldGenerator.generate(currentWorld, this.rand, var6, var7, var8);
		}
	}

	protected abstract void decorate();
}

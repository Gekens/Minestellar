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

package com.minestellar.api.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;

import com.minestellar.api.event.wgen.MinestellarEventPopulate;

public abstract class IMinestellarBiomeDecorator
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
			MinecraftForge.EVENT_BUS.post(new MinestellarEventPopulate.Pre(world, this.rand, this.chunkX, this.chunkZ));
			this.decorate();
			MinecraftForge.EVENT_BUS.post(new MinestellarEventPopulate.Post(world, this.rand, this.chunkX, this.chunkZ));
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

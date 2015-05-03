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

package com.minestellar.api.event.wgen;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.world.World;

import java.util.Random;

public class MinestellarEventPopulate extends Event {
	public final World worldObj;
	public final Random rand;
	public final int chunkX;
	public final int chunkZ;

	public MinestellarEventPopulate(World worldObj, Random rand, int chunkX, int chunkZ) {
		this.worldObj = worldObj;
		this.rand = rand;
		this.chunkX = chunkX;
		this.chunkZ = chunkZ;
	}

	public static class Pre extends MinestellarEventPopulate {
		public Pre(World world, Random rand, int worldX, int worldZ) {
			super(world, rand, worldX, worldZ);
		}
	}

	public static class Post extends MinestellarEventPopulate {
		public Post(World world, Random rand, int worldX, int worldZ) {
			super(world, rand, worldX, worldZ);
		}
	}
}

package com.minestellar.api.event;

import java.util.Random;

import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.Event;

public class EventWorldPopulate extends Event
{
	public final World worldObj;
	public final Random rand;
	public final int chunkX;
	public final int chunkZ;

	public EventWorldPopulate(World worldObj, Random rand, int chunkX, int chunkZ)
	{
		this.worldObj = worldObj;
		this.rand = rand;
		this.chunkX = chunkX;
		this.chunkZ = chunkZ;
	}

	public static class Pre extends EventWorldPopulate
	{
		public Pre(World world, Random rand, int worldX, int worldZ)
		{
			super(world, rand, worldX, worldZ);
		}
	}

	public static class Post extends EventWorldPopulate
	{
		public Post(World world, Random rand, int worldX, int worldZ)
		{
			super(world, rand, worldX, worldZ);
		}
	}
}

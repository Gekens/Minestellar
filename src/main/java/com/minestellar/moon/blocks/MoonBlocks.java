package com.minestellar.moon.blocks;

import net.minecraft.block.Block;

import com.minestellar.moon.MoonCore;
import com.minestellar.moon.blocks.items.ItemBlockBasic;
import com.minestellar.moon.blocks.items.ItemBlockTeleporter;

public class MoonBlocks
{
	public static Block moonBasicBlocks;
	public static BlockTeleporterMoon moonPortal;

	private static void initBlocks()
	{
		MoonBlocks.moonBasicBlocks = new BlockBasicMoon("moon_basic");
		MoonBlocks.moonPortal = new BlockTeleporterMoon("moon_portal");
	}

	public static void setHarvestLevels()
	{
	}

	private static void registerBlocks()
	{
		MoonCore.registerBlock(MoonBlocks.moonBasicBlocks, ItemBlockBasic.class);
		MoonCore.registerBlock(MoonBlocks.moonPortal, ItemBlockTeleporter.class);
	}

	public static void oreDictRegistration()
	{
	}

	public static void init()
	{
		initBlocks();
		setHarvestLevels();
		registerBlocks();
		oreDictRegistration();
	}
}

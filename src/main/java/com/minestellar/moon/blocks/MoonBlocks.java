package com.minestellar.moon.blocks;

import net.minecraft.block.Block;

import com.minestellar.core.Minestellar;
import com.minestellar.core.util.ItemBlockUtil;
import com.minestellar.moon.blocks.items.ItemBlockBasic;

public class MoonBlocks
{
	public static Block moonBasicBlocks;
	public static BlockTeleporterMoon moonPortal;

	private static void initBlocks()
	{
		MoonBlocks.moonBasicBlocks = new BlockBasicMoon("moon_basic");
		MoonBlocks.moonPortal = new BlockTeleporterMoon("moonPortal");
	}
	
	public static void setHarvestLevels()
	{
	}

	private static void registerBlocks()
	{
		Minestellar.registerBlock(MoonBlocks.moonBasicBlocks, ItemBlockBasic.class);
//		GameRegistry.registerBlock(MoonBlocks.moonPortal, "moonPortal");
		Minestellar.registerBlock(MoonBlocks.moonPortal, ItemBlockUtil.class);
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
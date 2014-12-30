package com.minestellar.moon.blocks;

import net.minecraft.block.Block;

import com.minestellar.core.Minestellar;
import com.minestellar.core.util.ItemBlockUtil;
import com.minestellar.moon.MoonCore;
import com.minestellar.moon.blocks.items.ItemBlockBasic;

import cpw.mods.fml.common.registry.GameRegistry;

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
		//GameRegistry.registerBlock(MoonBlocks.moonPortal, "moonPortal");
		MoonCore.registerBlock(MoonBlocks.moonPortal, ItemBlockUtil.class);
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
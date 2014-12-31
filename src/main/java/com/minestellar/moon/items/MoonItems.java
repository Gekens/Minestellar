package com.minestellar.moon.items;

import net.minecraft.item.Item;

import com.minestellar.core.Minestellar;

public class MoonItems
{
	public static Item moonBasicItems;

	private static void initItems()
	{
		MoonItems.moonBasicItems = new ItemBasicMoon();
	}

	private static void registerHarvestLevels()
	{
	}

	private static void registerItems()
	{
		Minestellar.registerItem(moonBasicItems);
	}

	public static void oreDictRegistration()
	{
	}

	public static void init()
	{
		initItems();
		registerHarvestLevels();
		registerItems();
		oreDictRegistration();
	}
}

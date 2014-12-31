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

package com.minestellar.moon.blocks;

import net.minecraft.block.Block;

import com.minestellar.moon.MinestellarMoon;
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
		MinestellarMoon.registerBlock(MoonBlocks.moonBasicBlocks, ItemBlockBasic.class);
		MinestellarMoon.registerBlock(MoonBlocks.moonPortal, ItemBlockTeleporter.class);
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

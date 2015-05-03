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

package com.minestellar.moon.blocks;

import com.minestellar.core.util.ItemBlockUtil;
import com.minestellar.moon.MinestellarMoon;
import com.minestellar.moon.blocks.items.ItemBlockBasicMoon;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class MoonBlocks {
	public static void init() {
		initBlocks();
		registerBlocks();
		oreDictRegistration();
		setHarvestLevels();
	}

	public static Block moonBasicBlocks;
	public static BlockTeleporterMoon moonPortal;

	private static void initBlocks() {
		MoonBlocks.moonBasicBlocks = new BlockBasicMoon("moon_basic");
		MoonBlocks.moonPortal = new BlockTeleporterMoon("moon_portal");
	}

	private static void registerBlocks() {
		MinestellarMoon.registerBlock(MoonBlocks.moonBasicBlocks, ItemBlockBasicMoon.class);
		MinestellarMoon.registerBlock(MoonBlocks.moonPortal, ItemBlockUtil.class);
	}

	private static void oreDictRegistration() {
		OreDictionary.registerOre("moonStone", new ItemStack(MoonBlocks.moonBasicBlocks, 1, 10));
	}

	private static void setHarvestLevels() {
	}
}

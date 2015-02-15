/**
 * Copyright (c) 15/feb/2015 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.space.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.minestellar.space.MinestellarSpace;
import com.minestellar.space.blocks.items.ItemBlockBasicSpace;

public class SpaceBlocks {

	public static void init(){
		initBlocks();
		registerBlocks();
		oreDictRegistration();
		setHarvestLevels();
	}
	
	public static Block spaceBasicBlocks;
	
	private static void initBlocks() {
		spaceBasicBlocks = new BlockBasicSpace("space_basic");
	}

	private static void registerBlocks() {
		MinestellarSpace.registerBlock(SpaceBlocks.spaceBasicBlocks, ItemBlockBasicSpace.class);
	}

	private static void oreDictRegistration() {
		OreDictionary.registerOre("asteroidStone", new ItemStack(SpaceBlocks.spaceBasicBlocks, 1, 10));
	}

	private static void setHarvestLevels() {
	}
	
}
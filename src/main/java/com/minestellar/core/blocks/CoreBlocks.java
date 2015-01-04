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

package com.minestellar.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.blocks.fluid.FluidOil;
import com.minestellar.core.blocks.items.ItemBlockCable;
import com.minestellar.core.blocks.items.ItemBlockDecor;
import com.minestellar.core.blocks.items.ItemBlockOres;
import com.minestellar.core.blocks.items.ItemBlockPipe;
import com.minestellar.core.util.ItemBlockUtil;

public class CoreBlocks
{
	public static Block coreOreBlocks;
	public static Block coreDecorBlocks;
	public static Block cableBlock;
	public static Block pipeBlock;
	public static Block teleportBlock;

	public static Block oilFluidBlock;
	public static Fluid oilFluid;

	private static void initBlocks()
	{
		CoreBlocks.coreOreBlocks = new BlockOreCore("core_ore");
		CoreBlocks.coreDecorBlocks = new BlockDecorCore("core_decor");
		CoreBlocks.teleportBlock = new BlockTeleportCore("teleporter", Material.iron);
		CoreBlocks.cableBlock = new BlockCable("core_cable");
		CoreBlocks.pipeBlock = new BlockPipe("core_pipe");

		CoreBlocks.oilFluid = new FluidOil("oil").setBlock(CoreBlocks.oilFluidBlock);
		FluidRegistry.registerFluid(CoreBlocks.oilFluid);
		CoreBlocks.oilFluidBlock = new BlockFluidOil("oil", CoreBlocks.oilFluid, Material.water);
	}

	public static void setHarvestLevels()
	{
	}

	private static void registerBlocks()
	{
		MinestellarCore.registerBlock(CoreBlocks.coreOreBlocks, ItemBlockOres.class);
		MinestellarCore.registerBlock(CoreBlocks.coreDecorBlocks, ItemBlockDecor.class);
		MinestellarCore.registerBlock(CoreBlocks.teleportBlock, ItemBlockUtil.class);
		MinestellarCore.registerBlock(CoreBlocks.cableBlock, ItemBlockCable.class);
		MinestellarCore.registerBlock(CoreBlocks.pipeBlock, ItemBlockPipe.class);
		
		MinestellarCore.registerBlock(CoreBlocks.oilFluidBlock, ItemBlockUtil.class);
	}

	public static void oreDictRegistration()
	{
		/* Ores */
		OreDictionary.registerOre("oreCopper", new ItemStack(CoreBlocks.coreOreBlocks, 1, 0));
		OreDictionary.registerOre("oreTin", new ItemStack(CoreBlocks.coreOreBlocks, 1, 1));
		OreDictionary.registerOre("oreLithium", new ItemStack(CoreBlocks.coreOreBlocks, 1, 2));
		OreDictionary.registerOre("oreSilicon", new ItemStack(CoreBlocks.coreOreBlocks, 1, 3));
		OreDictionary.registerOre("oreAluminum", new ItemStack(CoreBlocks.coreOreBlocks, 1, 4));
		OreDictionary.registerOre("oreTitanium", new ItemStack(CoreBlocks.coreOreBlocks, 1, 5));
		OreDictionary.registerOre("oreCarbon", new ItemStack(CoreBlocks.coreOreBlocks, 1, 6));

		/* Blocks */
		OreDictionary.registerOre("blockCopper", new ItemStack(CoreBlocks.coreDecorBlocks, 1, 0));
		OreDictionary.registerOre("blockTin", new ItemStack(CoreBlocks.coreDecorBlocks, 1, 1));
		OreDictionary.registerOre("blockSteel", new ItemStack(CoreBlocks.coreDecorBlocks, 1, 2));
		OreDictionary.registerOre("blockLithium", new ItemStack(CoreBlocks.coreDecorBlocks, 1, 3));
		OreDictionary.registerOre("blockSilicon", new ItemStack(CoreBlocks.coreDecorBlocks, 1, 4));
		OreDictionary.registerOre("blockAluminum", new ItemStack(CoreBlocks.coreDecorBlocks, 1, 5));
		OreDictionary.registerOre("blockTitanium", new ItemStack(CoreBlocks.coreDecorBlocks, 1, 6));
		OreDictionary.registerOre("blockCarbon", new ItemStack(CoreBlocks.coreDecorBlocks, 1, 7));
	}

	public static void init()
	{
		initBlocks();
		setHarvestLevels();
		registerBlocks();
		oreDictRegistration();
	}
}
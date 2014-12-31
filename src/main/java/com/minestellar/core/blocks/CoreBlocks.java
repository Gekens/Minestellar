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
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.blocks.fluid.OilFluid;
import com.minestellar.core.blocks.items.ItemBlockOres;
import com.minestellar.core.util.ItemBlockUtil;

public class CoreBlocks
{
	public static Block coreOreBlocks;
	// public static Block coreDecorBlocks;

	public static Block cableBlock;

	public static Block oilFluidBlock;
	public static Fluid oilFluid;

	private static void initBlocks()
	{
		CoreBlocks.coreOreBlocks = new BlockOreCore("core_ore");
		// CoreBlocks.coreDecorBlocks = new BlockDecorCore("core_decor");
		CoreBlocks.cableBlock = new BlockCable("core_cable");

		CoreBlocks.oilFluid = new OilFluid("oil").setBlock(CoreBlocks.oilFluidBlock);
		FluidRegistry.registerFluid(CoreBlocks.oilFluid);
		CoreBlocks.oilFluidBlock = new BlockFluidOil("oil", CoreBlocks.oilFluid, Material.water);
	}

	public static void setHarvestLevels()
	{
	}

	private static void registerBlocks()
	{
		MinestellarCore.registerBlock(CoreBlocks.coreOreBlocks, ItemBlockOres.class);
		// MinestellarCore.registerBlock(CoreBlocks.coreDecorBlocks,
		// ItemBlockDecor.class);

		MinestellarCore.registerBlock(CoreBlocks.cableBlock, ItemBlockUtil.class);

		MinestellarCore.registerBlock(CoreBlocks.oilFluidBlock, ItemBlockUtil.class);

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

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

package com.minestellar.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.blocks.fluid.FluidBlockHydrogen;
import com.minestellar.core.blocks.fluid.FluidBlockNitrogen;
import com.minestellar.core.blocks.fluid.FluidBlockOil;
import com.minestellar.core.blocks.fluid.FluidBlockOxygen;
import com.minestellar.core.blocks.items.ItemBlockCable;
import com.minestellar.core.blocks.items.ItemBlockDecoration;
import com.minestellar.core.blocks.items.ItemBlockOre;
import com.minestellar.core.blocks.items.ItemBlockPipe;
import com.minestellar.core.blocks.machines.Computer;
import com.minestellar.core.blocks.machines.GasSinkMachine;
import com.minestellar.core.blocks.machines.OxygenCollectorMachine;
import com.minestellar.core.blocks.machines.SolarGenerator;
import com.minestellar.core.util.ItemBlockUtil;

import cpw.mods.fml.common.registry.GameRegistry;

public class CoreBlocks {
	public static void init() {
		initBlocks();
		registerBlocks();
		oreDictRegistration();
		setHarvestLevels();
	}

	public static Block coreOreBlocks;
	public static Block coreDecorBlocks;
	public static Block teleportBlock;

	public static Block oxygenCollector;
	public static Block gasSink;
	public static Block solarGenerator;

	public static Block cableBlock;
	public static Block pipeBlock;

	public static Block computer;

	public static Block radioWall;
	public static Block radioHead;

	public static Block oilFluidBlock;
	public static Fluid oilFluid;

	public static Block oxygenFluidBlock;
	public static Fluid oxygenFluid;

	public static Block hydrogenFluidBlock;
	public static Fluid hydrogenFluid;

	public static Block nitrogenFluidBlock;
	public static Fluid nitrogenFluid;

	private static void initBlocks() {
		CoreBlocks.coreOreBlocks = new BlockOre("core_ore");
		CoreBlocks.coreDecorBlocks = new BlockDecoration("core_decor");
		CoreBlocks.teleportBlock = new BlockTeleporter("teleporter");

		CoreBlocks.radioWall = new BlockRadioWall("radio_wall");
		CoreBlocks.radioHead = new BlockRadioHead("radio_head");

		CoreBlocks.cableBlock = new BlockCable("core_cable");
		CoreBlocks.pipeBlock = new BlockPipe("core_pipe");

		CoreBlocks.gasSink = new GasSinkMachine("gas_sink");
		CoreBlocks.oxygenCollector = new OxygenCollectorMachine("oxygen_collector");
		CoreBlocks.solarGenerator = new SolarGenerator("solar_generator");

		CoreBlocks.computer = new Computer("computer");

		CoreBlocks.oilFluid = new FluidBlockOil("oil").setBlock(CoreBlocks.oilFluidBlock);
		FluidRegistry.registerFluid(CoreBlocks.oilFluid);
		CoreBlocks.oilFluidBlock = new BlockFluidOil("oil", CoreBlocks.oilFluid, Material.water);

		CoreBlocks.oxygenFluid = new FluidBlockOxygen("oxygen").setBlock(CoreBlocks.oxygenFluidBlock);
		FluidRegistry.registerFluid(CoreBlocks.oxygenFluid);
		CoreBlocks.oxygenFluidBlock = new BlockFluidOil("oxygen", CoreBlocks.oxygenFluid, Material.water);

		CoreBlocks.hydrogenFluid = new FluidBlockHydrogen("hydrogen").setBlock(CoreBlocks.hydrogenFluidBlock);
		FluidRegistry.registerFluid(CoreBlocks.hydrogenFluid);
		CoreBlocks.hydrogenFluidBlock = new BlockFluidOil("hydrogen", CoreBlocks.hydrogenFluid, Material.water);

		CoreBlocks.nitrogenFluid = new FluidBlockNitrogen("nitrogen").setBlock(CoreBlocks.nitrogenFluidBlock);
		FluidRegistry.registerFluid(CoreBlocks.nitrogenFluid);
		CoreBlocks.nitrogenFluidBlock = new BlockFluidOil("nitrogen", CoreBlocks.nitrogenFluid, Material.water);
	}

	private static void registerBlocks() {
		MinestellarCore.registerBlock(CoreBlocks.coreOreBlocks, ItemBlockOre.class);
		MinestellarCore.registerBlock(CoreBlocks.coreDecorBlocks, ItemBlockDecoration.class);
		MinestellarCore.registerBlock(CoreBlocks.teleportBlock, ItemBlockUtil.class);

		GameRegistry.registerBlock(CoreBlocks.radioWall, "radio_wall");
		GameRegistry.registerBlock(CoreBlocks.radioHead, "radio_head");

		MinestellarCore.registerBlock(CoreBlocks.cableBlock, ItemBlockCable.class);
		MinestellarCore.registerBlock(CoreBlocks.pipeBlock, ItemBlockPipe.class);

		GameRegistry.registerBlock(CoreBlocks.oxygenCollector, "oxygen_collector");
		GameRegistry.registerBlock(CoreBlocks.gasSink, "gas_sink");
		GameRegistry.registerBlock(CoreBlocks.solarGenerator, "solar_generator");
		GameRegistry.registerBlock(CoreBlocks.computer, "computer");

		MinestellarCore.registerBlock(CoreBlocks.oilFluidBlock, ItemBlockUtil.class);
		MinestellarCore.registerBlock(CoreBlocks.oxygenFluidBlock, ItemBlockUtil.class);
		MinestellarCore.registerBlock(CoreBlocks.hydrogenFluidBlock, ItemBlockUtil.class);
		MinestellarCore.registerBlock(CoreBlocks.nitrogenFluidBlock, ItemBlockUtil.class);
	}

	private static void oreDictRegistration() {
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

	private static void setHarvestLevels() {
	}
}

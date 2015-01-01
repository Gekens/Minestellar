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

package com.minestellar.core;

import java.io.File;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.minestellar.core.blocks.CoreBlocks;
import com.minestellar.core.blocks.tileEntities.TileEntityCable;
import com.minestellar.core.entities.EntityZombieCore;
import com.minestellar.core.items.CoreItems;
import com.minestellar.core.proxy.CommonProxyCore;
import com.minestellar.core.recipe.RecipeManagerCore;
import com.minestellar.core.util.ConfigManagerCore;
import com.minestellar.core.util.MinestellarCreativeTab;
import com.minestellar.core.util.MinestellarUtil;
import com.minestellar.core.world.gen.OverworldGenerator;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = MinestellarCore.MODID, name = MinestellarCore.MODNAME, version = Constants.VERSION)
public class MinestellarCore
{
	public static final String MODID = "MinestellarCore";
	public static final String MODNAME = "Minestellar Core";

	public static final String ASSET_PREFIX = "minestellarcore";
	public static final String TEXTURE_PREFIX = MinestellarCore.ASSET_PREFIX + ":";

	public static CreativeTabs stellarBlocksTab;
	public static CreativeTabs stellarItemsTab;

	public static HashMap<String, ItemStack> blocksList = new HashMap<String, ItemStack>();
	public static HashMap<String, ItemStack> itemList = new HashMap<String, ItemStack>();

	@Instance(MinestellarCore.MODID)
	public static MinestellarCore instance = new MinestellarCore();

	@SidedProxy(clientSide = "com.minestellar.core.proxy.ClientProxyCore", serverSide = "com.minestellar.core.proxy.CommonProxyCore")
	public static CommonProxyCore proxy;

	/**
	 * Runs before anything else. Reads/sets up your config, create blocks,
	 * items and register them with the GameRegistry.
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		new ConfigManagerCore(new File(event.getModConfigurationDirectory(), Constants.MOD_NAME + "/core.cfg"));

		CoreBlocks.init();
		CoreItems.init();

		this.proxy.preInit(event);
	}

	/**
	 * Build whatever data structures you care about. And registers recipes.
	 */
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		MinestellarCore.stellarBlocksTab = new MinestellarCreativeTab(CreativeTabs.getNextID(), "MinestellarBlocks", Item.getItemFromBlock(CoreBlocks.coreOreBlocks), 0);
		MinestellarCore.stellarItemsTab = new MinestellarCreativeTab(CreativeTabs.getNextID(), "MinestellarItems", CoreItems.carbonPickaxe, 0);

		RecipeManagerCore.loadRecipes();

		GameRegistry.registerWorldGenerator(new OverworldGenerator(CoreBlocks.coreOreBlocks, 0, 24, 0, 200, 7), 6);
		GameRegistry.registerWorldGenerator(new OverworldGenerator(CoreBlocks.coreOreBlocks, 1, 22, 0, 200, 7), 4);
		GameRegistry.registerWorldGenerator(new OverworldGenerator(CoreBlocks.coreOreBlocks, 2, 12, 0, 200, 3), 4);
		GameRegistry.registerWorldGenerator(new OverworldGenerator(CoreBlocks.coreOreBlocks, 3, 12, 0, 200, 3), 2);
		GameRegistry.registerWorldGenerator(new OverworldGenerator(CoreBlocks.coreOreBlocks, 4, 24, 0, 200, 7), 4);
		GameRegistry.registerWorldGenerator(new OverworldGenerator(CoreBlocks.coreOreBlocks, 5, 22, 0, 200, 7), 4);
		GameRegistry.registerWorldGenerator(new OverworldGenerator(CoreBlocks.coreOreBlocks, 6, 12, 0, 200, 3), 7);

		this.registerTileEntities();
		this.registerCreatures();
		this.registerOtherEntities();

		this.proxy.init(event);
	}

	private void registerOtherEntities()
	{
	}

	private void registerCreatures()
	{
		MinestellarUtil.registerMinestellarCreature(EntityZombieCore.class, "entityZombieCore", -030201, -102030);
	}

	private void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityCable.class, "cable");
	}

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass)
	{
		GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""));
	}

	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}

	/**
	 * Handle interaction with other mods.
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		this.proxy.postInit(event);
	}
}

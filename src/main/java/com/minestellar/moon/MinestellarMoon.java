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

package com.minestellar.moon;

import com.google.common.base.Stopwatch;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.minestellar.core.Constants;
import com.minestellar.core.util.LogHelper;
import com.minestellar.moon.blocks.MoonBlocks;
import com.minestellar.moon.items.MoonItems;
import com.minestellar.moon.proxy.CommonProxyMoon;
import com.minestellar.moon.recipe.RecipeManagerMoon;
import com.minestellar.moon.util.ConfigManagerMoon;
import com.minestellar.moon.world.DimensionMoon;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Mod(modid = MinestellarMoon.MODID, name = MinestellarMoon.MODNAME, version = Constants.VERSION)
public class MinestellarMoon {
	public static final String MODID = "MinestellarMoon";
	public static final String MODNAME = "Minestellar Moon";

	public static final String ASSET_PREFIX = "minestellar_moon";
	public static final String TEXTURE_PREFIX = MinestellarMoon.ASSET_PREFIX + ":";

    public static LogHelper log = new LogHelper(MinestellarMoon.MODID);

	public static HashMap<String, ItemStack> blocksList = new HashMap<String, ItemStack>();
	public static HashMap<String, ItemStack> itemList = new HashMap<String, ItemStack>();

	@Instance(MinestellarMoon.MODID)
	public static MinestellarMoon instance = new MinestellarMoon();

	@SidedProxy(clientSide = "com.minestellar.moon.proxy.ClientProxyMoon", serverSide = "com.minestellar.moon.proxy.CommonProxyMoon")
	public static CommonProxyMoon proxy;

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass) {
		GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""));
	}

	public static void registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
        Stopwatch stopwatch = Stopwatch.createStarted();

		new ConfigManagerMoon(new File(event.getModConfigurationDirectory(), "Minestellar/moon.cfg"));

		MoonBlocks.init();
		MoonItems.init();

		DimensionMoon.init();

		MinestellarMoon.proxy.preInit(event);

        log.info("PreInitialization (Moon) Completed in " + stopwatch.elapsed( TimeUnit.MILLISECONDS ) + " ms.");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
        Stopwatch stopwatch = Stopwatch.createStarted();
		RecipeManagerMoon.loadRecipes();

		this.registerTileEntities();
		this.registerCreatures();
		this.registerOtherEntities();

		MinestellarMoon.proxy.init(event);

        log.info("Initialization (Moon) Completed in " + stopwatch.elapsed( TimeUnit.MILLISECONDS ) + " ms.");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
        Stopwatch stopwatch = Stopwatch.createStarted();

        MinestellarMoon.proxy.postInit(event);

        log.info("PostInitialization (Moon) Completed in " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms.");
	}

	private void registerTileEntities() {
	}

	private void registerCreatures() {
	}

	private void registerOtherEntities() {
	}
}

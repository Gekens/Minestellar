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

import com.minestellar.core.Constants;
import com.minestellar.core.util.MinestellarLog;
import com.minestellar.moon.blocks.MoonBlocks;
import com.minestellar.moon.items.MoonItems;
import com.minestellar.moon.proxy.CommonProxyMoon;
import com.minestellar.moon.recipe.RecipeManagerMoon;
import com.minestellar.moon.util.ConfigManagerMoon;
import com.minestellar.moon.world.DimensionMoon;
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

import java.io.File;
import java.util.HashMap;

@Mod(modid = MinestellarMoon.MODID, name = MinestellarMoon.MODNAME, version = Constants.VERSION)
public class MinestellarMoon {
	public static final String MODID = "MinestellarMoon";
	public static final String MODNAME = "Minestellar Moon";

	public static final String ASSET_PREFIX = "minestellar_moon";
	public static final String TEXTURE_PREFIX = MinestellarMoon.ASSET_PREFIX + ":";

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
		long currTime = System.currentTimeMillis();
		new ConfigManagerMoon(new File(event.getModConfigurationDirectory(), "Minestellar/moon.cfg"));

		MoonBlocks.init();
		MoonItems.init();

		DimensionMoon.init();

		MinestellarMoon.proxy.preInit(event);
        MinestellarLog.info("PreInitialitazion completed in " + (System.currentTimeMillis()-currTime) + " millis.");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
        long currTime = System.currentTimeMillis();
		RecipeManagerMoon.loadRecipes();

		this.registerTileEntities();
		this.registerCreatures();
		this.registerOtherEntities();

		MinestellarMoon.proxy.init(event);
        MinestellarLog.info("Initialitazion completed in " + (System.currentTimeMillis() - currTime) + " millis.");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
        long currTime = System.currentTimeMillis();
		MinestellarMoon.proxy.postInit(event);
        MinestellarLog.info("PostInitialitazion completed in " + (System.currentTimeMillis() - currTime) + " millis.");
	}

	private void registerTileEntities() {
	}

	private void registerCreatures() {
	}

	private void registerOtherEntities() {
	}
}

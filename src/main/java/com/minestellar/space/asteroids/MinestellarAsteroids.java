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

package com.minestellar.space.asteroids;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import com.minestellar.core.Constants;
import com.minestellar.moon.items.MoonItems;
import com.minestellar.space.asteroids.blocks.AsteroidsBlocks;
import com.minestellar.space.asteroids.items.AsteroidsItems;
import com.minestellar.space.asteroids.proxy.CommonProxyAsteroids;
import com.minestellar.space.asteroids.util.ConfigManagerAsteroids;
import com.minestellar.space.asteroids.world.DimensionAsteroids;
import com.minestellar.space.asteroids.world.gen.WorldGeneratorAsteroids;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = MinestellarAsteroids.MODID, name = MinestellarAsteroids.MODNAME, version = Constants.VERSION)
public class MinestellarAsteroids {
	public static final String MODID = "MinestellarAsteroids";
	public static final String MODNAME = "Minestellar Asteroids";

	public static final String ASSET_PREFIX = "minestellar_space_asteroids";
	public static final String TEXTURE_PREFIX = MinestellarAsteroids.ASSET_PREFIX + ":";

	@SidedProxy(clientSide = "com.minestellar.space.asteroids.proxy.ClientProxyAsteroids", serverSide = "com.minestellar.space.asteroids.proxy.CommonProxyAsteroids")
	public static CommonProxyAsteroids proxy;

	private static WorldGeneratorAsteroids asteroidGenerator = new WorldGeneratorAsteroids();

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass) {
		GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""));
	}

	public static void registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		new ConfigManagerAsteroids(new File(event.getModConfigurationDirectory(), Constants.MOD_NAME + "/asteroids.cfg"));

		AsteroidsBlocks.init();
		AsteroidsItems.init();

		GameRegistry.registerWorldGenerator(asteroidGenerator, 1);

		DimensionAsteroids.init();

		MinestellarAsteroids.proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinestellarAsteroids.proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		MinestellarAsteroids.proxy.postInit(event);
	}

	private void registerTileEntities() {
	}

	private void registerCreatures() {
	}

	private void registerOtherEntities() {
	}

}
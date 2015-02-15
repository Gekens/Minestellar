/**
 * Copyright (c) 11/feb/2015 Davide Cossu.
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

package com.minestellar.space;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import com.minestellar.core.Constants;
import com.minestellar.space.blocks.SpaceBlocks;
import com.minestellar.space.proxy.CommonProxySpace;
import com.minestellar.space.util.ConfigManagerSpace;
import com.minestellar.space.world.DimensionSpace;
import com.minestellar.space.world.gen.WorldGeneratorSpace;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = MinestellarSpace.MODID, name = MinestellarSpace.MODNAME, version = Constants.VERSION)
public class MinestellarSpace {

	public static final String MODID = "MinestellarSpace";
	public static final String MODNAME = "Minestellar Space";

	public static final String ASSET_PREFIX = "minestellarspace";
	public static final String TEXTURE_PREFIX = MinestellarSpace.ASSET_PREFIX + ":";
	
	@SidedProxy(clientSide = "com.minestellar.space.proxy.ClientProxySpace", serverSide = "com.minestellar.space.proxy.CommonProxySpace")
	public static CommonProxySpace proxy;
	
	//private static WorldGeneratorSpace gen = new WorldGeneratorSpace();
	
	public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass) {
		GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""));
	}

	public static void registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		new ConfigManagerSpace(new File(event.getModConfigurationDirectory(), Constants.MOD_NAME + "/space.cfg"));
		
		SpaceBlocks.init();
		
		//GameRegistry.registerWorldGenerator(gen, 1);
		
		DimensionSpace.init();
		
		this.proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		this.proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		this.proxy.postInit(event);
	}

	private void registerTileEntities() {
	}

	private void registerCreatures() {
	}

	private void registerOtherEntities() {
	}
	
}
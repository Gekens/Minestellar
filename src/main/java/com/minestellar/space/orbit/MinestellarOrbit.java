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

package com.minestellar.space.orbit;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import com.minestellar.core.Constants;
import com.minestellar.moon.items.MoonItems;
import com.minestellar.space.orbit.blocks.OrbitBlocks;
import com.minestellar.space.orbit.items.OrbitItems;
import com.minestellar.space.orbit.proxy.CommonProxyOrbit;
import com.minestellar.space.orbit.util.ConfigManagerOrbit;
import com.minestellar.space.orbit.world.DimensionOrbit;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = MinestellarOrbit.MODID, name = MinestellarOrbit.MODNAME, version = Constants.VERSION)
public class MinestellarOrbit {
	public static final String MODID = "MinestellarOrbit";
	public static final String MODNAME = "Minestellar Orbit";

	public static final String ASSET_PREFIX = "minestellar_space_orbit";
	public static final String TEXTURE_PREFIX = MinestellarOrbit.ASSET_PREFIX + ":";

	@SidedProxy(clientSide = "com.minestellar.space.orbit.proxy.ClientProxyOrbit", serverSide = "com.minestellar.space.orbit.proxy.CommonProxyOrbit")
	public static CommonProxyOrbit proxy;

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass) {
		GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""));
	}

	public static void registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		new ConfigManagerOrbit(new File(event.getModConfigurationDirectory(), Constants.MOD_NAME + "/orbit.cfg"));

		OrbitBlocks.init();
		OrbitItems.init();

		DimensionOrbit.init();

		MinestellarOrbit.proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinestellarOrbit.proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		MinestellarOrbit.proxy.postInit(event);
	}

	private void registerTileEntities() {
	}

	private void registerCreatures() {
	}

	private void registerOtherEntities() {
	}

}
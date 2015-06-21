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

package com.minestellar.core;

import com.google.common.base.Stopwatch;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.minestellar.api.data.wireless.WirelessDataNetwork;
import com.minestellar.core.blocks.CoreBlocks;
import com.minestellar.core.blocks.tile.*;
import com.minestellar.core.gui.PlanetMover;
import com.minestellar.core.handler.GuiHandler;
import com.minestellar.core.items.CoreItems;
import com.minestellar.core.network.NetworkHandler;
import com.minestellar.core.proxy.CommonProxyCore;
import com.minestellar.core.recipe.RecipeManagerCore;
import com.minestellar.core.util.ConfigManagerCore;
import com.minestellar.core.util.MinestellarCreativeTab;
import com.minestellar.core.world.gen.OverworldGenerator;
import com.minestellar.test.blocks.TestDataBlock;
import com.minestellar.test.blocks.TestDataTE;
import com.minestellar.utils.LogHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Mod(modid = MinestellarCore.MOD_ID, name = MinestellarCore.MOD_NAME, version = Constants.VERSION)
public class MinestellarCore {

	public static final String MOD_ID = "MinestellarCore";
	public static final String MOD_NAME = "Minestellar Core";

	public static final String ASSET_PREFIX = "minestellar_core";
	public static final String TEXTURE_PREFIX = MinestellarCore.ASSET_PREFIX + ":";

	public static CreativeTabs stellarBlocksTab;
	public static CreativeTabs stellarItemsTab;

    public static LogHelper log = new LogHelper(MinestellarCore.MOD_ID);

	public static HashMap<String, ItemStack> blocksList = new HashMap<String, ItemStack>();
	public static HashMap<String, ItemStack> itemList = new HashMap<String, ItemStack>();

    public static ArrayList<PlanetMover> planetMovers = new ArrayList<PlanetMover>();

	@Instance(MinestellarCore.MOD_ID)
	public static MinestellarCore instance = new MinestellarCore();

	@SidedProxy(clientSide = "com.minestellar.core.proxy.ClientProxyCore", serverSide = "com.minestellar.core.proxy.CommonProxyCore")
	public static CommonProxyCore proxy;

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass) {
		GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""));
	}

	public static void registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
        Stopwatch stopwatch = Stopwatch.createStarted();

		new ConfigManagerCore(new File(event.getModConfigurationDirectory(), "Minestellar/blocks.cfg"));

		CoreBlocks.init();
		CoreItems.init();

        GameRegistry.registerBlock(new TestDataBlock("testDataBlock"), "testDataBlock");

		MinestellarCore.proxy.preInit(event);

        log.info("PreInitialization Completed in " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms.");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
        Stopwatch stopwatch = Stopwatch.createStarted();

        NetworkHandler.init();

		MinestellarCore.stellarBlocksTab = new MinestellarCreativeTab(CreativeTabs.getNextID(), "MinestellarBlocks", Item.getItemFromBlock(CoreBlocks.coreOreBlocks), 0);
		MinestellarCore.stellarItemsTab = new MinestellarCreativeTab(CreativeTabs.getNextID(), "MinestellarItems", CoreItems.coreBasicItems, 0);

		GameRegistry.registerWorldGenerator(new OverworldGenerator(CoreBlocks.coreOreBlocks, 0, 24, 0, 200, 7), 6);
		GameRegistry.registerWorldGenerator(new OverworldGenerator(CoreBlocks.coreOreBlocks, 1, 22, 0, 200, 7), 4);
		GameRegistry.registerWorldGenerator(new OverworldGenerator(CoreBlocks.coreOreBlocks, 2, 12, 0, 200, 3), 4);
		GameRegistry.registerWorldGenerator(new OverworldGenerator(CoreBlocks.coreOreBlocks, 3, 12, 0, 200, 3), 2);
		GameRegistry.registerWorldGenerator(new OverworldGenerator(CoreBlocks.coreOreBlocks, 4, 24, 0, 200, 7), 4);
		GameRegistry.registerWorldGenerator(new OverworldGenerator(CoreBlocks.coreOreBlocks, 5, 22, 0, 200, 7), 4);
		GameRegistry.registerWorldGenerator(new OverworldGenerator(CoreBlocks.coreOreBlocks, 6, 12, 0, 200, 3), 7);

		GameRegistry.registerWorldGenerator(new OverworldGenerator(CoreBlocks.oilFluidBlock, 0, 25, 20, 75, 3), 20);

		RecipeManagerCore.loadRecipes();

		this.registerTileEntities();
		this.registerCreatures();
		this.registerOtherEntities();

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

		MinestellarCore.proxy.init(event);

        log.info("Initialization Completed in " + stopwatch.elapsed( TimeUnit.MILLISECONDS ) + " ms.");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
        Stopwatch stopwatch = Stopwatch.createStarted();

        MinestellarCore.proxy.postInit(event);

        log.info("PostInitialization Completed in " + stopwatch.elapsed( TimeUnit.MILLISECONDS ) + " ms.");
	}

	private void registerTileEntities() {
        GameRegistry.registerTileEntity(TestDataTE.class, "testData");
		GameRegistry.registerTileEntity(TileEntityCable.class, "cable");
		GameRegistry.registerTileEntity(TileEntityPipe.class, "pipe");
		GameRegistry.registerTileEntity(TileEntityOxygenCollector.class, "oxygen_collector");
		GameRegistry.registerTileEntity(TileEntitySolarGenerator.class, "solar_generator");
		GameRegistry.registerTileEntity(TileEntityGasSink.class, "gas_sink");
		GameRegistry.registerTileEntity(TileEntityComputer.class, "computer");
		GameRegistry.registerTileEntity(TileEntityRadioAntenna.class, "radio_antenna");
	}

	private void registerCreatures() {
	}

	private void registerOtherEntities() {
	}

    @EventHandler
    public void serverStopping(FMLServerStoppingEvent event){
        WirelessDataNetwork.clearQueue(); //Clears the queue of packets
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event){
        WirelessDataNetwork wirelessDataNetwork = new WirelessDataNetwork();
        wirelessDataNetwork.initTimer(); //Initialises the timer for the wireless network
    }

}

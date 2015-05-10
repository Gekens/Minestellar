package com.minestellar.venus;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import com.minestellar.core.Constants;
import com.minestellar.core.util.MinestellarLog;
import com.minestellar.core.util.MinestellarUtil;
import com.minestellar.venus.blocks.VenusBlocks;
import com.minestellar.venus.entities.EntityEvolvedBlaze;
import com.minestellar.venus.entities.EntityVenusianTNT;
import com.minestellar.venus.entities.EntityVenusianVillager;
import com.minestellar.venus.items.VenusItems;
import com.minestellar.venus.proxy.CommonProxyVenus;
import com.minestellar.venus.util.ConfigManagerVenus;
import com.minestellar.venus.util.RecipeManagerVenus;
import com.minestellar.venus.world.DimensionVenus;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = MinestellarVenus.MODID, name = MinestellarVenus.MODNAME, version = Constants.VERSION)
public class MinestellarVenus {
	public static final String MODID = "MinestellarVenus";
	public static final String MODNAME = "Minestellar Venus";

	public static final String ASSET_PREFIX = "minestellar_venus";
	public static final String TEXTURE_PREFIX = MinestellarVenus.ASSET_PREFIX + ":";

	@SidedProxy(clientSide = "com.minestellar.venus.proxy.ClientProxyVenus", serverSide = "com.minestellar.venus.proxy.CommonProxyVenus")
	public static CommonProxyVenus proxy;

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass) {
		GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""));
	}

	public static void registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		long currTime = System.currentTimeMillis();
		new ConfigManagerVenus(new File(event.getModConfigurationDirectory(), "Minestellar/venus.cfg"));

		VenusBlocks.init();
		VenusItems.init();

		DimensionVenus.init();

		this.proxy.preInit(event);
		MinestellarLog.info("Initialitazion completed in " + (System.currentTimeMillis() - currTime) + " millis.");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		long currTime = System.currentTimeMillis();
		this.registerTileEntities();
		this.registerCreatures();
		this.registerOtherEntities();

		this.proxy.init(event);
		MinestellarLog.info("Initialitazion completed in " + (System.currentTimeMillis() - currTime) + " millis.");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		long currTime = System.currentTimeMillis();
		RecipeManagerVenus.loadRecipes();

		this.proxy.postInit(event);
		MinestellarLog.info("Initialitazion completed in " + (System.currentTimeMillis() - currTime) + " millis.");
	}

	private void registerTileEntities() {
	}

	private void registerCreatures() {
		MinestellarUtil.registerMinestellarCreature(EntityEvolvedBlaze.class, "EvolvedBlaze", -771829, -870131);
		MinestellarUtil.registerMinestellarCreature(EntityVenusianVillager.class, "VenusianVillager", MinestellarUtil.to32BitColor(255, 103, 181, 145), 16167425);
	}

	private void registerOtherEntities() {
		MinestellarUtil.registerMinestellarNonMobEntity(EntityVenusianTNT.class, "VenusianTNT", 150, 1, true);
	}

	@EventHandler
	public static void PreLoad(FMLPreInitializationEvent PreEvent) {
		proxy.registerRenderInfo();
	}
}

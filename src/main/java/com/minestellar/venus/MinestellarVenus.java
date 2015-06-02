package com.minestellar.venus;

import com.google.common.base.Stopwatch;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
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

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Mod(modid = MinestellarVenus.MODID, name = MinestellarVenus.MODNAME, version = Constants.VERSION)
public class MinestellarVenus {
	public static final String MODID = "MinestellarVenus";
	public static final String MODNAME = "Minestellar Venus";

	public static final String ASSET_PREFIX = "minestellar_venus";
	public static final String TEXTURE_PREFIX = MinestellarVenus.ASSET_PREFIX + ":";

    LogHelper log = new LogHelper(MinestellarVenus.MODID);

	public static HashMap<String, ItemStack> blocksList = new HashMap<String, ItemStack>();
	public static HashMap<String, ItemStack> itemList = new HashMap<String, ItemStack>();

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
        Stopwatch stopwatch = Stopwatch.createStarted();

		new ConfigManagerVenus(new File(event.getModConfigurationDirectory(), "Minestellar/venus.cfg"));

		VenusBlocks.init();
		VenusItems.init();

		DimensionVenus.init();

		proxy.preInit(event);

        log.info("PreInitialization (Venus) Completed in " + stopwatch.elapsed( TimeUnit.MILLISECONDS ) + " ms.");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
        Stopwatch stopwatch = Stopwatch.createStarted();

		this.registerTileEntities();
		this.registerCreatures();
		this.registerOtherEntities();

		proxy.init(event);

        log.info("Initialization (Venus) Completed in " + stopwatch.elapsed( TimeUnit.MILLISECONDS ) + " ms.");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
        Stopwatch stopwatch = Stopwatch.createStarted();

		RecipeManagerVenus.loadRecipes();

		proxy.postInit(event);

        log.info("PostInitialization (Venus) Completed in " + stopwatch.elapsed( TimeUnit.MILLISECONDS ) + " ms.");
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

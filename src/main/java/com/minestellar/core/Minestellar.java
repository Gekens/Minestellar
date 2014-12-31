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
import com.minestellar.core.items.CoreItems;
import com.minestellar.core.proxy.CommonProxyCore;
import com.minestellar.core.recipe.RecipeManagerCore;
import com.minestellar.core.util.ConfigManagerCore;
import com.minestellar.core.util.MinestellarTab;
import com.minestellar.core.util.ThreadVersionCheck;
import com.minestellar.core.wgen.OverworldGenerator;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Minestellar.MOD_ID_CORE, name = Minestellar.MOD_NAME_CORE, version = Constants.VERSION)
public class Minestellar
{
	public static CreativeTabs stellarBlocksTab;
	public static CreativeTabs stellarItemsTab;

	public static HashMap<String, ItemStack> itemList = new HashMap<String, ItemStack>();
	public static HashMap<String, ItemStack> blocksList = new HashMap<String, ItemStack>();

	public static final String MOD_ID_CORE = "MSCore";
	public static final String MOD_NAME_CORE = "Minestellar Core";

	public static final String ASSET_PREFIX = "stellarcore";
	public static final String TEXTURE_PREFIX = Minestellar.ASSET_PREFIX + ":";

	@SidedProxy(clientSide = "com.minestellar.core.proxy.ClientProxyCore", serverSide = "com.minestellar.core.proxy.CommonProxyCore")
	public static CommonProxyCore proxy;

	@Instance(Minestellar.MOD_ID_CORE)
	public static Minestellar instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		new ConfigManagerCore(new File(event.getModConfigurationDirectory(), "Minestellar/core.cfg"));

		CoreBlocks.init();
		CoreItems.init();

		Minestellar.proxy.preInit(event);
	}

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass)
	{
		GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""));
	}

	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		Minestellar.stellarBlocksTab = new MinestellarTab(CreativeTabs.getNextID(), "MinestellarBlocks", Item.getItemFromBlock(CoreBlocks.coreOreBlocks), 1);
		Minestellar.stellarItemsTab = new MinestellarTab(CreativeTabs.getNextID(), "MinestellarItems", CoreItems.titaniumPickaxe, 0);

		if (ConfigManagerCore.enableOverworldOreGen == true)
		{
			GameRegistry.registerWorldGenerator(new OverworldGenerator(CoreBlocks.coreOreBlocks, 0, 24, 0, 75, 7), 4);
			GameRegistry.registerWorldGenerator(new OverworldGenerator(CoreBlocks.coreOreBlocks, 1, 22, 0, 60, 7), 4);
			GameRegistry.registerWorldGenerator(new OverworldGenerator(CoreBlocks.coreOreBlocks, 2, 12, 0, 45, 3), 2);
			GameRegistry.registerWorldGenerator(new OverworldGenerator(CoreBlocks.coreOreBlocks, 3, 12, 0, 45, 3), 3);
			GameRegistry.registerWorldGenerator(new OverworldGenerator(CoreBlocks.oilFluidBlock, 2, 17, 0, 45, 17), 17);
		}

		this.registerTileEntities();
		this.registerCreatures();
		this.registerOtherEntities();

		Minestellar.proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		RecipeManagerCore.loadRecipes();

		Minestellar.proxy.postInit(event);
	}

	private void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityCable.class, "cable");
	}

	private void registerCreatures()
	{
	}

	private void registerOtherEntities()
	{
	}

	@EventHandler
	public static void PreLoad(FMLPreInitializationEvent PreEvent)
	{
		proxy.registerRenderInfo();
	}

	@EventHandler
	public void serverInit(FMLServerStartedEvent event)
	{
		ThreadVersionCheck.startCheck();
	}
}

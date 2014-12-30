package com.minestellar.moon;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import com.minestellar.core.Constants;
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
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = MoonCore.MOD_ID_MOON, name = MoonCore.MOD_NAME_MOON, version = Constants.VERSION)
public class MoonCore 
{
	public static final String MOD_ID_MOON = "MSMoon";
	public static final String MOD_NAME_MOON = "Minestellar Moon";
	
    public static final String ASSET_PREFIX = "stellarmoon";
    public static final String TEXTURE_PREFIX = MoonCore.ASSET_PREFIX + ":";
    
	@SidedProxy(clientSide = "com.minestellar.moon.proxy.ClientProxyMoon", serverSide = "com.minestellar.core.proxy.CommonProxyMoon")
	public static CommonProxyMoon proxy;
	
	@Instance(MoonCore.MOD_ID_MOON)
	public static MoonCore instance;
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
		new ConfigManagerMoon(new File(event.getModConfigurationDirectory(), "Minestellar/moon.cfg"));

		MoonBlocks.init();		
		MoonItems.init();

		DimensionMoon.registerWorldProvider();
		DimensionMoon.registerDimensions();
		
        this.proxy.preInit(event);
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
		this.registerTileEntities();
		this.registerCreatures();
		this.registerOtherEntities();
		
        this.proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
    	RecipeManagerMoon.loadRecipes();
    	
        this.proxy.postInit(event);
    }
    
	private void registerTileEntities()
	{
	}
	
	private void registerCreatures()
	{
	}

	private void registerOtherEntities()
	{
	}
    
    @EventHandler
    public void serverInit(FMLServerStartedEvent event)
    {
    }
}

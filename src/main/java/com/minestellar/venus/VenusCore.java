package com.minestellar.venus;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import com.minestellar.core.Constants;
import com.minestellar.core.util.MinestellarUtil;
import com.minestellar.venus.blocks.VenusBlocks;
import com.minestellar.venus.entities.EntityEvolvedBlaze;
import com.minestellar.venus.entities.EntityVenusianTNT;
import com.minestellar.venus.entities.EntityVenusianVillager;
import com.minestellar.venus.items.VenusItems;
import com.minestellar.venus.proxy.CommonProxyVenus;
import com.minestellar.venus.recipe.RecipeManagerVenus;
import com.minestellar.venus.util.ConfigManagerVenus;
import com.minestellar.venus.world.DimensionVenus;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = VenusCore.MOD_ID_VENUS, name = VenusCore.MOD_NAME_VENUS, version = Constants.VERSION)
public class VenusCore 
{
	public static final String MOD_ID_VENUS = "MSVenus";
	public static final String MOD_NAME_VENUS = "Minestellar Venus";
	
    public static final String ASSET_PREFIX = "stellarvenus";
    public static final String TEXTURE_PREFIX = VenusCore.ASSET_PREFIX + ":";

	@SidedProxy(clientSide = "com.minestellar.venus.proxy.ClientProxyVenus", serverSide = "com.minestellar.venus.proxy.CommonProxyVenus")
	public static CommonProxyVenus proxy;
	
	@Instance(VenusCore.MOD_ID_VENUS)
	public static VenusCore instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		new ConfigManagerVenus(new File(event.getModConfigurationDirectory(), "Minestellar/venus.cfg"));

		VenusBlocks.init();		
		VenusItems.init();
		
		DimensionVenus.registerWorldProvider();
		DimensionVenus.registerDimensions();

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
//		CompressorRecipes.addShapelessRecipe(new ItemStack(VenusItems.venusBasicItem, 2, 5), new ItemStack(VenusItems.venusBasicItem, 1, 0));
//		CompressorRecipes.addShapelessRecipe(new ItemStack(VenusItems.venusBasicItem, 1, 5), new ItemStack(VenusItems.venusBasicItem, 1, 1));
//		CompressorRecipes.addShapelessRecipe(new ItemStack(VenusItems.venusBasicItem, 1, 6), new ItemStack(VenusItems.venusBasicItem, 1, 2));
//		CompressorRecipes.addShapelessRecipe(new ItemStack(VenusItems.venusBasicItem, 1, 7), new ItemStack(VenusItems.venusBasicItem, 1, 3));
//		CompressorRecipes.addShapelessRecipe(new ItemStack(VenusItems.venusBasicItem, 3, 8), new ItemStack(VenusItems.venusBasicItem, 1, 5), new ItemStack(VenusItems.venusBasicItem, 1, 6), new ItemStack(VenusItems.venusBasicItem, 1, 7));
		  
		this.registerTileEntities();
		this.registerCreatures();
		this.registerOtherEntities();
		
        this.proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
    	RecipeManagerVenus.loadRecipes();
    	
        this.proxy.postInit(event);
    }
    
	private void registerTileEntities()
	{
	}
	
	private void registerCreatures()
	{
		MinestellarUtil.registerSpaceCreature(EntityEvolvedBlaze.class, "EvolvedBlaze", -771829, -870131);
		MinestellarUtil.registerSpaceCreature(EntityVenusianVillager.class, "VenusianVillager", MinestellarUtil.to32BitColor(255, 103, 181, 145), 16167425);
	}

	private void registerOtherEntities()
	{
		MinestellarUtil.registerSpaceNonMobEntity(EntityVenusianTNT.class, "VenusianTNT", 150, 1, true);
	}
    
	@EventHandler
	public static void PreLoad(FMLPreInitializationEvent PreEvent) 
	{
		proxy.registerRenderInfo();
	}
}

package com.minestellar.core.proxy;

import net.minecraft.item.EnumRarity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.EnumHelper;

import com.minestellar.core.util.tick.TickHandlerClient;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxyCore extends CommonProxyCore
{
	public static EnumRarity stellarItem = EnumHelper.addRarity("MinestellarRarity", EnumChatFormatting.BLUE, "Minestellar");
	
	private static int renderIndexTitaniumArmor;
	
    @Override
    public void preInit(FMLPreInitializationEvent event) 
    {
    	ClientProxyCore.renderIndexTitaniumArmor = RenderingRegistry.addNewArmourRendererPrefix("titanium");
    	 
        super.preInit(event);
    }
    
    @Override
    public int getTitaniumArmorRenderIndex()
    {
        return ClientProxyCore.renderIndexTitaniumArmor;
    }

    public static void registerEntityRenderers()
    {   
    }
    
    @Override
    public void init(FMLInitializationEvent event) 
    {
    	FMLCommonHandler.instance().bus().register(new TickHandlerClient());
    	
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) 
    {
    	ClientProxyCore.registerEntityRenderers();
        super.postInit(event);
    }
    
    public static void registerHandlers()
    {
        TickHandlerClient tickHandlerClient = new TickHandlerClient();
    }
}

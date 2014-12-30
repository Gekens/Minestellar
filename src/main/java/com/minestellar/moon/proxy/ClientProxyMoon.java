package com.minestellar.moon.proxy;

import com.minestellar.core.util.tick.TickHandlerClient;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxyMoon extends CommonProxyMoon
{
    @Override
    public void preInit(FMLPreInitializationEvent event) 
    { 	 
        super.preInit(event);
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
    	ClientProxyMoon.registerEntityRenderers();
        super.postInit(event);
    }
    
    public static void registerHandlers()
    {
        TickHandlerClient tickHandlerClient = new TickHandlerClient();
    }
}

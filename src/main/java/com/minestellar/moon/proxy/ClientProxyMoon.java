package com.minestellar.moon.proxy;

import java.util.Random;

import net.minecraft.entity.EntityList;

import com.minestellar.core.util.TickHandlerClient;
import com.minestellar.moon.MoonCore;
import com.minestellar.moon.entities.EntityMoonZombie;
import com.minestellar.moon.entities.render.RenderMoonZombie;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ClientProxyMoon extends CommonProxyMoon
{
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
	}

	public static void registerEntityRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityMoonZombie.class, new RenderMoonZombie());
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

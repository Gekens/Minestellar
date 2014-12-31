package com.minestellar.core.proxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.EnumHelper;

import com.minestellar.core.blocks.tileEntities.TileEntityCable;
import com.minestellar.core.particles.EntityCoreOilDripFX;
import com.minestellar.core.render.TileEntityRenderCable;
import com.minestellar.core.util.TickHandlerClient;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxyCore extends CommonProxyCore
{
	private static Minecraft mc = FMLClientHandler.instance().getClient();

	public static EnumRarity stellarItem = EnumHelper.addRarity("MinestellarRarity", EnumChatFormatting.RED, "MinestellarCore");

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
	
	public static void registerTileEntityRenders(){
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCable.class, new TileEntityRenderCable());
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		FMLCommonHandler.instance().bus().register(new TickHandlerClient());

		registerTileEntityRenders();
		
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		ClientProxyCore.registerEntityRenderers();

		super.postInit(event);
	}

	@Override
	public void registerRenderInfo()
	{
	}

	@Override
	public int getBlockRender(Block block)
	{
		return -1;
	}

	public static void registerHandlers()
	{
		TickHandlerClient tickHandlerClient = new TickHandlerClient();
	}

	@Override
	public void spawnParticle(String string, double x, double y, double z)
	{
		EntityFX entityfx = null;

		if (string == "oilDrip")
		{
			entityfx = new EntityCoreOilDripFX(mc.theWorld, x, y, z, Material.water);
		}
		mc.effectRenderer.addEffect(entityfx);
	}
}

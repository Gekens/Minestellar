/**
 * Copyright (c) 31/dic/2014 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.core.proxy;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.EnumHelper;

import com.minestellar.core.blocks.tileEntities.TileEntityCable;
import com.minestellar.core.blocks.tileEntities.TileEntityPipe;
import com.minestellar.core.entities.EntityZombieCore;
import com.minestellar.core.entities.render.RenderZombieCore;
import com.minestellar.core.particles.EntityCoreOilDripFX;
import com.minestellar.core.render.TileEntityRenderCable;
import com.minestellar.core.render.TileEntityRenderPipe;
import com.minestellar.core.util.tick.TickHandlerClient;

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
	public static ArrayList<SoundPoolEntry> newMusic = new ArrayList<SoundPoolEntry>();
	public static EnumRarity stellarItem = EnumHelper.addRarity("MinestellarRarity", EnumChatFormatting.RED, "MinestellarCore");

	private static int renderIndexCarbonArmor;

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		ClientProxyCore.renderIndexCarbonArmor = RenderingRegistry.addNewArmourRendererPrefix("carbon");

		super.preInit(event);
	}

	@Override
	public int getCarbonArmorRenderIndex()
	{
		return ClientProxyCore.renderIndexCarbonArmor;
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
		ClientProxyCore.registerTileEntityRenders();

		super.postInit(event);
	}

	private static void registerEntityRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieCore.class, new RenderZombieCore());
	}

	public static void registerTileEntityRenders()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCable.class, new TileEntityRenderCable());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPipe.class, new TileEntityRenderPipe());
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

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

package com.minestellar.moon.proxy;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundPoolEntry;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxyMoon extends CommonProxyMoon
{
	private static Minecraft mc = FMLClientHandler.instance().getClient();
	public static ArrayList<SoundPoolEntry> newMusic = new ArrayList<SoundPoolEntry>();

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		ClientProxyMoon.registerEntityRenderers();
		ClientProxyMoon.registerTileEntityRenders();

		super.postInit(event);
	}

	private static void registerEntityRenderers()
	{
	}

	public static void registerTileEntityRenders()
	{
	}

	public static void registerHandlers()
	{
	}

	@Override
	public void spawnParticle(String string, double x, double y, double z)
	{
	}
}

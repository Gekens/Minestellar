/**
 * Copyright (c) 04/January/2015 Davide Cossu & Matthew Albrecht.
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

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;

import com.minestellar.api.world.IMinestellarWorldProvider;
import com.minestellar.core.world.CloudRenderer;
import com.minestellar.moon.MinestellarMoon;
import com.minestellar.moon.entity.EntityFootstep;
import com.minestellar.moon.render.entity.EntityFootstepRender;
import com.minestellar.moon.util.ConfigManagerMoon;
import com.minestellar.moon.world.SkyRendererMoon;
import com.minestellar.moon.world.WorldProviderMoon;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxyMoon extends CommonProxyMoon {
	private static Minecraft mc = FMLClientHandler.instance().getClient();
	private final HashMap<EntityPlayer, EntityFootstep> footsteps = new HashMap<EntityPlayer, EntityFootstep>();
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		FMLCommonHandler.instance().bus().register(new TickHandlerClient());
		FMLCommonHandler.instance().bus().register(new TickHandlerPlayer());

		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		this.registerTileEntityRenders();
		this.registerEntityRenderers();
		super.postInit(event);
	}

	private void registerTileEntityRenders() {
	}

	private void registerEntityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityFootstep.class, new EntityFootstepRender());
	}

	@Override
	public void spawnParticle(String string, double x, double y, double z) {
	}
	
	@Override
	public void spawnFootprint(EntityPlayer player) {
        if (player.fallDistance == 0 && !player.isInWater() && !player.worldObj.isAirBlock((int) player.posX, (int) player.boundingBox.minY - 1, (int) player.posZ)) {
            if ((this.footsteps.containsKey(player) && this.footsteps.get(player).getDistanceToEntity(player) > 1.4) || !this.footsteps.containsKey(player)) {
                EntityFootstep footstep = new EntityFootstep(player);
                player.worldObj.spawnEntityInWorld(footstep);
                this.footsteps.put(player, footstep);
            }
        }
    }

	public static class TickHandlerPlayer{
		@SideOnly(Side.CLIENT)
		@SubscribeEvent
		public void onPlayerTick(TickEvent.PlayerTickEvent e){
			if(e.player.dimension == ConfigManagerMoon.idDimensionMoon)
				MinestellarMoon.proxy.spawnFootprint(e.player);
		}
	}
	
	public static class TickHandlerClient {
		@SideOnly(Side.CLIENT)
		@SubscribeEvent
		public void onClientTick(ClientTickEvent event) {
			final Minecraft minecraft = FMLClientHandler.instance().getClient();

			final WorldClient world = minecraft.theWorld;

			if (world != null) {
				if (world.provider instanceof WorldProviderMoon) {
					if (world.provider.getSkyRenderer() == null) {
						world.provider.setSkyRenderer(new SkyRendererMoon((IMinestellarWorldProvider) world.provider));
					}

					if (world.provider.getCloudRenderer() == null) {
						world.provider.setCloudRenderer(new CloudRenderer());
					}
				}
			}
		}
	}
}

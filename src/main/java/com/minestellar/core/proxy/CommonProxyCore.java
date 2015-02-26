/**
 * Copyright (c) 22/Feb/2015 Davide Cossu & Matthew Albrecht.
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

import com.minestellar.core.util.WorldUtil;
import com.minestellar.moon.util.ConfigManagerMoon;
import com.minestellar.space.asteroids.util.ConfigManagerAsteroids;
import com.minestellar.space.orbit.util.ConfigManagerOrbit;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class CommonProxyCore {
	public void preInit(FMLPreInitializationEvent event) {
	}

	public void init(FMLInitializationEvent event) {
		FMLCommonHandler.instance().bus().register(new TickHandler());
	}

	public void postInit(FMLPostInitializationEvent event) {
	}

	public void spawnParticle(String string, double x, double y, double z) {
	}

	public int getCarbonArmorRenderIndex() {
		return 0;
	}

	public void spawnFootprint(EntityPlayer player) {
	}

	public void onUpdate() {
	}
	
	public static class TickHandler{
		@SubscribeEvent
		public void onTick(PlayerTickEvent event){
			if(event.player.dimension == ConfigManagerAsteroids.idDimensionAsteroids ||
				event.player.dimension == ConfigManagerOrbit.idDimensionOrbit ||
				event.player.dimension == ConfigManagerMoon.idDimensionMoon){
				double g = WorldUtil.getGravityForEntity(event.player);
				if(!event.player.onGround && event.player.motionY >= 0.1){
					event.player.motionY = event.player.motionY * g * 0.32;
					if (event.player.isSprinting()){
			            float f = event.player.rotationYaw * 0.017453292F;
			            event.player.motionX -= (double)(MathHelper.sin(f) * 0.2F);
			            event.player.motionZ += (double)(MathHelper.cos(f) * 0.2F);
			        }
					event.player.velocityChanged = true;
				}
			}
		}
	}
}
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

import com.minestellar.moon.util.ConfigManagerMoon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class CommonProxyMoon {
	public void preInit(FMLPreInitializationEvent event) {
	}

	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new JumpHandlerCommon());
		//MinecraftForge.EVENT_BUS.register(new PlayerTickHandlerCommon());
		FMLCommonHandler.instance().bus().register(new PlayerTickHandlerCommon());
	}

	public void postInit(FMLPostInitializationEvent event) {
	}

	public void spawnParticle(String string, double x, double y, double z) {
	}

	public static class JumpHandlerCommon{
		@SubscribeEvent
		public void onLivingJumpEvent(LivingJumpEvent event){
			if(event.entityLiving instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer) event.entityLiving;
				if(player.dimension == ConfigManagerMoon.idDimensionMoon){
					double addY = 0.5D; // change to the entity's Y motion.
					player.addVelocity(0, addY, 0);
					player.velocityChanged = true;
					System.out.println("Motion: " + player.motionY);
				}
			}	
		}
	}

	public static class PlayerTickHandlerCommon{
		@SubscribeEvent
		public void onPlayerTick(PlayerTickEvent e) {
			if(e.player.dimension == ConfigManagerMoon.idDimensionMoon){
				if(e.player.motionY <= 0){
					double addY = 0.95D; // change to the entity's Y motion.
					e.player.motionY *= addY;
					e.player.velocityChanged = true;
					System.out.println("Motion1: " + e.player.motionY);
				}
			}
		}
	}

}
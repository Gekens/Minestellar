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

import net.minecraft.entity.player.EntityPlayer;

import com.minestellar.core.Constants;
import com.minestellar.core.network.NetworkHandler;
import com.minestellar.core.network.message.MessageLogin;
import com.minestellar.core.network.message.MessageLogout;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public abstract class CommonProxyCore {
	public void preInit(FMLPreInitializationEvent event) {
	}

	public void init(FMLInitializationEvent event) {
		FMLCommonHandler.instance().bus().register(new PlayerInteractor());
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

	public EntityPlayer getClientPlayer(){
		return null;
	}

	public class PlayerInteractor{
		@SubscribeEvent
		public void onLogin(PlayerEvent.PlayerLoggedInEvent event){
			NetworkHandler.sendToServer(new MessageLogin(Constants.runTimer));
			System.out.println(event.player.getDisplayName());
		}

		@SubscribeEvent
		public void onLogout(PlayerEvent.PlayerLoggedOutEvent event){ //Log-out isn't working. I think it loggs out too soon.
			NetworkHandler.sendToServer(new MessageLogout("state.txt"));
			System.out.println(event.player.getDisplayName());
		}
	}

}
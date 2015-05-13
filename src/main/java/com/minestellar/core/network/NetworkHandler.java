/**
 * Copyright (c) 11/apr/2015 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.core.network;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.network.message.MessageAntennaOnline;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayerMP;

public class NetworkHandler {
	private static SimpleNetworkWrapper INSTANCE;

	public static void init() {
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(MinestellarCore.MOD_ID);
        INSTANCE.registerMessage(MessageAntennaOnline.class, MessageAntennaOnline.class, 0, Side.SERVER);
	}

	public static void sendToServer(IMessage message) {
		INSTANCE.sendToServer(message);
	}

	public static void sendTo(IMessage message, EntityPlayerMP player) {
		INSTANCE.sendTo(message, player);
	}

	public static void sendToAllAround(IMessage message, TargetPoint point) {
		INSTANCE.sendToAllAround(message, point);
	}

	public static void sendToAll(IMessage message) {
		INSTANCE.sendToAll(message);
	}

	public static void sendToDimension(IMessage message, int dimensionId) {
		INSTANCE.sendToDimension(message, dimensionId);
	}
}

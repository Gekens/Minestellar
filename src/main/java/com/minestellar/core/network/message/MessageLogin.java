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

package com.minestellar.core.network.message;

import com.minestellar.core.handler.FileHandler;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class MessageLogin extends MessageBase<MessageLogin>{

	private boolean value;
	
	public MessageLogin(){}
	
	public MessageLogin(boolean b){
		this.value = b;
		System.out.println("Initializing with " + value);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.value = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(value);
	}

	@Override
	public void handleClientSide(MessageLogin message, EntityPlayer player) {
	}

	@Override
	public void handleServerSide(MessageLogin message, EntityPlayer player) {
		FileHandler.writeToFile("state.txt", message.value ?  "true" : "false");
	}

}
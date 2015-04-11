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

import com.minestellar.core.Constants;
import com.minestellar.core.handler.FileHandler;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class MessageLogout extends MessageBase<MessageLogout>{

	private String fileName;
	private boolean state;
	
	public MessageLogout(){}
	
	public MessageLogout(String fileName){
		this.fileName = fileName;
	}
	
	@Override
	public void fromBytes(ByteBuf buf){
		this.fileName = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, fileName);
	}

	@Override
	public void handleClientSide(MessageLogout message, EntityPlayer player) {
	}

	@Override
	public void handleServerSide(MessageLogout message, EntityPlayer player) {
		message.state = FileHandler.readFromFile(message.fileName).equals("true") ? true : false;
		Constants.runTimer = message.state;
	}

}
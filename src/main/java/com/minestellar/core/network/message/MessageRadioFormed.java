/**
 * Copyright (c) 26/apr/2015 Davide Cossu & Matthew Albrecht.
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

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class MessageRadioFormed extends MessageBase<MessageRadioFormed>{

	private int x, y, z;
	
	public MessageRadioFormed(){}
	
	public MessageRadioFormed(int x, int y, int z){
		this.x = x; this.y = y; this.z = z;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}

	@Override
	public void handleClientSide(MessageRadioFormed message, EntityPlayer player) {
		//NO-OP
	}

	@Override
	public void handleServerSide(MessageRadioFormed message, EntityPlayer player) {
		player.worldObj.setBlockMetadataWithNotify(message.x, message.y, message.z, 1, 1|2);
	}

}
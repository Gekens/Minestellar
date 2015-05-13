/**
 * Copyright (c) 11/05/15 Davide Cossu & Matthew Albrecht.
 * <p/>
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 * <p/>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, see <http://www.gnu.org/licenses>.
 */

package com.minestellar.core.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class MessageAntennaOnline extends MessageBase<MessageAntennaOnline>{

    private boolean isOnline;
    private int x, y, z;

    /**
     * We keep this to prevent NPEs on Client calls of this class
     * <p>We'll never use this constructor</p>
     */

    public MessageAntennaOnline(){}

    public MessageAntennaOnline(boolean isOnline, int x, int y, int z){
        this.isOnline = isOnline;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void fromBytes(ByteBuf buf){
        this.isOnline = buf.readBoolean();
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf){
        buf.writeBoolean(isOnline);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

    @Override
    public void handleClientSide(MessageAntennaOnline message, EntityPlayer player){
        // NO-OP
    }

    @Override
    public void handleServerSide(MessageAntennaOnline message, EntityPlayer player){
        if(message.isOnline){
            player.worldObj.setBlockMetadataWithNotify(message.x, message.y, message.z, 1, 1 | 2);
        }else{
            player.worldObj.setBlockMetadataWithNotify(message.x, message.y, message.z, 0, 1 | 2);
        }
    }

}

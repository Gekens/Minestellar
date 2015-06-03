/**
 * Copyright (c) 03/06/15 Davide Cossu & Matthew Albrecht.
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

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import com.minestellar.core.handler.PlanetKnowledgeHandler;

public class MessageSyncKnowledge extends MessageBase<MessageSyncKnowledge>{

    private NBTTagCompound data;

    public MessageSyncKnowledge(){}

    public MessageSyncKnowledge(EntityPlayer player){
        data = new NBTTagCompound();
        PlanetKnowledgeHandler.get(player).saveNBTData(data);
    }

    @Override
    public void handleClientSide(MessageSyncKnowledge message, EntityPlayer player){
        //PlanetKnowledgeHandler.register(player);
        //player.getExtendedProperties(PlanetKnowledgeHandler.PLANET_KNOWLEDGE).saveNBTData(data);
    }

    @Override
    public void handleServerSide(MessageSyncKnowledge message, EntityPlayer player){
        //PlanetKnowledgeHandler.register(player);
        //player.getExtendedProperties(PlanetKnowledgeHandler.PLANET_KNOWLEDGE).saveNBTData(data);
    }

    @Override
    public void fromBytes(ByteBuf buf){
        data = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf){
        ByteBufUtils.writeTag(buf, data);
    }

}
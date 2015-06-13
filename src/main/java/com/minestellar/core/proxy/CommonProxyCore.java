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

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import com.minestellar.core.Constants;
import com.minestellar.core.gui.ComputerGui;
import com.minestellar.core.handler.FileHandler;
import com.minestellar.core.handler.PlanetKnowledgeHandler;
import com.minestellar.core.network.NetworkHandler;
import com.minestellar.core.network.message.MessageSyncKnowledge;

public abstract class CommonProxyCore {
    public void preInit(FMLPreInitializationEvent event) {
    }

    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new PlayerKnowledge());
        FMLCommonHandler.instance().bus().register(new PlayerTicker());
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

    public EntityPlayer getClientPlayer() {
        return null;
    }

    public class PlayerKnowledge{
        @SubscribeEvent
        public void onEntityConstructing(EntityEvent.EntityConstructing event){
            if (event.entity instanceof EntityPlayer && PlanetKnowledgeHandler.get((EntityPlayer) event.entity) == null)
                PlanetKnowledgeHandler.register((EntityPlayer) event.entity);
        }

        @SubscribeEvent
        public void onClonePlayer(PlayerEvent.Clone event){ //In case the player dies
            NBTTagCompound compound = new NBTTagCompound();
            PlanetKnowledgeHandler.get(event.original).saveNBTData(compound);
            PlanetKnowledgeHandler.get(event.entityPlayer).loadNBTData(compound);
        }

        @SubscribeEvent
        public void onEntityJoinWorld(EntityJoinWorldEvent event){
            if(event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote){
                NetworkHandler.sendTo(new MessageSyncKnowledge((EntityPlayer) event.entity), (EntityPlayerMP) event.entity);
            }
        }
    }

    public class PlayerTicker{
        private int temp = 0;
        @SubscribeEvent
        public void onTick(TickEvent.WorldTickEvent event){
            if(FileHandler.readFromFile(Constants.fileName, false).equals("run")){
                if(temp >= 500){
                    temp = 0;
                    ComputerGui.movePlanets();
                }
                temp++;
            }
        }
    }

}
/**
 * Copyright (c) 11/06/15 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.core.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.blocks.BlockRadioAntenna;
import com.minestellar.core.handler.PlanetKnowledgeHandler;

public class ItemBindingCard extends Item{

    public ItemBindingCard(String name){
        this.setUnlocalizedName(name);
        this.setMaxStackSize(1);
    }

    /*
	 * This ensures that the compound won't be null
	 */

    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player){
        if(itemStack.stackTagCompound == null){
            itemStack.setTagCompound(new NBTTagCompound());
        }
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){
        if(world.getBlock(x, y, z) != null && world.getBlock(x, y, z) instanceof BlockRadioAntenna){
            ((BlockRadioAntenna)world.getBlock(x, y, z)).addPlayer(entityPlayer); //Adds the player to the block. That player will start then learning new planets over time.
        }

        if(world.isRemote && entityPlayer.isSneaking()){
            PlanetKnowledgeHandler props = (PlanetKnowledgeHandler)entityPlayer.getExtendedProperties(PlanetKnowledgeHandler.PLANET_KNOWLEDGE);
            props.setAcknowledgedNext();
            MinestellarCore.log.info("Known: " + props.getAcknowledgedPlanets());
        }
        if(world.isRemote && !entityPlayer.isSneaking()){
            PlanetKnowledgeHandler props = (PlanetKnowledgeHandler)entityPlayer.getExtendedProperties(PlanetKnowledgeHandler.PLANET_KNOWLEDGE);
            props.reset();
            MinestellarCore.log.info("Known: " + props.getAcknowledgedPlanets());
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public CreativeTabs getCreativeTab() {
        return MinestellarCore.stellarItemsTab;
    }

}
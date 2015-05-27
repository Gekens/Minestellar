/**
 * Copyright (c) 25/05/15 Davide Cossu & Matthew Albrecht.
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

import com.minestellar.api.data.block.DataTileEntity;
import com.minestellar.api.data.block.IDataConnection;
import com.minestellar.core.MinestellarCore;
import com.minestellar.core.util.NBTHelper;

import java.util.List;

public class ItemMemoryCard extends Item{

    public ItemMemoryCard(String name){
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
        if(entityPlayer.isSneaking()){
            if(world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof IDataConnection){
                NBTHelper.setInteger(itemStack, "xCoord", x);
                NBTHelper.setInteger(itemStack, "yCoord", y);
                NBTHelper.setInteger(itemStack, "zCoord", z);

                return true;
            }
        }else{
            if(world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof IDataConnection){
                ((DataTileEntity)world.getTileEntity(x, y, z)).setCoordinates(itemStack);
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4){
        list.add("X: " + NBTHelper.getInt(stack, "xCoord"));
        list.add("Y: " + NBTHelper.getInt(stack, "yCoord"));
        list.add("Z: " + NBTHelper.getInt(stack, "zCoord"));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public CreativeTabs getCreativeTab() {
        return MinestellarCore.stellarItemsTab;
    }

}
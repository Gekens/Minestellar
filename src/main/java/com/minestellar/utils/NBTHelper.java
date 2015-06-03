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

package com.minestellar.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class NBTHelper{

    public static boolean hasTag(ItemStack itemStack, String keyName){
        return itemStack != null && itemStack.stackTagCompound != null && itemStack.stackTagCompound.hasKey(keyName);
    }

    public static void removeTag(ItemStack itemStack, String keyName){
        if (itemStack.stackTagCompound != null){
            itemStack.stackTagCompound.removeTag(keyName);
        }
    }

    /**
     * Initializes the NBT Tag Compound for the given ItemStack if it is null
     *
     * @param itemStack The ItemStack for which its NBT Tag Compound is being checked for initialization
     */
    private static void initNBTTagCompound(ItemStack itemStack){
        if (itemStack.stackTagCompound == null){
            itemStack.setTagCompound(new NBTTagCompound());
        }
    }

    public static void setLong(ItemStack itemStack, String keyName, long keyValue){
        initNBTTagCompound(itemStack);

        itemStack.stackTagCompound.setLong(keyName, keyValue);
    }

    // String
    public static String getString(ItemStack itemStack, String keyName){
        initNBTTagCompound(itemStack);

        if (!itemStack.stackTagCompound.hasKey(keyName)){
            setString(itemStack, keyName, "");
        }

        return itemStack.stackTagCompound.getString(keyName);
    }

    public static void setString(ItemStack itemStack, String keyName, String keyValue){
        initNBTTagCompound(itemStack);

        itemStack.stackTagCompound.setString(keyName, keyValue);
    }

    // boolean
    public static boolean getBoolean(ItemStack itemStack, String keyName){
        initNBTTagCompound(itemStack);

        if (!itemStack.stackTagCompound.hasKey(keyName)){
            setBoolean(itemStack, keyName, false);
        }

        return itemStack.stackTagCompound.getBoolean(keyName);
    }

    public static void setBoolean(ItemStack itemStack, String keyName, boolean keyValue){
        initNBTTagCompound(itemStack);

        itemStack.stackTagCompound.setBoolean(keyName, keyValue);
    }

    // byte
    public static byte getByte(ItemStack itemStack, String keyName){
        initNBTTagCompound(itemStack);

        if (!itemStack.stackTagCompound.hasKey(keyName)){
            setByte(itemStack, keyName, (byte) 0);
        }

        return itemStack.stackTagCompound.getByte(keyName);
    }

    public static void setByte(ItemStack itemStack, String keyName, byte keyValue){
        initNBTTagCompound(itemStack);

        itemStack.stackTagCompound.setByte(keyName, keyValue);
    }

    // short
    public static short getShort(ItemStack itemStack, String keyName){
        initNBTTagCompound(itemStack);

        if (!itemStack.stackTagCompound.hasKey(keyName)){
            setShort(itemStack, keyName, (short) 0);
        }

        return itemStack.stackTagCompound.getShort(keyName);
    }

    public static void setShort(ItemStack itemStack, String keyName, short keyValue){
        initNBTTagCompound(itemStack);

        itemStack.stackTagCompound.setShort(keyName, keyValue);
    }

    // int
    public static int getInt(ItemStack itemStack, String keyName){
        initNBTTagCompound(itemStack);

        if (!itemStack.stackTagCompound.hasKey(keyName)){
            setInteger(itemStack, keyName, 0);
        }

        return itemStack.stackTagCompound.getInteger(keyName);
    }

    public static void setInteger(ItemStack itemStack, String keyName, int keyValue){
        initNBTTagCompound(itemStack);

        itemStack.stackTagCompound.setInteger(keyName, keyValue);
    }

    // long
    public static long getLong(ItemStack itemStack, String keyName){
        initNBTTagCompound(itemStack);

        if (!itemStack.stackTagCompound.hasKey(keyName)){
            setLong(itemStack, keyName, 0);
        }

        return itemStack.stackTagCompound.getLong(keyName);
    }

    // float
    public static float getFloat(ItemStack itemStack, String keyName){
        initNBTTagCompound(itemStack);

        if (!itemStack.stackTagCompound.hasKey(keyName)){
            setFloat(itemStack, keyName, 0);
        }

        return itemStack.stackTagCompound.getFloat(keyName);
    }

    public static void setFloat(ItemStack itemStack, String keyName, float keyValue){
        initNBTTagCompound(itemStack);

        itemStack.stackTagCompound.setFloat(keyName, keyValue);
    }

    // double
    public static double getDouble(ItemStack itemStack, String keyName){
        initNBTTagCompound(itemStack);

        if (!itemStack.stackTagCompound.hasKey(keyName)){
            setDouble(itemStack, keyName, 0);
        }

        return itemStack.stackTagCompound.getDouble(keyName);
    }

    public static void setDouble(ItemStack itemStack, String keyName, double keyValue){
        initNBTTagCompound(itemStack);

        itemStack.stackTagCompound.setDouble(keyName, keyValue);
    }

    // tag list
    public static NBTTagList getTagList(ItemStack itemStack, String keyName, int nbtBaseType){
        initNBTTagCompound(itemStack);

        if (!itemStack.stackTagCompound.hasKey(keyName))
        {
            setTagList(itemStack, keyName, new NBTTagList());
        }

        return itemStack.stackTagCompound.getTagList(keyName, nbtBaseType);
    }

    public static void setTagList(ItemStack itemStack, String keyName, NBTTagList nbtTagList){
        initNBTTagCompound(itemStack);

        itemStack.stackTagCompound.setTag(keyName, nbtTagList);
    }

    // tag compound
    public static NBTTagCompound getTagCompound(ItemStack itemStack, String keyName){
        initNBTTagCompound(itemStack);

        if (!itemStack.stackTagCompound.hasKey(keyName)){
            setTagCompound(itemStack, keyName, new NBTTagCompound());
        }

        return itemStack.stackTagCompound.getCompoundTag(keyName);
    }

    public static void setTagCompound(ItemStack itemStack, String keyName, NBTTagCompound nbtTagCompound){
        initNBTTagCompound(itemStack);

        itemStack.stackTagCompound.setTag(keyName, nbtTagCompound);
    }

}
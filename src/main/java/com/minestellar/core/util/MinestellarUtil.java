/**
 * Copyright (c) 04/January/2015 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.core.util;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.minestellar.core.MinestellarCore;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;

public class MinestellarUtil {
	public static int nextID = 0;

	public static int to32BitColor(int a, int r, int g, int b) {
		a = a << 24;
		r = r << 16;
		g = g << 8;

		return a | r | g | b;
	}

	public static int nextInternalID() {
		MinestellarUtil.nextID++;
		return MinestellarUtil.nextID - 1;
	}

	public static void registerMinestellarCreature(Class<? extends Entity> var0, String var1, int back, int fore) {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			LanguageRegistry.instance().addStringLocalization("entity." + var1 + ".name", MinestellarUtil.translate("entity.minestellar." + var1 + ".name"));
		}

		int newID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(var0, var1, newID, back, fore);
		EntityRegistry.registerModEntity(var0, var1, nextInternalID(), MinestellarCore.instance, 80, 3, true);
	}

	public static void registerMinestellarNonMobEntity(Class<? extends Entity> var0, String var1, int trackingDistance, int updateFreq, boolean sendVel) {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			LanguageRegistry.instance().addStringLocalization("entity." + var1 + ".name", MinestellarUtil.translate("entity.minestellar." + var1 + ".name"));
		}

		EntityRegistry.registerModEntity(var0, var1, nextInternalID(), MinestellarCore.instance, trackingDistance, updateFreq, sendVel);
	}

	public static void registerMinestellarItem(String key, Item item) {
		MinestellarCore.itemList.put(key, new ItemStack(item));
	}

	public static void registerMinestellarItem(String key, Item item, int metadata) {
		MinestellarCore.itemList.put(key, new ItemStack(item, 1, metadata));
	}

	public static void registerMinestellarItem(String key, ItemStack stack) {
		MinestellarCore.itemList.put(key, stack);
	}

	public static void registerMinestellarBlock(String key, Block block) {
		MinestellarCore.blocksList.put(key, new ItemStack(block));
	}

	public static void registerMinestellarBlock(String key, Block block, int metadata) {
		MinestellarCore.blocksList.put(key, new ItemStack(block, 1, metadata));
	}

	public static void registerMinestellarBlock(String key, ItemStack stack) {
		MinestellarCore.blocksList.put(key, stack);
	}

	public static String translate(String key) {
		String result = StatCollector.translateToLocal(key);
		int comment = result.indexOf('#');
		return (comment > 0) ? result.substring(0, comment).trim() : result;
	}

	public static List<String> translateWithSplit(String key) {
		String translated = translate(key);
		int comment = translated.indexOf('#');
		translated = (comment > 0) ? translated.substring(0, comment).trim() : translated;
		return Arrays.asList(translated.split("\\$"));
	}

	public static String translateWithFormat(String key, Object... values) {
		String result = StatCollector.translateToLocalFormatted(key, values);
		int comment = result.indexOf('#');
		return (comment > 0) ? result.substring(0, comment).trim() : result;
	}
}
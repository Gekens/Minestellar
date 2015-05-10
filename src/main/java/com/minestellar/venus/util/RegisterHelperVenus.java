package com.minestellar.venus.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.minestellar.venus.MinestellarVenus;

import cpw.mods.fml.common.registry.GameRegistry;

public class RegisterHelperVenus {
	public static void registerBlock(Block block) {
		GameRegistry.registerBlock(block, MinestellarVenus.MODID + "_" + block.getUnlocalizedName().substring(5));
	}

	public static void registerItem(Item item) {
		GameRegistry.registerItem(item, MinestellarVenus.MODID + "_" + item.getUnlocalizedName().substring(5));
	}
}

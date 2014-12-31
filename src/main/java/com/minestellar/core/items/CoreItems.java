/**
 * Copyright (c) 31/dic/2014 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.core.items;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.FluidContainerRegistry;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.blocks.CoreBlocks;

public class CoreItems
{
	public static Item coreBasicItems;

	public static Item titaniumSword;
	public static Item titaniumPickaxe;
	public static Item titaniumShovel;
	public static Item titaniumAxe;
	public static Item titaniumHoe;

	public static Item titaniumHelmet;
	public static Item titaniumChestplate;
	public static Item titaniumLeggings;
	public static Item titaniumBoots;

	public static Item canisterOil;
	public static Item extractorOil;

	public static Item oilBucket;

	public static ArmorMaterial ARMOR_TITANIUM = EnumHelper.addArmorMaterial("titanium", 30, new int[] { 3, 8, 6, 3 }, 12);
	public static ToolMaterial TOOL_TITANIUM = EnumHelper.addToolMaterial("titanium", 3, 768, 5.0F, 2, 8);

	private static void initItems()
	{
		CoreItems.coreBasicItems = new ItemBasicCore();

		CoreItems.titaniumSword = new ItemSwordCore("titanium_sword", CoreItems.TOOL_TITANIUM);
		CoreItems.titaniumPickaxe = new ItemPickaxeCore("titanium_pickaxe", CoreItems.TOOL_TITANIUM);
		CoreItems.titaniumShovel = new ItemSpadeCore("titanium_shovel", CoreItems.TOOL_TITANIUM);
		CoreItems.titaniumAxe = new ItemAxeCore("titanium_axe", CoreItems.TOOL_TITANIUM);
		CoreItems.titaniumHoe = new ItemHoeCore("titanium_hoe", CoreItems.TOOL_TITANIUM);

		CoreItems.titaniumHelmet = new ItemArmorTitanium("titanium_helmet", CoreItems.ARMOR_TITANIUM, 7, 0);
		CoreItems.titaniumChestplate = new ItemArmorTitanium("titanium_chestplate", CoreItems.ARMOR_TITANIUM, 7, 1);
		CoreItems.titaniumLeggings = new ItemArmorTitanium("titanium_leggings", CoreItems.ARMOR_TITANIUM, 7, 2);
		CoreItems.titaniumBoots = new ItemArmorTitanium("titanium_boots", CoreItems.ARMOR_TITANIUM, 7, 3);

		CoreItems.canisterOil = new ItemCanisterOil("canisterOilPartial");
		CoreItems.extractorOil = new ItemExtractorOil("extractorOil");

		CoreItems.oilBucket = new ItemBucketOil("oil_bucket");
	}

	private static void registerHarvestLevels()
	{
		CoreItems.titaniumPickaxe.setHarvestLevel("pickaxe", 4);
		CoreItems.titaniumAxe.setHarvestLevel("axe", 4);
		CoreItems.titaniumShovel.setHarvestLevel("shovel", 4);
	}

	private static void registerItems()
	{
		MinestellarCore.registerItem(coreBasicItems);

		MinestellarCore.registerItem(titaniumPickaxe);
		MinestellarCore.registerItem(titaniumAxe);
		MinestellarCore.registerItem(titaniumHoe);
		MinestellarCore.registerItem(titaniumShovel);
		MinestellarCore.registerItem(titaniumSword);

		MinestellarCore.registerItem(titaniumHelmet);
		MinestellarCore.registerItem(titaniumChestplate);
		MinestellarCore.registerItem(titaniumLeggings);
		MinestellarCore.registerItem(titaniumBoots);

		MinestellarCore.registerItem(CoreItems.canisterOil);
		MinestellarCore.registerItem(CoreItems.extractorOil);

		MinestellarCore.registerItem(oilBucket);
	}

	public static void oreDictRegistration()
	{
	}

	private static void registerFluidContainer()
	{
		FluidContainerRegistry.registerFluidContainer(CoreBlocks.oilFluid, new ItemStack(CoreItems.oilBucket), new ItemStack(Items.bucket));
	}

	public static void init()
	{
		initItems();
		registerHarvestLevels();
		registerItems();
		oreDictRegistration();
		registerFluidContainer();
	}
}

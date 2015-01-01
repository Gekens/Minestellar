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
import net.minecraftforge.oredict.OreDictionary;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.blocks.CoreBlocks;

public class CoreItems
{
	public static Item coreBasicItems;

	public static Item carbonSword;
	public static Item carbonPickaxe;
	public static Item carbonShovel;
	public static Item carbonAxe;
	public static Item carbonHoe;

	public static Item carbonHelmet;
	public static Item carbonChestplate;
	public static Item carbonLeggings;
	public static Item carbonBoots;

	public static Item canisterOil;
	public static Item extractorOil;

	public static Item oilBucket;

	public static ArmorMaterial ARMOR_CARBON = EnumHelper.addArmorMaterial("carbon", 30, new int[] { 3, 8, 6, 3 }, 12);
	public static ToolMaterial TOOL_CARBON = EnumHelper.addToolMaterial("carbon", 3, 768, 5.0F, 2, 8);

	private static void initItems()
	{
		CoreItems.coreBasicItems = new ItemBasicCore();

		CoreItems.carbonSword = new ItemSwordCore("carbon_sword", CoreItems.TOOL_CARBON);
		CoreItems.carbonPickaxe = new ItemPickaxeCore("carbon_pickaxe", CoreItems.TOOL_CARBON);
		CoreItems.carbonShovel = new ItemSpadeCore("carbon_shovel", CoreItems.TOOL_CARBON);
		CoreItems.carbonAxe = new ItemAxeCore("carbon_axe", CoreItems.TOOL_CARBON);
		CoreItems.carbonHoe = new ItemHoeCore("carbon_hoe", CoreItems.TOOL_CARBON);

		CoreItems.carbonHelmet = new ItemArmorCarbon("carbon_helmet", CoreItems.ARMOR_CARBON, 7, 0);
		CoreItems.carbonChestplate = new ItemArmorCarbon("carbon_chestplate", CoreItems.ARMOR_CARBON, 7, 1);
		CoreItems.carbonLeggings = new ItemArmorCarbon("carbon_leggings", CoreItems.ARMOR_CARBON, 7, 2);
		CoreItems.carbonBoots = new ItemArmorCarbon("carbon_boots", CoreItems.ARMOR_CARBON, 7, 3);

		CoreItems.canisterOil = new ItemCanisterOil("canisterOilPartial");
		CoreItems.extractorOil = new ItemExtractorOil("extractorOil");

		CoreItems.oilBucket = new ItemBucketOil("oil_bucket");
	}

	private static void registerHarvestLevels()
	{
		CoreItems.carbonPickaxe.setHarvestLevel("pickaxe", 4);
		CoreItems.carbonAxe.setHarvestLevel("axe", 4);
		CoreItems.carbonShovel.setHarvestLevel("shovel", 4);
	}

	private static void registerItems()
	{
		MinestellarCore.registerItem(coreBasicItems);

		MinestellarCore.registerItem(carbonPickaxe);
		MinestellarCore.registerItem(carbonAxe);
		MinestellarCore.registerItem(carbonHoe);
		MinestellarCore.registerItem(carbonShovel);
		MinestellarCore.registerItem(carbonSword);

		MinestellarCore.registerItem(carbonHelmet);
		MinestellarCore.registerItem(carbonChestplate);
		MinestellarCore.registerItem(carbonLeggings);
		MinestellarCore.registerItem(carbonBoots);

		MinestellarCore.registerItem(CoreItems.canisterOil);
		MinestellarCore.registerItem(CoreItems.extractorOil);

		MinestellarCore.registerItem(oilBucket);
	}

	public static void oreDictRegistration()
	{
		/* Ingots */
		OreDictionary.registerOre("ingotCopper", new ItemStack(CoreItems.coreBasicItems, 1, 0));
		OreDictionary.registerOre("ingotTin", new ItemStack(CoreItems.coreBasicItems, 1, 1));
		OreDictionary.registerOre("ingotSteel", new ItemStack(CoreItems.coreBasicItems, 1, 2));
		OreDictionary.registerOre("ingotLithium", new ItemStack(CoreItems.coreBasicItems, 1, 3));
		OreDictionary.registerOre("ingotSilicon", new ItemStack(CoreItems.coreBasicItems, 1, 4));
		OreDictionary.registerOre("ingotAluminum", new ItemStack(CoreItems.coreBasicItems, 1, 5));
		OreDictionary.registerOre("ingotTitanium", new ItemStack(CoreItems.coreBasicItems, 1, 6));
		OreDictionary.registerOre("ingotCarbon", new ItemStack(CoreItems.coreBasicItems, 1, 7));

		/* Dust */
		OreDictionary.registerOre("ingotCarbon", new ItemStack(CoreItems.coreBasicItems, 1, 8));

		/* Plates */
		OreDictionary.registerOre("plateCopper", new ItemStack(CoreItems.coreBasicItems, 1, 9));
		OreDictionary.registerOre("plateTin", new ItemStack(CoreItems.coreBasicItems, 1, 10));
		OreDictionary.registerOre("plateSteel", new ItemStack(CoreItems.coreBasicItems, 1, 11));
		OreDictionary.registerOre("plateLithium", new ItemStack(CoreItems.coreBasicItems, 1, 12));
		OreDictionary.registerOre("plateSilicon", new ItemStack(CoreItems.coreBasicItems, 1, 13));
		OreDictionary.registerOre("plateAluminum", new ItemStack(CoreItems.coreBasicItems, 1, 14));
		OreDictionary.registerOre("plateTitanium", new ItemStack(CoreItems.coreBasicItems, 1, 15));
		OreDictionary.registerOre("plateCarbon", new ItemStack(CoreItems.coreBasicItems, 1, 16));

		/* Others */
		OreDictionary.registerOre("electricMotor", new ItemStack(CoreItems.coreBasicItems, 1, 17));
		OreDictionary.registerOre("electricPump", new ItemStack(CoreItems.coreBasicItems, 1, 18));
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

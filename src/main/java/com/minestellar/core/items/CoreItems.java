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

package com.minestellar.core.items;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.items.tools.*;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

public class CoreItems {
	public static void init() {
		initItems();
		registerItems();
		oreDictRegistration();
		registerHarvestLevels();
		registerFluidContainer();
	}

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
	public static Item oxygenBucket;
	public static Item hydrogenBucket;
	public static Item nitrogenBucket;

	public static ArmorMaterial ARMOR_CARBON = EnumHelper.addArmorMaterial("carbon", 30, new int[] {3, 8, 6, 3}, 12);
	public static ToolMaterial TOOL_CARBON = EnumHelper.addToolMaterial("carbon", 3, 768, 5.0F, 2, 8);

	private static void initItems() {
		CoreItems.coreBasicItems = new ItemBasicCore();

		CoreItems.carbonSword = new ItemSwordCore("carbon_sword", CoreItems.TOOL_CARBON);
		CoreItems.carbonPickaxe = new ItemPickaxeCore("carbon_pickaxe", CoreItems.TOOL_CARBON);
		CoreItems.carbonShovel = new ItemSpadeCore("carbon_shovel", CoreItems.TOOL_CARBON);
		CoreItems.carbonAxe = new ItemAxeCore("carbon_axe", CoreItems.TOOL_CARBON);
		CoreItems.carbonHoe = new ItemHoeCore("carbon_hoe", CoreItems.TOOL_CARBON);

		CoreItems.carbonHelmet = new ItemArmorCarbon(0, "helmet");
		CoreItems.carbonChestplate = new ItemArmorCarbon(1, "chestplate");
		CoreItems.carbonLeggings = new ItemArmorCarbon(2, "leggings");
		CoreItems.carbonBoots = new ItemArmorCarbon(3, "boots");

		CoreItems.canisterOil = new ItemCanisterOil("canisterOilPartial");
		CoreItems.extractorOil = new ItemExtractorOil("extractorOil");

		CoreItems.oilBucket = new ItemBucketOil("oil_bucket");
		CoreItems.oxygenBucket = new ItemBucketOxygen("oxygen_bucket");

		CoreItems.hydrogenBucket = new ItemBucketHydrogen("hydrogen_bucket");
		CoreItems.nitrogenBucket = new ItemBucketNitrogen("nitrogen_bucket");
	}

	private static void registerItems() {
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
		MinestellarCore.registerItem(oxygenBucket);

		MinestellarCore.registerItem(hydrogenBucket);
		MinestellarCore.registerItem(nitrogenBucket);
	}

	private static void oreDictRegistration() {
		/* Ingots */
		OreDictionary.registerOre("ingotCopper", new ItemStack(CoreItems.coreBasicItems, 1, 0));
		OreDictionary.registerOre("ingotTin", new ItemStack(CoreItems.coreBasicItems, 1, 1));
		OreDictionary.registerOre("ingotSteel", new ItemStack(CoreItems.coreBasicItems, 1, 2));
		OreDictionary.registerOre("ingotLithium", new ItemStack(CoreItems.coreBasicItems, 1, 3));
		OreDictionary.registerOre("ingotSilicon", new ItemStack(CoreItems.coreBasicItems, 1, 4));
		OreDictionary.registerOre("ingotAluminum", new ItemStack(CoreItems.coreBasicItems, 1, 5));
		OreDictionary.registerOre("ingotTitanium", new ItemStack(CoreItems.coreBasicItems, 1, 6));
		OreDictionary.registerOre("ingotCarbon", new ItemStack(CoreItems.coreBasicItems, 1, 7));

		/* Dusts */
		OreDictionary.registerOre("dustSteel", new ItemStack(CoreItems.coreBasicItems, 1, 8));

		/* Plates */
		OreDictionary.registerOre("compressedCopper", new ItemStack(CoreItems.coreBasicItems, 1, 9));
		OreDictionary.registerOre("compressedTin", new ItemStack(CoreItems.coreBasicItems, 1, 10));
		OreDictionary.registerOre("compressedSteel", new ItemStack(CoreItems.coreBasicItems, 1, 11));
		OreDictionary.registerOre("compressedLithium", new ItemStack(CoreItems.coreBasicItems, 1, 12));
		OreDictionary.registerOre("compressedSilicon", new ItemStack(CoreItems.coreBasicItems, 1, 13));
		OreDictionary.registerOre("compressedAluminum", new ItemStack(CoreItems.coreBasicItems, 1, 14));
		OreDictionary.registerOre("compressedTitanium", new ItemStack(CoreItems.coreBasicItems, 1, 15));
		OreDictionary.registerOre("compressedCarbon", new ItemStack(CoreItems.coreBasicItems, 1, 16));

		/* Others */
		OreDictionary.registerOre("electricMotor", new ItemStack(CoreItems.coreBasicItems, 1, 17));
		OreDictionary.registerOre("electricPump", new ItemStack(CoreItems.coreBasicItems, 1, 18));
		OreDictionary.registerOre("filterAir", new ItemStack(CoreItems.coreBasicItems, 1, 19));
		OreDictionary.registerOre("filterOil", new ItemStack(CoreItems.coreBasicItems, 1, 20));
		OreDictionary.registerOre("nozzleSteel", new ItemStack(CoreItems.coreBasicItems, 1, 21));
		OreDictionary.registerOre("pipeSteel", new ItemStack(CoreItems.coreBasicItems, 1, 22));
		OreDictionary.registerOre("pipeMagnetic", new ItemStack(CoreItems.coreBasicItems, 1, 23));
	}

	private static void registerHarvestLevels() {
		CoreItems.carbonPickaxe.setHarvestLevel("pickaxe", 4);
		CoreItems.carbonAxe.setHarvestLevel("axe", 4);
		CoreItems.carbonShovel.setHarvestLevel("shovel", 4);
	}

	private static void registerFluidContainer() {
	}
}

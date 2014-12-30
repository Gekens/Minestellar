package com.minestellar.core.items;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

import com.minestellar.core.Minestellar;
import com.minestellar.core.items.tools.ItemAxeCore;
import com.minestellar.core.items.tools.ItemHoeCore;
import com.minestellar.core.items.tools.ItemPickaxeCore;
import com.minestellar.core.items.tools.ItemSpadeCore;
import com.minestellar.core.items.tools.ItemSwordCore;

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
	}

	private static void registerHarvestLevels()
	{
		CoreItems.titaniumPickaxe.setHarvestLevel("pickaxe", 4);
		CoreItems.titaniumAxe.setHarvestLevel("axe", 4);
		CoreItems.titaniumShovel.setHarvestLevel("shovel", 4);
	}

	private static void registerItems()
	{
		Minestellar.registerItem(coreBasicItems);
		
		Minestellar.registerItem(titaniumPickaxe);
		Minestellar.registerItem(titaniumAxe);
		Minestellar.registerItem(titaniumHoe);
		Minestellar.registerItem(titaniumShovel);
		Minestellar.registerItem(titaniumSword);

		Minestellar.registerItem(titaniumHelmet);
		Minestellar.registerItem(titaniumChestplate);
		Minestellar.registerItem(titaniumLeggings);
		Minestellar.registerItem(titaniumBoots);
	}
	
    public static void oreDictRegistration()
    {
    	OreDictionary.registerOre("ingotCopper", new ItemStack(CoreItems.coreBasicItems, 1, 0));
    	OreDictionary.registerOre("ingotTin", new ItemStack(CoreItems.coreBasicItems, 1, 1));
		OreDictionary.registerOre("ingotTitanium", new ItemStack(CoreItems.coreBasicItems, 1, 2));
    	OreDictionary.registerOre("containerCopper", new ItemStack(CoreItems.coreBasicItems, 1, 3));
    	OreDictionary.registerOre("containerTin", new ItemStack(CoreItems.coreBasicItems, 1, 4));
		OreDictionary.registerOre("containerTitanium", new ItemStack(CoreItems.coreBasicItems, 1, 5));
    }
	
	public static void init()
	{
		initItems();
		registerHarvestLevels();
		registerItems();
		oreDictRegistration();
	}
}
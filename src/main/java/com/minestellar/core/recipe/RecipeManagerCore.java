package com.minestellar.core.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;

import com.minestellar.core.blocks.CoreBlocks;
import com.minestellar.core.items.CoreItems;
import com.minestellar.core.util.RecipeUtil;


public class RecipeManagerCore
{
    private static void addUniversalRecipes()
    {
		// ARMOR AND TOOLS CRAFTING
		RecipeUtil.addRecipe(new ItemStack(CoreItems.titaniumBoots), new Object[] { "X X", "X X", 'X', "ingotTitanium" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.titaniumHelmet), new Object[] { "XXX", "X X", 'X', "ingotTitanium" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.titaniumChestplate), new Object[] { "X X", "XXX", "XXX", 'X', "ingotTitanium" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.titaniumLeggings), new Object[] { "XXX", "X X", "X X", 'X', "ingotTitanium" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.titaniumSword), new Object[] { "X", "X", "Y", 'X', "ingotTitanium", 'Y', Items.stick  });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.titaniumPickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', "ingotTitanium", 'Y',Items.stick  });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.titaniumShovel), new Object[] { "X", "Y", "Y", 'X', "ingotTitanium", 'Y', Items.stick  });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.titaniumHoe), new Object[] { "XX", " Y", " Y", 'X', "ingotTitanium", 'Y', Items.stick  });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.titaniumAxe), new Object[] { "XX", "XY", " Y", 'X', "ingotTitanium", 'Y', Items.stick });
		
		// BASIC ITEMS CRAFTING
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 3, 3), new Object[] { "X X", "X X", "XXX", 'X', "ingotCopper" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 3, 4), new Object[] { "X X", "X X", "XXX", 'X', "ingotTin" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 2, 5), new Object[] { "X X", "X X", "XXX", 'X', "ingotTitanium" });
    
		// ITEM TO BLOCK
		RecipeUtil.addRecipe(new ItemStack(CoreBlocks.coreDecorBlocks, 1, 0), new Object[] { "XXX", "XXX", "XXX", 'X', "ingotCopper" });
		RecipeUtil.addRecipe(new ItemStack(CoreBlocks.coreDecorBlocks, 1, 1), new Object[] { "XXX", "XXX", "XXX", 'X', "ingotTin" });
		RecipeUtil.addRecipe(new ItemStack(CoreBlocks.coreDecorBlocks, 1, 2), new Object[] { "XXX", "XXX", "XXX", 'X', "ingotTitanium" });

		// BLOCK TO ITEM
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 1, 0), new Object[] { "X", 'X', "blockCopper" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 1, 1), new Object[] { "X", 'X', "blockTin" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 1, 2), new Object[] { "X", 'X', "blockTitanium" });
		
		// SMELTING
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(CoreBlocks.coreOreBlocks, 1, 0), OreDictionary.getOres("ingotCopper").get(0), 0.2F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(CoreBlocks.coreOreBlocks, 1, 1), OreDictionary.getOres("ingotTin").get(0), 0.2F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(CoreBlocks.coreOreBlocks, 1, 2), OreDictionary.getOres("ingotTitanium").get(0), 0.2F);
    }

    public static void loadRecipes()
    {
        RecipeManagerCore.addUniversalRecipes();
    }
}
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
		RecipeUtil.addRecipe(new ItemStack(CoreItems.carbonBoots), new Object[] { "X X", "X X", 'X', "ingotCarbon" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.carbonHelmet), new Object[] { "XXX", "X X", 'X', "ingotCarbon" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.carbonChestplate), new Object[] { "X X", "XXX", "XXX", 'X', "ingotCarbon" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.carbonLeggings), new Object[] { "XXX", "X X", "X X", 'X', "ingotCarbon" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.carbonSword), new Object[] { "X", "X", "Y", 'X', "ingotCarbon", 'Y', Items.stick });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.carbonPickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', "ingotCarbon", 'Y', Items.stick });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.carbonShovel), new Object[] { "X", "Y", "Y", 'X', "ingotCarbon", 'Y', Items.stick });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.carbonHoe), new Object[] { "XX", " Y", " Y", 'X', "ingotCarbon", 'Y', Items.stick });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.carbonAxe), new Object[] { "XX", "XY", " Y", 'X', "ingotCarbon", 'Y', Items.stick });

		// OTHER CRAFTING
		// TODO: Make rest of crafting

		// ITEM TO BLOCK
		RecipeUtil.addRecipe(new ItemStack(CoreBlocks.coreDecorBlocks, 1, 0), new Object[] { "XXX", "XXX", "XXX", 'X', "ingotCopper" });
		RecipeUtil.addRecipe(new ItemStack(CoreBlocks.coreDecorBlocks, 1, 1), new Object[] { "XXX", "XXX", "XXX", 'X', "ingotTin" });
		RecipeUtil.addRecipe(new ItemStack(CoreBlocks.coreDecorBlocks, 1, 2), new Object[] { "XXX", "XXX", "XXX", 'X', "ingotSteel" });
		RecipeUtil.addRecipe(new ItemStack(CoreBlocks.coreDecorBlocks, 1, 3), new Object[] { "XXX", "XXX", "XXX", 'X', "ingotLithium" });
		RecipeUtil.addRecipe(new ItemStack(CoreBlocks.coreDecorBlocks, 1, 4), new Object[] { "XXX", "XXX", "XXX", 'X', "ingotSilicon" });
		RecipeUtil.addRecipe(new ItemStack(CoreBlocks.coreDecorBlocks, 1, 5), new Object[] { "XXX", "XXX", "XXX", 'X', "ingotAluminum" });
		RecipeUtil.addRecipe(new ItemStack(CoreBlocks.coreDecorBlocks, 1, 6), new Object[] { "XXX", "XXX", "XXX", 'X', "ingotTitanium" });
		RecipeUtil.addRecipe(new ItemStack(CoreBlocks.coreDecorBlocks, 1, 7), new Object[] { "XXX", "XXX", "XXX", 'X', "ingotCarbon" });

		// BLOCK TO ITEM
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 9, 0), new Object[] { "X", 'X', "blockCopper" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 9, 1), new Object[] { "X", 'X', "blockTin" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 9, 2), new Object[] { "X", 'X', "blockSteel" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 9, 3), new Object[] { "X", 'X', "blockLithium" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 9, 4), new Object[] { "X", 'X', "blockSilicon" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 9, 5), new Object[] { "X", 'X', "blockAluminum" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 9, 6), new Object[] { "X", 'X', "blockTitanium" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 9, 7), new Object[] { "X", 'X', "blockCarbon" });

		// SMELTING
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(CoreBlocks.coreOreBlocks, 1, 0), OreDictionary.getOres("ingotCopper").get(0), 0.2F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(CoreBlocks.coreOreBlocks, 1, 1), OreDictionary.getOres("ingotTin").get(0), 0.2F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(CoreBlocks.coreOreBlocks, 1, 2), OreDictionary.getOres("ingotLithium").get(0), 0.2F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(CoreBlocks.coreOreBlocks, 1, 3), OreDictionary.getOres("ingotSilicon").get(0), 0.2F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(CoreBlocks.coreOreBlocks, 1, 4), OreDictionary.getOres("ingotAluminum").get(0), 0.2F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(CoreBlocks.coreOreBlocks, 1, 5), OreDictionary.getOres("ingotTitanium").get(0), 0.2F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(CoreBlocks.coreOreBlocks, 1, 6), OreDictionary.getOres("ingotCarbon").get(0), 0.2F);

	}

	public static void loadRecipes()
	{
		RecipeManagerCore.addUniversalRecipes();
	}
}

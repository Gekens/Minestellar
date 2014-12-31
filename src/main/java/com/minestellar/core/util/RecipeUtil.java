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

package com.minestellar.core.util;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeUtil
{
	@SuppressWarnings("unchecked")
	public static void addRecipe(ItemStack result, Object[] obj)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(result, obj));
	}

	public static void addBlockRecipe(ItemStack result, String oreDictIngot, ItemStack gcIngot)
	{
		if (OreDictionary.getOres(oreDictIngot).size() > 1)
		{
			CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(result, new Object[] { gcIngot, oreDictIngot, oreDictIngot, oreDictIngot, oreDictIngot, oreDictIngot, oreDictIngot, oreDictIngot, oreDictIngot }));
		}
		else
			RecipeUtil.addRecipe(result, new Object[] { "XXX", "XXX", "XXX", 'X', gcIngot });
	}
}

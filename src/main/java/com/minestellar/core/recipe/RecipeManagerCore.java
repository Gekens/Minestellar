package com.minestellar.core.recipe;

import net.minecraft.init.Blocks;
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
		RecipeUtil.addRecipe(new ItemStack(CoreItems.titaniumSword), new Object[] { "X", "X", "Y", 'X', "ingotTitanium", 'Y', Items.stick });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.titaniumPickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', "ingotTitanium", 'Y', Items.stick });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.titaniumShovel), new Object[] { "X", "Y", "Y", 'X', "ingotTitanium", 'Y', Items.stick });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.titaniumHoe), new Object[] { "XX", " Y", " Y", 'X', "ingotTitanium", 'Y', Items.stick });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.titaniumAxe), new Object[] { "XX", "XY", " Y", 'X', "ingotTitanium", 'Y', Items.stick });

		// OTHER CRAFTING
		RecipeUtil.addRecipe(new ItemStack(CoreItems.extractorOil, 1, 0), new Object[] { "N  ", " T ", "  P", 'N', "nozzleSteel", 'T', "pipeSteel", 'P', "electricPump" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.canisterOil, 1, (CoreItems.canisterOil.getMaxDamage())), new Object[] { "F", "T", 'F', "nozzleSteel", 'T', "containerTin" });

		// GENARIC PARTS CRAFTING
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 1, 14), new Object[] { "X X", "Y Y", "X X", 'X', "ingotSteel", 'Y', "ingotCopper" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 1, 15), new Object[] { "X X", "Y Y", "X X", 'X', "ingotSteel", 'Y', "ingotTin" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 1, 16), new Object[] { "XRX", "XRX", "XAX", 'X', "plateSteel", 'A', "exceleratorCopper", 'R', "pipeSteel" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 1, 17), new Object[] { "XRX", "XRX", "XAX", 'X', "plateTin", 'A', Blocks.piston, 'R', "pipeSteel" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 1, 18), new Object[] { "X  ", "XXX", "  X", 'X', "ingotSteel" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 1, 19), new Object[] { "X X", " X ", 'X', "ingotSteel" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 1, 20), new Object[] { "X", "X", 'X', "ingotSteel" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 1, 21), new Object[] { "XXX", "XCX", "XXX", 'X', "ingotTitanium", 'C', "ingotCopper" });

		// PLATES RECIPES
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 2, 9), new Object[] { "XX", "XX", 'X', "ingotCopper" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 2, 10), new Object[] { "XX", "XX", 'X', "ingotTin" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 2, 11), new Object[] { "XX", "XX", 'X', "ingotTitanium" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 1, 12), new Object[] { "XX", "XX", 'X', "bitsCarbon" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 3, 13), new Object[] { "XX", "XX", 'X', "ingotSteel" });

		// STEEL RECIPES
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 2, 4), new Object[] { "XY", "Y ", 'X', Items.iron_ingot, 'Y', Items.coal });
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(CoreItems.coreBasicItems, 1, 4), OreDictionary.getOres("ingotSteel").get(0), 0.2F);

		// CONTAINER CRAFTING
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 3, 6), new Object[] { "X X", "X X", "XXX", 'X', "ingotCopper" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 3, 7), new Object[] { "X X", "X X", "XXX", 'X', "ingotTin" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 2, 8), new Object[] { "X X", "X X", "XXX", 'X', "ingotTitanium" });

		// ITEM TO BLOCK
		RecipeUtil.addRecipe(new ItemStack(CoreBlocks.coreDecorBlocks, 1, 0), new Object[] { "XXX", "XXX", "XXX", 'X', "ingotCopper" });
		RecipeUtil.addRecipe(new ItemStack(CoreBlocks.coreDecorBlocks, 1, 1), new Object[] { "XXX", "XXX", "XXX", 'X', "ingotTin" });
		RecipeUtil.addRecipe(new ItemStack(CoreBlocks.coreDecorBlocks, 1, 2), new Object[] { "XXX", "XXX", "XXX", 'X', "ingotTitanium" });
		RecipeUtil.addRecipe(new ItemStack(CoreBlocks.coreDecorBlocks, 1, 3), new Object[] { "XXX", "XXX", "XXX", 'X', "ingotCarbon" });
		RecipeUtil.addRecipe(new ItemStack(CoreBlocks.coreDecorBlocks, 1, 4), new Object[] { "XXX", "XXX", "XXX", 'X', "ingotSteel" });

		// BLOCK TO ITEM
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 1, 0), new Object[] { "X", 'X', "blockCopper" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 1, 1), new Object[] { "X", 'X', "blockTin" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 1, 2), new Object[] { "X", 'X', "blockTitanium" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 1, 3), new Object[] { "X", 'X', "blockCarbon" });
		RecipeUtil.addRecipe(new ItemStack(CoreItems.coreBasicItems, 1, 4), new Object[] { "X", 'X', "blockSteel" });

		// SMELTING
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(CoreBlocks.coreOreBlocks, 1, 0), OreDictionary.getOres("ingotCopper").get(0), 0.2F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(CoreBlocks.coreOreBlocks, 1, 1), OreDictionary.getOres("ingotTin").get(0), 0.2F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(CoreBlocks.coreOreBlocks, 1, 2), OreDictionary.getOres("ingotTitanium").get(0), 0.2F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(CoreBlocks.coreOreBlocks, 3, 3), OreDictionary.getOres("bitsCarbon").get(0), 0.2F);
	}

	public static void loadRecipes()
	{
		RecipeManagerCore.addUniversalRecipes();
	}
}

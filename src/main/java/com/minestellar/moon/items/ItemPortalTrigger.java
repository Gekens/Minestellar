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

package com.minestellar.moon.items;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.proxy.ClientProxyCore;
import com.minestellar.moon.MinestellarMoon;
import com.minestellar.moon.blocks.MoonBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPortalTrigger extends Item {
	public ItemPortalTrigger(String name) {
		super();
		this.maxStackSize = 1;
		this.setMaxDamage(64);
		this.setUnlocalizedName(name);
		this.setTextureName(MinestellarMoon.TEXTURE_PREFIX + name);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public CreativeTabs getCreativeTab() {
		return MinestellarCore.stellarItemsTab;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return ClientProxyCore.stellarItem;
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if (par7 == 0) {
			par5--;
		}

		if (par7 == 1) {
			par5++;
		}

		if (par7 == 2) {
			par6--;
		}

		if (par7 == 3) {
			par6++;
		}

		if (par7 == 4) {
			par4--;
		}

		if (par7 == 5) {
			par4++;
		}

		if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)) {
			return false;
		}

		Block i1 = par3World.getBlock(par4, par5, par6);

		if (i1 == Blocks.air) {
			par3World.playSoundEffect(par4 + 0.5D, par5 + 0.5D, par6 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
			MoonBlocks.moonPortal.tryToCreatePortal(par3World, par4, par5, par6);
		}

		par1ItemStack.damageItem(1, par2EntityPlayer);

		return true;
	}
}

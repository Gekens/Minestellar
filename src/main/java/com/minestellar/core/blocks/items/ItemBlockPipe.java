/**
 * Copyright (c) 04/January/2015 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.core.blocks.items;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.minestellar.core.proxy.ClientProxyCore;

public class ItemBlockPipe extends ItemBlock {

	public static String[] types = new String[] {
	"light_pipe", "medium_pipe", "heavy_pipe"
	};

	public ItemBlockPipe(Block par1) {
		super(par1);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return ClientProxyCore.stellarItem;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		int meta = itemstack.getItemDamage();
		if (meta < 0 || meta >= ItemBlockPipe.types.length) {
			meta = 0;
		}
		return super.getUnlocalizedName() + "." + ItemBlockPipe.types[meta];
	}

}

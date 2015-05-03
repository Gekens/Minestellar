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
import com.minestellar.core.blocks.CoreBlocks;
import com.minestellar.core.proxy.ClientProxyCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBucketNitrogen extends ItemBucket {
	private Block isFull;

	public ItemBucketNitrogen(String name) {
		super(CoreBlocks.nitrogenFluidBlock);
		this.isFull = CoreBlocks.nitrogenFluidBlock;
		this.setMaxStackSize(1);
		this.setUnlocalizedName(name);
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return ClientProxyCore.stellarItem;
	}

	@Override
	public CreativeTabs getCreativeTab() {
		return MinestellarCore.stellarItemsTab;
	}

	@Override
	public boolean tryPlaceContainedLiquid(World par1World, int x, int y, int z) {
		Material material = par1World.getBlock(x, y, z).getMaterial();
		boolean flag = !material.isSolid();

		if (this.isFull == Blocks.air) {
			return false;
		} else if (!par1World.isAirBlock(x, y, z) && par1World.getBlock(x, y, z).getMaterial().isSolid()) {
			return false;
		} else {
			if (par1World.provider.isHellWorld && this.isFull != Blocks.air) {
				par1World.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, "random.fizz", 0.5F, 2.6F + (par1World.rand.nextFloat() - par1World.rand.nextFloat()) * 0.8F);

				for (int l = 0; l < 8; l++) {
					par1World.spawnParticle("largesmoke", x + Math.random(), y + Math.random(), z + Math.random(), 0.0D, 0.0D, 0.0D);
				}
			} else {
				if (!par1World.isRemote && flag && !material.isLiquid()) {
					par1World.func_147480_a(x, y, z, true);
				}
				par1World.setBlock(x, y, z, this.isFull, 0, 3);
			}
			return true;
		}
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "nitrogen_bucket");
	}
}

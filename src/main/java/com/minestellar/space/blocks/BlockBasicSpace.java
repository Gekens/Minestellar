/**
 * Copyright (c) 15/feb/2015 Davide Cossu.
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

package com.minestellar.space.blocks;

import java.util.List;

import com.minestellar.core.MinestellarCore;
import com.minestellar.space.MinestellarSpace;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockBasicSpace extends Block{

	private IIcon[] blockIcon;
	
	public BlockBasicSpace(String name){
		super(Material.rock);
		this.setBlockName(name);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		// UPDATE WHEN ADDING BLOCKS
		this.blockIcon = new IIcon[1];
		this.blockIcon[0] = par1IconRegister.registerIcon(MinestellarSpace.TEXTURE_PREFIX + "asteroidStone");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn() {
		return MinestellarCore.stellarBlocksTab;
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return this.blockIcon[meta];
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) {
		// UPDATE WHEN ADDING BLOCKS
		for (int i = 0; i < 1; ++i) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4) {
		final int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta >= 0 && meta <= 8) {
			return 1.10F;
		}

		if (meta == 9) {
			return 1.60F;
		}

		if (meta == 10) {
			return 1.80F;
		}

		if (meta == 11) {
			return 1.80F;
		}

		return 1.0F;
	}

	@Override
	public int damageDropped(int meta) {
		if (meta >= 0 && meta <= 8) {
			return 0;
		}

		if (meta == 10) {
			return 11;
		}

		return meta;
	}
	
}
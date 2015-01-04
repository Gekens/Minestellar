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

package com.minestellar.moon.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.minestellar.core.MinestellarCore;
import com.minestellar.moon.MinestellarMoon;

public class BlockBasicMoon extends Block {
	private IIcon[] blockIcon;

	public BlockBasicMoon(String name) {
		super(Material.rock);
		this.setBlockName(name);
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		// UPDATE WHEN ADDING BLOCKS
		this.blockIcon = new IIcon[4];
		this.blockIcon[0] = par1IconRegister.registerIcon(MinestellarMoon.TEXTURE_PREFIX + "moonSurfaceStone");
		this.blockIcon[1] = par1IconRegister.registerIcon(MinestellarMoon.TEXTURE_PREFIX + "moonSubStone");
		this.blockIcon[2] = par1IconRegister.registerIcon(MinestellarMoon.TEXTURE_PREFIX + "moonStone");
		this.blockIcon[3] = par1IconRegister.registerIcon(MinestellarMoon.TEXTURE_PREFIX + "moonCobblestone");
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
		for (int i = 0; i < 4; ++i) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4) {
		final int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 0) {
			return 1.10F;
		}

		if (meta == 1) {
			return 1.60F;
		}

		if (meta == 2) {
			return 1.80F;
		}
		
		if (meta == 3) {
			return 1.80F;
		}

		return 1.0F;
	}
	
	@Override
	public int damageDropped(int meta)
	{
		if (meta == 2)
		{
			return 3;
		}

		return meta;
	}
}
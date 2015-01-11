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

package com.minestellar.core.blocks;

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

public class BlockOreCore extends Block {
	private IIcon[] blockIcon;

	public BlockOreCore(String name) {
		super(Material.rock);
		this.setBlockName(name);
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		// UPDATE WHEN ADDING BLOCKS
		this.blockIcon = new IIcon[7];
		this.blockIcon[0] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "oreCopper");
		this.blockIcon[1] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "oreTin");
		this.blockIcon[2] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "oreLithium");
		this.blockIcon[3] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "oreSilicon");
		this.blockIcon[4] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "oreAluminum");
		this.blockIcon[5] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "oreTitanium");
		this.blockIcon[6] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "oreCarbon");
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
		for (int i = 0; i < 7; ++i) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4) {
		final int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta >= 0 && meta <= 6) {
			return 2.25F;
		}

		return 1.0F;
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}
}

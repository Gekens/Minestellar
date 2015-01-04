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

public class BlockDecorCore extends Block
{
	private IIcon[] decorBlockIcon;

	public BlockDecorCore(String name)
	{
		super(Material.rock);
		this.setBlockName(name);
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.decorBlockIcon = new IIcon[8]; // UPDATE WHEN ADDING BLOCKS
		this.decorBlockIcon[0] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "blockCopper");
		this.decorBlockIcon[1] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "blockTin");
		this.decorBlockIcon[2] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "blockSteel");
		this.decorBlockIcon[3] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "blockLithium");
		this.decorBlockIcon[4] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "blockSilicon");
		this.decorBlockIcon[5] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "blockAluminum");
		this.decorBlockIcon[6] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "blockTitanium");
		this.decorBlockIcon[7] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "blockCarbon");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return MinestellarCore.stellarBlocksTab;
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return this.decorBlockIcon[meta];
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 8; ++i) // UPDATE WHEN ADDING BLOCKS
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4)
	{
		final int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 2 || meta == 6)
		{
			return 2.50F;
		}

		return 2.25F;
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
}

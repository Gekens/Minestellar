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
		this.blockIcon = new IIcon[12]; // UPDATE WHEN ADDING BLOCKS
		this.blockIcon[0] = par1IconRegister.registerIcon(MinestellarMoon.TEXTURE_PREFIX + "moonSurfaceStone");
		this.blockIcon[1] = par1IconRegister.registerIcon(MinestellarMoon.TEXTURE_PREFIX + "grass_step_1");
		this.blockIcon[2] = par1IconRegister.registerIcon(MinestellarMoon.TEXTURE_PREFIX + "grass_step_2");
		this.blockIcon[3] = par1IconRegister.registerIcon(MinestellarMoon.TEXTURE_PREFIX + "grass_step_3");
		this.blockIcon[4] = par1IconRegister.registerIcon(MinestellarMoon.TEXTURE_PREFIX + "grass_step_4");
		this.blockIcon[5] = par1IconRegister.registerIcon(MinestellarMoon.TEXTURE_PREFIX + "grass_step_5");
		this.blockIcon[6] = par1IconRegister.registerIcon(MinestellarMoon.TEXTURE_PREFIX + "grass_step_6");
		this.blockIcon[7] = par1IconRegister.registerIcon(MinestellarMoon.TEXTURE_PREFIX + "grass_step_7");
		this.blockIcon[8] = par1IconRegister.registerIcon(MinestellarMoon.TEXTURE_PREFIX + "grass_step_8");

		this.blockIcon[9] = par1IconRegister.registerIcon(MinestellarMoon.TEXTURE_PREFIX + "moonSubStone");
		this.blockIcon[10] = par1IconRegister.registerIcon(MinestellarMoon.TEXTURE_PREFIX + "moonStone");
		this.blockIcon[11] = par1IconRegister.registerIcon(MinestellarMoon.TEXTURE_PREFIX + "moonCobblestone");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn() {
		return MinestellarCore.stellarBlocksTab;
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if (meta >= 1 && meta <= 8) {
			if (side == 1) {
				switch (meta) {
				case 1:
					return this.blockIcon[1];
				case 2:
					return this.blockIcon[2];
				case 3:
					return this.blockIcon[3];
				case 4:
					return this.blockIcon[4];
				case 5:
					return this.blockIcon[5];
				case 6:
					return this.blockIcon[6];
				case 7:
					return this.blockIcon[7];
				case 8:
					return this.blockIcon[8];
				}
			}

			else if (side == 0) {
				return this.blockIcon[0];
			}

			else {
				return this.blockIcon[0];
			}
		}

		else {
			switch (meta) {
			case 0:
				return this.blockIcon[0];
			case 9:
				return this.blockIcon[9];
			case 10:
				return this.blockIcon[10];
			case 11:
				return this.blockIcon[11];
			default:
				return this.blockIcon[0];
			}
		}

		return null;
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < 12; ++i) { // UPDATE WHEN ADDING BLOCKS
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

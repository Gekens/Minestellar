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

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.blocks.tile.TileEntityPipe;

public class BlockPipe extends BlockContainer {

	float pixel = 1F / 16F;

	public static IIcon[] pipeBlockIcon;

	protected BlockPipe(String name) {
		super(Material.ground);
		this.setBlockName(name);

		this.setBlockBounds(11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2);
		this.useNeighborBrightness = true;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		TileEntityPipe pipe = (TileEntityPipe) world.getTileEntity(x, y, z);

		if (pipe != null) {

			float minX = 11 * pixel / 2 - (pipe.connections[5] != null ? (11 * pixel / 2) : 0);
			float minY = 11 * pixel / 2 - (pipe.connections[1] != null ? (11 * pixel / 2) : 0);
			float minZ = 11 * pixel / 2 - (pipe.connections[2] != null ? (11 * pixel / 2) : 0);
			float maxX = 1 - 11 * pixel / 2 + (pipe.connections[3] != null ? (11 * pixel / 2) : 0);
			float maxY = 1 - 11 * pixel / 2 + (pipe.connections[0] != null ? (11 * pixel / 2) : 0);
			float maxZ = 1 - 11 * pixel / 2 + (pipe.connections[4] != null ? (11 * pixel / 2) : 0);

			this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}

		return AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		TileEntityPipe cable = (TileEntityPipe) world.getTileEntity(x, y, z);

		if (cable != null) {

			float minX = 11 * pixel / 2 - (cable.connections[5] != null ? (11 * pixel / 2) : 0);
			float minY = 11 * pixel / 2 - (cable.connections[1] != null ? (11 * pixel / 2) : 0);
			float minZ = 11 * pixel / 2 - (cable.connections[2] != null ? (11 * pixel / 2) : 0);
			float maxX = 1 - 11 * pixel / 2 + (cable.connections[3] != null ? (11 * pixel / 2) : 0);
			float maxY = 1 - 11 * pixel / 2 + (cable.connections[0] != null ? (11 * pixel / 2) : 0);
			float maxZ = 1 - 11 * pixel / 2 + (cable.connections[4] != null ? (11 * pixel / 2) : 0);

			this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}

		return AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityPipe(meta);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn() {
		return MinestellarCore.stellarBlocksTab;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.pipeBlockIcon = new IIcon[3];
		this.pipeBlockIcon[0] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "light_cable_icon");
		this.pipeBlockIcon[1] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "medium_cable_icon");
		this.pipeBlockIcon[2] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "heavy_cable_icon");
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return this.pipeBlockIcon[meta];
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < 3; ++i) // UPDATE WHEN ADDING BLOCKS
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}
}
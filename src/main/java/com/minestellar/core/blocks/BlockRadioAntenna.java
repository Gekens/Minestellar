/**
 * Copyright (c) 26/apr/2015 Davide Cossu & Matthew Albrecht.
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

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.blocks.tile.TileEntityRadioAntenna;
import com.minestellar.core.items.ItemMemoryCard;

public class BlockRadioAntenna extends Block implements ITileEntityProvider {
	protected BlockRadioAntenna(String name) {
		super(Material.iron);
		this.setBlockName(name);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn() {
		return MinestellarCore.stellarBlocksTab;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityRadioAntenna();
	}

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
        if(player.getCurrentEquippedItem() != null){
            if(player.getCurrentEquippedItem().getItem() instanceof ItemMemoryCard){
                TileEntityRadioAntenna te = (TileEntityRadioAntenna) world.getTileEntity(x, y, z);
                if(te != null){
                    te.setCoordinates(player.getCurrentEquippedItem()); //I'm binding the other IDataConnection TE to this one
                }
            }
        }
        return false;
    }
}

/**
 * Copyright (c) 20/05/15 Davide Cossu & Matthew Albrecht.
 * <p/>
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 * <p/>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, see <http://www.gnu.org/licenses>.
 */

package com.minestellar.api.data.block.wire;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.minestellar.core.MinestellarCore;

public class DataWire extends Block implements ITileEntityProvider{

    private float pixel = 1F / 16F;

    public DataWire(String name){
        super(Material.ground);
        this.setBlockName(name);

        this.setBlockBounds(11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2);
        this.useNeighborBrightness = true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta){
        return new DataWireTileEntity();
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
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        DataWireTileEntity cable = (DataWireTileEntity) world.getTileEntity(x, y, z);

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
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        DataWireTileEntity cable = (DataWireTileEntity) world.getTileEntity(x, y, z);

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
    public CreativeTabs getCreativeTabToDisplayOn() {
        return MinestellarCore.stellarBlocksTab;
    }

}
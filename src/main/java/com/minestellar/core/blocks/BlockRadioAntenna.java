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
import com.minestellar.core.handler.PlanetKnowledgeHandler;

import java.util.ArrayList;
import java.util.Random;

public class BlockRadioAntenna extends Block implements ITileEntityProvider {

    /** Stores the players that will discover new planets */
    private ArrayList<EntityPlayer> playerList;

    public BlockRadioAntenna(String name) {
        super(Material.iron);
        this.setBlockName(name);
        this.setTickRandomly(true);
        playerList = new ArrayList<EntityPlayer>();
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
    public void updateTick(World world, int x, int y, int z, Random rnd){
        if(Math.random() <= 0.5 && !playerList.isEmpty()){
            MinestellarCore.log.info("Maybe adding planet to player");
            for(EntityPlayer player : playerList){
                if(Math.random() < 0.5){
                    PlanetKnowledgeHandler props = (PlanetKnowledgeHandler)player.getExtendedProperties(PlanetKnowledgeHandler.PLANET_KNOWLEDGE);
                    MinestellarCore.log.info("Known: " + props.getAcknowledgedPlanets());
                    props.setAcknowledgedNext();
                    MinestellarCore.log.info("Now known: " + props.getAcknowledgedPlanets());
                }
            }
        }
    }

    /**
     * Adds a player to the {@link BlockRadioAntenna#playerList}
     *
     * @param player The player to add
     */

    public void addPlayer(EntityPlayer player){
        playerList.listIterator().add(player);
    }

    @Override
    public int getRenderType() {
        return -2;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

}
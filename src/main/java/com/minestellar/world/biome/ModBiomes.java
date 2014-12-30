/**
 * Copyright (c) 29/dic/2014 Davide Cossu.
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

package com.minestellar.world.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

import com.minestellar.init.ModBlocks;
import com.minestellar.reference.BiomeIDs;

public class ModBiomes extends BiomeGenBase{

	/** The biome height */
    public static BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(0.3F, 0.6F);

    public static BiomeGenBase moonPlains;

    public ModBiomes(int biomeId){
        super(biomeId);
    }

    static{
        moonPlains = (new ModBiomes(BiomeIDs.MOON_PLAINS).setHeight(biomeHeight).setBiomeName("Moon Plains"));
    }


    public static void registerWithBiomeDictionary(){
        BiomeDictionary.registerBiomeType(moonPlains, Type.DRY);
        //BiomeDictionary.registerAllBiomes();
    }
    
    @Override
    public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_) {
        genBiomeModdedTerrain(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
    }

    /**
     * Replaces custom Stone to allow top/filler blocks to work in dimension.
     * 
     * @param world
     * @param random
     * @param replacableBlock
     * @param aByte
     * @param x
     * @param y
     * @param z
     */
    public void genBiomeModdedTerrain(World world, Random random, Block[] replacableBlock, byte[] aByte, int x, int y, double z){
        
    	replacableBlock[0] = Blocks.stone;
    	replacableBlock[1] = Blocks.dirt;
    	replacableBlock[2] = Blocks.grass;
    	replacableBlock[3] = Blocks.gravel;
    	replacableBlock[4] = Blocks.sand;
    	
    }
	
}
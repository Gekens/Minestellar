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
    public static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(0.3F, 0.6F);

    public static BiomeGenBase moonPlains;

    public ModBiomes(int biomeId){
        super(biomeId);
    }

    static{
        moonPlains = (new ModBiomes(BiomeIDs.MOON_PLAINS).setColor(0xd2d2d2).setTemperatureRainfall(0.95F, 0.9F).setHeight(biomeHeight).setBiomeName("Moon Plains"));
    }


    public static void registerWithBiomeDictionary(){
        BiomeDictionary.registerBiomeType(moonPlains, Type.DRY);
        BiomeDictionary.registerAllBiomes();
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
        Block block = this.topBlock;
        byte b0 = (byte) (this.field_150604_aj & 255);
        Block block1 = this.fillerBlock;
        int k = -1;
        int l = (int) (z / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        int i1 = x & 15;
        int j1 = y & 15;
        int k1 = replacableBlock.length / 256;
        for (int l1 = 255; l1 >= 0; --l1){
            int i2 = (j1 * 16 + i1) * k1 + l1;

            if (l1 <= 0 + random.nextInt(5)){
                replacableBlock[i2] = Blocks.bedrock;
            }else{
                Block block2 = replacableBlock[i2];

                if (block2 != null && block2.getMaterial() != Material.air){
                    if (block2 == ModBlocks.moonStone){
                        if (k == -1){
                            if (l <= 0){
                                block = null;
                                b0 = 0;
                                block1 = ModBlocks.moonStone;
                            }else if (l1 >= 59 && l1 <= 64){
                                block = this.topBlock;
                                b0 = (byte) (this.field_150604_aj & 255);
                                block1 = this.fillerBlock;
                            }

                            if (l1 < 63 && (block == null || block.getMaterial() == Material.air)){
                                if (this.getFloatTemperature(x, l1, y) < 0.15F){
                                    block = Blocks.ice;
                                    b0 = 0;
                                }else{
                                    block = Blocks.water;
                                    b0 = 0;
                                }
                            }

                            k = l;

                            if (l1 >= 62){
                                replacableBlock[i2] = block;
                                aByte[i2] = b0;
                            }else if (l1 < 56 - l){
                                block = null;
                                block1 = ModBlocks.moonStone;
                                replacableBlock[i2] = Blocks.gravel;
                            }else{
                                replacableBlock[i2] = block1;
                            }
                        }else if (k > 0){
                            --k;
                            replacableBlock[i2] = block1;

                            if (k == 0 && block1 == Blocks.sand){
                                k = random.nextInt(4) + Math.max(0, l1 - 63);
                                block1 = Blocks.sandstone;
                            }
                        }
                    }
                }else{
                    k = -1;
                }
            }
        }
    }
	
}
/**
 * Copyright (c) 15/feb/2015 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.space.world.gen.structure;

import java.util.Random;

import com.minestellar.space.blocks.SpaceBlocks;
import com.minestellar.space.world.WorldProviderSpace;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenStructureAsteroid extends WorldGenerator{

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z){
		if(world.provider instanceof WorldProviderSpace){

			if(rand.nextInt() == 1){
				//This would create any size sphere from 0x0 to 7x7 shape. You might have to modify a couple things to it but it makes the code more modular	
				int r = rand.nextInt(7);
				for(int a = -r; a < r; a++){
					for(int b = -r; b < r; b++){ 
						for(int c = -r; c < r; c++){					
							double dist = MathHelper.sqrt_double((a*a + b*b + c*c)); //Calculates the distance
							if(dist > r)
								continue;
							if(world == null)
								world = FMLClientHandler.instance().getClient().theWorld;

							world.setBlock(x+a, y+b, z+c, SpaceBlocks.spaceBasicBlocks, 0, 2);

						}
					}
				}
			}

			/*{//5x5
				world.setBlock(x, y, z, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x-1, y, z, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x+1, y, z, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x, y, z-1, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x, y, z+1, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x+1, y, z+1, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x-1, y, z-1, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x+1, y, z-1, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x-1, y, z+1, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x+2, y, z, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x-2, y, z, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x, y, z+2, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x, y, z-2, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x+2, y, z+2, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x-2, y, z-2, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x+2, y, z-2, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x-2, y, z+2, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x+1, y, z+2, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x-1, y, z+2, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x+1, y, z-2, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x-1, y, z-2, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x+2, y, z-1, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x+2, y, z+1, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x-2, y, z+1, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x-2, y, z-1, SpaceBlocks.spaceBasicBlocks, 0, 2);
			}

			{//3x3, up and down
				world.setBlock(x, y+1, z, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x-1, y+1, z, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x+1, y+1, z, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x, y+1, z-1, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x, y+1, z+1, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x+1, y+1, z+1, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x-1, y+1, z-1, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x+1, y+1, z-1, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x-1, y+1, z+1, SpaceBlocks.spaceBasicBlocks, 0, 2);

				world.setBlock(x, y-1, z, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x-1, y-1, z, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x+1, y-1, z, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x, y-1, z-1, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x, y-1, z+1, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x+1, y-1, z+1, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x-1, y-1, z-1, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x+1, y-1, z-1, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x-1, y-1, z+1, SpaceBlocks.spaceBasicBlocks, 0, 2);
			}

			{//1x1, up and down
				world.setBlock(x, y+2, z, SpaceBlocks.spaceBasicBlocks, 0, 2);
				world.setBlock(x, y-2, z, SpaceBlocks.spaceBasicBlocks, 0, 2);
			}*/

			System.out.println(x + " " + y + " " + z);

			return true;
		}else{
			return false;
		}
	}

}
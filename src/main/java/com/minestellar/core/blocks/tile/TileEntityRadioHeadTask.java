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

package com.minestellar.core.blocks.tile;

import java.util.TimerTask;

import net.minecraft.world.World;

import com.minestellar.core.blocks.BlockRadioWall;
import com.minestellar.core.network.NetworkHandler;
import com.minestellar.core.network.message.MessageRadioFormed;
import com.minestellar.core.network.message.MessageRadioUnformed;

public class TileEntityRadioHeadTask extends TimerTask{

	private TileEntityRadioHead master;

	public TileEntityRadioHeadTask(TileEntityRadioHead tile){
		master = tile;
	}

	/**
	 * Insanely long method to check if the head is surrounded by a frame of walls in a 5x5 cube. Might not be the most efficient way.
	 */

	public boolean isSurrondedCorrectly(){
		World world = master.getWorldObj();
		if(world.getBlock(master.xCoord, master.yCoord-2, master.zCoord-2) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord-2, master.yCoord-2, master.zCoord) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord, master.yCoord-2, master.zCoord+2) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord+2, master.yCoord-2, master.zCoord) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord-2, master.yCoord-2, master.zCoord-2) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord+2, master.yCoord-2, master.zCoord+2) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord-2, master.yCoord-2, master.zCoord+2) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord+2, master.yCoord-2, master.zCoord-2) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord-1, master.yCoord-2, master.zCoord-2) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord-1, master.yCoord-2, master.zCoord+2) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord+1, master.yCoord-2, master.zCoord+2) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord+1, master.yCoord-2, master.zCoord-2) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord-2, master.yCoord-2, master.zCoord-1) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord-2, master.yCoord-2, master.zCoord+1) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord+2, master.yCoord-2, master.zCoord-1) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord+2, master.yCoord-2, master.zCoord+1) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord, master.yCoord-2, master.zCoord) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord+1, master.yCoord-2, master.zCoord) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord-1, master.yCoord-2, master.zCoord) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord, master.yCoord-2, master.zCoord+1) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord, master.yCoord-2, master.zCoord-1) instanceof BlockRadioWall){ // 5x5 base
			if(world.getBlock(master.xCoord-2, master.yCoord-1, master.zCoord+2) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord-2, master.yCoord-1, master.zCoord-2) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord+2, master.yCoord-1, master.zCoord+2) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord+2, master.yCoord-1, master.zCoord-2) instanceof BlockRadioWall){ //4 corners
				if(world.getBlock(master.xCoord-2, master.yCoord, master.zCoord+2) instanceof BlockRadioWall &&
						world.getBlock(master.xCoord-2, master.yCoord, master.zCoord-2) instanceof BlockRadioWall &&
						world.getBlock(master.xCoord+2, master.yCoord, master.zCoord+2) instanceof BlockRadioWall &&
						world.getBlock(master.xCoord+2, master.yCoord, master.zCoord-2) instanceof BlockRadioWall){ //4 corners
					if(world.getBlock(master.xCoord-2, master.yCoord+1, master.zCoord+2) instanceof BlockRadioWall &&
							world.getBlock(master.xCoord-2, master.yCoord+1, master.zCoord-2) instanceof BlockRadioWall &&
							world.getBlock(master.xCoord+2, master.yCoord+1, master.zCoord+2) instanceof BlockRadioWall &&
							world.getBlock(master.xCoord+2, master.yCoord+1, master.zCoord-2) instanceof BlockRadioWall){ //4 corners
						if(world.getBlock(master.xCoord, master.yCoord+2, master.zCoord-2) instanceof BlockRadioWall &&
								world.getBlock(master.xCoord-2, master.yCoord+2, master.zCoord) instanceof BlockRadioWall &&
								world.getBlock(master.xCoord, master.yCoord+2, master.zCoord+2) instanceof BlockRadioWall &&
								world.getBlock(master.xCoord+2, master.yCoord+2, master.zCoord) instanceof BlockRadioWall &&
								world.getBlock(master.xCoord-2, master.yCoord+2, master.zCoord-2) instanceof BlockRadioWall &&
								world.getBlock(master.xCoord+2, master.yCoord+2, master.zCoord+2) instanceof BlockRadioWall &&
								world.getBlock(master.xCoord-2, master.yCoord+2, master.zCoord+2) instanceof BlockRadioWall &&
								world.getBlock(master.xCoord+2, master.yCoord+2, master.zCoord-2) instanceof BlockRadioWall &&
								world.getBlock(master.xCoord-1, master.yCoord+2, master.zCoord-2) instanceof BlockRadioWall &&
								world.getBlock(master.xCoord-1, master.yCoord+2, master.zCoord+2) instanceof BlockRadioWall &&
								world.getBlock(master.xCoord+1, master.yCoord+2, master.zCoord+2) instanceof BlockRadioWall &&
								world.getBlock(master.xCoord+1, master.yCoord+2, master.zCoord-2) instanceof BlockRadioWall &&
								world.getBlock(master.xCoord-2, master.yCoord+2, master.zCoord-1) instanceof BlockRadioWall &&
								world.getBlock(master.xCoord-2, master.yCoord+2, master.zCoord+1) instanceof BlockRadioWall &&
								world.getBlock(master.xCoord+2, master.yCoord+2, master.zCoord-1) instanceof BlockRadioWall &&
								world.getBlock(master.xCoord+2, master.yCoord+2, master.zCoord+1) instanceof BlockRadioWall){ //5x5 top
							if(world.isAirBlock(master.xCoord-2, master.yCoord-1, master.zCoord) &&
									world.isAirBlock(master.xCoord-2, master.yCoord-1, master.zCoord-1) &&
									world.isAirBlock(master.xCoord-2, master.yCoord-1, master.zCoord+1) &&
									world.isAirBlock(master.xCoord+2, master.yCoord-1, master.zCoord) &&
									world.isAirBlock(master.xCoord+2, master.yCoord-1, master.zCoord-1) &&
									world.isAirBlock(master.xCoord+2, master.yCoord-1, master.zCoord+1) &&
									world.isAirBlock(master.xCoord, master.yCoord-1, master.zCoord-2) &&
									world.isAirBlock(master.xCoord-1, master.yCoord-1, master.zCoord-2) &&
									world.isAirBlock(master.xCoord+1, master.yCoord-1, master.zCoord-2) &&
									world.isAirBlock(master.xCoord-1, master.yCoord-1, master.zCoord+2) &&
									world.isAirBlock(master.xCoord+1, master.yCoord-1, master.zCoord+2) &&
									world.isAirBlock(master.xCoord, master.yCoord-1, master.zCoord+2)){ //From here all the inners
								if(world.isAirBlock(master.xCoord-2, master.yCoord, master.zCoord) &&
										world.isAirBlock(master.xCoord-2, master.yCoord, master.zCoord-1) &&
										world.isAirBlock(master.xCoord-2, master.yCoord, master.zCoord+1) &&
										world.isAirBlock(master.xCoord+2, master.yCoord, master.zCoord) &&
										world.isAirBlock(master.xCoord+2, master.yCoord, master.zCoord-1) &&
										world.isAirBlock(master.xCoord+2, master.yCoord, master.zCoord+1) &&
										world.isAirBlock(master.xCoord, master.yCoord, master.zCoord-2) &&
										world.isAirBlock(master.xCoord-1, master.yCoord, master.zCoord-2) &&
										world.isAirBlock(master.xCoord+1, master.yCoord, master.zCoord-2) &&
										world.isAirBlock(master.xCoord-1, master.yCoord, master.zCoord+2) &&
										world.isAirBlock(master.xCoord+1, master.yCoord, master.zCoord+2) &&
										world.isAirBlock(master.xCoord, master.yCoord, master.zCoord+2)){
									if(world.isAirBlock(master.xCoord-2, master.yCoord+1, master.zCoord) &&
											world.isAirBlock(master.xCoord-2, master.yCoord+1, master.zCoord-1) &&
											world.isAirBlock(master.xCoord-2, master.yCoord+1, master.zCoord+1) &&
											world.isAirBlock(master.xCoord+2, master.yCoord+1, master.zCoord) &&
											world.isAirBlock(master.xCoord+2, master.yCoord+1, master.zCoord-1) &&
											world.isAirBlock(master.xCoord+2, master.yCoord+1, master.zCoord+1) &&
											world.isAirBlock(master.xCoord, master.yCoord+1, master.zCoord-2) &&
											world.isAirBlock(master.xCoord-1, master.yCoord+1, master.zCoord-2) &&
											world.isAirBlock(master.xCoord+1, master.yCoord+1, master.zCoord-2) &&
											world.isAirBlock(master.xCoord-1, master.yCoord+1, master.zCoord+2) &&
											world.isAirBlock(master.xCoord+1, master.yCoord+1, master.zCoord+2) &&
											world.isAirBlock(master.xCoord, master.yCoord+1, master.zCoord+2)){
										if(world.isAirBlock(master.xCoord-1, master.yCoord-1, master.zCoord) &&
												world.isAirBlock(master.xCoord-1, master.yCoord-1, master.zCoord-1) &&
												world.isAirBlock(master.xCoord-1, master.yCoord-1, master.zCoord+1) &&
												world.isAirBlock(master.xCoord+1, master.yCoord-1, master.zCoord) &&
												world.isAirBlock(master.xCoord+1, master.yCoord-1, master.zCoord-1) &&
												world.isAirBlock(master.xCoord+1, master.yCoord-1, master.zCoord+1) &&
												world.isAirBlock(master.xCoord, master.yCoord-1, master.zCoord-1) &&
												world.isAirBlock(master.xCoord-1, master.yCoord-1, master.zCoord-1) &&
												world.isAirBlock(master.xCoord+1, master.yCoord-1, master.zCoord-1) &&
												world.isAirBlock(master.xCoord-1, master.yCoord-1, master.zCoord+1) &&
												world.isAirBlock(master.xCoord+1, master.yCoord-1, master.zCoord+1) &&
												world.isAirBlock(master.xCoord, master.yCoord-1, master.zCoord+1)){
											if(world.isAirBlock(master.xCoord-1, master.yCoord, master.zCoord) &&
													world.isAirBlock(master.xCoord-1, master.yCoord, master.zCoord-1) &&
													world.isAirBlock(master.xCoord-1, master.yCoord, master.zCoord+1) &&
													world.isAirBlock(master.xCoord+1, master.yCoord, master.zCoord) &&
													world.isAirBlock(master.xCoord+1, master.yCoord, master.zCoord-1) &&
													world.isAirBlock(master.xCoord+1, master.yCoord, master.zCoord+1) &&
													world.isAirBlock(master.xCoord, master.yCoord, master.zCoord-1) &&
													world.isAirBlock(master.xCoord-1, master.yCoord, master.zCoord-1) &&
													world.isAirBlock(master.xCoord+1, master.yCoord, master.zCoord-1) &&
													world.isAirBlock(master.xCoord-1, master.yCoord, master.zCoord+1) &&
													world.isAirBlock(master.xCoord+1, master.yCoord, master.zCoord+1) &&
													world.isAirBlock(master.xCoord, master.yCoord, master.zCoord+1)){
												if(world.isAirBlock(master.xCoord-1, master.yCoord+1, master.zCoord) &&
														world.isAirBlock(master.xCoord-1, master.yCoord+1, master.zCoord-1) &&
														world.isAirBlock(master.xCoord-1, master.yCoord+1, master.zCoord+1) &&
														world.isAirBlock(master.xCoord+1, master.yCoord+1, master.zCoord) &&
														world.isAirBlock(master.xCoord+1, master.yCoord+1, master.zCoord-1) &&
														world.isAirBlock(master.xCoord+1, master.yCoord+1, master.zCoord+1) &&
														world.isAirBlock(master.xCoord, master.yCoord+1, master.zCoord-1) &&
														world.isAirBlock(master.xCoord-1, master.yCoord+1, master.zCoord-1) &&
														world.isAirBlock(master.xCoord+1, master.yCoord+1, master.zCoord-1) &&
														world.isAirBlock(master.xCoord-1, master.yCoord+1, master.zCoord+1) &&
														world.isAirBlock(master.xCoord+1, master.yCoord+1, master.zCoord+1) &&
														world.isAirBlock(master.xCoord, master.yCoord+1, master.zCoord+1)){
													if(world.isAirBlock(master.xCoord, master.yCoord+2, master.zCoord) &&
															world.isAirBlock(master.xCoord+1, master.yCoord+2, master.zCoord) &&
															world.isAirBlock(master.xCoord, master.yCoord+2, master.zCoord+1) &&
															world.isAirBlock(master.xCoord-1, master.yCoord+2, master.zCoord) &&
															world.isAirBlock(master.xCoord, master.yCoord+2, master.zCoord-1) &&
															world.isAirBlock(master.xCoord+1, master.yCoord+2, master.zCoord+1) &&
															world.isAirBlock(master.xCoord+1, master.yCoord+2, master.zCoord-1) &&
															world.isAirBlock(master.xCoord-1, master.yCoord+2, master.zCoord+1) &&
															world.isAirBlock(master.xCoord-1, master.yCoord+2, master.zCoord-1)){
														if(world.isAirBlock(master.xCoord+1, master.yCoord-2, master.zCoord+1) &&
																world.isAirBlock(master.xCoord+1, master.yCoord-2, master.zCoord-1) &&
																world.isAirBlock(master.xCoord-1, master.yCoord-2, master.zCoord+1) &&
																world.isAirBlock(master.xCoord-1, master.yCoord-2, master.zCoord-1)){
															if(world.isAirBlock(master.xCoord, master.yCoord+1, master.zCoord) &&
																	world.isAirBlock(master.xCoord, master.yCoord-1, master.zCoord)){
																return true;
															}
															return false;
														}
														return false;
													}	
													return false;
												}
												return false;
											}
											return false;
										}
										return false;
									}
									return false;
								}
								return false;
							}
							return false;
						}
						return false;
					}
					return false;
				}
				return false;
			}
			return false;
		}
		return false;
	}

	/**
	 * Checks if it has at least a 4 high pillar at the 4 corners.
	 */

	public boolean hasCorrectBase(){
		World world = master.getWorldObj();
		if(world.getBlock(master.xCoord, master.yCoord-2-1, master.zCoord) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord, master.yCoord-2-2, master.zCoord) instanceof BlockRadioWall &&
				world.getBlock(master.xCoord, master.yCoord-2-3, master.zCoord) instanceof BlockRadioWall){ // 1x3 column under the cube
			if(world.getBlock(master.xCoord, master.yCoord-2-4, master.zCoord-2) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord-2, master.yCoord-2-4, master.zCoord) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord, master.yCoord-2-4, master.zCoord+2) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord+2, master.yCoord-2-4, master.zCoord) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord-2, master.yCoord-2-4, master.zCoord-2) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord+2, master.yCoord-2-4, master.zCoord+2) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord-2, master.yCoord-2-4, master.zCoord+2) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord+2, master.yCoord-2-4, master.zCoord-2) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord-1, master.yCoord-2-4, master.zCoord-2) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord-1, master.yCoord-2-4, master.zCoord+2) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord+1, master.yCoord-2-4, master.zCoord+2) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord+1, master.yCoord-2-4, master.zCoord-2) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord-2, master.yCoord-2-4, master.zCoord-1) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord-2, master.yCoord-2-4, master.zCoord+1) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord+2, master.yCoord-2-4, master.zCoord-1) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord+2, master.yCoord-2-4, master.zCoord+1) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord, master.yCoord-2-4, master.zCoord) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord+1, master.yCoord-2-4, master.zCoord) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord-1, master.yCoord-2-4, master.zCoord) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord, master.yCoord-2-4, master.zCoord+1) instanceof BlockRadioWall &&
					world.getBlock(master.xCoord, master.yCoord-2-4, master.zCoord-1) instanceof BlockRadioWall){ // 5x5 with a cross base
				if(world.getBlock(master.xCoord-2, master.yCoord-2-5, master.zCoord+2) instanceof BlockRadioWall &&
						world.getBlock(master.xCoord-2, master.yCoord-2-5, master.zCoord-2) instanceof BlockRadioWall &&
						world.getBlock(master.xCoord+2, master.yCoord-2-5, master.zCoord+2) instanceof BlockRadioWall &&
						world.getBlock(master.xCoord+2, master.yCoord-2-5, master.zCoord-2) instanceof BlockRadioWall){ // 4 corners
					if(world.getBlock(master.xCoord-2, master.yCoord-2-6, master.zCoord+2) instanceof BlockRadioWall &&
							world.getBlock(master.xCoord-2, master.yCoord-2-6, master.zCoord-2) instanceof BlockRadioWall &&
							world.getBlock(master.xCoord+2, master.yCoord-2-6, master.zCoord+2) instanceof BlockRadioWall &&
							world.getBlock(master.xCoord+2, master.yCoord-2-6, master.zCoord-2) instanceof BlockRadioWall){
						if(world.getBlock(master.xCoord-2, master.yCoord-2-7, master.zCoord+2) instanceof BlockRadioWall &&
								world.getBlock(master.xCoord-2, master.yCoord-2-7, master.zCoord-2) instanceof BlockRadioWall &&
								world.getBlock(master.xCoord+2, master.yCoord-2-7, master.zCoord+2) instanceof BlockRadioWall &&
								world.getBlock(master.xCoord+2, master.yCoord-2-7, master.zCoord-2) instanceof BlockRadioWall){
							if(world.isAirBlock(master.xCoord+1, master.yCoord-2-4, master.zCoord+1) &&
									world.isAirBlock(master.xCoord+1, master.yCoord-2-4, master.zCoord-1) &&
									world.isAirBlock(master.xCoord-1, master.yCoord-2-4, master.zCoord+1) &&
									world.isAirBlock(master.xCoord-1, master.yCoord-2-4, master.zCoord-1)){
								if(world.isAirBlock(master.xCoord-2, master.yCoord-2-1, master.zCoord) &&
										world.isAirBlock(master.xCoord+2, master.yCoord-2-1, master.zCoord) &&
										world.isAirBlock(master.xCoord, master.yCoord-2-1, master.zCoord-2) &&
										world.isAirBlock(master.xCoord, master.yCoord-2-1, master.zCoord+2) &&
										world.isAirBlock(master.xCoord+2, master.yCoord-2-1, master.zCoord+2) &&
										world.isAirBlock(master.xCoord+2, master.yCoord-2-1, master.zCoord-2) &&
										world.isAirBlock(master.xCoord-2, master.yCoord-2-1, master.zCoord-2) &&
										world.isAirBlock(master.xCoord-2, master.yCoord-2-1, master.zCoord+2) &&
										world.isAirBlock(master.xCoord+1, master.yCoord-2-1, master.zCoord) &&
										world.isAirBlock(master.xCoord-1, master.yCoord-2-1, master.zCoord) &&
										world.isAirBlock(master.xCoord, master.yCoord-2-1, master.zCoord+1) &&
										world.isAirBlock(master.xCoord, master.yCoord-2-1, master.zCoord-1) &&
										world.isAirBlock(master.xCoord+1, master.yCoord-2-1, master.zCoord+1) &&
										world.isAirBlock(master.xCoord+1, master.yCoord-2-1, master.zCoord-1) &&
										world.isAirBlock(master.xCoord-1, master.yCoord-2-1, master.zCoord+1) &&
										world.isAirBlock(master.xCoord-1, master.yCoord-2-1, master.zCoord-1)){
									if(world.isAirBlock(master.xCoord-2, master.yCoord-2-2, master.zCoord) &&
											world.isAirBlock(master.xCoord+2, master.yCoord-2-2, master.zCoord) &&
											world.isAirBlock(master.xCoord, master.yCoord-2-2, master.zCoord-2) &&
											world.isAirBlock(master.xCoord, master.yCoord-2-2, master.zCoord+2) &&
											world.isAirBlock(master.xCoord+2, master.yCoord-2-2, master.zCoord+2) &&
											world.isAirBlock(master.xCoord+2, master.yCoord-2-2, master.zCoord-2) &&
											world.isAirBlock(master.xCoord-2, master.yCoord-2-2, master.zCoord-2) &&
											world.isAirBlock(master.xCoord-2, master.yCoord-2-2, master.zCoord+2) &&
											world.isAirBlock(master.xCoord+1, master.yCoord-2-2, master.zCoord) &&
											world.isAirBlock(master.xCoord-1, master.yCoord-2-2, master.zCoord) &&
											world.isAirBlock(master.xCoord, master.yCoord-2-2, master.zCoord+1) &&
											world.isAirBlock(master.xCoord, master.yCoord-2-2, master.zCoord-1) &&
											world.isAirBlock(master.xCoord+1, master.yCoord-2-2, master.zCoord+1) &&
											world.isAirBlock(master.xCoord+1, master.yCoord-2-2, master.zCoord-1) &&
											world.isAirBlock(master.xCoord-1, master.yCoord-2-2, master.zCoord+1) &&
											world.isAirBlock(master.xCoord-1, master.yCoord-2-2, master.zCoord-1)){
										if(world.isAirBlock(master.xCoord-2, master.yCoord-2-3, master.zCoord) &&
												world.isAirBlock(master.xCoord+2, master.yCoord-2-3, master.zCoord) &&
												world.isAirBlock(master.xCoord, master.yCoord-2-3, master.zCoord-2) &&
												world.isAirBlock(master.xCoord, master.yCoord-2-3, master.zCoord+2) &&
												world.isAirBlock(master.xCoord+2, master.yCoord-2-3, master.zCoord+2) &&
												world.isAirBlock(master.xCoord+2, master.yCoord-2-3, master.zCoord-2) &&
												world.isAirBlock(master.xCoord-2, master.yCoord-2-3, master.zCoord-2) &&
												world.isAirBlock(master.xCoord-2, master.yCoord-2-3, master.zCoord+2) &&
												world.isAirBlock(master.xCoord+1, master.yCoord-2-3, master.zCoord) &&
												world.isAirBlock(master.xCoord-1, master.yCoord-2-3, master.zCoord) &&
												world.isAirBlock(master.xCoord, master.yCoord-2-3, master.zCoord+1) &&
												world.isAirBlock(master.xCoord, master.yCoord-2-3, master.zCoord-1) &&
												world.isAirBlock(master.xCoord+1, master.yCoord-2-3, master.zCoord+1) &&
												world.isAirBlock(master.xCoord+1, master.yCoord-2-3, master.zCoord-1) &&
												world.isAirBlock(master.xCoord-1, master.yCoord-2-3, master.zCoord+1) &&
												world.isAirBlock(master.xCoord-1, master.yCoord-2-3, master.zCoord-1)){
											return true;
										}
										return false;
									}
									return false;
								}
								return false;
							}
							return false;
						}
						return false;
					}
					return false;
				}
				return false;
			}
			return false;
		}
		return false;
	}

	@Override
	public void run(){
		if(isSurrondedCorrectly() && hasCorrectBase()){
			NetworkHandler.sendToServer(new MessageRadioFormed(master.xCoord, master.yCoord, master.zCoord));
		}else{
			NetworkHandler.sendToServer(new MessageRadioUnformed(master.xCoord, master.yCoord, master.zCoord));
		}
	}

}
/**
 * Copyright (c) 11/feb/2015 Davide Cossu.
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

package com.minestellar.space.world.gen;

import com.minestellar.api.world.gen.WorldChunkManagerMinestellar;

import net.minecraft.world.biome.BiomeGenBase;

public class WorldChunkManagerSpace extends WorldChunkManagerMinestellar{

	@Override
	public BiomeGenBase getBiome(){
		return BiomeGenBaseSpace.space;
	}
}
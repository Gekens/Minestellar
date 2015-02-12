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

package com.minestellar.space.world;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

import com.minestellar.api.world.IMinestellarWorldProvider;
import com.minestellar.space.util.ConfigManagerSpace;
import com.minestellar.space.world.gen.ChunkProviderSpace;
import com.minestellar.space.world.gen.WorldChunkManagerSpace;

public class WorldProviderSpace extends WorldProvider implements IMinestellarWorldProvider{

	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderSpace(this.worldObj, this.worldObj.getSeed(), true);
	}
	
	@Override
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerSpace();
		this.dimensionId = ConfigManagerSpace.idDimensionSpace;
	}
	
	@Override
	public float getGravity() {
		return 0;
	}

	@Override
	public boolean hasAtmosphere() {
		return false;
	}

	@Override
	public long getDayLength() {
		return 0;
	}

	@Override
	public float getHeatLevelsDay() {
		return 0;
	}

	@Override
	public float getHeatLevelsNight() {
		return 0;
	}

	@Override
	public float getAirPressure() {
		return 0;
	}

	@Override
	public String getDimensionName() {
		return "Spaaaace!";
	}

}
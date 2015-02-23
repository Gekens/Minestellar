/**
 * Copyright (c) 22/Feb/2015 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.space.orbit.world;

import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

import com.minestellar.api.world.IMinestellarWorldProvider;
import com.minestellar.space.orbit.util.ConfigManagerOrbit;
import com.minestellar.space.orbit.world.gen.ChunkProviderOrbit;
import com.minestellar.space.orbit.world.gen.WorldChunkManagerOrbit;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderOrbit extends WorldProvider implements IMinestellarWorldProvider {
	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderOrbit(this.worldObj, this.worldObj.getSeed(), true);
	}

	@Override
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerOrbit();
		this.dimensionId = ConfigManagerOrbit.idDimensionOrbit;
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

	@SideOnly(Side.CLIENT)
	public boolean renderVoidFog() {
		return false;
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	@Override
	public IRenderHandler getSkyRenderer() {
		return new SkyRendererOrbit();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness(float par1) {
		return 0.3F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
		return Vec3.createVectorHelper(0.01F, 0.01F, 0.01F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3 getFogColor(float par1, float par2) {
		return Vec3.createVectorHelper(0.0F, 0.0F, 0.0F);
	}

	@Override
	public String getDimensionName() {
		return "Orbit";
	}
}
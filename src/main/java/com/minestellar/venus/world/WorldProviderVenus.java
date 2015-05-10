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

package com.minestellar.venus.world;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

import com.minestellar.api.vector.Vector3;
import com.minestellar.api.world.IMinestellarWorldProvider;
import com.minestellar.venus.util.ConfigManagerVenus;
import com.minestellar.venus.world.gen.ChunkProviderVenus;
import com.minestellar.venus.world.gen.WorldChunkManagerVenus;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderVenus extends WorldProvider implements IMinestellarWorldProvider {
	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderVenus(this.worldObj, this.worldObj.getSeed(), true);
	}

	@Override
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerVenus();
		this.dimensionId = ConfigManagerVenus.idDimensionVenus;
	}

	@Override
	public IRenderHandler getSkyRenderer() {
		return new SkyRendererVenus(null);
	}

	@Override
	public String getSaveFolder() {
		return "DIM" + ConfigManagerVenus.idDimensionVenus;
	}

	@Override
	public String getDimensionName() {
		return "Venus";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness(float par1) {
		float f1 = this.worldObj.getCelestialAngle(par1);
		float f2 = 1.0F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

		if (f2 < 0.0F) {
			f2 = 0.0F;
		}

		if (f2 > 1.0F) {
			f2 = 1.0F;
		}

		return f2 * f2 * 0.75F;
	}

	@Override
	public Vector3 getFogColor() {
		return new Vector3(200, 150, 5);
	}

	@Override
	public Vector3 getSkyColor() {
		return new Vector3(200, 150, 5);
	}


	@Override
	public float[] calcSunriseSunsetColors(float var1, float var2) {
		return null;
	}

	@SideOnly(Side.CLIENT)
	public boolean renderVoidFog() {
		return true;
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public boolean isSurfaceWorld() {
		return true;
	}

	@Override
	protected void generateLightBrightnessTable() {
		final float var1 = 0.0F;

		for (int var2 = 0; var2 <= 15; ++var2) {
			final float var3 = 1.0F - var2 / 15.0F;
			this.lightBrightnessTable[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Vec3 getFogColor(float var1, float var2) {
		return Vec3.createVectorHelper((double) 210F / 255F, (double) 120F / 255F, (double) 59F / 255F);
	}

	@Override
	public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
		return Vec3.createVectorHelper(154 / 255.0F, 114 / 255.0F, 66 / 255.0F);
	}

	@Override
	public float calculateCelestialAngle(long par1, float par3) {
		return super.calculateCelestialAngle(par1, par3);
	}

	public float calculatePhobosAngle(long par1, float par3) {
		return this.calculateCelestialAngle(par1, par3) * 3000;
	}

	public float calculateDeimosAngle(long par1, float par3) {
		return this.calculatePhobosAngle(par1, par3) * 0.0000000001F;
	}

	@Override
	public float getGravity() {
		return 0.034F;
	}

	@Override
	public boolean hasAtmosphere() {
		return true;
	}

	@Override
	public long getDayLength() {
		return 895200L; // TODO
	}

	@Override
	public float getHeatLevelsDay() {
		return 200F; // TODO
	}

	@Override
	public float getHeatLevelsNight() {
		return 173F; // TODO
	}

	@Override
	public float getAirPressure() {
		return 26F; // TODO
	}
}

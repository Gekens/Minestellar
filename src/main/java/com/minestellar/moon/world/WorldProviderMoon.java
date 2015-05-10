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

package com.minestellar.moon.world;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

import com.minestellar.api.vector.Vector3;
import com.minestellar.api.world.IMinestellarWorldProvider;
import com.minestellar.moon.util.ConfigManagerMoon;
import com.minestellar.moon.world.gen.ChunkProviderMoon;
import com.minestellar.moon.world.gen.WorldChunkManagerMoon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderMoon extends WorldProvider implements IMinestellarWorldProvider {
	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderMoon(this.worldObj, this.worldObj.getSeed(), true);
	}

	@Override
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerMoon();
		this.dimensionId = ConfigManagerMoon.idDimensionMoon;
	}

	@Override
	public IRenderHandler getSkyRenderer() {
		return new SkyRendererMoon(null);
	}

	@Override
	public String getSaveFolder() {
		return "DIM" + ConfigManagerMoon.idDimensionMoon;
	}

	@Override
	public String getDimensionName() {
		return "Moon";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness(float par1) {
		final float var2 = this.worldObj.getCelestialAngle(par1);
		float var3 = 1.0F - (MathHelper.cos(var2 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

		var3 = 0.0F;

		return var3 * var3 * 0.5F + 0.3F;
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
	@SideOnly(Side.CLIENT)
	public float[] calcSunriseSunsetColors(float p_76560_1_, float p_76560_2_) {
		float[] sunriseColors = new float[4];

		sunriseColors[0] = 0.0F;
		sunriseColors[1] = 0.0F;
		sunriseColors[2] = 0.0F;
		sunriseColors[3] = 0.0F;

		return sunriseColors;
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

	@Override
	public Vector3 getSkyColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector3 getFogColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getGravity() {
		return 0.062F;
	}

	@Override
	public boolean hasAtmosphere() {
		return false;
	}

	@Override
	public long getDayLength() {
		return 655200L;
	}

	@Override
	public float getHeatLevelsDay() {
		return 100F;
	}

	@Override
	public float getHeatLevelsNight() {
		return -173F;
	}

	@Override
	public float getAirPressure() {
		return 0F;
	}
}

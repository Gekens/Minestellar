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

package com.minestellar.world.provider;

import net.minecraft.entity.Entity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.common.DimensionManager;

import com.minestellar.reference.DimensionIDs;
import com.minestellar.world.WorldChunkManagerMoon;
import com.minestellar.world.provider.chunk.ChunkProviderMoon;
import com.minestellar.world.renderer.MoonSkyRenderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderMoon extends WorldProvider{

	/** tells Minecraft to use our new Terrain Generator */
	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderMoon(this.worldObj, this.worldObj.getSeed(), true);
	}

	@Override
	/** tells Minecraft to use our new WorldChunkManager **/
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerMoon(worldObj.getSeed(), terrainType);
		this.dimensionId = DimensionIDs.MOON_DIMENSION;
	}
	
	/** Get Provider for Dimension **/
	public static WorldProvider getProviderForDimension(int id){
		return DimensionManager.createProviderFor(DimensionIDs.MOON_DIMENSION);
	}

	@Override
	/**
	 * @return the name of the dimension
	 */
	public String getDimensionName() {
		return "Moon Plains";
	}

	@Override
	/** sets/creates the save folder */
	public String getSaveFolder() {
			return "DIM" + DimensionIDs.MOON_DIMENSION;
	}

	@SideOnly(Side.CLIENT)
	/** should stars be rendered? */
	public boolean renderStars() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	/** @return the player speed */
	public double getMovementFactor() {
		return 0.04;
	}

	@SideOnly(Side.CLIENT)
	/** @return the light value of the stars*/
	public float getStarBrightness(World world, float f) {
		return 1.0F;
	}

	@SideOnly(Side.CLIENT)
	/** should clouds be rendered? */
	public boolean renderClouds() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public boolean renderVoidFog() {
		return false;
	}
	
	/** should the end sky be rendered or the overworld sky? */
	@SideOnly(Side.CLIENT)
	public boolean renderEndSky() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	/** @return the size of the sun */
	public float setSunSize() {
		return 0.5F;
	}

	/** @return the size of the moon */
	@SideOnly(Side.CLIENT)
	public float setMoonSize() {
		return 0.0F;
	}

	/**
	 * @return the sky color
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
		return worldObj.getSkyColorBody(cameraEntity, partialTicks);
	}

	@SideOnly(Side.CLIENT)
	/** should a color for the sky be rendered? */
	public boolean isSkyColored(){
		return false;
	}

	/** can the player respawn in this dimension? */
	@Override
	public boolean canRespawnHere(){
		return true;
	}
	
	/** is this a surface world or an underworld */
	@Override
	public boolean isSurfaceWorld(){
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	/** @return the high of the clouds */
	public float getCloudHeight(){
		return 0;
	}

	@Override
	public ChunkCoordinates getEntrancePortalLocation(){
		return new ChunkCoordinates(50, 5, 0);
	}


	/** the light value in this dimension */
	@Override
	protected void generateLightBrightnessTable(){
		float f = 0.0F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
	}


	/** @return the dimension join message */
	@Override
	@SideOnly(Side.CLIENT)
	public String getWelcomeMessage(){
		return "Entering the Dimension";
	}


	/** @return the dimension leave message */
	@Override
	@SideOnly(Side.CLIENT)
	public String getDepartMessage(){
		return "Leaving the Dimension";
	}

	@Override
	public IRenderHandler getSkyRenderer() {
		return new MoonSkyRenderer();
	}

	@Override
	public IRenderHandler getCloudRenderer() {
		return null;
	}

	@Override
	public IRenderHandler getWeatherRenderer() {
		return null;
	}

	@Override
	public Vec3 drawClouds(float partialTicks){
		return super.drawClouds(partialTicks);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3 getFogColor(float par1, float par2){
		 float f2 = MathHelper.cos(par1 * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

	        if (f2 < 0.0F){
	            f2 = 0.0F;
	        }

	        if (f2 > 1.0F){
	            f2 = 1.0F;
	        }

	        float f3 = 0.7529412F;
	        float f4 = 0.84705883F;
	        float f5 = 1.0F;
	        f3 *= f2 * 0.94F + 0.06F;
	        f4 *= f2 * 0.94F + 0.06F;
	        f5 *= f2 * 0.91F + 0.09F;
	        return Vec3.createVectorHelper((double)f3, (double)f4, (double)f5);
	}
	
}
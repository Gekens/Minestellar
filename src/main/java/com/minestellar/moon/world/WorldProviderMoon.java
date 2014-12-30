package com.minestellar.moon.world;

import net.minecraft.entity.Entity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.common.DimensionManager;

import com.minestellar.api.vector.Vector3;
import com.minestellar.api.world.MinestellarWorldProvider;
import com.minestellar.moon.util.ConfigManagerMoon;
import com.minestellar.moon.world.gen.ChunkProviderMoon;
import com.minestellar.moon.world.gen.WorldChunkManagerMoon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderMoon extends WorldProvider implements MinestellarWorldProvider
{    
	/** Tells Minecraft to use our new Terrain Generator. */
	@Override
	public IChunkProvider createChunkGenerator() 
	{
		return new ChunkProviderMoon(this.worldObj, this.worldObj.getSeed(), true);
	}

	@Override
	/** Tells Minecraft to use our new WorldChunkManager. **/
	public void registerWorldChunkManager() 
	{
		this.worldChunkMgr = new WorldChunkManagerMoon();
		this.dimensionId = ConfigManagerMoon.idDimensionMoon;
	}

	/** Get Provider for Dimension. **/
	public static WorldProvider getProviderForDimension(int id)
	{
		return DimensionManager.createProviderFor(ConfigManagerMoon.idDimensionMoon);
	}

	@Override
	/** @return the name of the dimension. */
	public String getDimensionName() 
	{
		return "Moon";
	}

	@Override
	/** Sets/creates the save folder. */
	public String getSaveFolder() 
	{
		return "DIM" + ConfigManagerMoon.idDimensionMoon;
	}

	@SideOnly(Side.CLIENT)
	/** Should stars be rendered? */
	public boolean renderStars() 
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	/** @return the player speed. */
	public double getMovementFactor() 
	{
		return 0.04D;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	/** @return the light value of the stars. */
	public float getStarBrightness(float par1)
	{
		final float var2 = this.worldObj.getCelestialAngle(par1);
		float var3 = 1.0F - (MathHelper.cos(var2 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

		if (var3 < 0.0F)
		{
			var3 = 0.0F;
		}

		if (var3 > 1.0F)
		{
			var3 = 1.0F;
		}

		return var3 * var3 * 0.5F + 0.3F;
	}

	@SideOnly(Side.CLIENT)
	/** Should clouds be rendered? */
	public boolean renderClouds() 
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	public boolean renderVoidFog() 
	{
		return false;
	}

	/** Should the end sky be rendered or the overworld sky? */
	@SideOnly(Side.CLIENT)
	public boolean renderEndSky() 
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	/** @return the size of the sun. */
	public float setSunSize() 
	{
		return 0.5F;
	}

	/** @return the size of the moon. */
	@SideOnly(Side.CLIENT)
	public float setMoonSize() 
	{
		return 9.0F;
	}

	/** @return the sky color. */
	 
	@Override
	@SideOnly(Side.CLIENT)
	public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) 
	{
		return Vec3.createVectorHelper(0.01F, 0.01F, 0.01F);
	}

	@SideOnly(Side.CLIENT)
	/** Should a color for the sky be rendered? */
	public boolean isSkyColored()
	{
		return true;
	}

	/** Can the player respawn in this dimension? */
	@Override
	public boolean canRespawnHere()
	{
		return true;
	}

	/** Is this a surface world or an underworld? */
	@Override
	public boolean isSurfaceWorld()
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	/** @return the high of the clouds. */
	public float getCloudHeight()
	{
		return 0;
	}

	@Override
	public ChunkCoordinates getEntrancePortalLocation()
	{
		return new ChunkCoordinates(50, 5, 0);
	}

	/** The light value in this dimension. */
	@Override
	protected void generateLightBrightnessTable()
	{
		final float var1 = 0.0F;

		for (int var2 = 0; var2 <= 15; ++var2)
		{
			final float var3 = 1.0F - var2 / 15.0F;
			this.lightBrightnessTable[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
		}
	}

	/** @return the dimension join message. */
	@Override
	@SideOnly(Side.CLIENT)
	public String getWelcomeMessage()
	{
		return "Entering the Moon";
	}

	/** @return the dimension leave message. */
	@Override
	@SideOnly(Side.CLIENT)
	public String getDepartMessage()
	{
		return "Leaving the Moon";
	}

	@Override
	public IRenderHandler getSkyRenderer() 
	{
		return new SkyRendererMoon(null);
	}

	@Override
	public IRenderHandler getCloudRenderer() 
	{
		return null;
	}

	@Override
	public IRenderHandler getWeatherRenderer() 
	{
		return null;
	}

	@Override
	public Vec3 drawClouds(float partialTicks)
	{
		return super.drawClouds(partialTicks);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3 getFogColor(float par1, float par2)
	{
		return Vec3.createVectorHelper(0.0F, 0.0F, 0.0F);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public float[] calcSunriseSunsetColors(float p_76560_1_, float p_76560_2_){
		
		float[] sunriseColors = new float[4];
		
		sunriseColors[0] = 0.0F;
		sunriseColors[1] = 0.0F;
		sunriseColors[2] = 0.0F;
		sunriseColors[3] = 0.0F;
		
		return sunriseColors;
	}
	
    @Override
    public float getGravity()
    {
        return 0.062F;
    }

	@Override
	public float getFallDamageModifier()
	{
		return 0.18F;
	}

	@Override
	public boolean hasBreathableAtmosphere()
	{
		return false;
	}
	
    @Override
    public long getDayLength()
    {
        return 655200L;
    }

	@Override
	public Vector3 getFogColor()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector3 getSkyColor()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
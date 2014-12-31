package com.minestellar.venus.world;

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
import com.minestellar.venus.util.ConfigManagerVenus;
import com.minestellar.venus.world.gen.ChunkProviderVenus;
import com.minestellar.venus.world.gen.WorldChunkManagerVenus;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderVenus extends WorldProvider implements MinestellarWorldProvider
{    
	@Override
	public Vector3 getFogColor()
	{
		return new Vector3(200, 150, 5);
	}

	@Override
	public Vector3 getSkyColor()
	{
		return new Vector3(200, 150, 5);
	}

	
	/** Tells Minecraft to use our new Terrain Generator. */
	@Override
	public IChunkProvider createChunkGenerator() 
	{
		return new ChunkProviderVenus(this.worldObj, this.worldObj.getSeed(), true);
	}

	@Override
	/** Tells Minecraft to use our new WorldChunkManager. **/
	public void registerWorldChunkManager() 
	{
		this.worldChunkMgr = new WorldChunkManagerVenus();
		this.dimensionId = ConfigManagerVenus.idDimensionVenus;
	}

	/** Get Provider for Dimension. **/
	public static WorldProvider getProviderForDimension(int id)
	{
		return DimensionManager.createProviderFor(ConfigManagerVenus.idDimensionVenus);
	}

	@Override
	/** @return the name of the dimension. */
	public String getDimensionName() 
	{
		return "Venus";
	}

	@Override
	/** Sets/creates the save folder. */
	public String getSaveFolder() 
	{
		return "DIM" + ConfigManagerVenus.idDimensionVenus;
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
        float f1 = this.worldObj.getCelestialAngle(par1);
        float f2 = 1.0F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        return f2 * f2 * 0.75F;
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
	public Vec3 getSkyColor(Entity cameraEntity, float partialTicks)
	{
		return Vec3.createVectorHelper(154 / 255.0F, 114 / 255.0F, 66 / 255.0F);
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
		return "Entering Venus";
	}

	/** @return the dimension leave message. */
	@Override
	@SideOnly(Side.CLIENT)
	public String getDepartMessage()
	{
		return "Leaving Venus";
	}

	@Override
	public IRenderHandler getSkyRenderer() 
	{
		return new SkyProviderVenus(null);
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

	@SideOnly(Side.CLIENT)
	@Override
	public Vec3 getFogColor(float var1, float var2)
	{
		return Vec3.createVectorHelper((double) 210F / 255F, (double) 120F / 255F, (double) 59F / 255F);
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
        return 0.90F;
    }

	@Override
	public float getFallDamageModifier()
	{
		return 0.90F;
	}

	@Override
	public boolean hasBreathableAtmosphere()
	{
		return false;
	}
	
    @Override
    public long getDayLength()
    {
    	if (ConfigManagerVenus.idDayLength == false)
    	{
    		return 24000L;
    	}
    	
    	else
    	{
    		return 160000L;
    	}
    }
}
package com.minestellar.moon.world;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.IRenderHandler;

import org.lwjgl.opengl.GL11;

import com.minestellar.api.world.MinestellarWorldProvider;
import com.minestellar.core.ConfigManagerCore;
import com.minestellar.moon.MoonCore;

import cpw.mods.fml.client.FMLClientHandler;

public class SkyRendererMoon extends IRenderHandler
{
<<<<<<< HEAD
	private int starGLCallList;
	private int glSkyList;
	private int glSkyList2;

	public SkyRendererMoon()
	{
		RenderGlobal renderGlobal = Minecraft.getMinecraft().renderGlobal;
		this.glSkyList2 = (this.glSkyList = (this.starGLCallList = ReflectionHelper.getPrivateValue(RenderGlobal.class, renderGlobal, "starGLCallList")) + 1) + 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void render(float partialTicks, WorldClient world, Minecraft mc)
	{
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		Vec3 vec3 = world.getSkyColor(mc.renderViewEntity, partialTicks);
		float f1 = (float) vec3.xCoord;
		float f2 = (float) vec3.yCoord;
		float f3 = (float) vec3.zCoord;
		float f4;
		
		if (mc.gameSettings.anaglyph)
		{
			float f5 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
			float f6 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
			f4 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
			f1 = f5;
			f2 = f6;
			f3 = f4;
		}
		
		GL11.glColor3f(f1, f2, f3);
		Tessellator tessellator1 = Tessellator.instance;
		GL11.glDepthMask(false);
		GL11.glEnable(GL11.GL_FOG);
		GL11.glColor3f(f1, f2, f3);
		GL11.glCallList(this.glSkyList);
		GL11.glDisable(GL11.GL_FOG);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		RenderHelper.disableStandardItemLighting();
		float[] afloat = world.provider.calcSunriseSunsetColors(world.getCelestialAngle(partialTicks), partialTicks);
		float f7;
		float f8;
		float f9;
		float f10;
		
		if(afloat != null)
		{
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			GL11.glPushMatrix();
			GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(MathHelper.sin(world.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
			f4 = afloat[0];
			f7 = afloat[1];
			f8 = afloat[2];
			float f11;
			
			if (mc.gameSettings.anaglyph)
			{
				f9 = (f4 * 30.0F + f7 * 59.0F + f8 * 11.0F) / 100.0F;
				f10 = (f4 * 30.0F + f7 * 70.0F) / 100.0F;
				f11 = (f4 * 30.0F + f8 * 70.0F) / 100.0F;
				f4 = f9;
				f7 = f10;
				f8 = f11;
			}
			
			tessellator1.startDrawing(6);
			tessellator1.setColorRGBA_F(f4, f7, f8, afloat[3]);
			tessellator1.addVertex(0.0D, 100.0D, 0.0D);
			byte b0 = 16;
			tessellator1.setColorRGBA_F(afloat[0], afloat[1], afloat[2], 0.0F);
			
			for(int j = 0; j <= b0; ++j)
			{
				f11 = (float) j * (float) Math.PI * 2.0F / (float) b0;
				float f12 = MathHelper.sin(f11);
				float f13 = MathHelper.cos(f11);
				tessellator1.addVertex((double) (f12 * 120.0F), (double) (f13 * 120.0F), (double) (-f13 * 40.0F * afloat[3]));
			}
			
			tessellator1.draw();
			GL11.glPopMatrix();
			GL11.glShadeModel(GL11.GL_FLAT);
		}
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GL11.glPushMatrix();
		f4 = 1.0F - world.getRainStrength(partialTicks);
		f7 = 0.0F;
		f8 = 0.0F;
		f9 = 0.0F;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, f4);
		GL11.glTranslatef(f7, f8, f9);
		GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
		
		
		f10 = 20.0F; // Size of sun from center
		mc.renderEngine.getTexture(new ResourceLocation(Minestellar.TEXTURE_PREFIX + "/textures/enviroment/sun.png")); 
		
		tessellator1.startDrawingQuads();
		tessellator1.addVertexWithUV((double) (-f10), 100.0D, (double) (-f10), 0.0D, 0.0D);
		tessellator1.addVertexWithUV((double) f10, 100.0D, (double) (-f10), 1.0D, 0.0D);
		tessellator1.addVertexWithUV((double) f10, 100.0D, (double) f10, 1.0D, 1.0D);
		tessellator1.addVertexWithUV((double) (-f10), 100.0D, (double) f10, 0.0D, 1.0D);
		
		tessellator1.draw(); // Draw sun
		
		f10 = 15.0F; // Size of moon from center
		mc.renderEngine.bindTexture(new ResourceLocation(MoonCore.TEXTURE_PREFIX + "/textures/environment/earth_phases.png"));
		
		int k = world.getMoonPhase();
		int l = k % 4;
		int i1 = k / 4 % 2;
		float f14 = (float) (l + 0) / 4.0F;
		float f15 = (float) (i1 + 0) / 2.0F;
		float f16 = (float) (l + 1) / 4.0F;
		float f17 = (float) (i1 + 1) / 2.0F;
		tessellator1.startDrawingQuads();
		tessellator1.addVertexWithUV((double) (-f10), -100.0D, (double) f10, (double) f16, (double) f17);
		tessellator1.addVertexWithUV((double) f10, -100.0D, (double) f10, (double) f14, (double) f17);
		tessellator1.addVertexWithUV((double) f10, -100.0D, (double) (-f10), (double) f14, (double) f15);
		tessellator1.addVertexWithUV((double) (-f10), -100.0D, (double) (-f10), (double) f16, (double) f15);
		
		tessellator1.draw(); //Draw "moon"
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		float f18 = world.getStarBrightness(partialTicks) * f4;
		
		if (f18 > 0.0F)
		{
			GL11.glColor4f(f18, f18, f18, f18);
			GL11.glCallList(this.starGLCallList);
		}
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_FOG);
		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor3f(0.0F, 0.0F, 0.0F);
		double d0 = mc.thePlayer.getPosition(partialTicks).yCoord - world.getHorizon();
		
		if (d0 < 0.0D)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, 12.0F, 0.0F);
			GL11.glCallList(this.glSkyList2);
			GL11.glPopMatrix();
			f8 = 1.0F;
			f9 = -((float) (d0 + 65.0D));
			f10 = -f8;
			tessellator1.startDrawingQuads();
			tessellator1.setColorRGBA_I(0, 255);
			tessellator1.addVertex((double) (-f8), (double) f9, (double) f8);
			tessellator1.addVertex((double) f8, (double) f9, (double) f8);
			tessellator1.addVertex((double) f8, (double) f10, (double) f8);
			tessellator1.addVertex((double) (-f8), (double) f10, (double) f8);
			tessellator1.addVertex((double) (-f8), (double) f10, (double) (-f8));
			tessellator1.addVertex((double) f8, (double) f10, (double) (-f8));
			tessellator1.addVertex((double) f8, (double) f9, (double) (-f8));
			tessellator1.addVertex((double) (-f8), (double) f9, (double) (-f8));
			tessellator1.addVertex((double) f8, (double) f10, (double) (-f8));
			tessellator1.addVertex((double) f8, (double) f10, (double) f8);
			tessellator1.addVertex((double) f8, (double) f9, (double) f8);
			tessellator1.addVertex((double) f8, (double) f9, (double) (-f8));
			tessellator1.addVertex((double) (-f8), (double) f9, (double) (-f8));
			tessellator1.addVertex((double) (-f8), (double) f9, (double) f8);
			tessellator1.addVertex((double) (-f8), (double) f10, (double) f8);
			tessellator1.addVertex((double) (-f8), (double) f10, (double) (-f8));
			tessellator1.addVertex((double) (-f8), (double) f10, (double) (-f8));
			tessellator1.addVertex((double) (-f8), (double) f10, (double) f8);
			tessellator1.addVertex((double) f8, (double) f10, (double) f8);
			tessellator1.addVertex((double) f8, (double) f10, (double) (-f8));
			tessellator1.draw();
		}
		
		if (world.provider.isSkyColored())
		{
			GL11.glColor3f(0.2F + 0.04F, f2 * 0.2F + 0.04F, f3 * 0.6F + 0.1F);
		}
		
		else
		{
			GL11.glColor3f(f1, f2, f3);
		}
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, -((float) (d0 - 16.0D)), 0.0F);
		GL11.glCallList(this.glSkyList2);
		GL11.glPopMatrix();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDepthMask(true);
	}
}
=======
	private static final ResourceLocation sunTexture = new ResourceLocation("textures/environment/sun.png");
    private static final ResourceLocation earthTexture = new ResourceLocation(MoonCore.ASSET_PREFIX, "textures/environment/earth_phases.png");

    public int starGLCallList = GLAllocation.generateDisplayLists(3);
    public int glSkyList;
    public int glSkyList2;
    
    private float sunSize;

    public SkyRendererMoon(MinestellarWorldProvider moonProvider)
    {
//        this.sunSize = 17.5F * moonProvider.getSolarSize();

    	GL11.glPushMatrix();
        GL11.glNewList(this.starGLCallList, GL11.GL_COMPILE);
        this.renderStars();
        GL11.glEndList();
        GL11.glPopMatrix();
        final Tessellator tessellator = Tessellator.instance;
        this.glSkyList = this.starGLCallList + 1;
        GL11.glNewList(this.glSkyList, GL11.GL_COMPILE);
        final byte byte2 = 64;
        final int i = 256 / byte2 + 2;
        float f = 16F;

        for (int j = -byte2 * i; j <= byte2 * i; j += byte2)
        {
            for (int l = -byte2 * i; l <= byte2 * i; l += byte2)
            {
                tessellator.startDrawingQuads();
                tessellator.addVertex(j + 0, f, l + 0);
                tessellator.addVertex(j + byte2, f, l + 0);
                tessellator.addVertex(j + byte2, f, l + byte2);
                tessellator.addVertex(j + 0, f, l + byte2);
                tessellator.draw();
            }
        }

        GL11.glEndList();
        this.glSkyList2 = this.starGLCallList + 2;
        GL11.glNewList(this.glSkyList2, GL11.GL_COMPILE);
        f = -16F;
        tessellator.startDrawingQuads();

        for (int k = -byte2 * i; k <= byte2 * i; k += byte2)
        {
            for (int i1 = -byte2 * i; i1 <= byte2 * i; i1 += byte2)
            {
                tessellator.addVertex(k + byte2, f, i1 + 0);
                tessellator.addVertex(k + 0, f, i1 + 0);
                tessellator.addVertex(k + 0, f, i1 + byte2);
                tessellator.addVertex(k + byte2, f, i1 + byte2);
            }
        }

        tessellator.draw();
        GL11.glEndList();
    }

    @Override
    public void render(float partialTicks, WorldClient world, Minecraft mc)
    {
        WorldProviderMoon gcProvider = null;

        if (world.provider instanceof WorldProviderMoon)
        {
            gcProvider = (WorldProviderMoon) world.provider;
        }

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor3f(1, 1, 1);
        final Tessellator var23 = Tessellator.instance;
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_FOG);
        GL11.glColor3f(0, 0, 0);
        GL11.glCallList(this.glSkyList);
        GL11.glDisable(GL11.GL_FOG);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderHelper.disableStandardItemLighting();
        float var10;
        float var11;
        float var12;

        float var20 = 0;

        if (gcProvider != null)
        {
            var20 = gcProvider.getStarBrightness(partialTicks);
        }

        if (var20 > 0.0F)
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, var20);
            GL11.glCallList(this.starGLCallList);
        }

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glPushMatrix();

        GL11.glPopMatrix();

        GL11.glPushMatrix();

        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 5F);
        GL11.glRotatef(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
        var12 = 30.0F;
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(SkyRendererMoon.sunTexture);
        var23.startDrawingQuads();
        var23.addVertexWithUV(-var12, 150.0D, -var12, 0.0D, 0.0D);
        var23.addVertexWithUV(var12, 150.0D, -var12, 1.0D, 0.0D);
        var23.addVertexWithUV(var12, 150.0D, var12, 1.0D, 1.0D);
        var23.addVertexWithUV(-var12, 150.0D, var12, 0.0D, 1.0D);
        var23.draw();

        GL11.glPopMatrix();

        GL11.glPushMatrix();

        GL11.glDisable(GL11.GL_BLEND);

        // HOME:
        var12 = 50.0F;
        final float earthRotation = (float) (world.getSpawnPoint().posZ - mc.thePlayer.posZ) * 0.01F;
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        GL11.glRotatef(earthRotation, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(200F, 1.0F, 0.0F, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(SkyRendererMoon.earthTexture);
        world.getMoonPhase();
        var23.startDrawingQuads();
        var23.addVertexWithUV(-var12, -500.0D, var12, 0, 1);
        var23.addVertexWithUV(var12, -500.0D, var12, 1, 1);
        var23.addVertexWithUV(var12, -500.0D, -var12, 1, 0);
        var23.addVertexWithUV(-var12, -500.0D, -var12, 0, 0);
        var23.draw();

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_FOG);
        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor3f(0.0F, 0.0F, 0.0F);
        final double var25 = mc.thePlayer.getPosition(partialTicks).yCoord - world.getHorizon();

        if (var25 < 0.0D)
        {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 12.0F, 0.0F);
            GL11.glCallList(this.glSkyList2);
            GL11.glPopMatrix();
            var10 = 1.0F;
            var11 = -((float) (var25 + 65.0D));
            var12 = -var10;
            var23.startDrawingQuads();
            var23.setColorRGBA_I(0, 255);
            var23.addVertex(-var10, var11, var10);
            var23.addVertex(var10, var11, var10);
            var23.addVertex(var10, var12, var10);
            var23.addVertex(-var10, var12, var10);
            var23.addVertex(-var10, var12, -var10);
            var23.addVertex(var10, var12, -var10);
            var23.addVertex(var10, var11, -var10);
            var23.addVertex(-var10, var11, -var10);
            var23.addVertex(var10, var12, -var10);
            var23.addVertex(var10, var12, var10);
            var23.addVertex(var10, var11, var10);
            var23.addVertex(var10, var11, -var10);
            var23.addVertex(-var10, var11, -var10);
            var23.addVertex(-var10, var11, var10);
            var23.addVertex(-var10, var12, var10);
            var23.addVertex(-var10, var12, -var10);
            var23.addVertex(-var10, var12, -var10);
            var23.addVertex(-var10, var12, var10);
            var23.addVertex(var10, var12, var10);
            var23.addVertex(var10, var12, -var10);
            var23.draw();
        }

        GL11.glColor3f(70F / 256F, 70F / 256F, 70F / 256F);

        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, -((float) (var25 - 16.0D)), 0.0F);
        GL11.glCallList(this.glSkyList2);
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(true);
    }

    private void renderStars()
    {
        final Random var1 = new Random(10842L);
        final Tessellator var2 = Tessellator.instance;
        var2.startDrawingQuads();

        for (int var3 = 0; var3 < (ConfigManagerCore.moreStars ? 20000 : 6000); ++var3)
        {
            double var4 = var1.nextFloat() * 2.0F - 1.0F;
            double var6 = var1.nextFloat() * 2.0F - 1.0F;
            double var8 = var1.nextFloat() * 2.0F - 1.0F;
            final double var10 = 0.15F + var1.nextFloat() * 0.1F;
            double var12 = var4 * var4 + var6 * var6 + var8 * var8;

            if (var12 < 1.0D && var12 > 0.01D)
            {
                var12 = 1.0D / Math.sqrt(var12);
                var4 *= var12;
                var6 *= var12;
                var8 *= var12;
                final double var14 = var4 * (ConfigManagerCore.moreStars ? var1.nextDouble() * 100D + 150D : 100.0D);
                final double var16 = var6 * (ConfigManagerCore.moreStars ? var1.nextDouble() * 100D + 150D : 100.0D);
                final double var18 = var8 * (ConfigManagerCore.moreStars ? var1.nextDouble() * 100D + 150D : 100.0D);
                final double var20 = Math.atan2(var4, var8);
                final double var22 = Math.sin(var20);
                final double var24 = Math.cos(var20);
                final double var26 = Math.atan2(Math.sqrt(var4 * var4 + var8 * var8), var6);
                final double var28 = Math.sin(var26);
                final double var30 = Math.cos(var26);
                final double var32 = var1.nextDouble() * Math.PI * 2.0D;
                final double var34 = Math.sin(var32);
                final double var36 = Math.cos(var32);

                for (int var38 = 0; var38 < 4; ++var38)
                {
                    final double var39 = 0.0D;
                    final double var41 = ((var38 & 2) - 1) * var10;
                    final double var43 = ((var38 + 1 & 2) - 1) * var10;
                    final double var47 = var41 * var36 - var43 * var34;
                    final double var49 = var43 * var36 + var41 * var34;
                    final double var53 = var47 * var28 + var39 * var30;
                    final double var55 = var39 * var28 - var47 * var30;
                    final double var57 = var55 * var22 - var49 * var24;
                    final double var61 = var49 * var22 + var55 * var24;
                    var2.addVertex(var14 + var57, var16 + var53, var18 + var61);
                }
            }
        }

        var2.draw();
    }

    private Vec3 getCustomSkyColor()
    {
        return Vec3.createVectorHelper(0.26796875D, 0.1796875D, 0.0D);
    }

    public float getSkyBrightness(float par1)
    {
        final float var2 = FMLClientHandler.instance().getClient().theWorld.getCelestialAngle(par1);
        float var3 = 1.0F - (MathHelper.sin(var2 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

        if (var3 < 0.0F)
        {
            var3 = 0.0F;
        }

        if (var3 > 1.0F)
        {
            var3 = 1.0F;
        }

        return var3 * var3 * 1F;
    }
}
>>>>>>> 6c51eb4dea33b5e1688b235115e8fe5da3617867

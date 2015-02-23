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

package com.minestellar.core.world;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;

import com.minestellar.api.vector.Vector3;
import com.minestellar.api.world.IMinestellarWorldProvider;
import com.minestellar.moon.world.WorldProviderMoon;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldUtil {
	public static double getGravityForEntity(Entity entity) {
		if (entity.worldObj.provider instanceof IMinestellarWorldProvider) {
			final IMinestellarWorldProvider customProvider = (IMinestellarWorldProvider) entity.worldObj.provider;
			return 0.08D - customProvider.getGravity();
		} else {
			return 0.08D;
		}
	}

	public static double getItemGravity(EntityItem e) {
		if (e.worldObj.provider instanceof IMinestellarWorldProvider) {
			final IMinestellarWorldProvider customProvider = (IMinestellarWorldProvider) e.worldObj.provider;
			return Math.max(0.002D, 0.03999999910593033D - (customProvider.getGravity()) / 1.75D);
		} else {
			return 0.03999999910593033D;
		}
	}

	public static boolean shouldRenderFire(Entity entity) {
		if (!(entity instanceof EntityLivingBase)) {
			return entity.isBurning();
		}

		return !(entity.worldObj.provider instanceof IMinestellarWorldProvider) && entity.isBurning();
	}

	public static Vector3 getWorldColor(World world) {
		return new Vector3(1, 1, 1);
	}

	@SideOnly(Side.CLIENT)
	public static float getWorldBrightness(WorldClient world) {
		if (world.provider instanceof WorldProviderMoon) { // TODO: Remove moon reliance
			float f1 = world.getCelestialAngle(1.0F);
			float f2 = 1.0F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.2F);

			if (f2 < 0.0F) {
				f2 = 0.0F;
			}

			if (f2 > 1.0F) {
				f2 = 1.0F;
			}

			f2 = 1.0F - f2;
			return f2 * 0.8F;
		}

		return world.getSunBrightness(1.0F);
	}

	public static float getColorRed(World world) {
		return (float) WorldUtil.getWorldColor(world).x;
	}

	public static float getColorGreen(World world) {
		return (float) WorldUtil.getWorldColor(world).y;
	}

	public static float getColorBlue(World world) {
		return (float) WorldUtil.getWorldColor(world).z;
	}

	public static Vec3 getFogColorHook(World world) {
		if (world.provider instanceof WorldProviderSurface && FMLClientHandler.instance().getClient().thePlayer.posY >= 200) {
			float var20 = (float) (FMLClientHandler.instance().getClient().thePlayer.posY - 200.0F) / 1000.0F;
			final float var21 = Math.max(1.0F - var20 * 4.0F, 0.0F);

			Vec3 vec = world.getFogColor(1.0F);

			return Vec3.createVectorHelper(vec.xCoord * var21, vec.yCoord * var21, vec.zCoord * var21);
		}

		return world.getFogColor(1.0F);
	}

	public static Vec3 getSkyColorHook(World world) {
		if (world.provider instanceof WorldProviderSurface && FMLClientHandler.instance().getClient().thePlayer.posY >= 200) {
			float var20 = (float) (FMLClientHandler.instance().getClient().thePlayer.posY - 200.0F) / 1000.0F;
			final float var21 = Math.max(1.0F - var20 * 2.0F, 0.0F);

			Vec3 vec = world.getSkyColor(FMLClientHandler.instance().getClient().renderViewEntity, 1.0F);

			return Vec3.createVectorHelper(vec.xCoord * var21, vec.yCoord * var21, vec.zCoord * var21);
		}

		return world.getSkyColor(FMLClientHandler.instance().getClient().renderViewEntity, 1.0F);
	}

	public static WorldProvider getProviderForDimension(int id) {
		WorldProvider provider = null;
		MinecraftServer theServer = FMLCommonHandler.instance().getMinecraftServerInstance();
		
		if (theServer != null) {
			WorldServer ws = theServer.worldServerForDimension(id);
			if (ws != null) {
				provider = ws.provider;
			}
		}
		
		if (provider == null) {
			provider = WorldProvider.getProviderForDimension(id);
		}
		
		return provider;
	}

	@SideOnly(Side.CLIENT)
	public static EntityPlayer forceRespawnClient(int dimID, int par2, String par3, int par4) {
		S07PacketRespawn fakePacket = new S07PacketRespawn(dimID, EnumDifficulty.getDifficultyEnum(par2), WorldType.parseWorldType(par3), WorldSettings.GameType.getByID(par4));
		Minecraft.getMinecraft().getNetHandler().handleRespawn(fakePacket);
		
		return FMLClientHandler.instance().getClientPlayerEntity();
	}
}

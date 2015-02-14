/**
 * Copyright (c) 12/feb/2015 Davide Cossu.
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.minestellar.core.blocks.CoreBlocks;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterSpace extends Teleporter{

	private final WorldServer worldServerInstance;
	private final Random random;
	private final LongHashMap destinationCoordinateCache = new LongHashMap();
	private final List destinationCoordinateKeys = new ArrayList();
	private static final String __OBFID = "CL_00000153";

	public TeleporterSpace(WorldServer par1WorldServer) {
		super(par1WorldServer);
		this.worldServerInstance = par1WorldServer;
		this.random = new Random(par1WorldServer.getSeed());
	}

	public void placeInPortal(Entity entity, double par2, double par4, double par6, float par8) {
		if (this.worldServerInstance.provider.dimensionId != 1) {
			if (!this.placeInExistingPortal(entity, par2, par4, par6, par8)) {
				this.makePortal(entity);
				this.placeInExistingPortal(entity, par2, par4, par6, par8);
			}
		}else {
			entity.worldObj.setBlock((int)entity.posX, (int)entity.posY-2, (int)entity.posZ, Blocks.stone);
			entity.worldObj.setBlock((int)entity.posX-1, (int)entity.posY-2, (int)entity.posZ, Blocks.stone);
			entity.worldObj.setBlock((int)entity.posX+1, (int)entity.posY-2, (int)entity.posZ, Blocks.stone);
			entity.worldObj.setBlock((int)entity.posX, (int)entity.posY-2, (int)entity.posZ-1, Blocks.stone);
			entity.worldObj.setBlock((int)entity.posX, (int)entity.posY-2, (int)entity.posZ+1, Blocks.stone);
			entity.worldObj.setBlock((int)entity.posX+1, (int)entity.posY-2, (int)entity.posZ+1, Blocks.stone);
			entity.worldObj.setBlock((int)entity.posX-1, (int)entity.posY-2, (int)entity.posZ-1, Blocks.stone);

			entity.setLocationAndAngles(entity.posX, entity.posY, entity.posZ-2, entity.rotationYaw, 0.0F);
			entity.motionX = entity.motionY = entity.motionZ = 0.0D;
		}
	}

	public boolean placeInExistingPortal(Entity par1Entity, double par2, double par4, double par6, float par8) {
		short short1 = 128;
		double d3 = -1.0D;
		int i = 0;
		int j = 0;
		int k = 0;
		int l = MathHelper.floor_double(par1Entity.posX);
		int i1 = MathHelper.floor_double(par1Entity.posZ);
		long j1 = ChunkCoordIntPair.chunkXZ2Int(l, i1);
		boolean flag = true;
		double d7;
		int l3;

		if (this.destinationCoordinateCache.containsItem(j1)) {
			Teleporter.PortalPosition portalposition = (Teleporter.PortalPosition) this.destinationCoordinateCache.getValueByKey(j1);
			d3 = 0.0D;
			i = portalposition.posX;
			j = portalposition.posY;
			k = portalposition.posZ;
			portalposition.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
			flag = false;
		}

		if (d3 >= 0.0D) {
			if (flag) {
				this.destinationCoordinateCache.add(j1, new Teleporter.PortalPosition(i, j, k, this.worldServerInstance.getTotalWorldTime()));
				this.destinationCoordinateKeys.add(Long.valueOf(j1));
			}

			double d11 = (double) i + 0.5D;
			d7 = (double) k + 0.5D;

			par1Entity.setLocationAndAngles(d11, par1Entity.posY, d7, par1Entity.rotationYaw, par1Entity.rotationPitch);
			return true;
		}else {
			return false;
		}
	}

	public boolean makePortal(Entity entity) {
		entity.worldObj.setBlock((int)entity.posX, (int)entity.posY-2, (int)entity.posZ, Blocks.stone);
		entity.worldObj.setBlock((int)entity.posX-1, (int)entity.posY-2, (int)entity.posZ, Blocks.stone);
		entity.worldObj.setBlock((int)entity.posX+1, (int)entity.posY-2, (int)entity.posZ, Blocks.stone);
		entity.worldObj.setBlock((int)entity.posX, (int)entity.posY-2, (int)entity.posZ-1, Blocks.stone);
		entity.worldObj.setBlock((int)entity.posX, (int)entity.posY-2, (int)entity.posZ+1, Blocks.stone);
		entity.worldObj.setBlock((int)entity.posX+1, (int)entity.posY-2, (int)entity.posZ+1, Blocks.stone);
		entity.worldObj.setBlock((int)entity.posX-1, (int)entity.posY-2, (int)entity.posZ-1, Blocks.stone);
		return true;
	}

	public void removeStalePortalLocations(long par1) {
		if (par1 % 100L == 0L) {
			Iterator iterator = this.destinationCoordinateKeys.iterator();
			long j = par1 - 600L;

			while (iterator.hasNext()) {
				Long olong = (Long) iterator.next();
				Teleporter.PortalPosition portalposition = (Teleporter.PortalPosition) this.destinationCoordinateCache.getValueByKey(olong.longValue());

				if (portalposition == null || portalposition.lastUpdateTime < j) {
					iterator.remove();
					this.destinationCoordinateCache.remove(olong.longValue());
				}
			}
		}
	}

	public class PortalPosition extends ChunkCoordinates {
		public long lastUpdateTime;
		private static final String __OBFID = "CL_00000154";

		public PortalPosition(int par2, int par3, int par4, long par5) {
			super(par2, par3, par4);
			this.lastUpdateTime = par5;
		}
	}

}
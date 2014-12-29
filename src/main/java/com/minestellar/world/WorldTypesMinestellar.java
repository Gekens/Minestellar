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

package com.minestellar.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldTypesMinestellar extends WorldType{

private boolean hasNotificationData;
	
	public static WorldType tutDimOverWorld;

	public WorldTypesMinestellar(String name) {
		super(name);
	}
	
	public static void addCustomWorldTypes(){
		tutDimOverWorld = new WorldTypesMinestellar("MINESTELLAR").setNotificationData();
	}

	@Override
	public WorldChunkManager getChunkManager(World world) {
		return new WorldChunkManagerMoon();
	}

	@Override
	public IChunkProvider getChunkGenerator(World world, String generatorOptions) {
		return new ChunkProviderGenerate(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
	}

	@Override
	public int getMinimumSpawnHeight(World world) {
		return 64;
	} 
	
	/**
     * enables the display of generator.[worldtype].info message on the customize world menu
     */
    private WorldType setNotificationData(){
        this.hasNotificationData = true;
        return this;
    }
    
    /**
     * returns true if selecting this worldtype from the customize menu should display the generator.[worldtype].info
     * message
     */
    @SideOnly(Side.CLIENT)
    @Override
    public boolean showWorldInfoNotice(){
        return this.hasNotificationData;
    }
	
}
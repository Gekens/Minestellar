/**
 * Copyright (c) 27/12/14 Davide Cossu.
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

package com.minestellar;

import net.minecraft.creativetab.CreativeTabs;

import com.minestellar.init.ModBlocks;
import com.minestellar.proxy.CommonProxy;
import com.minestellar.reference.References;
import com.minestellar.util.LogHelper;
import com.minestellar.world.Dimension;
import com.minestellar.world.biome.ModBiomes;
import com.minestellar.world.provider.WorldProviderMoon;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.VERSION)
public class Minestellar{

    @SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.Instance
    public Minestellar instance;

    public static CreativeTabs minestellarTab = new MinestellarTab(CreativeTabs.getNextID(), "MineStellar");
    
    public static LogHelper log = new LogHelper(References.MOD_ID);
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){

    	ModBlocks.loadBlocks();
		ModBiomes.registerWithBiomeDictionary();
		Dimension.registerWorldProvider();
		Dimension.registerDimensions();
    	
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){

    }

}
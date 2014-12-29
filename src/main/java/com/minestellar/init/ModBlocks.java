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

package com.minestellar.init;

import com.minestellar.Minestellar;
import com.minestellar.block.MoonStone;
import com.minestellar.block.MoonTeleporterBlock;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModBlocks{

	public static Block moonStone;
	
	public static MoonTeleporterBlock moonPortal;
	
	public static void loadBlocks(){
		
		moonStone = new MoonStone().setBlockName("moonStone").setLightLevel(1.0F).setStepSound(Block.soundTypeSnow).setCreativeTab(Minestellar.minestellarTab);
		moonPortal = new MoonTeleporterBlock("moonPortal");
	
		registerBlocks();
		
	}
	
	public static void registerBlocks(){
		
		GameRegistry.registerBlock(moonStone, "moonStone");
		GameRegistry.registerBlock(moonPortal, "moonPortal");
		
	}
	
}
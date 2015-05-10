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

package com.minestellar.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import com.minestellar.core.MinestellarCore;

public class BlockTeleporter extends Block {
	protected BlockTeleporter(String unlocalizedName) {
		super(Material.iron);
		this.setBlockName(unlocalizedName);
		this.setBlockTextureName(MinestellarCore.TEXTURE_PREFIX + unlocalizedName);
		this.setHardness(3.0F);
		this.setResistance(6.0F);
		this.setLightLevel(0.75F);
		this.setHarvestLevel("pickaxe", 3);
		this.setStepSound(soundTypeMetal);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn() {
		return MinestellarCore.stellarBlocksTab;
	}
}

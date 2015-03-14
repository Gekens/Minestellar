/**
 * Copyright (c) 23/feb/2015 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.core.blocks.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

import com.minestellar.core.blocks.tile.TileEntityComputer;

public class ComputerContainer extends Container{

	private TileEntityComputer te;
	
	public ComputerContainer(TileEntityComputer te) {
		this.te = te;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

}
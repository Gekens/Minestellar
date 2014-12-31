/**
 * Copyright (c) 31/dic/2014 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;

import com.minestellar.core.util.MinestellarLog;

public class MinestellarRegistry
{
	private static List<Integer> worldProviderIDs = new ArrayList<Integer>();

	public static void registerProvider(int id, Class<? extends WorldProvider> provider, boolean keepLoaded)
	{
		boolean flag = DimensionManager.registerProviderType(id, provider, keepLoaded);

		if (flag)
		{
			MinestellarRegistry.worldProviderIDs.add(id);
		}

		else
		{
			MinestellarRegistry.worldProviderIDs.add(0);
			MinestellarLog.severe("Failed to register dimension " + id + " - check if the id is registered with another mod.");
		}
	}

	public static int getProviderID(int index)
	{
		return MinestellarRegistry.worldProviderIDs.get(index);
	}
}

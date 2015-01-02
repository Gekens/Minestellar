/**
 * Copyright (c) 1/dic/2015 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.moon.event;

import com.minestellar.moon.MinestellarMoon;
import com.minestellar.moon.items.MoonItems;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class EventAchievementMoon
{
	@SubscribeEvent
	public void moon(PlayerEvent.ItemCraftedEvent event)
	{
		if (event.crafting.getItem().equals(MoonItems.moonPortalTrigger))
		{
			event.player.addStat(MinestellarMoon.achievementMoon, 1);
		}
	}
}

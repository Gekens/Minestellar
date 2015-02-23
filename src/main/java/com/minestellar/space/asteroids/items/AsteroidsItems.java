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

package com.minestellar.space.asteroids.items;

import net.minecraft.item.Item;

import com.minestellar.space.asteroids.MinestellarAsteroids;

public class AsteroidsItems {
	public static void init() {
		initItems();
		registerItems();
		oreDictRegistration();
		registerHarvestLevels();
		registerFluidContainer();
	}

	public static Item asteroidsBasicItems;
	public static Item asteroidsPortalTrigger;

	private static void initItems() {
		AsteroidsItems.asteroidsBasicItems = new ItemBasicAsteroids();
		AsteroidsItems.asteroidsPortalTrigger = new ItemPortalTrigger("asteroids_trigger");
	}

	private static void registerItems() {
		MinestellarAsteroids.registerItem(asteroidsBasicItems);
		MinestellarAsteroids.registerItem(asteroidsPortalTrigger);
	}

	private static void oreDictRegistration() {
	}

	private static void registerHarvestLevels() {
	}

	private static void registerFluidContainer() {
	}
}

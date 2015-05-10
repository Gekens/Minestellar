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

package com.minestellar.core.world.gen;

public enum EnumCraterSize {
	SMALL(8, 12, 14), MEDIUM(13, 17, 8), LARGE(18, 25, 2), EXTREME(26, 30, 1);

	public final int MIN_SIZE;
	public final int MAX_SIZE;
	private final int PROBABILITY;
	public static EnumCraterSize[] sizeArray;

	private EnumCraterSize(int min, int max, int prob) {
		this.MIN_SIZE = min;
		this.MAX_SIZE = max;
		this.PROBABILITY = prob;
	}

	static {
		int amount = 0;

		for (final EnumCraterSize c : EnumCraterSize.values()) {
			amount += c.PROBABILITY;
		}

		EnumCraterSize.sizeArray = new EnumCraterSize[amount];
		int pointer = 0;
		
		for (final EnumCraterSize c : EnumCraterSize.values()) {
			for (int i = 0; i < c.PROBABILITY; i++) {
				EnumCraterSize.sizeArray[pointer] = c;
				pointer++;
			}
		}
	}
}

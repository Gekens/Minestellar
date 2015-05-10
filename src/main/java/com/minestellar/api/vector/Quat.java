/**
 * Copyright (c) 02/05/15 Davide Cossu & Matthew Albrecht.
 * <p/>
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 * <p/>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, see <http://www.gnu.org/licenses>.
 */

package com.minestellar.api.vector;

import java.util.Formatter;
import java.util.Locale;

/**
 * @author Vazkii
 */

public class Quat {

	public double x;
	public double y;
	public double z;
	public double s;

	public Quat() {
		s = 1.0D;
		x = 0.0D;
		y = 0.0D;
		z = 0.0D;
	}

	public Quat(Quat quat) {
		x = quat.x;
		y = quat.y;
		z = quat.z;
		s = quat.s;
	}

	public Quat(double d, double d1, double d2, double d3) {
		x = d1;
		y = d2;
		z = d3;
		s = d;
	}

	public void set(Quat quat) {
		x = quat.x;
		y = quat.y;
		z = quat.z;
		s = quat.s;
	}

	public static Quat aroundAxis(double ax, double ay, double az, double angle) {
		angle *= 0.5D;
		double d4 = Math.sin(angle);
		return new Quat(Math.cos(angle), ax * d4, ay * d4, az * d4);
	}

	public void multiply(Quat quat) {
		double d = s * quat.s - x * quat.x - y * quat.y - z * quat.z;
		double d1 = s * quat.x + x * quat.s - y * quat.z + z * quat.y;
		double d2 = s * quat.y + x * quat.z + y * quat.s - z * quat.x;
		double d3 = s * quat.z - x * quat.y + y * quat.x + z * quat.s;
		s = d;
		x = d1;
		y = d2;
		z = d3;
	}

	public void rightMultiply(Quat quat) {
		double d = s * quat.s - x * quat.x - y * quat.y - z * quat.z;
		double d1 = s * quat.x + x * quat.s + y * quat.z - z * quat.y;
		double d2 = s * quat.y - x * quat.z + y * quat.s + z * quat.x;
		double d3 = s * quat.z + x * quat.y - y * quat.x + z * quat.s;
		s = d;
		x = d1;
		y = d2;
		z = d3;
	}

	public double mag() {
		return Math.sqrt(x * x + y * y + z * z + s * s);
	}

	public void normalize() {
		double d = mag();
		if (d == 0.0D) {
			return;
		} else {
			d = 1.0D / d;
			x *= d;
			y *= d;
			z *= d;
			s *= d;
			return;
		}
	}

	public void rotate(Vector3 vec) {
		double d = -x * vec.x - y * vec.y - z * vec.z;
		double d1 = s * vec.x + y * vec.z - z * vec.y;
		double d2 = s * vec.y - x * vec.z + z * vec.x;
		double d3 = s * vec.z + x * vec.y - y * vec.x;
		vec.x = d1 * s - d * x - d2 * z + d3 * y;
		vec.y = d2 * s - d * y + d1 * z - d3 * x;
		vec.z = d3 * s - d * z - d1 * y + d2 * x;
	}

	@Override
	public String toString() {
		StringBuilder stringbuilder = new StringBuilder();
		Formatter formatter = new Formatter(stringbuilder, Locale.US);
		formatter.format("Quaternion:\n");
		formatter.format("  < %f %f %f %f >\n", s, x, y, z);
		formatter.close();
		return stringbuilder.toString();
	}

	public static Quat aroundAxis(Vector3 axis, double angle) {
		return aroundAxis(axis.x, axis.y, axis.z, angle);
	}

}

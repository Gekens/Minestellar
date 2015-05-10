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

package com.minestellar.core.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * GasSink - Gekens Created using Tabula 4.1.1
 */
public class ModelGasSink extends ModelBase {
	public ModelRenderer top;
	public ModelRenderer center;
	public ModelRenderer under;
	public ModelRenderer pipedock;
	public ModelRenderer turbine3;
	public ModelRenderer turbine4;
	public ModelRenderer turbine2;
	public ModelRenderer turbine1;
	public ModelRenderer right;
	public ModelRenderer left;
	public ModelRenderer support;

	public ModelGasSink() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.left = new ModelRenderer(this, 14, 45);
		this.left.setRotationPoint(-8.0F, 8.0F, -8.0F);
		this.left.addBox(0.0F, 0.0F, 0.0F, 16, 16, 1, 0.0F);
		this.turbine3 = new ModelRenderer(this, 0, 0);
		this.turbine3.setRotationPoint(-0.5F, 15.5F, 0.0F);
		this.turbine3.addBox(0.0F, 0.0F, -1.0F, 1, 6, 2, 0.0F);
		this.setRotateAngle(turbine3, -0.7853981633974483F, 0.0F, 0.0F);
		this.turbine1 = new ModelRenderer(this, 0, 0);
		this.turbine1.setRotationPoint(-0.5F, 15.5F, 0.0F);
		this.turbine1.addBox(0.0F, 0.0F, -1.0F, 1, 6, 2, 0.0F);
		this.setRotateAngle(turbine1, 2.356194490192345F, 0.0F, 0.0F);
		this.top = new ModelRenderer(this, 0, 26);
		this.top.setRotationPoint(-8.0F, 8.0F, -8.0F);
		this.top.addBox(0.0F, 0.0F, 0.0F, 16, 1, 16, 0.0F);
		this.support = new ModelRenderer(this, 0, 11);
		this.support.setRotationPoint(0.5F, 17.0F, -0.5F);
		this.support.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1, 0.0F);
		this.center = new ModelRenderer(this, 23, 0);
		this.center.setRotationPoint(-1.5F, 14.0F, -1.5F);
		this.center.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
		this.pipedock = new ModelRenderer(this, 9, 0);
		this.pipedock.setRotationPoint(0.5F, 13.0F, -2.5F);
		this.pipedock.addBox(0.0F, 0.0F, 0.0F, 1, 5, 5, 0.0F);
		this.turbine4 = new ModelRenderer(this, 0, 0);
		this.turbine4.setRotationPoint(-0.5F, 15.5F, 0.0F);
		this.turbine4.addBox(0.0F, 0.0F, -1.0F, 1, 6, 2, 0.0F);
		this.setRotateAngle(turbine4, 0.7853981633974483F, 0.0F, 0.0F);
		this.right = new ModelRenderer(this, 14, 45);
		this.right.setRotationPoint(-8.0F, 8.0F, 7.0F);
		this.right.addBox(0.0F, 0.0F, 0.0F, 16, 16, 1, 0.0F);
		this.under = new ModelRenderer(this, 0, 26);
		this.under.setRotationPoint(-8.0F, 23.0F, -8.0F);
		this.under.addBox(0.0F, 0.0F, 0.0F, 16, 1, 16, 0.0F);
		this.turbine2 = new ModelRenderer(this, 0, 0);
		this.turbine2.setRotationPoint(-0.5F, 15.5F, 0.0F);
		this.turbine2.addBox(0.0F, 0.0F, -1.0F, 1, 6, 2, 0.0F);
		this.setRotateAngle(turbine2, -2.356194490192345F, 0.0F, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.left.render(f5);
		this.turbine3.render(f5);
		this.turbine1.render(f5);
		this.top.render(f5);
		this.support.render(f5);
		this.center.render(f5);
		this.pipedock.render(f5);
		this.turbine4.render(f5);
		this.right.render(f5);
		this.under.render(f5);
		this.turbine2.render(f5);
	}

	public void renderModel(float size) {
		this.left.render(size);
		this.turbine3.render(size);
		this.turbine1.render(size);
		this.top.render(size);
		this.support.render(size);
		this.center.render(size);
		this.pipedock.render(size);
		this.turbine4.render(size);
		this.right.render(size);
		this.under.render(size);
		this.turbine2.render(size);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}

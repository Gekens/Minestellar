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
 * Oxygen Sink - Gekens
 * Created using Tabula 4.1.1
 */
public class ModelOxygenCollector extends ModelBase {
	public ModelRenderer base;
	public ModelRenderer shape3;
	public ModelRenderer shape3_1;
	public ModelRenderer shape3_2;
	public ModelRenderer shape3_3;
	public ModelRenderer shape3_4;
	public ModelRenderer shape14;
	public ModelRenderer shape14_1;
	public ModelRenderer shape17;
	public ModelRenderer shape17_1;
	public ModelRenderer shape17_2;
	public ModelRenderer shape17_3;
	public ModelRenderer shape21;
	public ModelRenderer shape22;
	public ModelRenderer shape22_1;

	public ModelOxygenCollector() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.base = new ModelRenderer(this, 0, 0);
		this.base.setRotationPoint(-8.0F, 23.0F, -8.0F);
		this.base.addBox(0.0F, 0.0F, 0.0F, 16, 1, 16, 0.0F);
		this.shape3_4 = new ModelRenderer(this, 0, 19);
		this.shape3_4.setRotationPoint(-0.5F, 12.0F, 1.5F);
		this.shape3_4.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
		this.shape21 = new ModelRenderer(this, 19, 45);
		this.shape21.setRotationPoint(0.0F, 15.0F, -2.5F);
		this.shape21.addBox(0.0F, 0.0F, 0.0F, 8, 5, 5, 0.0F);
		this.shape17_3 = new ModelRenderer(this, 20, 25);
		this.shape17_3.setRotationPoint(-0.5F, 10.0F, -4.5F);
		this.shape17_3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.shape3 = new ModelRenderer(this, 0, 19);
		this.shape3.setRotationPoint(-2.5F, 12.0F, -0.5F);
		this.shape3.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
		this.shape17_1 = new ModelRenderer(this, 20, 25);
		this.shape17_1.setRotationPoint(-4.5F, 10.0F, -0.5F);
		this.shape17_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.shape3_2 = new ModelRenderer(this, 0, 19);
		this.shape3_2.setRotationPoint(1.5F, 12.0F, -0.5F);
		this.shape3_2.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
		this.shape14 = new ModelRenderer(this, 12, 19);
		this.shape14.setRotationPoint(-0.5F, 11.0F, -4.5F);
		this.shape14.addBox(0.0F, 0.0F, 0.0F, 1, 1, 9, 0.0F);
		this.shape17_2 = new ModelRenderer(this, 20, 25);
		this.shape17_2.setRotationPoint(-0.5F, 10.0F, 3.5F);
		this.shape17_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.shape22 = new ModelRenderer(this, 15, 37);
		this.shape22.setRotationPoint(-3.5F, 10.0F, -0.5F);
		this.shape22.addBox(0.0F, 0.0F, 0.0F, 7, 1, 1, 0.0F);
		this.shape14_1 = new ModelRenderer(this, 12, 27);
		this.shape14_1.setRotationPoint(-4.5F, 11.0F, -0.5F);
		this.shape14_1.addBox(0.0F, 0.0F, 0.0F, 9, 1, 1, 0.0F);
		this.shape17 = new ModelRenderer(this, 20, 25);
		this.shape17.setRotationPoint(3.5F, 10.0F, -0.5F);
		this.shape17.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.shape3_3 = new ModelRenderer(this, 0, 19);
		this.shape3_3.setRotationPoint(-0.5F, 12.0F, -2.5F);
		this.shape3_3.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
		this.shape22_1 = new ModelRenderer(this, 15, 31);
		this.shape22_1.setRotationPoint(-0.5F, 10.0F, -3.5F);
		this.shape22_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 7, 0.0F);
		this.shape3_1 = new ModelRenderer(this, 46, 18);
		this.shape3_1.setRotationPoint(-1.5F, 12.0F, -1.5F);
		this.shape3_1.addBox(0.0F, 0.0F, 0.0F, 3, 11, 3, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.base.render(f5);
		this.shape3_4.render(f5);
		this.shape21.render(f5);
		this.shape17_3.render(f5);
		this.shape3.render(f5);
		this.shape17_1.render(f5);
		this.shape3_2.render(f5);
		this.shape14.render(f5);
		this.shape17_2.render(f5);
		this.shape22.render(f5);
		this.shape14_1.render(f5);
		this.shape17.render(f5);
		this.shape3_3.render(f5);
		this.shape22_1.render(f5);
		this.shape3_1.render(f5);
	}

	public void renderModel(float size) {
		this.shape3_4.render(size);
		this.shape21.render(size);
		this.shape17_3.render(size);
		this.shape3.render(size);
		this.shape17_1.render(size);
		this.shape3_2.render(size);
		this.shape14.render(size);
		this.shape17_2.render(size);
		this.shape22.render(size);
		this.shape14_1.render(size);
		this.shape17.render(size);
		this.shape3_3.render(size);
		this.shape22_1.render(size);
		this.shape3_1.render(size);
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

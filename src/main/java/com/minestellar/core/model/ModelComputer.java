package com.minestellar.core.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Planet Controller - Gekens
 * Created using Tabula 4.1.1
 */
public class ModelComputer extends ModelBase {
    public ModelRenderer base;
    public ModelRenderer support;
    public ModelRenderer monitor;
    public ModelRenderer support_1;

    public ModelComputer() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.monitor = new ModelRenderer(this, 0, 0);
        this.monitor.setRotationPoint(0.0F, 18.0F, -1.5F);
        this.monitor.addBox(-6.0F, 0.0F, -2.0F, 12, 1, 8, 0.0F);
        this.setRotateAngle(monitor, 1.3962634015954636F, 0.0F, 0.0F);
        this.support_1 = new ModelRenderer(this, 0, 0);
        this.support_1.setRotationPoint(-1.0F, 18.0F, -1.3F);
        this.support_1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(support_1, 1.1383037381507017F, 0.0F, 0.0F);
        this.base = new ModelRenderer(this, 0, 22);
        this.base.setRotationPoint(-4.0F, 23.0F, -4.0F);
        this.base.addBox(0.0F, 0.0F, 0.0F, 8, 1, 8, 0.0F);
        this.support = new ModelRenderer(this, 0, 21);
        this.support.setRotationPoint(-1.0F, 18.0F, 0.0F);
        this.support.addBox(0.0F, 0.0F, 0.0F, 2, 5, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.monitor.render(f5);
        this.support_1.render(f5);
        this.base.render(f5);
        this.support.render(f5);
    }

    public void renderModel(float f5){
        this.monitor.render(f5);
        this.support_1.render(f5);
        this.base.render(f5);
        this.support.render(f5);
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

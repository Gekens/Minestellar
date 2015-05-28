package com.minestellar.core.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Antenna - Gekens
 * Created using Tabula 4.1.1
 */

public class ModelAntenna extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape24;
    public ModelRenderer shape25;
    public ModelRenderer shape26;
    public ModelRenderer shape24_1;
    public ModelRenderer shape25_1;
    public ModelRenderer shape26_1;
    public ModelRenderer shape4;
    public ModelRenderer shape4_1;
    public ModelRenderer shape6;
    public ModelRenderer shape3;
    public ModelRenderer shape7;
    public ModelRenderer shape7_1;
    public ModelRenderer shape7_2;
    public ModelRenderer shape7_3;
    public ModelRenderer shape7_4;
    public ModelRenderer shape7_5;
    public ModelRenderer shape7_6;
    public ModelRenderer shape7_7;
    public ModelRenderer shape7_8;
    public ModelRenderer shape7_9;
    public ModelRenderer shape7_10;
    public ModelRenderer shape7_11;
    public ModelRenderer shape23;

    public ModelAntenna() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.shape7_10 = new ModelRenderer(this, 0, 37);
        this.shape7_10.setRotationPoint(-1.0F, -32.3F, 20.799999999999994F);
        this.shape7_10.addBox(0.0F, 0.0F, 0.0F, 2, 16, 2, 0.0F);
        this.setRotateAngle(shape7_10, -0.7853981633974483F, 0.0F, 0.0F);
        this.shape7_4 = new ModelRenderer(this, 0, 37);
        this.shape7_4.setRotationPoint(-13.0F, -27.59999999999999F, 0.1999999999999999F);
        this.shape7_4.addBox(0.0F, 0.0F, 0.0F, 26, 2, 26, 0.0F);
        this.setRotateAngle(shape7_4, -0.7853981633974483F, 0.0F, 0.0F);
        this.shape7_9 = new ModelRenderer(this, 0, 37);
        this.shape7_9.setRotationPoint(-13.0F, -12.000000000000002F, 21.39999999999999F);
        this.shape7_9.addBox(0.0F, 0.0F, 0.0F, 26, 2, 2, 0.0F);
        this.setRotateAngle(shape7_9, -0.7853981633974483F, 0.0F, 0.0F);
        this.shape7_3 = new ModelRenderer(this, 0, 37);
        this.shape7_3.setRotationPoint(-9.0F, -21.89999999999999F, 0.3F);
        this.shape7_3.addBox(0.0F, 0.0F, 0.0F, 18, 2, 18, 0.0F);
        this.setRotateAngle(shape7_3, -0.7853981633974483F, 0.0F, 0.0F);
        this.shape25 = new ModelRenderer(this, 0, 0);
        this.shape25.setRotationPoint(-14.9F, 17.4F, -3.0F);
        this.shape25.addBox(0.0F, 0.0F, 0.0F, 3, 6, 6, 0.0F);
        this.setRotateAngle(shape25, 0.0F, 0.0F, 0.6829473363053812F);
        this.shape7 = new ModelRenderer(this, 0, 37);
        this.shape7.setRotationPoint(-5.0F, -14.900000000000002F, -1.5F);
        this.shape7.addBox(0.0F, 0.0F, 0.0F, 10, 2, 10, 0.0F);
        this.setRotateAngle(shape7, -0.7853981633974483F, 0.0F, 0.0F);
        this.shape7_5 = new ModelRenderer(this, 0, 37);
        this.shape7_5.setRotationPoint(-15.0F, -30.39999999999999F, 0.1999999999999999F);
        this.shape7_5.addBox(0.0F, 0.0F, 0.0F, 30, 2, 30, 0.0F);
        this.setRotateAngle(shape7_5, -0.7853981633974483F, 0.0F, 0.0F);
        this.shape24 = new ModelRenderer(this, 0, 0);
        this.shape24.setRotationPoint(-15.0F, 17.2F, -3.0F);
        this.shape24.addBox(0.0F, 0.0F, 0.0F, 7, 4, 6, 0.0F);
        this.shape24_1 = new ModelRenderer(this, 0, 0);
        this.shape24_1.setRotationPoint(8.0F, 17.2F, -3.0F);
        this.shape24_1.addBox(0.0F, 0.0F, 0.0F, 7, 4, 6, 0.0F);
        this.shape7_7 = new ModelRenderer(this, 0, 37);
        this.shape7_7.setRotationPoint(13.0F, -31.799999999999994F, 1.5999999999999994F);
        this.shape7_7.addBox(0.0F, 0.0F, 0.0F, 2, 2, 30, 0.0F);
        this.setRotateAngle(shape7_7, -0.7853981633974483F, 0.0F, 0.0F);
        this.shape7_1 = new ModelRenderer(this, 0, 37);
        this.shape7_1.setRotationPoint(-6.0F, -16.89999999999999F, -0.7F);
        this.shape7_1.addBox(0.0F, 0.0F, 0.0F, 12, 2, 12, 0.0F);
        this.setRotateAngle(shape7_1, -0.7853981633974483F, 0.0F, 0.0F);
        this.shape23 = new ModelRenderer(this, 0, 72);
        this.shape23.setRotationPoint(-1.7F, -32.3F, 20.0F);
        this.shape23.addBox(0.2F, 0.0F, 0.0F, 3, 3, 4, 0.0F);
        this.setRotateAngle(shape23, 0.7853981633974483F, 0.0F, 0.0F);
        this.shape2 = new ModelRenderer(this, 37, 0);
        this.shape2.setRotationPoint(-3.0F, 4.0F, -3.5F);
        this.shape2.addBox(0.0F, 0.0F, 0.0F, 6, 4, 7, 0.0F);
        this.shape1 = new ModelRenderer(this, 0, 76);
        this.shape1.setRotationPoint(-8.0F, 8.0F, -8.0F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 16, 16, 16, 0.0F);
        this.shape3 = new ModelRenderer(this, 73, 0);
        this.shape3.setRotationPoint(-7.0F, 0.0F, -3.0F);
        this.shape3.addBox(0.0F, 0.0F, 0.0F, 14, 4, 6, 0.0F);
        this.shape7_11 = new ModelRenderer(this, 0, 37);
        this.shape7_11.setRotationPoint(-11.0F, -24.79999999999999F, 0.1999999999999999F);
        this.shape7_11.addBox(0.0F, 0.0F, 0.0F, 22, 2, 22, 0.0F);
        this.setRotateAngle(shape7_11, -0.7853981633974483F, 0.0F, 0.0F);
        this.shape4_1 = new ModelRenderer(this, 73, 0);
        this.shape4_1.setRotationPoint(5.5F, -8.4F, -3.0F);
        this.shape4_1.addBox(0.0F, 0.0F, 0.0F, 4, 9, 6, 0.0F);
        this.setRotateAngle(shape4_1, 0.0F, 0.0F, 0.27314402793711257F);
        this.shape7_8 = new ModelRenderer(this, 0, 37);
        this.shape7_8.setRotationPoint(-13.0F, -31.799999999999994F, 1.5999999999999994F);
        this.shape7_8.addBox(0.0F, 0.0F, 0.0F, 26, 2, 2, 0.0F);
        this.setRotateAngle(shape7_8, -0.7853981633974483F, 0.0F, 0.0F);
        this.shape4 = new ModelRenderer(this, 73, 0);
        this.shape4.setRotationPoint(-9.4F, -7.4F, -3.0F);
        this.shape4.addBox(0.0F, 0.0F, 0.0F, 4, 9, 6, 0.0F);
        this.setRotateAngle(shape4, 0.0F, 0.0F, -0.27314402793711257F);
        this.shape26 = new ModelRenderer(this, 0, 18);
        this.shape26.setRotationPoint(-19.2F, 22.0F, -3.0F);
        this.shape26.addBox(0.0F, 0.0F, 0.0F, 5, 2, 6, 0.0F);
        this.shape7_6 = new ModelRenderer(this, 0, 37);
        this.shape7_6.setRotationPoint(-15.0F, -31.799999999999994F, 1.5999999999999994F);
        this.shape7_6.addBox(0.0F, 0.0F, 0.0F, 2, 2, 30, 0.0F);
        this.setRotateAngle(shape7_6, -0.7853981633974483F, 0.0F, 0.0F);
        this.shape7_2 = new ModelRenderer(this, 0, 37);
        this.shape7_2.setRotationPoint(-7.0F, -18.99999999999999F, 0.1999999999999999F);
        this.shape7_2.addBox(0.0F, 0.0F, 0.0F, 14, 2, 14, 0.0F);
        this.setRotateAngle(shape7_2, -0.7853981633974483F, 0.0F, 0.0F);
        this.shape25_1 = new ModelRenderer(this, 0, 18);
        this.shape25_1.setRotationPoint(12.6F, 19.3F, -3.0F);
        this.shape25_1.addBox(0.0F, 0.0F, 0.0F, 3, 6, 6, 0.0F);
        this.setRotateAngle(shape25_1, 0.0F, 0.0F, -0.6829473363053812F);
        this.shape26_1 = new ModelRenderer(this, 0, 0);
        this.shape26_1.setRotationPoint(14.2F, 21.9F, -3.0F);
        this.shape26_1.addBox(0.0F, 0.0F, 0.0F, 5, 2, 6, 0.0F);
        this.shape6 = new ModelRenderer(this, 73, 0);
        this.shape6.setRotationPoint(-9.5F, -10.4F, -2.0F);
        this.shape6.addBox(0.0F, 0.0F, 0.0F, 19, 4, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape7_10.render(f5);
        this.shape7_4.render(f5);
        this.shape7_9.render(f5);
        this.shape7_3.render(f5);
        this.shape25.render(f5);
        this.shape7.render(f5);
        this.shape7_5.render(f5);
        this.shape24.render(f5);
        this.shape24_1.render(f5);
        this.shape7_7.render(f5);
        this.shape7_1.render(f5);
        this.shape23.render(f5);
        this.shape2.render(f5);
        this.shape1.render(f5);
        this.shape3.render(f5);
        this.shape7_11.render(f5);
        this.shape4_1.render(f5);
        this.shape7_8.render(f5);
        this.shape4.render(f5);
        this.shape26.render(f5);
        this.shape7_6.render(f5);
        this.shape7_2.render(f5);
        this.shape25_1.render(f5);
        this.shape26_1.render(f5);
        this.shape6.render(f5);
    }

    public void renderModel(float f5) {
        this.shape7_10.render(f5);
        this.shape7_4.render(f5);
        this.shape7_9.render(f5);
        this.shape7_3.render(f5);
        this.shape25.render(f5);
        this.shape7.render(f5);
        this.shape7_5.render(f5);
        this.shape24.render(f5);
        this.shape24_1.render(f5);
        this.shape7_7.render(f5);
        this.shape7_1.render(f5);
        this.shape23.render(f5);
        this.shape2.render(f5);
        this.shape1.render(f5);
        this.shape3.render(f5);
        this.shape7_11.render(f5);
        this.shape4_1.render(f5);
        this.shape7_8.render(f5);
        this.shape4.render(f5);
        this.shape26.render(f5);
        this.shape7_6.render(f5);
        this.shape7_2.render(f5);
        this.shape25_1.render(f5);
        this.shape26_1.render(f5);
        this.shape6.render(f5);
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

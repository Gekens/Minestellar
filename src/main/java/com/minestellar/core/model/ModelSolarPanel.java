package com.minestellar.core.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Solar Panel - Gekens
 * Created using Tabula 4.1.1
 */
public class ModelSolarPanel extends ModelBase {
    public ModelRenderer baseblock;
    public ModelRenderer body;
    public ModelRenderer centercube;
    public ModelRenderer rightarm;
    public ModelRenderer leftarm;
    public ModelRenderer leftpanel;
    public ModelRenderer rightpanel;
    public ModelRenderer border;
    public ModelRenderer border_1;
    public ModelRenderer border_2;
    public ModelRenderer border_3;
    public ModelRenderer border_4;
    public ModelRenderer border_5;
    public ModelRenderer border_6;
    public ModelRenderer border_7;

    public ModelSolarPanel() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.rightpanel = new ModelRenderer(this, 0, 80);
        this.rightpanel.setRotationPoint(-45.0F, -15.0F, -20.5F);
        this.rightpanel.addBox(0.0F, 0.0F, 0.0F, 35, 2, 41, 0.0F);
        this.border_5 = new ModelRenderer(this, 0, 0);
        this.border_5.setRotationPoint(10.0F, -16.0F, -19.5F);
        this.border_5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 39, 0.0F);
        this.body = new ModelRenderer(this, 0, 45);
        this.body.setRotationPoint(-2.0F, -17.0F, -2.0F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 4, 25, 4, 0.0F);
        this.rightarm = new ModelRenderer(this, 80, 20);
        this.rightarm.setRotationPoint(-10.0F, -15.0F, -1.0F);
        this.rightarm.addBox(0.0F, 0.0F, 0.0F, 7, 2, 2, 0.0F);
        this.border_6 = new ModelRenderer(this, 0, 0);
        this.border_6.setRotationPoint(-11.0F, -16.0F, -19.5F);
        this.border_6.addBox(0.0F, 0.0F, 0.0F, 1, 1, 39, 0.0F);
        this.baseblock = new ModelRenderer(this, 40, 40);
        this.baseblock.setRotationPoint(-8.0F, 8.0F, -8.0F);
        this.baseblock.addBox(0.0F, 0.0F, 0.0F, 16, 16, 16, 0.0F);
        this.centercube = new ModelRenderer(this, 80, 0);
        this.centercube.setRotationPoint(-3.0F, -17.0F, -3.0F);
        this.centercube.addBox(0.0F, 0.0F, 0.0F, 6, 6, 6, 0.0F);
        this.border = new ModelRenderer(this, 0, 0);
        this.border.setRotationPoint(-45.0F, -16.0F, -20.5F);
        this.border.addBox(0.0F, 0.0F, 0.0F, 35, 1, 1, 0.0F);
        this.leftarm = new ModelRenderer(this, 80, 20);
        this.leftarm.setRotationPoint(3.0F, -15.0F, -1.0F);
        this.leftarm.addBox(0.0F, 0.0F, 0.0F, 7, 2, 2, 0.0F);
        this.border_1 = new ModelRenderer(this, 0, 0);
        this.border_1.setRotationPoint(10.0F, -16.0F, -20.5F);
        this.border_1.addBox(0.0F, 0.0F, 0.0F, 35, 1, 1, 0.0F);
        this.border_3 = new ModelRenderer(this, 0, 0);
        this.border_3.setRotationPoint(10.0F, -16.0F, 19.5F);
        this.border_3.addBox(0.0F, 0.0F, 0.0F, 35, 1, 1, 0.0F);
        this.border_4 = new ModelRenderer(this, 0, 0);
        this.border_4.setRotationPoint(-45.0F, -16.0F, -19.5F);
        this.border_4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 39, 0.0F);
        this.border_7 = new ModelRenderer(this, 0, 0);
        this.border_7.setRotationPoint(44.0F, -16.0F, -19.5F);
        this.border_7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 39, 0.0F);
        this.leftpanel = new ModelRenderer(this, 0, 80);
        this.leftpanel.setRotationPoint(10.0F, -15.0F, -20.5F);
        this.leftpanel.addBox(0.0F, 0.0F, 0.0F, 35, 2, 41, 0.0F);
        this.border_2 = new ModelRenderer(this, 0, 0);
        this.border_2.setRotationPoint(-45.0F, -16.0F, 19.5F);
        this.border_2.addBox(0.0F, 0.0F, 0.0F, 35, 1, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.rightpanel.render(f5);
        this.border_5.render(f5);
        this.body.render(f5);
        this.rightarm.render(f5);
        this.border_6.render(f5);
        this.baseblock.render(f5);
        this.centercube.render(f5);
        this.border.render(f5);
        this.leftarm.render(f5);
        this.border_1.render(f5);
        this.border_3.render(f5);
        this.border_4.render(f5);
        this.border_7.render(f5);
        this.leftpanel.render(f5);
        this.border_2.render(f5);
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

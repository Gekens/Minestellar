/**
 * Copyright (c) 02/05/15 Davide Cossu & Matthew Aplbrecht.
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
 * You should have received a clone of the GNU General Public License along with
 * this program; if not, see <http://www.gnu.org/licenses>.
 */

package com.minestellar.core.particles;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class EntityLightningBoltFX extends EntityFX{

    private float endX, endY, endZ;

    public EntityLightningBoltFX(World world, double x, double y, double z){
        super(world, x, y, z);
    }

    @Override
    public void renderParticle(Tessellator tessellator, float partialTicks, float par3, float par4, float par5, float par6, float par7){
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
    }

    @Override
    public void moveEntity(double motionX, double motionY, double motionZ){
        super.moveEntity(motionX, motionY, motionZ);
    }

    @Override
    public void onUpdate(){
        super.onUpdate();
    }

    /**
     * Sets the coordinates of the point at which the particle should arrive.
     */

    public EntityLightningBoltFX setArrivalCoords(float x, float y, float z){
        this.endX = x;
        this.endY = y;
        this.endZ = z;
        return this;
    }

    /**
     * Calculates the <code>x</code> component of the velocity needed to get to the end position.
     *
     * @param time Time in in-game ticks.
     */

    public float calculateVelocityX(int time){
        return (float) ((this.endX-this.posX)/(time));
    }

    /**
     * Calculates the <code>y</code> component of the velocity needed to get to the end position.
     *
     * @param time Time in in-game ticks.
     */

    public float calculateVelocityY(int time){
        float angle = (float) (Math.atan((this.endY-this.posY)/(this.endX-this.posX)));
        return (float) (calculateVelocityX(time)*Math.tan(angle));
    }

    /**
     * Calculates the <code>z</code> component of the velocity needed to get to the end position.
     *
     * @param time Time in in-game ticks.
     */

    public float calculateVelocityZ(int time){
        return (float) ((this.endZ-this.posZ)/(time));
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public int getFXLayer(){
        return 3;
    }

    public EntityLightningBoltFX setMaxAge(int maxAge){
        particleMaxAge = maxAge;
        return this;
    }

    public EntityLightningBoltFX setGravity(float gravity){
        particleGravity = gravity;
        return this;
    }

    public EntityLightningBoltFX setScale(float scale){
        particleScale = scale;
        return this;
    }

    public EntityLightningBoltFX setColor(float r, float g, float b){
        setRBGColorF(r, g, b);
        return this;
    }

}
/**
 * Copyright (c) 26/apr/2015 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.core.blocks.tile;

import com.minestellar.core.particles.EntityLightningFX;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.Timer;

public class TileEntityRadioHead extends TileEntity{

    public TileEntityRadioHead(){
        Timer checkerTimer = new Timer();
        checkerTimer.scheduleAtFixedRate(new TileEntityRadioHeadTask(this), 100, 10 * 1000);
    }

    @Override
    public void updateEntity(){
        if(this.getBlockMetadata() == 1){ // If the head is surrounded
            if(Math.random() <= 0.1){
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityLightningFX(worldObj, xCoord, yCoord, zCoord, 5, 2).setColor((float)Math.random(), (float)Math.random(), (float)Math.random()).setArrivalCoords(xCoord-2, yCoord+2, zCoord-2));
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityLightningFX(worldObj, xCoord, yCoord, zCoord, 5, 2).setColor((float)Math.random(), (float)Math.random(), (float)Math.random()).setArrivalCoords(xCoord+2, yCoord+2, zCoord-2));
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityLightningFX(worldObj, xCoord, yCoord, zCoord, 5, 2).setColor((float)Math.random(), (float)Math.random(), (float)Math.random()).setArrivalCoords(xCoord-2, yCoord+2, zCoord+2));
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityLightningFX(worldObj, xCoord, yCoord, zCoord, 5, 2).setColor((float)Math.random(), (float)Math.random(), (float)Math.random()).setArrivalCoords(xCoord+2, yCoord+2, zCoord+2));
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityLightningFX(worldObj, xCoord, yCoord, zCoord, 5, 2).setColor((float)Math.random(), (float)Math.random(), (float)Math.random()).setArrivalCoords(xCoord-2, yCoord-2, zCoord-2));
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityLightningFX(worldObj, xCoord, yCoord, zCoord, 5, 2).setColor((float)Math.random(), (float)Math.random(), (float)Math.random()).setArrivalCoords(xCoord+2, yCoord-2, zCoord-2));
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityLightningFX(worldObj, xCoord, yCoord, zCoord, 5, 2).setColor((float)Math.random(), (float)Math.random(), (float)Math.random()).setArrivalCoords(xCoord-2, yCoord-2, zCoord+2));
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityLightningFX(worldObj, xCoord, yCoord, zCoord, 5, 2).setColor((float)Math.random(), (float)Math.random(), (float)Math.random()).setArrivalCoords(xCoord+2, yCoord-2, zCoord+2));
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt){
        super.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt){
        super.writeToNBT(nbt);
    }

}
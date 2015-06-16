/**
 * Copyright (c) 03/06/15 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.core.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import java.util.ArrayList;

public class PlanetKnowledgeHandler implements IExtendedEntityProperties{

    public static final String PLANET_KNOWLEDGE = "PlanetKnowledge";

    /**
     * Array containing all the planets
     */

    private static final String[] planetNames = new String[]{
            "earth",
            "sun",
            "moon",
            "venus",
            "mercury",
            "mars",
            "jupiter",
            "saturn",
            "uranus",
            "neptune"
    };

    /**
     * Array containing all the values of the planets
     */

    private static final boolean[] planetFound = new boolean[]{
            true,
            true,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false
    };

    private final EntityPlayer player;

    public PlanetKnowledgeHandler(EntityPlayer player){
        this.player = player;
    }

    /**
     * Used to register these extended properties for the player during EntityConstructing event
     * This method is for convenience only; it will make your code look nicer
     */

    public static void register(EntityPlayer player){
        player.registerExtendedProperties(PlanetKnowledgeHandler.PLANET_KNOWLEDGE, new PlanetKnowledgeHandler(player));
    }

    /**
     * Returns ExtendedPlayer properties for player
     * This method is for convenience only; it will make your code look nicer
     */

    public static PlanetKnowledgeHandler get(EntityPlayer player){
        return (PlanetKnowledgeHandler) player.getExtendedProperties(PLANET_KNOWLEDGE);
    }

    @Override
    public void saveNBTData(NBTTagCompound tag){

        NBTTagCompound compound = new NBTTagCompound();

        for(int i = 0; i < planetNames.length; i++){
            compound.setBoolean(planetNames[i], planetFound[i]);
        }

        tag.setTag(PLANET_KNOWLEDGE, compound);

    }

    @Override
    public void loadNBTData(NBTTagCompound tag){

        NBTTagCompound compound = (NBTTagCompound) tag.getTag(PLANET_KNOWLEDGE);

        for(int i = 0; i < planetNames.length; i++){
            planetFound[i] = compound.getBoolean(planetNames[i]);
        }

    }

    /**
     * @return The array containing all known the planets
     */

    public ArrayList<String> getAcknowledgedPlanets(){
        ArrayList<String> returnArray = new ArrayList<String>();
        for(int i = 0; i < planetNames.length; i++){
            if(planetFound[i]){
                returnArray.add(planetNames[i]);
            }
        }
        return returnArray;
    }

    /**
     * @return The array containing all the values
     */

    public boolean[] getAcknowledgedValues(){
        return planetFound;
    }

    /**
     * Sets the value of the given planet
     *
     * @param planet The planet to change the value of
     */

    public void setAcknowledged(String planet){
        for(int i = 0; i < planetNames.length; i++){
            if(planetNames[i].equals(planet)){
                planetFound[i] = true;
            }
        }
    }

    /**
     * Sets the next available planet to known
     */

    public void setAcknowledgedNext(){
        for(int i = 0; i < planetNames.length; i++){
            if(!isAcknowledged(planetNames[i])){
                planetFound[i] = true;
                break;
            }
        }
    }

    /**
     * Returns if the planet has already been acknowledged
     *
     * @param planet The planet to know the value of
     *
     * @return {@code return planetFound[i];}
     */

    public boolean isAcknowledged(String planet){
        for(int i = 0; i < planetNames.length; i++){
            if(planetNames[i].equals(planet)){
                return planetFound[i];
            }
        }
        return false;
    }

    public void reset(){
        for(int i = 0; i < planetNames.length; i++){
            planetFound[i] = false;
        }
        planetFound[0] = true;
        planetFound[1] = true;
    }

    /**
     * This is useless.
     */

    @Override
    public void init(Entity entity, World world){}
}

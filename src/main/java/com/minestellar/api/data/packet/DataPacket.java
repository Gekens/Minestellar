/**
 * Copyright (c) 14/05/15 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.api.data.packet;

import net.minecraft.util.Vec3;

import java.util.Hashtable;
import java.util.Map;

/**
 * This class represents a packet of data sent from Object A to Object B
 */

public class DataPacket{

    private double senderX, senderY, senderZ, receiverX, receiverY, receiverZ;
    /**
     * The map that stores all the different data. To every identifier (String) corresponds a different Object
     */
    private Map<String, Object> dataMap;

    public DataPacket(double senderX, double senderY, double senderZ, double receiverX, double receiverY, double receiverZ){
        this.senderX = senderX;
        this.senderY = senderY;
        this.senderZ = senderZ;
        this.receiverX = receiverX;
        this.receiverY = receiverY;
        this.receiverZ = receiverZ;
        this.dataMap = new Hashtable<String, Object>();
    }

    public DataPacket(Vec3 senderCoordinates, Vec3 receiverCoordinates){
        this(senderCoordinates.xCoord, senderCoordinates.zCoord, senderCoordinates.zCoord,
                receiverCoordinates.xCoord, receiverCoordinates.yCoord, receiverCoordinates.zCoord);
    }

    /**
     * Adds the given object to the given identifier
     *
     * @param identifier The identifier to which the given object corresponds
     * @param object The object to add the the map
     */

    public void setAttribute(String identifier, Object object){
        dataMap.put(identifier, object);
    }

    /**
     * Returns the object associated to the given identifier
     * @param identifier The identifier
     * @return The object that corresponds to the given identifier
     */

    public Object getAttribute(String identifier){
        return dataMap.get(identifier);
    }

}
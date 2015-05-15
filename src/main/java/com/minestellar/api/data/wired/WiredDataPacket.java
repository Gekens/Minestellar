/**
 * Copyright (c) 15/05/15 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.api.data.wired;

import net.minecraftforge.common.util.ForgeDirection;

import java.util.Hashtable;
import java.util.Map;

public class WiredDataPacket{

    private ForgeDirection senderDirection, receiverDirection;

    /**
     * The map that stores all the different data. To every identifier (String) corresponds a different Object
     */
    private Map<String, Object> dataMap;

    public WiredDataPacket(ForgeDirection senderDirection, ForgeDirection reciverDirection){
        this.senderDirection = senderDirection;
        this.receiverDirection = reciverDirection;
        this.dataMap = new Hashtable<String, Object>();
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

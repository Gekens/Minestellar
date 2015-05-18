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

package com.minestellar.api.data.block;

import com.minestellar.api.data.wired.WiredDataPacket;
import com.minestellar.api.data.wireless.WirelessDataPacket;

/**
 * Implement this Interface on TileEntities that should send and/or receive packets of data.
 * <p>It does not matter if it should only be wired/wireless</p>
 */

public interface IDataConnection{

    /**
     * @return Whether or not this {@code TileEntity} should connect to any data-related network
     */

    boolean canDataConnect();

    /**
     * Receives a packet from the network
     *
     * @param packet The packet received
     */

    void receiveWirelessPacket(WirelessDataPacket packet);

    /**
     * Receives a packet from the network
     *
     * @param packet The packet received
     */

    void receiveWiredPacket(WiredDataPacket packet);

    /**
     * Sends a wireless packet
     *
     * @return The {@code WirelessDataPacket} to send
     */

    void sendWirelessPacket();

    /**
     * Sends a wired packet
     *
     * @return The {@code WiredDataPacket} to send
     */

    WiredDataPacket sendWiredPacket();

}

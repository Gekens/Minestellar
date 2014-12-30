package com.minestellar.core.util.tick;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;

import com.minestellar.core.util.ThreadVersionCheck;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

public class TickHandlerClient
{
    public static boolean checkedVersion = true;

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event)
    {
        final Minecraft minecraft = FMLClientHandler.instance().getClient();
        final WorldClient world = minecraft.theWorld;
        final EntityClientPlayerMP player = minecraft.thePlayer;

        if (event.phase == Phase.START)
        {
            if (world != null && TickHandlerClient.checkedVersion)
            {
                ThreadVersionCheck.startCheck();
                TickHandlerClient.checkedVersion = false;
            }
        }
    }
}

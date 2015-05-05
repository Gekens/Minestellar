package com.minestellar.api.frontier;

import com.minestellar.moon.util.ConfigManagerMoon;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;

public class Gravity {
	
	/* This is the event when the player is jumping and which this would increase the height of jump based
	*  on the current dimensions set gravity.
	*
	*/
	@SubscribeEvent
	
	public void GravityJump(LivingJumpEvent event)
	{
		if(!(event.entity instanceof EntityPlayer))
		{
			return;
		}
		// The dimension id for the moon
		if (event.entity.dimension == ConfigManagerMoon.idDimensionMoon)
		{
			event.entity.motionY *= 2; // The normal jump height is 2.125 block high, which this times that by 2.
		}
		
	}

	
	@SubscribeEvent
	
	public void GravityFall(PlayerTickEvent event)
	{
		EntityPlayer player = event.player;
		
		if(!player.isAirBorne || !player.isSneaking())
		{
			return;
		}
		
		event.player.motionY = -0.375; // This makes the fall slower to simulate low gravity.
		player.fallDistance = 0.0F;		
	}


	
	
}

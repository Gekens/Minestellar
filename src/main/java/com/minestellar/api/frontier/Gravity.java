package com.minestellar.api.frontier;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

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
		if (event.entity.dimension == -25)
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

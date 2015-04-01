package com.minestellar.api.gravity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class Gravity {
	
	/* This is the event when the player is jumping and which this would increase the height of jump based
	*  on the current dimensions set gravity.
	*
	*/
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	
	public void onEvent(LivingJumpEvent event, EntityPlayer player)
	{
		
		
		if(player != null)
		{
			double x = getHeight(1, getGravity(player));
			player.addVelocity(0,x,0);
		}
	}

	
	//This is used for a later idea of reducing movement speeds and etc.
	private static double getWeight(double mass, double gravity)
	{
		//Figures out the player's weight on the specific planet
		double weight = mass * gravity;
						
		return weight;
	}

	private static double getHeight(int jumpHeight, double gravity){
		// This calculates the height of the players jump on a specific planet or space.
		double height = (jumpHeight / gravity);
		
		return height;
	}
	/* This controls the event upon the user is falling to create falling with gravity
	 * 
	 */
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	
	public void onEvent(LivingFallEvent event, EntityPlayer player)
	{
		
		if(player != null)
		{
			double x = -1.0 * getGravity(player); 
			player.addVelocity(0,x,0);  
		}
		
	}


	private double getGravity(EntityPlayer player) 
	{
		int ID = player.dimension;
		double gravity = 0.0;
		
	
		if(ID >= -1 && ID <= 1)
		{
			switch(ID)
			{
				case -1: gravity = 1.2; break;
				case 0: gravity = 1.0;  break;
				case 1: gravity = 3.0;  break;
			}
		}
		else
		{
			
			
		}
		
		return gravity;
	}
	
}

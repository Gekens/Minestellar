package com.minestellar.api.frontier;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.event.entity.living.LivingEvent;

public abstract class MSOxygenSuffocationEvent extends LivingEvent 
{

	public final WorldProvider provider;
	
	public MSOxygenSuffocationEvent(EntityLivingBase entity)
	{
		super(entity);
		this.provider = entity.worldObj.provider;
		
	}
	
	@Cancelable
	public static class Pre extends MSOxygenSuffocationEvent
	{
		public Pre(EntityLivingBase entity)
		{
			super(entity);
		}
	}
	
	public static class Post extends MSOxygenSuffocationEvent
	{
		
		public Post(EntityLivingBase entity)
		{
			super(entity);
		}
	}
	
}

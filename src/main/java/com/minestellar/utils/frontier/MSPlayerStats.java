package com.minestellar.utils.frontier;

import java.lang.ref.WeakReference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class MSPlayerStats implements IExtendedEntityProperties {
	public static final String MS_PLAYER_PROP = "MSPlayerStats";
	public WeakReference<EntityPlayerMP> player;
	public int airRemaining;
	public int damageCounter;
	public int gravityLevel;
	public boolean oxygenValid;
	public boolean lastOxygenValid;

	public MSPlayerStats(EntityPlayerMP player) {
		this.player = new WeakReference<EntityPlayerMP>(player);
	}

	@Override
	public void saveNBTData(NBTTagCompound nbt) {
		nbt.setInteger("playerAirRemaining", this.airRemaining);
		nbt.setInteger("damageCounter", this.damageCounter);
		nbt.setInteger("gravityLevel", this.gravityLevel);
		nbt.setBoolean("OxygenValid", this.oxygenValid);
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt) {
		this.airRemaining = nbt.getInteger("playerAirRemaining");
		this.damageCounter = nbt.getInteger("damageCounter");
		this.gravityLevel = nbt.getInteger("gravityLevel");
		this.oxygenValid = this.lastOxygenValid = nbt.getBoolean("OxygenValid");
	}

	@Override
	public void init(Entity entity, World world) {
	}

	public static void register(EntityPlayerMP player) {
		player.registerExtendedProperties(MSPlayerStats.MS_PLAYER_PROP, new MSPlayerStats(player));
	}

	public static MSPlayerStats get(EntityPlayerMP player) {
		return (MSPlayerStats) player.getExtendedProperties(MSPlayerStats.MS_PLAYER_PROP);
	}
}

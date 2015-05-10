package com.minestellar.api.frontier;

import net.minecraft.util.DamageSource;

public class DamageSourceMS extends DamageSource {
	public static final DamageSourceMS suffocation = (DamageSourceMS) new DamageSourceMS("suffocation").setDamageBypassesArmor();
	public static final DamageSourceMS heavyGravity = (DamageSourceMS) new DamageSourceMS("heavyGravity").setDamageBypassesArmor();

	public DamageSourceMS(String damageType) {
		super(damageType);
	}
}

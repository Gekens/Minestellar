package com.minestellar.api.world;

public interface MinestellarWorldProvider{
    public float getGravity();
    public float getFallDamageModifier();
    public boolean hasBreathableAtmosphere();
}
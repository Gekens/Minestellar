package com.minestellar.api.world;

import com.minestellar.api.vector.Vector3;

/**
 * Implement this in your World Provider, this sets the basic info for your new world!
 */
public interface MinestellarWorldProvider
{
	/**
	 * Gets the gravity levels on the world. 
	 * 1 would be equivalent to overworld. DO NOT USE 0!
	 * @return (1.0 - 0.01)F
	 */
    public float getGravity();
    
    /**
     * Gets the fall damage on the world.
     * 1 would be equivalent to overworld. 0 would cause no fall damage.
     * @return (1.0 - 0.0)F
     */
    public float getFallDamageModifier();
    
    /**
     * Tells weather the planet has a breathable atmosphere or not.
     * True means it has a atmosphere and you can breath, false has no atmosphere.
     * @return (true / false)
     */
    public boolean hasBreathableAtmosphere();
    
    
    /**
     * How long the days are.
     * 24000 is 1 minecraft day. Try now to use very short values, the sun will start glitching.
     * @return (Any Non-Decimal Number)L
     */
	long getDayLength();

	/**
	 * Fog color, what player sees in the distance.
	 * @return
	 */
	Vector3 getFogColor();

	/**
	 * Sky color, its everywhere!!
	 * @return
	 */
	Vector3 getSkyColor();
}
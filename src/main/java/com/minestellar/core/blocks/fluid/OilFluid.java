package com.minestellar.core.blocks.fluid;

import net.minecraftforge.fluids.Fluid;

import com.minestellar.core.blocks.BlockFluidOil;

public class OilFluid extends Fluid
{
	public OilFluid(String fluidName)
	{
		super(fluidName);
		this.setIcons(BlockFluidOil.oilStillIcon, BlockFluidOil.oilFlowingIcon);
	}
}
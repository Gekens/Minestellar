package com.minestellar.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import com.minestellar.core.MinestellarCore;

public class BlockTeleportCore extends Block
{
	protected BlockTeleportCore(String unlocalizedName, Material material)
	{
		super(material);
		this.setBlockName(unlocalizedName);
		this.setBlockTextureName(MinestellarCore.TEXTURE_PREFIX + unlocalizedName);
		this.setHardness(3.0F);
		this.setResistance(6.0F);
		this.setLightLevel(0.75F);
		this.setHarvestLevel("pickaxe", 3);
		this.setStepSound(soundTypeMetal);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return MinestellarCore.stellarBlocksTab;
	}
}

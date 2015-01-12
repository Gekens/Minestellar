package com.minestellar.moon.blocks.items;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.minestellar.core.proxy.ClientProxyCore;

public class ItemBlockBasicMoon extends ItemBlock {
	private static final String[] types = new String[] {
	"surfaceStone", "surfaceStone1", "surfaceStone2", "surfaceStone3", "surfaceStone4", "surfaceStone5", "surfaceStone6", "surfaceStone7", "surfaceStone8", "subSurfaceStone", "stone", "cobblestone",
	};

	public ItemBlockBasicMoon(Block par1) {
		super(par1);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return ClientProxyCore.stellarItem;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		int meta = itemstack.getItemDamage();

		if (meta < 0 || meta >= ItemBlockBasicMoon.types.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + ItemBlockBasicMoon.types[meta];
	}
}

package com.minestellar.venus.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.proxy.ClientProxyCore;
import com.minestellar.venus.MinestellarVenus;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHoeVenus extends ItemHoe {
	public ItemHoeVenus(String name, ToolMaterial par2EnumToolMaterial) {
		super(par2EnumToolMaterial);
		this.setUnlocalizedName(name);
	}

	@Override
	public CreativeTabs getCreativeTab() {
		return MinestellarCore.stellarItemsTab;
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return ClientProxyCore.stellarItem;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(this.getUnlocalizedName().replace("item.", MinestellarVenus.TEXTURE_PREFIX));
	}
}

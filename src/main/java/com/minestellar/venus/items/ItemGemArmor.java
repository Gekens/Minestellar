package com.minestellar.venus.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.proxy.ClientProxyCore;
import com.minestellar.venus.MinestellarVenus;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGemArmor extends ItemArmor {
	private final ArmorMaterial material;

	public ItemGemArmor(String name, ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par2EnumArmorMaterial, par3, par4);
		this.material = par2EnumArmorMaterial;
		this.setUnlocalizedName(name);
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return ClientProxyCore.stellarItem;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer) {
		if (this.material == VenusItems.ARMOR_GEM) {
			if (stack.getItem() == VenusItems.gemHelmet) {
				return MinestellarVenus.TEXTURE_PREFIX + "textures/model/armor/gem_1.png";
			} else if (stack.getItem() == VenusItems.gemChestplate || stack.getItem() == VenusItems.gemBoots) {
				return MinestellarVenus.TEXTURE_PREFIX + "textures/model/armor/gem_2.png";
			} else if (stack.getItem() == VenusItems.gemLeggings) {
				return MinestellarVenus.TEXTURE_PREFIX + "textures/model/armor/gem_3.png";
			}
		}

		return null;
	}

	@Override
	public CreativeTabs getCreativeTab() {
		return MinestellarCore.stellarItemsTab;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(this.getUnlocalizedName().replace("item.", MinestellarVenus.TEXTURE_PREFIX));
	}

	@Override
	public Item setUnlocalizedName(String par1Str) {
		super.setTextureName(par1Str);
		super.setUnlocalizedName(par1Str);
		return this;
	}
}

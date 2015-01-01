/**
 * Copyright (c) 31/dic/2014 Davide Cossu & Matthew Albrecht.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, see <http://www.gnu.org/licenses>.
 */

package com.minestellar.core.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.proxy.ClientProxyCore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemArmorCarbon extends ItemArmor
{
	private final ArmorMaterial material;

	public ItemArmorCarbon(String name, ArmorMaterial par2EnumArmorMaterial, int par3, int par4)
	{
		super(par2EnumArmorMaterial, par3, par4);
		this.material = par2EnumArmorMaterial;
		this.setUnlocalizedName(name);
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyCore.stellarItem;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
	{
		if (this.material == CoreItems.ARMOR_CARBON)
		{
			if (stack.getItem() == CoreItems.carbonHelmet)
			{
				return MinestellarCore.TEXTURE_PREFIX + "textures/model/armor/carbon_1.png";
			}
			else if (stack.getItem() == CoreItems.carbonChestplate || stack.getItem() == CoreItems.carbonBoots)
			{
				return MinestellarCore.TEXTURE_PREFIX + "textures/model/armor/carbon_2.png";
			}
			else if (stack.getItem() == CoreItems.carbonLeggings)
			{
				return MinestellarCore.TEXTURE_PREFIX + "textures/model/armor/carbon_3.png";
			}
		}

		return null;
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return MinestellarCore.stellarItemsTab;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(this.getUnlocalizedName().replace("item.", MinestellarCore.TEXTURE_PREFIX));
	}

	@Override
	public Item setUnlocalizedName(String par1Str)
	{
		super.setTextureName(par1Str);
		super.setUnlocalizedName(par1Str);
		return this;
	}
}

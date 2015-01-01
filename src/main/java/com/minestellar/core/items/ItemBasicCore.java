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

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.proxy.ClientProxyCore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBasicCore extends Item
{
	private static String[] names = {
			"ingotCopper", // 0
			"ingotTin", // 1
			"ingotSteel", // 2
			"ingotLithium", // 3
			"ingotSilicon", // 4
			"ingotAluminum", // 5
			"ingotTitanium", // 6
			"ingotCarbon", // 7

			"dustSteel", // 8

			"compressedCopper", // 9
			"compressedTin", // 10
			"compressedSteel", // 11
			"compressedLithium", // 12
			"compressedSilicon", // 13
			"compressedAluminum", // 14
			"compressedTitanium", // 15
			"compressedCarbon", // 16

			"electricMotor", // 17
			"electricPump", // 18
			"filterAir", // 19
			"filterOil", // 20
			"nozzleSteel", // 21
			"pipeSteel", // 22
			"pipeMagnetic", // 23
	};

	protected IIcon[] icons = new IIcon[ItemBasicCore.names.length];

	public ItemBasicCore()
	{
		super();
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public CreativeTabs getCreativeTab()
	{
		return MinestellarCore.stellarItemsTab;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyCore.stellarItem;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		int i = 0;

		for (String name : ItemBasicCore.names)
		{
			this.icons[i++] = iconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + name);
		}
	}

	@Override
	public IIcon getIconFromDamage(int damage)
	{
		if (this.icons.length > damage)
		{
			return this.icons[damage];
		}

		return super.getIconFromDamage(damage);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < ItemBasicCore.names.length; i++)
		{
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		if (this.icons.length > par1ItemStack.getItemDamage())
		{
			return "item." + ItemBasicCore.names[par1ItemStack.getItemDamage()];
		}

		return "unnamed";
	}

	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}
}

package com.minestellar.core.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.minestellar.core.Minestellar;
import com.minestellar.core.proxy.ClientProxyCore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBasicCore extends Item
{
	private static String[] names = {
			"ingotCopper", // 0
			"ingotTin", // 1
			"ingotTitanium", // 2
			"ingotSteel", // 3

			"dustSteel", // 4
			"bitsCarbon", // 5

			"containerCopper", // 6
			"containerTin", // 7
			"containerTitanium", // 8

			"compressedCopper", // 9
			"compressedTin", // 10
			"compressedTitanium", // 11
			"compressedCarbon", // 12
			"compressedSteel", // 13

			"filterAir", // 14
			"filterOil", // 15
			"electricMotor", // 16
			"electricPump", // 17
			"fanSteel", // 18
			"nozzleSteel", // 19
			"pipeSteel", // 20
			"exceleratorCopper", // 21
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
		return Minestellar.stellarItemsTab;
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
			this.icons[i++] = iconRegister.registerIcon(Minestellar.TEXTURE_PREFIX + name);
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

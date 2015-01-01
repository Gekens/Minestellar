package com.minestellar.core.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.proxy.ClientProxyCore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemArmorCarbon extends ItemArmor
{
	public ItemArmorCarbon(int armorIndex, String assetSuffix)
	{
		super(CoreItems.ARMOR_CARBON, MinestellarCore.proxy.getCarbonArmorRenderIndex(), armorIndex);
		this.setUnlocalizedName("carbon_" + assetSuffix);
		this.setTextureName(MinestellarCore.TEXTURE_PREFIX + "carbon_" + assetSuffix);
	}

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
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (this.getArmorMaterial() == CoreItems.ARMOR_CARBON)
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
}

/**
 * Copyright (c) 22/Feb/2015 Davide Cossu & Matthew Albrecht.
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

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ItemFluidContainer;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.proxy.ClientProxyCore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ItemCanisterGeneric extends ItemFluidContainer {
	private String allowedFluid = null;
	public final static int EMPTY = FluidContainerRegistry.BUCKET_VOLUME + 1;

	public ItemCanisterGeneric(String assetName) {
		super(0, FluidContainerRegistry.BUCKET_VOLUME);
		this.setMaxDamage(FluidContainerRegistry.BUCKET_VOLUME + 1);
		this.setMaxStackSize(1);
		this.setNoRepair();
		this.setUnlocalizedName(assetName);
		this.setContainerItem(CoreItems.canisterOil);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return ClientProxyCore.stellarItem;
	}

	@Override
	public CreativeTabs getCreativeTab() {
		return MinestellarCore.stellarItemsTab;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 1));
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		if (itemStack != null && itemStack.getItem() == this.getContainerItem() && itemStack.getItemDamage() == ItemCanisterGeneric.EMPTY) {
			return null;
		}

		return new ItemStack(this.getContainerItem(), 1, ItemCanisterGeneric.EMPTY);
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		if (ItemCanisterGeneric.EMPTY == par1ItemStack.getItemDamage()) {
			final int stackSize = par1ItemStack.stackSize;

			if (!(par1ItemStack.getItem() instanceof ItemCanisterOil)) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setShort("id", (short) Item.getIdFromItem(CoreItems.canisterOil));
				tag.setByte("Count", (byte) stackSize);
				tag.setShort("Damage", (short) ItemCanisterGeneric.EMPTY);
				par1ItemStack.readFromNBT(tag);
			}
		}
	}

	public void setAllowedFluid(String name) {
		this.allowedFluid = new String(name);
	}

	public String getAllowedFluid() {
		return this.allowedFluid;
	}

	@Override
	public int fill(ItemStack container, FluidStack resource, boolean doFill) {
		if (resource == null || resource.getFluid() == null || !(container.getItem() instanceof ItemCanisterGeneric)) {
			return 0;
		}

		String fluidName = resource.getFluid().getName();
		if (container.getItemDamage() == ItemCanisterGeneric.EMPTY) {
			for (String key : MinestellarCore.itemList.keySet()) {
				if (key.contains("CanisterFull")) {
					Item i = MinestellarCore.itemList.get(key).getItem();
					if (i instanceof ItemCanisterGeneric && fluidName.equalsIgnoreCase(((ItemCanisterGeneric) i).allowedFluid)) {
						NBTTagCompound tag = new NBTTagCompound();
						tag.setShort("id", (short) Item.getIdFromItem(i));
						tag.setByte("Count", (byte) 1);
						tag.setShort("Damage", (short) ItemCanisterGeneric.EMPTY);
						container.readFromNBT(tag);

						break;
					}
				}
			}
		}

		if (fluidName.equalsIgnoreCase(((ItemCanisterGeneric) container.getItem()).allowedFluid)) {
			int added = super.fill(container, resource, doFill);
			container.setItemDamage(Math.min(1, container.getItemDamage() - added));
			return added;
		}

		return 0;
	}

	@Override
	public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain) {
		if (this.allowedFluid == null) {
			return null;
		}

		container.stackTagCompound = null;

		super.fill(container, this.getFluid(container), true);

		return super.drain(container, maxDrain, doDrain);
	}

	@Override
	public FluidStack getFluid(ItemStack container) {
		if (this.allowedFluid == null || ItemCanisterGeneric.EMPTY == container.getItemDamage()) {
			return null;
		}

		Fluid fluid = FluidRegistry.getFluid(this.allowedFluid);

		if (fluid == null) {
			return null;
		}

		return new FluidStack(fluid, ItemCanisterGeneric.EMPTY - container.getItemDamage());
	}
}

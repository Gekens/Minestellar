/**
 * Copyright (c) 11/January/2015 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.core.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.minestellar.core.MinestellarCore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFluidNitrogen extends BlockFluidClassic {
	public static IIcon nitrogenStillIcon;
	public static IIcon nitrogenFlowingIcon;

	public BlockFluidNitrogen(String name, Fluid fluid, Material par2Material) {
		super(fluid, par2Material);
		this.setQuantaPerBlock(8);
		this.setRenderPass(1);
		this.needsRandomTick = true;
		this.stack = new FluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME);
		this.setBlockName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return par1 != 0 && par1 != 1 ? BlockFluidNitrogen.nitrogenStillIcon : BlockFluidNitrogen.nitrogenFlowingIcon;
	}

	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
		if (world.getBlock(x, y, z).getMaterial().isLiquid()) {
			return false;
		}
		return super.canDisplace(world, x, y, z);
	}

	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z) {
		if (world.getBlock(x, y, z).getMaterial().isLiquid()) {
			return false;
		}
		return super.displaceIfPossible(world, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		BlockFluidNitrogen.nitrogenStillIcon = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "nitrogen_flowing");
		BlockFluidNitrogen.nitrogenFlowingIcon = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + "nitrogen_still");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		super.randomDisplayTick(world, x, y, z, rand);

		if (rand.nextInt(64) == 0) {
			final int l = world.getBlockMetadata(x, y, z);

			if (l > 0 && l < 8) {
				world.playSound(x + 0.5F, y + 0.5F, z + 0.5F, "liquid.water", rand.nextFloat() * 0.25F + 0.75F, rand.nextFloat() * 1.0F + 0.5F, false);
			}
		}

		if (rand.nextInt(10) == 0) {
			final int l = world.getBlockMetadata(x, y, z);

			if (l <= 0 || l >= 8) {
				world.spawnParticle("suspended", x + rand.nextFloat(), y + rand.nextFloat(), z + rand.nextFloat(), 0.0D, 0.0D, 0.0D);
			}
		}

		if (rand.nextInt(10) == 0 && World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && !world.getBlock(x, y - 2, z).getMaterial().blocksMovement()) {
			final double d5 = x + rand.nextFloat();
			final double d6 = y - 1.05D;
			final double d7 = z + rand.nextFloat();
			MinestellarCore.proxy.spawnParticle("nitrogenDrip", d5, d6, d7);
		}
	}
}

/**
 * Copyright (c) 04/January/2015 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.moon.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.minestellar.core.MinestellarCore;
import com.minestellar.core.blocks.CoreBlocks;
import com.minestellar.moon.MinestellarMoon;
import com.minestellar.moon.util.ConfigManagerMoon;
import com.minestellar.moon.world.TeleporterMoon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTeleporterMoon extends BlockBreakable {
	public static final int[][] field_150001_a = new int[][] {
	new int[0], {
	3, 1
	}, {
	2, 0
	}
	};

	private static final String __OBFID = "CL_00000284";

	public BlockTeleporterMoon(String name) {
		super(MinestellarMoon.MODID + name, Material.portal, false);
		this.setTickRandomly(true);
		this.setBlockName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public CreativeTabs getCreativeTabToDisplayOn() {
		return MinestellarCore.stellarBlocksTab;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		super.updateTick(world, x, y, z, random);

		if (world.provider.isSurfaceWorld() && world.getGameRules().getGameRuleBooleanValue("doMobSpawning") && random.nextInt(2000) < world.difficultySetting.getDifficultyId()) {
			int l;

			for (l = y; !World.doesBlockHaveSolidTopSurface(world, x, l, z) && l > 0; --l) {
			}

			if (l > 0 && !world.getBlock(x, l + 1, z).isNormalCube()) {
				Entity entity = ItemMonsterPlacer.spawnCreature(world, 57, x + 0.5D, l + 1.1D, z + 0.5D);

				if (entity != null) {
					entity.timeUntilPortal = entity.getPortalCooldown();
				}
			}
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockaccess, int x, int y, int z) {
		int l = func_149999_b(blockaccess.getBlockMetadata(x, y, z));
		if (l == 0) {
			if (blockaccess.getBlock(x - 1, y, z) != this && blockaccess.getBlock(x + 1, y, z) != this) {
				l = 2;
			}

			else {
				l = 1;
			}

			if (blockaccess instanceof World && !((World) blockaccess).isRemote) {
				((World) blockaccess).setBlockMetadataWithNotify(x, y, z, l, 2);
			}
		}

		float f = 0.125F;
		float f1 = 0.125F;

		if (l == 1) {
			f = 0.5F;
		}

		if (l == 2) {
			f1 = 0.5F;
		}

		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
		int i1 = 0;

		if (p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_) == this) {
			i1 = func_149999_b(p_149646_1_.getBlockMetadata(p_149646_2_, p_149646_3_, p_149646_4_));

			if (i1 == 0) {
				return false;
			}

			if (i1 == 2 && p_149646_5_ != 5 && p_149646_5_ != 4) {
				return false;
			}

			if (i1 == 1 && p_149646_5_ != 3 && p_149646_5_ != 2) {
				return false;
			}
		}

		boolean flag = p_149646_1_.getBlock(p_149646_2_ - 1, p_149646_3_, p_149646_4_) == this && p_149646_1_.getBlock(p_149646_2_ - 2, p_149646_3_, p_149646_4_) != this;
		boolean flag1 = p_149646_1_.getBlock(p_149646_2_ + 1, p_149646_3_, p_149646_4_) == this && p_149646_1_.getBlock(p_149646_2_ + 2, p_149646_3_, p_149646_4_) != this;
		boolean flag2 = p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_ - 1) == this && p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_ - 2) != this;
		boolean flag3 = p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_ + 1) == this && p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_ + 2) != this;
		boolean flag4 = flag || flag1 || i1 == 1;
		boolean flag5 = flag2 || flag3 || i1 == 2;
		return flag4 && p_149646_5_ == 4 ? true : (flag4 && p_149646_5_ == 5 ? true : (flag5 && p_149646_5_ == 2 ? true : flag5 && p_149646_5_ == 3));
	}

	@Override
	public int quantityDropped(Random p_149745_1_) {
		return 0;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if ((entity.ridingEntity == null) && (entity.riddenByEntity == null) && ((entity instanceof EntityPlayerMP))) {
			EntityPlayerMP thePlayer = (EntityPlayerMP) entity;

			if (thePlayer.timeUntilPortal > 0) {
				thePlayer.timeUntilPortal = 10;
			}

			else if (thePlayer.dimension != ConfigManagerMoon.idDimensionMoon) {
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, ConfigManagerMoon.idDimensionMoon, new TeleporterMoon(thePlayer.mcServer.worldServerForDimension(ConfigManagerMoon.idDimensionMoon)));
			}

			else {
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new TeleporterMoon(thePlayer.mcServer.worldServerForDimension(0)));
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		for (int l = 0; l < 4; ++l) {
			double d0 = x + random.nextFloat();
			double d1 = y + random.nextFloat();
			double d2 = z + random.nextFloat();
			double d3 = 0.0D;
			double d4 = 0.0D;
			double d5 = 0.0D;
			int i1 = random.nextInt(2) * 2 - 1;
			d3 = (random.nextFloat() - 0.5D) * 0.5D;
			d4 = (random.nextFloat() - 0.5D) * 0.5D;
			d5 = (random.nextFloat() - 0.5D) * 0.5D;

			if (world.getBlock(x - 1, y, z) != this && world.getBlock(x + 1, y, z) != this) {
				d0 = x + 0.5D + 0.25D * i1;
				d3 = random.nextFloat() * 2.0F * i1;
			}

			else {
				d2 = z + 0.5D + 0.25D * i1;
				d5 = random.nextFloat() * 2.0F * i1;
			}
		}
	}

	public static int func_149999_b(int p_149999_0_) {
		return p_149999_0_ & 3;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
		return Item.getItemById(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(MinestellarMoon.TEXTURE_PREFIX + "moon_portal");
	}

	public boolean tryToCreatePortal(World par1World, int par2, int par3, int par4) {
		byte b0 = 0;
		byte b1 = 0;

		if (par1World.getBlock(par2 - 1, par3, par4) == CoreBlocks.teleportBlock || par1World.getBlock(par2 + 1, par3, par4) == CoreBlocks.teleportBlock) {
			b0 = 1;
		}

		if (par1World.getBlock(par2, par3, par4 - 1) == CoreBlocks.teleportBlock || par1World.getBlock(par2, par3, par4 + 1) == CoreBlocks.teleportBlock) {
			b1 = 1;
		}

		if (b0 == b1) {
			return false;
		}

		else {
			if (par1World.getBlock(par2 - b0, par3, par4 - b1) == Blocks.air) {
				par2 -= b0;
				par4 -= b1;
			}

			int l;
			int i1;

			for (l = -1; l <= 2; ++l) {
				for (i1 = -1; i1 <= 3; ++i1) {
					boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;

					if (l != -1 && l != 2 || i1 != -1 && i1 != 3) {
						Block j1 = par1World.getBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l);

						if (flag) {
							if (j1 != CoreBlocks.teleportBlock) {
								return false;
							}
						}
					}
				}
			}

			for (l = 0; l < 2; ++l) {
				for (i1 = 0; i1 < 3; ++i1) {
					par1World.setBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l, this, 0, 2);
				}
			}

			return true;
		}
	}
}

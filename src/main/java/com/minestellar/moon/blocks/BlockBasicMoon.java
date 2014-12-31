package com.minestellar.moon.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.minestellar.core.Minestellar;
import com.minestellar.moon.MoonCore;

public class BlockBasicMoon extends Block
{
	private IIcon[] basicBlockIcon;

	public BlockBasicMoon(String name)
	{
		super(Material.rock);
		this.setBlockName(name);
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.basicBlockIcon = new IIcon[3]; // UPDATE WHEN ADDING BLOCKS
		this.basicBlockIcon[0] = par1IconRegister.registerIcon(MoonCore.TEXTURE_PREFIX + "moonSurfaceRock");
		this.basicBlockIcon[1] = par1IconRegister.registerIcon(MoonCore.TEXTURE_PREFIX + "moonSubRock");
		this.basicBlockIcon[2] = par1IconRegister.registerIcon(MoonCore.TEXTURE_PREFIX + "moonRock");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return Minestellar.stellarBlocksTab;
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return this.basicBlockIcon[meta];
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 3; ++i) // UPDATE WHEN ADDING BLOCKS
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4) // FIX
	{
		final int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 0)
		{
			return 2.25F;
		}

		if (meta == 1)
		{
			return 2.25F;
		}

		if (meta == 2)
		{
			return 2.75F;
		}

		return 1.0F;
	}

	@Override
	public int damageDropped(int meta)
	{
		/*
		 * if (meta == 2) { return 3; }
		 */

		return meta;
	}
}

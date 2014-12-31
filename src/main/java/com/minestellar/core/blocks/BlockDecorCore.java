package com.minestellar.core.blocks;

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

public class BlockDecorCore extends Block
{
	private IIcon[] stellarBlockIcon;

	public BlockDecorCore(String name)
	{
		super(Material.rock);
		this.setBlockName(name);
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.stellarBlockIcon = new IIcon[5]; // UPDATE WHEN ADDING BLOCKS
		this.stellarBlockIcon[0] = par1IconRegister.registerIcon(Minestellar.TEXTURE_PREFIX + "blockCopper");
		this.stellarBlockIcon[1] = par1IconRegister.registerIcon(Minestellar.TEXTURE_PREFIX + "blockTin");
		this.stellarBlockIcon[2] = par1IconRegister.registerIcon(Minestellar.TEXTURE_PREFIX + "blockTitanium");
		this.stellarBlockIcon[3] = par1IconRegister.registerIcon(Minestellar.TEXTURE_PREFIX + "blockCarbon");
		this.stellarBlockIcon[4] = par1IconRegister.registerIcon(Minestellar.TEXTURE_PREFIX + "blockSteel");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return Minestellar.stellarBlocksTab;
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return this.stellarBlockIcon[meta];
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 5; ++i) // UPDATE WHEN ADDING BLOCKS
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
			return 3.0F;
		}

		if (meta == 1)
		{
			return 3.0F;
		}

		if (meta == 2)
		{
			return 3.5F;
		}

		if (meta == 3)
		{
			return 3.23F;
		}

		if (meta == 4)
		{
			return 3.45F;
		}

		return 1.0F;
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
}

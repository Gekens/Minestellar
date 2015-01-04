package com.minestellar.core.blocks;

import com.minestellar.api.block.WireType;
import com.minestellar.core.MinestellarCore;
import com.minestellar.core.blocks.tileEntities.TileEntityCable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class BlockCable extends Block implements ITileEntityProvider
{
	public static final String[] names = { "wireLight", "wireMedium", "wireHeavy" };
	float pixel = 1F / 16F;
	private static IIcon[] blockIcons;

	public BlockCable(String assetName)
	{
		super(Material.cloth);
		this.setStepSound(Block.soundTypeCloth);
		this.setResistance(0.2F);
		this.setBlockBounds(0.4F, 0.4F, 0.4F, 0.6F, 0.6F, 0.6F);
		this.setHardness(0.075F);
		this.setBlockName(assetName);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return MinestellarCore.stellarBlocksTab;
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		BlockCable.blockIcons = new IIcon[BlockCable.names.length];

		for (int i = 0; i < BlockCable.names.length; i++)
		{
			BlockCable.blockIcons[i] = par1IconRegister.registerIcon(MinestellarCore.TEXTURE_PREFIX + BlockCable.names[i]);
		}
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		switch (meta)
		{
			case 0:
				return BlockCable.blockIcons[0];
			case 1:
				return BlockCable.blockIcons[1];
			case 2:
				return BlockCable.blockIcons[2];
			default:
				return BlockCable.blockIcons[0];
		}
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public int damageDropped(int metadata)
	{
		return metadata;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		TileEntity tile;
		switch (metadata)
		{
			case 0:
				tile = new TileEntityCable(1);
				break;
			case 1:
				tile = new TileEntityCable(2);
				break;
			case 2:
				tile = new TileEntityCable(3);
				break;
			default:
				return null;
		}

		return tile;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
	}

//	@Override
	public WireType getNetworkType()
	{
		return WireType.energy;
	}
}
package mattparks.mods.space.venus.blocks;

import java.util.List;

import mattparks.mods.space.venus.VenusCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.minestellar.core.Minestellar;

public class BlockDecorVenus extends Block
{
	private IIcon[] venusBlockIcon;

	public BlockDecorVenus(String name)
	{
		super(Material.rock);
		this.setBlockName(name);
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.venusBlockIcon = new IIcon[4];
		this.venusBlockIcon[0] = par1IconRegister.registerIcon(VenusCore.TEXTURE_PREFIX + "sulfurBlock");
		this.venusBlockIcon[1] = par1IconRegister.registerIcon(VenusCore.TEXTURE_PREFIX + "uraniumBlock");
		this.venusBlockIcon[2] = par1IconRegister.registerIcon(VenusCore.TEXTURE_PREFIX + "gemBlock");
		this.venusBlockIcon[3] = par1IconRegister.registerIcon(VenusCore.TEXTURE_PREFIX + "crystalBlock");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return Minestellar.stellarBlocksTab;
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return this.venusBlockIcon[meta];
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 4; ++i)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4) //FIX
	{
		final int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 0)
		{
			return 4.0F;
		}
		
		if (meta == 1)
		{
			return 4.0F;
		}
		
		if (meta == 2)
		{
			return 4.0F;
		}
		
		if (meta == 3)
		{
			return 4.0F;
		}

		return 1.0F;
	}
}
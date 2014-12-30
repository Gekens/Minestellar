package com.minestellar.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import com.minestellar.core.Minestellar;
import com.minestellar.core.blocks.fluid.OilFluid;
import com.minestellar.core.blocks.items.ItemBlockDecor;
import com.minestellar.core.blocks.items.ItemBlockOres;
import com.minestellar.core.util.ItemBlockUtil;

public class CoreBlocks
{
	public static Block coreOreBlocks;
	public static Block coreDecorBlocks;
	
	public static Block oilFluidBlock;
	public static Fluid oilFluid;

	private static void initBlocks()
	{
		CoreBlocks.coreOreBlocks = new BlockOreCore("minestellar_ore");
		CoreBlocks.coreDecorBlocks = new BlockDecorCore("minestellar_decor");
		
		CoreBlocks.oilFluid = new OilFluid("oil").setBlock(CoreBlocks.oilFluidBlock);
		FluidRegistry.registerFluid(CoreBlocks.oilFluid);
		CoreBlocks.oilFluidBlock = new BlockFluidOil("oil", CoreBlocks.oilFluid, Material.water);
	}
	
	public static void setHarvestLevels()
	{
	}

	private static void registerBlocks()
	{
		Minestellar.registerBlock(CoreBlocks.coreOreBlocks, ItemBlockOres.class);
		Minestellar.registerBlock(CoreBlocks.coreDecorBlocks, ItemBlockDecor.class);
		
		Minestellar.registerBlock(CoreBlocks.oilFluidBlock, ItemBlockUtil.class);

	}
	
    public static void oreDictRegistration()
    {
        OreDictionary.registerOre("oreCopper", new ItemStack(CoreBlocks.coreOreBlocks, 1, 0));
        OreDictionary.registerOre("oreTin", new ItemStack(CoreBlocks.coreOreBlocks, 1, 1));
        OreDictionary.registerOre("oreTitanium", new ItemStack(CoreBlocks.coreOreBlocks, 1, 2));
        
        OreDictionary.registerOre("blockCopper", new ItemStack(CoreBlocks.coreDecorBlocks, 1, 0));
        OreDictionary.registerOre("blockTin", new ItemStack(CoreBlocks.coreDecorBlocks, 1, 1));
        OreDictionary.registerOre("blcokTitanium", new ItemStack(CoreBlocks.coreDecorBlocks, 1, 2));
    }
	
	public static void init()
	{
		initBlocks();
		setHarvestLevels();
		registerBlocks();
		oreDictRegistration();
	}
}
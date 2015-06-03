package com.minestellar.venus.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.minestellar.utils.ItemBlockUtil;
import com.minestellar.venus.MinestellarVenus;
import com.minestellar.venus.blocks.items.ItemBlockBasicVenus;
import com.minestellar.venus.blocks.items.ItemBlockDecorVenus;

public class VenusBlocks {
	public static Block venusBasicBlock;
	public static Block venusDecorBlock;
	public static Block evolvedBlazeEgg;
	public static Block sulfurTorch;
	public static Block venusianTNT;
	public static Block vurnStone;
	public static BlockTeleporterVenus venusPortal;

	private static void initBlocks() {
		VenusBlocks.venusBasicBlock = new BlockBasicVenus("venus_basic");
		VenusBlocks.venusDecorBlock = new BlockDecorVenus("venus_decor");
		VenusBlocks.evolvedBlazeEgg = new BlockEvolvedBlazeEgg("evolved_blaze_egg");
		VenusBlocks.sulfurTorch = new BlockSulfurTorch("sulfur_torch");
		VenusBlocks.venusianTNT = new BlockVenusianTNT("venusian_tnt");
		VenusBlocks.vurnStone = new BlockVurnStone("vurn_stone");
		VenusBlocks.venusPortal = new BlockTeleporterVenus("venus_portal");
	}

	public static void setHarvestLevels() {
	}

	private static void registerBlocks() {
		MinestellarVenus.registerBlock(VenusBlocks.venusBasicBlock, ItemBlockBasicVenus.class);
		MinestellarVenus.registerBlock(VenusBlocks.venusDecorBlock, ItemBlockDecorVenus.class);
		MinestellarVenus.registerBlock(VenusBlocks.venusianTNT, ItemBlockUtil.class);
		MinestellarVenus.registerBlock(VenusBlocks.vurnStone, ItemBlockUtil.class);
		MinestellarVenus.registerBlock(VenusBlocks.evolvedBlazeEgg, ItemBlockUtil.class);
		MinestellarVenus.registerBlock(VenusBlocks.sulfurTorch, ItemBlockUtil.class);
		MinestellarVenus.registerBlock(VenusBlocks.venusPortal, ItemBlockUtil.class);
	}

	public static void oreDictRegistration() {
		OreDictionary.registerOre("oreSulfur", new ItemStack(VenusBlocks.venusBasicBlock, 1, 4));
		OreDictionary.registerOre("oreUranium", new ItemStack(VenusBlocks.venusBasicBlock, 1, 5));
		OreDictionary.registerOre("oreRedGem", new ItemStack(VenusBlocks.venusBasicBlock, 1, 6));
		OreDictionary.registerOre("oreCrystal", new ItemStack(VenusBlocks.venusBasicBlock, 1, 7));
		OreDictionary.registerOre("oreTin", new ItemStack(VenusBlocks.venusBasicBlock, 1, 8));
		OreDictionary.registerOre("oreCopper", new ItemStack(VenusBlocks.venusBasicBlock, 1, 9));
		OreDictionary.registerOre("oreIron", new ItemStack(VenusBlocks.venusBasicBlock, 1, 10));
		OreDictionary.registerOre("oreCoal", new ItemStack(VenusBlocks.venusBasicBlock, 1, 11));
	}

	public static void init() {
		initBlocks();
		setHarvestLevels();
		registerBlocks();
		oreDictRegistration();
	}
}

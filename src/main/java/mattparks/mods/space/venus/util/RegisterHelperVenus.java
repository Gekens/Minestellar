package mattparks.mods.space.venus.util;

import mattparks.mods.space.venus.VenusCore;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class RegisterHelperVenus 
{
	public static void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, VenusCore.MOD_ID_VENUS + "_" + block.getUnlocalizedName().substring(5));
	}

	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, VenusCore.MOD_ID_VENUS + "_" + item.getUnlocalizedName().substring(5));
	}
}

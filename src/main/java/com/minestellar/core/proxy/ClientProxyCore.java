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

package com.minestellar.core.proxy;

import java.util.Objects;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.EnumHelper;

import com.minestellar.api.core.WireSpecialRender;
import com.minestellar.core.blocks.CoreBlocks;
import com.minestellar.core.blocks.tile.TileEntityCable;
import com.minestellar.core.blocks.tile.TileEntityGasSink;
import com.minestellar.core.blocks.tile.TileEntityOxygenCollector;
import com.minestellar.core.blocks.tile.TileEntityPipe;
import com.minestellar.core.blocks.tile.TileEntitySolarGenerator;
import com.minestellar.core.particles.EntityCoreOilDripFX;
import com.minestellar.core.render.item.CableItemRender;
import com.minestellar.core.render.item.GasSinkItemRender;
import com.minestellar.core.render.item.OxygenCollectorItemRender;
import com.minestellar.core.render.item.PipeItemRender;
import com.minestellar.core.render.item.SolarGeneratorItemRender;
import com.minestellar.core.render.tile.TileEntityRenderGasSink;
import com.minestellar.core.render.tile.TileEntityRenderOxygenCollector;
import com.minestellar.core.render.tile.TileEntityRenderSolarPanel;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxyCore extends CommonProxyCore {
	private static Minecraft mc = FMLClientHandler.instance().getClient();
	public static EnumRarity stellarItem = EnumHelper.addRarity("MinestellarRarity", EnumChatFormatting.RED, "MinestellarCore");
	private static int renderIndexCarbonArmor;

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		ClientProxyCore.renderIndexCarbonArmor = RenderingRegistry.addNewArmourRendererPrefix("carbon");

		super.preInit(event);
	}

	@Override
	public int getCarbonArmorRenderIndex() {
		return ClientProxyCore.renderIndexCarbonArmor;
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		this.registerTileEntityRenders();
		this.registerEntityRenderers();
		this.registerItemRenders();

		super.postInit(event);
	}

	private void registerTileEntityRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCable.class, new WireSpecialRender(0, 32, 5, false));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPipe.class, new WireSpecialRender(1, 32, 5, true));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOxygenCollector.class, new TileEntityRenderOxygenCollector());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGasSink.class, new TileEntityRenderGasSink());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySolarGenerator.class, new TileEntityRenderSolarPanel());
	}

	private void registerEntityRenderers() {
	}

	private void registerItemRenders() {
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(CoreBlocks.solarGenerator), new SolarGeneratorItemRender());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(CoreBlocks.gasSink), new GasSinkItemRender());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(CoreBlocks.oxygenCollector), new OxygenCollectorItemRender());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(CoreBlocks.cableBlock), new CableItemRender(0));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(CoreBlocks.pipeBlock), new PipeItemRender(0));
	}

	@Override
	public void spawnParticle(String string, double x, double y, double z) {
		EntityFX entityfx = null;

		if (Objects.equals(string, "oilDrip")) {
			entityfx = new EntityCoreOilDripFX(mc.theWorld, x, y, z, Material.water);
		}

		mc.effectRenderer.addEffect(entityfx);
	}

	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}
}

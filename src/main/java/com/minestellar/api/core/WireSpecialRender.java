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

package com.minestellar.api.core;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import com.minestellar.core.MinestellarCore;

/**
 * Class for wire renders that handles different wire dimension
 */
public class WireSpecialRender extends TileEntitySpecialRenderer {
	private ResourceLocation blockTexture;
	private int type;
	private float pixel = 1F / 16F;
	private float texturePixelX, texturePixelY, location, blockDimension, imageDimensionX, imageDimensionY;
	private boolean drawInside;

	public WireSpecialRender(int type, float imageDimension, float blockDimension, boolean drawInside) {
		this.imageDimensionX = imageDimension;
		this.imageDimensionY = imageDimension;
		this.texturePixelX = 1 / imageDimension;
		this.texturePixelY = 1 / imageDimension;
		this.blockDimension = blockDimension;
		this.location = 16F - blockDimension;
		this.type = type;
		this.drawInside = drawInside;
	}

	public WireSpecialRender(int type, float imageDimensionX, float imageDimensionY, float blockDimension, boolean drawInside) {
		this.imageDimensionX = imageDimensionX;
		this.imageDimensionY = imageDimensionY;
		this.texturePixelX = 1 / imageDimensionX;
		this.texturePixelY = 1 / imageDimensionY;
		this.blockDimension = blockDimension;
		this.location = 16F - blockDimension;
		this.type = type;
		this.drawInside = drawInside;
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double translationX, double translationY, double translationZ, float f) {
		if (type == 0) {
			blockTexture = new ResourceLocation(MinestellarCore.TEXTURE_PREFIX + "textures/model/tile/blockCable" + tileEntity.blockMetadata + ".png");
		} else if (type == 1) {
			blockTexture = new ResourceLocation(MinestellarCore.TEXTURE_PREFIX + "textures/model/tile/blockPipe" + tileEntity.blockMetadata + ".png");
		} else {
			blockTexture = null;
		}

		GL11.glTranslated(translationX, translationY, translationZ);
		GL11.glDisable(GL11.GL_LIGHTING);
		
		if (blockTexture != null) {
			this.bindTexture(blockTexture);
		} else {
			throw new NullPointerException("Null Texture, check the ClientProxy");
		}

		TileEntityWire wire = (TileEntityWire) tileEntity;

		if (!wire.onlyOneOpposite(wire.connections)) {
			drawCore();

			for (int i = 0; i < wire.connections.length; i++) {
				if (wire.connections[i] != null) {
					drawConnector(wire.connections[i]);
				}
			}
		} else {
			for (int i = 0; i < wire.connections.length; i++) {
				if (wire.connections[i] != null) {
					drawStraight(wire.connections[i]);
					break;
				}
			}
		}

		GL11.glTranslated(-translationX, -translationY, -translationZ);
	}

	/**
	 * Draws the connector of a line of at least 3 wires connected to each others
	 */
	public void drawStraight(ForgeDirection direction) {
		Tessellator tessellator = Tessellator.instance;

		tessellator.startDrawingQuads();
		{
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			
			if (direction.equals(ForgeDirection.SOUTH) || direction.equals(ForgeDirection.NORTH)) {
				GL11.glRotatef(90, 1, 0, 0);
			} else if (direction.equals(ForgeDirection.WEST) || direction.equals(ForgeDirection.EAST)) {
				GL11.glRotatef(90, 0, 0, 1);
			}
			
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

			{
				tessellator.addVertexWithUV(1 - location * pixel / 2, 0, 1 - location * pixel / 2, blockDimension * 2 * texturePixelX, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(1 - location * pixel / 2, 1, 1 - location * pixel / 2, (imageDimensionX - blockDimension - 1) * texturePixelX, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(location * pixel / 2, 1, 1 - location * pixel / 2, (imageDimensionX - blockDimension - 1) * texturePixelX, 0);
				tessellator.addVertexWithUV(location * pixel / 2, 0, 1 - location * pixel / 2, blockDimension * 2 * texturePixelX, 0);

				tessellator.addVertexWithUV(location * pixel / 2, 0, location * pixel / 2, blockDimension * 2 * texturePixelX, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(location * pixel / 2, 1, location * pixel / 2, (imageDimensionX - blockDimension - 1) * texturePixelX, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(1 - location * pixel / 2, 1, location * pixel / 2, (imageDimensionX - blockDimension - 1) * texturePixelX, 0);
				tessellator.addVertexWithUV(1 - location * pixel / 2, 0, location * pixel / 2, blockDimension * 2 * texturePixelX, 0);

				tessellator.addVertexWithUV(1 - location * pixel / 2, 0, location * pixel / 2, blockDimension * 2 * texturePixelX, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(1 - location * pixel / 2, 1, location * pixel / 2, (imageDimensionX - blockDimension - 1) * texturePixelX, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(1 - location * pixel / 2, 1, 1 - location * pixel / 2, (imageDimensionX - blockDimension - 1) * texturePixelX, 0);
				tessellator.addVertexWithUV(1 - location * pixel / 2, 0, 1 - location * pixel / 2, blockDimension * 2 * texturePixelX, 0);

				tessellator.addVertexWithUV(location * pixel / 2, 0, 1 - location * pixel / 2, blockDimension * 2 * texturePixelX, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(location * pixel / 2, 1, 1 - location * pixel / 2, (imageDimensionX - blockDimension - 1) * texturePixelX, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(location * pixel / 2, 1, location * pixel / 2, (imageDimensionX - blockDimension - 1) * texturePixelX, 0);
				tessellator.addVertexWithUV(location * pixel / 2, 0, location * pixel / 2, blockDimension * 2 * texturePixelX, 0);

				if (drawInside) {
					tessellator.addVertexWithUV(location * pixel / 2, 0, 1 - location * pixel / 2, blockDimension * 2 * texturePixelX, 0);
					tessellator.addVertexWithUV(location * pixel / 2, 1, 1 - location * pixel / 2, (imageDimensionX - blockDimension - 1) * texturePixelX, 0);
					tessellator.addVertexWithUV(1 - location * pixel / 2, 1, 1 - location * pixel / 2, (imageDimensionX - blockDimension - 1) * texturePixelX, blockDimension * texturePixelY);
					tessellator.addVertexWithUV(1 - location * pixel / 2, 0, 1 - location * pixel / 2, blockDimension * 2 * texturePixelX, blockDimension * texturePixelX);

					tessellator.addVertexWithUV(1 - location * pixel / 2, 0, location * pixel / 2, blockDimension * 2 * texturePixelX, 0);
					tessellator.addVertexWithUV(1 - location * pixel / 2, 1, location * pixel / 2, (imageDimensionX - blockDimension - 1) * texturePixelX, 0);
					tessellator.addVertexWithUV(location * pixel / 2, 1, location * pixel / 2, (imageDimensionX - blockDimension - 1) * texturePixelX, blockDimension * texturePixelY);
					tessellator.addVertexWithUV(location * pixel / 2, 0, location * pixel / 2, blockDimension * 2 * texturePixelX, blockDimension * texturePixelY);

					tessellator.addVertexWithUV(1 - location * pixel / 2, 0, 1 - location * pixel / 2, blockDimension * 2 * texturePixelX, 0);
					tessellator.addVertexWithUV(1 - location * pixel / 2, 1, 1 - location * pixel / 2, (imageDimensionX - blockDimension - 1) * texturePixelX, 0);
					tessellator.addVertexWithUV(1 - location * pixel / 2, 1, location * pixel / 2, (imageDimensionX - blockDimension - 1) * texturePixelX, blockDimension * texturePixelY);
					tessellator.addVertexWithUV(1 - location * pixel / 2, 0, location * pixel / 2, blockDimension * 2 * texturePixelX, blockDimension * texturePixelY);

					tessellator.addVertexWithUV(location * pixel / 2, 0, location * pixel / 2, blockDimension * 2 * texturePixelX, 0);
					tessellator.addVertexWithUV(location * pixel / 2, 1, location * pixel / 2, (imageDimensionX - blockDimension - 1) * texturePixelX, 0);
					tessellator.addVertexWithUV(location * pixel / 2, 1, 1 - location * pixel / 2, (imageDimensionX - blockDimension - 1) * texturePixelX, blockDimension * texturePixelY);
					tessellator.addVertexWithUV(location * pixel / 2, 0, 1 - location * pixel / 2, blockDimension * 2 * texturePixelX, blockDimension * texturePixelY);
				}
			}
			tessellator.draw();

			GL11.glTranslatef(0.5F, 0.5F, 0.5F);

			if (direction.equals(ForgeDirection.SOUTH) || direction.equals(ForgeDirection.NORTH)) {
				GL11.glRotatef(-90, 1, 0, 0);
			} else if (direction.equals(ForgeDirection.WEST) || direction.equals(ForgeDirection.EAST)) {
				GL11.glRotatef(-90, 0, 0, 1);
			}

			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		}
	}

	/**
	 * Draws the connector with other wires adjacent to other wires
	 */
	private void drawConnector(ForgeDirection direction) {
		Tessellator tessellator = Tessellator.instance;

		tessellator.startDrawingQuads();
		{
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			
			if (direction.equals(ForgeDirection.UP)) {
				// ROTATE
			} else if (direction.equals(ForgeDirection.DOWN)) {
				GL11.glRotatef(180, 1, 0, 0);
			} else if (direction.equals(ForgeDirection.SOUTH)) {
				GL11.glRotatef(90, 1, 0, 0);
			} else if (direction.equals(ForgeDirection.NORTH)) {
				GL11.glRotatef(270, 1, 0, 0);
			} else if (direction.equals(ForgeDirection.WEST)) {
				GL11.glRotatef(90, 0, 0, 1);
			} else if (direction.equals(ForgeDirection.EAST)) {
				GL11.glRotatef(270, 0, 0, 1);
			}
			
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

			{
				tessellator.addVertexWithUV(1 - location * pixel / 2, 1 - location * pixel / 2, 1 - location * pixel / 2, blockDimension * texturePixelX, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(1 - location * pixel / 2, 1, 1 - location * pixel / 2, blockDimension * 2 * texturePixelX, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(location * pixel / 2, 1, 1 - location * pixel / 2, blockDimension * 2 * texturePixelX, 0);
				tessellator.addVertexWithUV(location * pixel / 2, 1 - location * pixel / 2, 1 - location * pixel / 2, blockDimension * texturePixelX, 0);

				tessellator.addVertexWithUV(location * pixel / 2, 1 - location * pixel / 2, location * pixel / 2, blockDimension * texturePixelX, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(location * pixel / 2, 1, location * pixel / 2, blockDimension * 2 * texturePixelX, 5 * texturePixelY);
				tessellator.addVertexWithUV(1 - location * pixel / 2, 1, location * pixel / 2, blockDimension * 2 * texturePixelX, 0);
				tessellator.addVertexWithUV(1 - location * pixel / 2, 1 - location * pixel / 2, location * pixel / 2, blockDimension * texturePixelX, 0);

				tessellator.addVertexWithUV(1 - location * pixel / 2, 1 - location * pixel / 2, location * pixel / 2, blockDimension * texturePixelX, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(1 - location * pixel / 2, 1, location * pixel / 2, blockDimension * 2 * texturePixelX, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(1 - location * pixel / 2, 1, 1 - location * pixel / 2, blockDimension * 2 * texturePixelX, 0);
				tessellator.addVertexWithUV(1 - location * pixel / 2, 1 - location * pixel / 2, 1 - location * pixel / 2, blockDimension * texturePixelX, 0);

				tessellator.addVertexWithUV(location * pixel / 2, 1 - location * pixel / 2, 1 - location * pixel / 2, blockDimension * texturePixelX, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(location * pixel / 2, 1, 1 - location * pixel / 2, blockDimension * 2 * texturePixelX, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(location * pixel / 2, 1, location * pixel / 2, blockDimension * 2 * texturePixelX, 0);
				tessellator.addVertexWithUV(location * pixel / 2, 1 - location * pixel / 2, location * pixel / 2, blockDimension * texturePixelX, 0);

				if (drawInside) {
					tessellator.addVertexWithUV(location * pixel / 2, 1 - location * pixel / 2, 1 - location * pixel / 2, blockDimension * texturePixelX, 0);
					tessellator.addVertexWithUV(location * pixel / 2, 1, 1 - location * pixel / 2, blockDimension * 2 * texturePixelX, 0);
					tessellator.addVertexWithUV(1 - location * pixel / 2, 1, 1 - location * pixel / 2, blockDimension * 2 * texturePixelX, blockDimension * texturePixelY);
					tessellator.addVertexWithUV(1 - location * pixel / 2, 1 - location * pixel / 2, 1 - location * pixel / 2, blockDimension * texturePixelX, blockDimension * texturePixelY);

					tessellator.addVertexWithUV(1 - location * pixel / 2, 1 - location * pixel / 2, location * pixel / 2, blockDimension * texturePixelX, 0);
					tessellator.addVertexWithUV(1 - location * pixel / 2, 1, location * pixel / 2, blockDimension * 2 * texturePixelX, 0);
					tessellator.addVertexWithUV(location * pixel / 2, 1, location * pixel / 2, blockDimension * 2 * texturePixelX, blockDimension * texturePixelY);
					tessellator.addVertexWithUV(location * pixel / 2, 1 - location * pixel / 2, location * pixel / 2, blockDimension * texturePixelX, blockDimension * texturePixelY);

					tessellator.addVertexWithUV(1 - location * pixel / 2, 1 - location * pixel / 2, 1 - location * pixel / 2, blockDimension * texturePixelX, 0);
					tessellator.addVertexWithUV(1 - location * pixel / 2, 1, 1 - location * pixel / 2, blockDimension * 2 * texturePixelX, 0);
					tessellator.addVertexWithUV(1 - location * pixel / 2, 1, location * pixel / 2, blockDimension * 2 * texturePixelX, blockDimension * texturePixelY);
					tessellator.addVertexWithUV(1 - location * pixel / 2, 1 - location * pixel / 2, location * pixel / 2, blockDimension * texturePixelX, blockDimension * texturePixelY);

					tessellator.addVertexWithUV(location * pixel / 2, 1 - location * pixel / 2, location * pixel / 2, blockDimension * texturePixelX, 0);
					tessellator.addVertexWithUV(location * pixel / 2, 1, location * pixel / 2, blockDimension * 2 * texturePixelX, 0);
					tessellator.addVertexWithUV(location * pixel / 2, 1, 1 - location * pixel / 2, blockDimension * 2 * texturePixelX, blockDimension * texturePixelY);
					tessellator.addVertexWithUV(location * pixel / 2, 1 - location * pixel / 2, 1 - location * pixel / 2, blockDimension * texturePixelX, blockDimension * texturePixelY);
				}
			}
			
			tessellator.draw();

			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			
			if (direction.equals(ForgeDirection.UP)) {
				// NOPE
			} else if (direction.equals(ForgeDirection.DOWN)) {
				GL11.glRotatef(-180, 1, 0, 0);
			} else if (direction.equals(ForgeDirection.SOUTH)) {
				GL11.glRotatef(-90, 1, 0, 0);
			} else if (direction.equals(ForgeDirection.NORTH)) {
				GL11.glRotatef(-270, 1, 0, 0);
			} else if (direction.equals(ForgeDirection.WEST)) {
				GL11.glRotatef(-90, 0, 0, 1);
			} else if (direction.equals(ForgeDirection.EAST)) {
				GL11.glRotatef(-270, 0, 0, 1);
			}
			
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		}
	}

	/**
	 * Draws the core of the wire
	 */
	private void drawCore() {
		Tessellator tessellator = Tessellator.instance;

		tessellator.startDrawingQuads();
		{
			/* Sides */
			tessellator.addVertexWithUV(1 - location * pixel / 2, location * pixel / 2, 1 - location * pixel / 2, blockDimension * texturePixelX, blockDimension * texturePixelY);
			tessellator.addVertexWithUV(1 - location * pixel / 2, 1 - location * pixel / 2, 1 - location * pixel / 2, blockDimension * texturePixelX, 0);
			tessellator.addVertexWithUV(location * pixel / 2, 1 - location * pixel / 2, 1 - location * pixel / 2, 0, 0);
			tessellator.addVertexWithUV(location * pixel / 2, location * pixel / 2, 1 - location * pixel / 2, 0, blockDimension * texturePixelY);

			tessellator.addVertexWithUV(1 - location * pixel / 2, location * pixel / 2, location * pixel / 2, blockDimension * texturePixelX, blockDimension * texturePixelY);
			tessellator.addVertexWithUV(1 - location * pixel / 2, 1 - location * pixel / 2, location * pixel / 2, blockDimension * texturePixelX, 0);
			tessellator.addVertexWithUV(1 - location * pixel / 2, 1 - location * pixel / 2, 1 - location * pixel / 2, 0, 0);
			tessellator.addVertexWithUV(1 - location * pixel / 2, location * pixel / 2, 1 - location * pixel / 2, 0, blockDimension * texturePixelY);

			tessellator.addVertexWithUV(location * pixel / 2, location * pixel / 2, location * pixel / 2, blockDimension * texturePixelX, 5 * texturePixelY);
			tessellator.addVertexWithUV(location * pixel / 2, 1 - location * pixel / 2, location * pixel / 2, blockDimension * texturePixelX, 0);
			tessellator.addVertexWithUV(1 - location * pixel / 2, 1 - location * pixel / 2, location * pixel / 2, 0, 0);
			tessellator.addVertexWithUV(1 - location * pixel / 2, location * pixel / 2, location * pixel / 2, 0, 5 * texturePixelY);

			tessellator.addVertexWithUV(location * pixel / 2, location * pixel / 2, 1 - location * pixel / 2, blockDimension * texturePixelX, blockDimension * texturePixelY);
			tessellator.addVertexWithUV(location * pixel / 2, 1 - location * pixel / 2, 1 - location * pixel / 2, blockDimension * texturePixelX, 0);
			tessellator.addVertexWithUV(location * pixel / 2, 1 - location * pixel / 2, location * pixel / 2, 0, 0);
			tessellator.addVertexWithUV(location * pixel / 2, location * pixel / 2, location * pixel / 2, 0, blockDimension * texturePixelY);

			/* Top */
			tessellator.addVertexWithUV(1 - location * pixel / 2, 1 - location * pixel / 2, 1 - location * pixel / 2, blockDimension * texturePixelX, blockDimension * texturePixelY);
			tessellator.addVertexWithUV(1 - location * pixel / 2, 1 - location * pixel / 2, location * pixel / 2, blockDimension * texturePixelX, 0);
			tessellator.addVertexWithUV(location * pixel / 2, 1 - location * pixel / 2, location * pixel / 2, 0, 0);
			tessellator.addVertexWithUV(location * pixel / 2, 1 - location * pixel / 2, 1 - location * pixel / 2, 0, blockDimension * texturePixelY);

			/* Bottom */
			tessellator.addVertexWithUV(location * pixel / 2, location * pixel / 2, 1 - location * pixel / 2, blockDimension * texturePixelX, blockDimension * texturePixelY);
			tessellator.addVertexWithUV(location * pixel / 2, location * pixel / 2, location * pixel / 2, blockDimension * texturePixelX, 0);
			tessellator.addVertexWithUV(1 - location * pixel / 2, location * pixel / 2, location * pixel / 2, 0, 0);
			tessellator.addVertexWithUV(1 - location * pixel / 2, location * pixel / 2, 1 - location * pixel / 2, 0, blockDimension * texturePixelY);

			if (drawInside) {
				tessellator.addVertexWithUV(location * pixel / 2, location * pixel / 2, 1 - location * pixel / 2, 0, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(location * pixel / 2, 1 - location * pixel / 2, 1 - location * pixel / 2, 0, 0);
				tessellator.addVertexWithUV(1 - location * pixel / 2, 1 - location * pixel / 2, 1 - location * pixel / 2, blockDimension * texturePixelX, 0);
				tessellator.addVertexWithUV(1 - location * pixel / 2, location * pixel / 2, 1 - location * pixel / 2, blockDimension * texturePixelX, blockDimension * texturePixelY);

				tessellator.addVertexWithUV(1 - location * pixel / 2, location * pixel / 2, 1 - location * pixel / 2, 0, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(1 - location * pixel / 2, 1 - location * pixel / 2, 1 - location * pixel / 2, 0, 0);
				tessellator.addVertexWithUV(1 - location * pixel / 2, 1 - location * pixel / 2, location * pixel / 2, blockDimension * texturePixelX, 0);
				tessellator.addVertexWithUV(1 - location * pixel / 2, location * pixel / 2, location * pixel / 2, blockDimension * texturePixelX, blockDimension * texturePixelY);

				tessellator.addVertexWithUV(1 - location * pixel / 2, location * pixel / 2, location * pixel / 2, 0, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(1 - location * pixel / 2, 1 - location * pixel / 2, location * pixel / 2, 0, 0);
				tessellator.addVertexWithUV(location * pixel / 2, 1 - location * pixel / 2, location * pixel / 2, blockDimension * texturePixelX, 0);
				tessellator.addVertexWithUV(location * pixel / 2, location * pixel / 2, location * pixel / 2, blockDimension * texturePixelX, blockDimension * texturePixelY);

				tessellator.addVertexWithUV(location * pixel / 2, location * pixel / 2, location * pixel / 2, 0, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(location * pixel / 2, 1 - location * pixel / 2, location * pixel / 2, 0, 0);
				tessellator.addVertexWithUV(location * pixel / 2, 1 - location * pixel / 2, 1 - location * pixel / 2, blockDimension * texturePixelX, 0);
				tessellator.addVertexWithUV(location * pixel / 2, location * pixel / 2, 1 - location * pixel / 2, blockDimension * texturePixelX, blockDimension * texturePixelY);

				tessellator.addVertexWithUV(location * pixel / 2, 1 - location * pixel / 2, 1 - location * pixel / 2, 0, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(location * pixel / 2, 1 - location * pixel / 2, location * pixel / 2, 0, 0);
				tessellator.addVertexWithUV(1 - location * pixel / 2, 1 - location * pixel / 2, location * pixel / 2, blockDimension * texturePixelX, 0);
				tessellator.addVertexWithUV(1 - location * pixel / 2, 1 - location * pixel / 2, 1 - location * pixel / 2, blockDimension * texturePixelX, blockDimension * texturePixelY);

				tessellator.addVertexWithUV(1 - location * pixel / 2, location * pixel / 2, 1 - location * pixel / 2, 0, blockDimension * texturePixelY);
				tessellator.addVertexWithUV(1 - location * pixel / 2, location * pixel / 2, location * pixel / 2, 0, 0);
				tessellator.addVertexWithUV(location * pixel / 2, location * pixel / 2, location * pixel / 2, blockDimension * texturePixelX, 0);
				tessellator.addVertexWithUV(location * pixel / 2, location * pixel / 2, 1 - location * pixel / 2, blockDimension * texturePixelX, blockDimension * texturePixelY);
			}
		}
		
		tessellator.draw();
	}
}

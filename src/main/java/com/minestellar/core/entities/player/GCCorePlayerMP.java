package com.minestellar.core.entities.player;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.minestellar.moon.blocks.MoonBlocks;
import com.minestellar.moon.world.WorldProviderMoon;
import com.mojang.authlib.GameProfile;

public class GCCorePlayerMP extends EntityPlayerMP {
	private static GameProfile field_146106_i;
	private int lastStep;

	public GCCorePlayerMP(MinecraftServer server, World world, String username, ItemInWorldManager itemInWorldManager) {
		super(server, null, field_146106_i, itemInWorldManager);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		this.updateStep();
	}

	private void updateStep() {
		if (this.worldObj != null && this.worldObj.provider instanceof WorldProviderMoon && !this.isAirBorne && this.ridingEntity == null) {
			if (this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ)) == MoonBlocks.moonBasicBlocks) {
				if (this.worldObj.getBlockMetadata(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ)) == 5) {
					int meta = -1;

					final int i = 1 + MathHelper.floor_double(this.rotationYaw * 8.0F / 360.0F + 0.5D) & 7;

					switch (this.lastStep) {
					case 1:
						switch (i) {
						case 0:
							meta = 2;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 1:
							meta = 4;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 2:
							meta = 2;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 3:
							meta = 2;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 4:
							meta = 2;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 5:
							meta = 2;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 6:
							meta = 2;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 7:
							meta = 2;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						}
						this.lastStep = 2;
						break;
					case 2:
						switch (i) {
						case 0:
							meta = 1;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 1:
							meta = 1;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 2:
							meta = 4;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 3:
							meta = 4;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 4:
							meta = 1;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 5:
							meta = 3;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 6:
							meta = 2;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						case 7:
							meta = 4;
							this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
							break;
						}

						this.lastStep = 1;
						this.worldObj.setBlockMetadataWithNotify(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 1), MathHelper.floor_double(this.posZ), meta + 5, 3);
						break;
					default:
						this.lastStep = 1;
						break;
					}
				}
			}
		}
	}
}

/**
 * Copyright (c) 31/dic/2014 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.core.util.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.minecraft.util.ChatComponentText;

import com.minestellar.core.Constants;
import com.minestellar.core.util.EnumColor;
import com.minestellar.core.util.MinestellarLog;
import com.minestellar.core.util.MinestellarUtil;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class ThreadVersionCheck extends Thread
{
	public static ThreadVersionCheck INSTANCE = new ThreadVersionCheck();
	private int count = 0;

	public static int remoteMajVer;
	public static int remoteMinVer;
	public static int remoteBuildVer;

	public ThreadVersionCheck()
	{
		super("Minestellar Version Check Thread");
	}

	public static void startCheck()
	{
		final Thread thread = new Thread(ThreadVersionCheck.INSTANCE);
		thread.start();
	}

	@Override
	public void run()
	{
		final Side sideToCheck = FMLCommonHandler.instance().getSide();

		if (sideToCheck == null)
		{
			return;
		}

		while (this.count < 3 && remoteBuildVer == 0)
		{
			try
			{
				final URL url = new URL("http://minestellar.hostei.com/version.html");

				final HttpURLConnection http = (HttpURLConnection) url.openConnection();
				http.addRequestProperty("User-Agent", "Mozilla/4.76");

				final BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
				String str;
				String str2[] = null;

				while ((str = in.readLine()) != null)
				{

					if (str.contains("Version"))
					{
						str = str.replace("Version=", "");

						str2 = str.split("#");

						if (str2.length == 3)
						{
							remoteMajVer = Integer.parseInt(str2[0]);
							remoteMinVer = Integer.parseInt(str2[1]);
							remoteBuildVer = Integer.parseInt(str2[2]);
						}

						if (remoteMajVer > Constants.LOCALMAJVERSION || remoteMajVer == Constants.LOCALMAJVERSION && remoteMinVer > Constants.LOCALMINVERSION || remoteMajVer == Constants.LOCALMAJVERSION && remoteMinVer == Constants.LOCALMINVERSION && remoteBuildVer > Constants.LOCALBUILDVERSION)
						{
							Thread.sleep(5000);

							FMLClientHandler.instance().getClient().thePlayer.addChatMessage(new ChatComponentText(EnumColor.GREY + "New " + EnumColor.DARK_AQUA + "Minestellar" + EnumColor.GREY + " version available! v" + String.valueOf(remoteMajVer) + "." + String.valueOf(remoteMinVer) + "." + String.valueOf(remoteBuildVer) + EnumColor.DARK_BLUE + " http://minestellar.hostei.com/"));
							MinestellarLog.severe("New Minestellar version available! v" + String.valueOf(remoteMajVer) + "." + String.valueOf(remoteMinVer) + "." + String.valueOf(remoteBuildVer) + " http://minestellar.hostei.com/");
						}
					}
				}
			}

			catch (final Exception e)
			{
			}

			if (remoteBuildVer == 0)
			{
				try
				{
					MinestellarLog.severe(MinestellarUtil.translate("update.failed.name"));

					Thread.sleep(30000);
				}

				catch (final InterruptedException e)
				{
				}
			}

			else
			{
				MinestellarLog.info(MinestellarUtil.translate("update.success.name") + " " + remoteMajVer + "." + remoteMinVer + "." + remoteBuildVer);
			}

			this.count++;
		}
	}
}

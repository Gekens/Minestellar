package com.minestellar.core.util;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.relauncher.FMLRelaunchLog;

public class MinestellarLog
{
    public static void info(String message)
    {
        FMLRelaunchLog.log("Minestellar", Level.INFO, message);
    }

    public static void severe(String message)
    {
        FMLRelaunchLog.log("Minestellar", Level.ERROR, message);
    }
}

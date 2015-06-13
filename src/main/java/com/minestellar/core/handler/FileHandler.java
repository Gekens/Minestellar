/**
 * Copyright (c) 11/apr/2015 Davide Cossu & Matthew Albrecht.
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

package com.minestellar.core.handler;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;

import java.io.*;

/**
 * Custom file handler for writing and reading files
 */

public class FileHandler {

    /**
     * Writes the given text in the given file.
     * <p>
     * <b>Note:</b> a new folder will be created in the <i>mods</i> folder. The file will be put there.
     * </p>
     *
     * @param fileName The name of the file that should be written
     * @param text The text that should be written
     * @param inMods If the file should be created in the mods folder. If false, it'll be created inside the world save
     */

    public static void writeToFile(String fileName, String text, boolean inMods){
        FileWriter fileWriter;

        try {
            File myFolder;
            if(inMods){
                File modsFolder = new File(new File(Minecraft.getMinecraft().mcDataDir.getAbsolutePath().replace(File.separatorChar, '/').replace("/.", "/")), "mods");
                myFolder = new File(modsFolder, "MinestellarCore");
            }else{
                File savesFolder = FMLClientHandler.instance().getSavesDir();
                File worldSave = new File(savesFolder, FMLCommonHandler.instance().getMinecraftServerInstance().getWorldName());
                myFolder = new File(worldSave, "MinestellarCore");
            }

            myFolder.mkdir();

            File myFile = new File(myFolder, fileName);

            if(!myFile.exists())
                myFile.createNewFile();

            fileWriter = new FileWriter(myFile.getAbsolutePath());

            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(text);
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads all the lines on the given file name.
     *
     * @param fileName The name of the file
     * @param inMods If the file is to be searched in the mods folder, otherwise it's in the world's save
     */

    public static String readFromFile(String fileName, boolean inMods){
        String line, text = "";
        FileReader fileReader;

        try {
            File myFolder;

            if(inMods){
                File modsFolder = new File(new File(Minecraft.getMinecraft().mcDataDir.getAbsolutePath().replace(File.separatorChar, '/').replace("/.", "/")), "mods");
                myFolder = new File(modsFolder, "MinestellarCore");
            }else{
                File savesFolder = FMLClientHandler.instance().getSavesDir();
                File worldSave = new File(savesFolder, FMLCommonHandler.instance().getMinecraftServerInstance().getWorldName());
                myFolder = new File(worldSave, "MinestellarCore");
            }

            myFolder.mkdir();
            File myFile = new File(myFolder, fileName);

            if(!myFile.exists())
                myFile.createNewFile();

            fileReader = new FileReader(myFile.getAbsolutePath());

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                text += line;
            }

            bufferedReader.close();

            return text;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

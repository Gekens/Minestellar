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

import net.minecraft.client.Minecraft;

import java.io.*;

/**
 * Custom file handler for writing and reading files
 */

public class FileHandler{

	/**
	 * Writes the given text in the given file. 
	 * <p><b>Note:</b> a new folder will be created in the <i>mods</i> folder. The file will be put there.</p>
	 * 
	 * @param fileName The name of the file that should be written
	 * @param text The text that should be written
	 */
	
	public static void writeToFile(String fileName, String text){

		FileWriter fileWriter;

		try{

			File modsFolder = new File(new File(Minecraft.getMinecraft().mcDataDir.getAbsolutePath().replace(File.separatorChar, '/').replace("/.", "/")), "mods");
			File myFolder = new File(modsFolder, "minestellarCore");
			myFolder.mkdir();
			File myFile = new File(myFolder, fileName);
			
			fileWriter = new FileWriter(myFile.getAbsolutePath());
			
			BufferedWriter writer = new BufferedWriter(fileWriter);

			System.out.println("String: " + text);
			
			writer.write(text);
			writer.newLine();
			writer.close();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Reads all the lines on the given file name. The file will be checked in the custom folder.
	 * 
	 * @param fileName The name of the file
	 * @see FileHandler#writeToFile(String, String)
	 */
	
	public static String readFromFile(String fileName){
		String line, text = "";
		
		FileReader fileReader;
		
		try{
			
			File modsFolder = new File(new File(Minecraft.getMinecraft().mcDataDir.getAbsolutePath().replace(File.separatorChar, '/').replace("/.", "/")), "mods");
			File myFolder = new File(modsFolder, "minestellarCore");
			myFolder.mkdir();
			File myFile = new File(myFolder, fileName);
			
			fileReader = new FileReader(myFile.getAbsolutePath());

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null){
				text += line;
			}
			
			System.out.println("Text: " + text);
			
			bufferedReader.close();
			
			return text;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
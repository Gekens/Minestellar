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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cpw.mods.fml.relauncher.FMLInjectionData;

public class FileHandler{

	/**
	 * Writes the given text in the given file. The file will be created in the <code>mods<code> folder
	 * 
	 * @param fileName The name of the file that should be written
	 * @param text The text that should be written
	 * @throws IOException If the folder does not exist 
	 */
	
	public static void writeToFile(String fileName, String text){

		FileWriter fileWriter;

		try{

			String basePath = ((File)(FMLInjectionData.data()[6])).getAbsolutePath().replace(File.separatorChar, '/').replace("/.", ""); //This is the minecraft folder
			basePath = basePath + "/mods/";
			String file = basePath + fileName.replace(File.separatorChar, '/').replace("/./", "/");
			
			System.out.println("basePath: " + basePath);
			System.out.println("file: " + file);
			
			fileWriter = new FileWriter(file);
			
			BufferedWriter writer = new BufferedWriter(fileWriter);

			writer.write(text);
			writer.newLine();
			writer.close();

		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public static String readFromFile(String fileName){
		String line, text = "";
		
		FileReader fileReader;
		try {
			
			String basePath = ((File)(FMLInjectionData.data()[6])).getAbsolutePath().replace(File.separatorChar, '/').replace("/.", ""); //This is the minecraft folder
			basePath = basePath + "/mods/";
			String file = basePath + fileName.replace(File.separatorChar, '/').replace("/./", "/");

			System.out.println("basePath: " + basePath);
			System.out.println("file: " + file);
			
			fileReader = new FileReader(file);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null) {
				text += line;
			}
			
			System.out.println("Text: " + text);
			
			bufferedReader.close();
			
			return text;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
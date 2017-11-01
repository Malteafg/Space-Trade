package com.simaflux.spacetrade.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public abstract class FileHandler {
	
	/*
	 * WRITING
	 */
	public static void write(File file, String[] array) {
		try {
			FileWriter writer = new FileWriter(file);
			for(int i = 0; i < array.length; i++) {
				if(i == 0) {
					writer.write(array[i]);
				} else {
					writer.write("\n" + array[i]);
				}
			}
			writer.close();
		} catch(IOException e) {
			System.err.println("Failed to write to file: " + file.getPath());
		}
	}
	
	public static void writeObjectToFile(Object object, String path) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/savegame/" + path + "/" + path + ".dat"));
			out.writeObject(object);
			out.flush();
			out.close();
		} catch(IOException e) {
			System.err.println("out" + "data/savegame/" + path + "/" + path + ".dat");
			e.printStackTrace();
		}
	}
	
	/*
	 * READING
	 */
	public static String[] read(File file) {
		String[] result = null;
		try {
			Scanner scanner = new Scanner(file);
			
			// get number of lines
			int lines = getLines(file);
			
			// read text file
			result = new String[lines];
			for(int i = 0; i < lines; i++) {
				result[i] = scanner.nextLine();
			}
			scanner.close();
		} catch(IOException e) {
			System.out.println("Couldn't find: " + file.getPath());
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static int getLines(File file) {
		int lines = 0;
		try {
			LineNumberReader lnr = new LineNumberReader(new FileReader(file));
			lnr.skip(Long.MAX_VALUE);
			lines = lnr.getLineNumber() + 1;
			lnr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lines;
	}
	
	public static Object readObjectFromFile(String path) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/savegame/" + path + "/" + path + ".dat"));
			Object b = in.readObject();
			in.close();
			return b;
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("in" + "data/savegame/" + path + "/" + path + ".dat");
			e.printStackTrace();
			return null;
		}
	}

}

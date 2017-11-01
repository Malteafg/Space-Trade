package com.simaflux.spacetrade.loader;

import java.io.File;
import java.util.ArrayList;

import com.simaflux.spacetrade.objects.resources.MarketResource;
import com.simaflux.spacetrade.objects.resources.PlayerResource;
import com.simaflux.spacetrade.utils.ArrayUtils;
import com.simaflux.spacetrade.utils.FileHandler;

public class GameLoader {
	
	/*
	 * NAME LOADING
	 */
	public static ArrayList<String> planetNames;
	public static ArrayList<String> starNames;
	public static ArrayList<String> empireNames;
	
	public static void loadNames() {
		planetNames = ArrayUtils.convertToArrayList(FileHandler.read(new File("data/names/planets.txt")));
		starNames = ArrayUtils.convertToArrayList(FileHandler.read(new File("data/names/stars.txt")));
		empireNames = ArrayUtils.convertToArrayList(FileHandler.read(new File("data/names/empires.txt")));
	}
	
	public static String getName(ArrayList<String> s) {
		String r = "";
		
		int p = (int) (Math.random() * s.size());
		r = s.get(p);
		s.remove(p);
		
		return r;
	}
	
	/*
	 * RESOURCE LOADING
	 */
	public static PlayerResource[] playerResources;
	public static MarketResource[] marketResources;
	
	public static void loadResources() {
		final int parameters = 3;
		
		File file = new File("data/resources/resources.txt");
		String[] data = FileHandler.read(file);
		playerResources = new PlayerResource[FileHandler.getLines(file) / parameters];
		marketResources = new MarketResource[FileHandler.getLines(file) / parameters];
		
		int l = 0;
		while(l < data.length) {
			playerResources[l / parameters] = new PlayerResource(data[l]);
			marketResources[l / parameters] = new MarketResource(data[l], Double.parseDouble(data[l + 1]), Integer.parseInt(data[l + 2]));
			
			l += parameters;
		}
	}
	
	public static String[] getResourceNames() {
		String[] s = new String[playerResources.length];
		for(int i = 0; i < playerResources.length; i++) {
			s[i] = playerResources[i].getName();
		}
		
		return s;
	}

}

package com.simaflux.spacetrade.loader;

import static com.simaflux.spacetrade.utils.Vars.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.simaflux.spacetrade.objects.buildings.BuildingTemplate;
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
	
	/*
	 * BUILDING LOADING
	 */	
	public static final String[] buildingNames = new String[] {
		IRON_MINE, CARBON_EXTRACTOR, ALUMINUM_MINE,
		STEEL_FACTORY,
		SOLAR_POWER_PLANT
	};
	
	public static Map<String, BuildingTemplate> buildings = new HashMap<>();
	
	public static void loadBuildings() {
		buildings.put(IRON_MINE, new BuildingTemplate(
				new String[] {STEEL, ALUMINUM}, new String[] {IRON}, new String[] {}, 
				new int[] {10, 5}, new int[] {3}, new int[] {},
				-2));

		buildings.put(CARBON_EXTRACTOR, new BuildingTemplate(
				new String[] {STEEL}, new String[] {CARBON}, new String[] {}, 
				new int[] {10}, new int[] {3}, new int[] {},
				-3));

		buildings.put(ALUMINUM_MINE, new BuildingTemplate(
				new String[] {STEEL, ALUMINUM}, new String[] {ALUMINUM}, new String[] {}, 
				new int[] {10, 5}, new int[] {3}, new int[] {},
				-2));

		buildings.put(STEEL_FACTORY, new BuildingTemplate(
				new String[] {STEEL, ALUMINUM, IRON}, new String[] {STEEL}, new String[] {IRON, CARBON}, 
				new int[] {15, 10, 6}, new int[] {2}, new int[] {4, 3},
				-5));
		
		buildings.put(SOLAR_POWER_PLANT, new BuildingTemplate(
				new String[] {STEEL, ALUMINUM, ELECTRONICS}, new String[] {}, new String[] {}, 
				new int[] {12, 12, 3}, new int[] {}, new int[] {}, 
				15));
	}
	
	public static BuildingTemplate getBuildingInfo(String name) {
		return buildings.get(name);
	}

}

package com.simaflux.spacetrade.loader;

import static com.simaflux.spacetrade.utils.Vars.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.simaflux.spacetrade.objects.buildings.BuildingTemplate;
import com.simaflux.spacetrade.objects.resources.MarketResource;
import com.simaflux.spacetrade.objects.resources.PlayerResource;
import com.simaflux.spacetrade.utils.FileHandler;

public class GameLoader {
	
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
		IRON_MINE, CARBON_EXTRACTOR, ALUMINUM_MINE, OIL_PUMP, GOLD_MINE,
		STEEL_FACTORY, OIL_REFINERY, ELECTRONICS_FACTORY, COMPUTER_FACTORY, SPACESHIP_FACTORY, FUEL_FACTORY,
		SOLAR_POWER_PLANT, OIL_POWER_PLANT, NUCLEAR_POWER_PLANT
	};
	
	public static Map<String, BuildingTemplate> buildings = new HashMap<>();
	
	public static void loadBuildings() {
		/*
		 * EXTRACTORS
		 */
		buildings.put(IRON_MINE, new BuildingTemplate(
				new String[] {STEEL, ALUMINUM}, new String[] {IRON}, new String[] {}, 
				new int[] {10, 5}, new int[] {3}, new int[] {},
				-2, 
				IRON));

		buildings.put(CARBON_EXTRACTOR, new BuildingTemplate(
				new String[] {STEEL}, new String[] {CARBON}, new String[] {}, 
				new int[] {10}, new int[] {3}, new int[] {},
				-3, 
				CARBON));

		buildings.put(ALUMINUM_MINE, new BuildingTemplate(
				new String[] {STEEL, ALUMINUM}, new String[] {ALUMINUM}, new String[] {}, 
				new int[] {10, 5}, new int[] {3}, new int[] {},
				-2,
				ALUMINUM));

		buildings.put(OIL_PUMP, new BuildingTemplate(
				new String[] {STEEL, ALUMINUM, PLASTIC}, new String[] {OIL}, new String[] {}, 
				new int[] {6, 5, 8}, new int[] {3}, new int[] {},
				-2,
				OIL));

		buildings.put(GOLD_MINE, new BuildingTemplate(
				new String[] {STEEL, ALUMINUM}, new String[] {GOLD}, new String[] {}, 
				new int[] {10, 15}, new int[] {2}, new int[] {},
				-3,
				GOLD));
		
		/*
		 * FACTORIES
		 */
		buildings.put(STEEL_FACTORY, new BuildingTemplate(
				new String[] {STEEL, ALUMINUM, IRON}, new String[] {STEEL}, new String[] {IRON, CARBON}, 
				new int[] {20, 15, 12}, new int[] {3}, new int[] {4, 2},
				-6,
				null));

		buildings.put(OIL_REFINERY, new BuildingTemplate(
				new String[] {STEEL, ALUMINUM, PLASTIC}, new String[] {PLASTIC}, new String[] {OIL}, 
				new int[] {15, 12, 20}, new int[] {2}, new int[] {4},
				-5,
				null));

		buildings.put(ELECTRONICS_FACTORY, new BuildingTemplate(
				new String[] {STEEL, ALUMINUM, IRON, COMPUTER}, new String[] {ELECTRONICS}, new String[] {PLASTIC, CARBON, GOLD}, 
				new int[] {12, 20, 6, 10}, new int[] {3}, new int[] {3, 3, 1},
				-8,
				null));

		buildings.put(COMPUTER_FACTORY, new BuildingTemplate(
				new String[] {STEEL, ALUMINUM, COMPUTER}, new String[] {COMPUTER}, new String[] {ELECTRONICS, PLASTIC}, 
				new int[] {18, 13, 15}, new int[] {2}, new int[] {3, 5},
				-8,
				null));

		buildings.put(SPACESHIP_FACTORY, new BuildingTemplate(
				new String[] {STEEL, ALUMINUM, PLASTIC, COMPUTER}, new String[] {SPACESHIP}, new String[] {ALUMINUM, COMPUTER, CARBON, ELECTRONICS, FUEL}, 
				new int[] {15, 10, 8, 15}, new int[] {1}, new int[] {3, 1, 2, 2, 2},
				-12,
				null));

		buildings.put(FUEL_FACTORY, new BuildingTemplate(
				new String[] {STEEL, ALUMINUM, PLASTIC}, new String[] {FUEL}, new String[] {OIL}, 
				new int[] {15, 10, 6}, new int[] {2}, new int[] {4},
				-4,
				null));
		
		/*
		 * POWER PLANTS
		 */
		buildings.put(SOLAR_POWER_PLANT, new BuildingTemplate(
				new String[] {STEEL, ALUMINUM, ELECTRONICS}, new String[] {}, new String[] {}, 
				new int[] {12, 12, 3}, new int[] {}, new int[] {}, 
				15,
				null));
		buildings.put(OIL_POWER_PLANT, new BuildingTemplate(
				new String[] {STEEL, ALUMINUM}, new String[] {}, new String[] {OIL}, 
				new int[] {12, 10}, new int[] {}, new int[] {2}, 
				20,
				OIL));
		buildings.put(NUCLEAR_POWER_PLANT, new BuildingTemplate(
				new String[] {STEEL, ALUMINUM, ELECTRONICS, IRON, COMPUTER}, new String[] {}, new String[] {}, 
				new int[] {30, 20, 5, 23, 2}, new int[] {}, new int[] {}, 
				40,
				null));
	}
	
	public static BuildingTemplate getBuildingInfo(String name) {
		return buildings.get(name);
	}

}

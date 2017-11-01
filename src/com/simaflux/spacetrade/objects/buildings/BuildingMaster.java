package com.simaflux.spacetrade.objects.buildings;

import java.util.HashMap;
import java.util.Map;

import com.simaflux.spacetrade.objects.resources.StaticResource;
import com.simaflux.spacetrade.objects.space.Planet;
import com.simaflux.spacetrade.players.Player;

public abstract class BuildingMaster {
	
	public final static Map<String, BuildingTemplate> templates = new HashMap<>();
	
	public static void initBuildings() {
		templates.put("Iron Mine", new BuildingTemplate(new StaticResource[] {
				new StaticResource("Steel", 5)
				}));
		
		templates.put("Carbon Extractor", new BuildingTemplate(new StaticResource[] {
				new StaticResource("Steel", 5),
				new StaticResource("Oil", 1)
				}));
		
		templates.put("Oil Pump", new BuildingTemplate(new StaticResource[] {
				new StaticResource("Steel", 5),
				new StaticResource("Plastic", 2),
				new StaticResource("Iron", 1)
				}));
		
		templates.put("Gold Mine", new BuildingTemplate(new StaticResource[] {
				new StaticResource("Steel", 5)
				}));
		
		templates.put("Aluminum Mine", new BuildingTemplate(new StaticResource[] {
				new StaticResource("Steel", 5)
				}));
		
		
		
		
		
	}
	
	public static Building createBuilding(String tag, Planet planet, Player owner) {
		Building b = new Building(tag, planet, owner);
		
		for(BuildingComponent c : templates.get(tag).getComponents()) {
			b.addComponent(c.copy());
		}
		
		return b;
	}

}

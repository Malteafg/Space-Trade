package com.simaflux.spacetrade.objects.buildings;

import java.util.HashMap;
import java.util.Map;

import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.objects.resources.StaticResource;

public class BuildingMarket {
	
	private final Map<String, Building> buildings = new HashMap<>();
	private final Map<String, StaticResource[]> cost = new HashMap<>();
	
	public BuildingMarket() {
		loadBuildings();
	}
	
	private void loadBuildings() {
		buildings.put("Iron Mine", new IronMine(null));
		cost.put("Iron Mine", new StaticResource[] {
			new StaticResource("Steel", 10),
			new StaticResource("Aluminum", 5)
		});
	}
	
	public Building getBuildingInfo(String name) {
		return buildings.get(name);
	}
	
	public StaticResource[] getBuildingInfoCost(String name) {
		return cost.get(name);
	}
	
	public float getCost(String name) {
		float p = 0;
		for(StaticResource r : cost.get(name)) {
			p += r.getAmount() * GameHandler.game.market.getPrice(r.getName());
		}
		return p;
	}

}

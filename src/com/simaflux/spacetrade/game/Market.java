package com.simaflux.spacetrade.game;

import java.io.Serializable;
import java.util.ArrayList;

import com.simaflux.spacetrade.loader.GameLoader;
import com.simaflux.spacetrade.objects.buildings.BuildingTemplate;
import com.simaflux.spacetrade.objects.resources.MarketResource;

public class Market implements Serializable {
	
	private static final long serialVersionUID = 7871937297703658451L;
	
	private MarketResource[] resources;
	
	public Market() {
		resources = GameLoader.marketResources;
	}
	
	public void tick() {
		if(resources == null) System.out.println("r");
		for(MarketResource r : resources) {
			r.tick();
		}
	}
	
	public double getPrice(String tag) {
		return getResource(tag).getPrice();
	}
	
	public ArrayList<Double> getAllPrices(String tag) {
		return getResource(tag).getAllPrices();
	}
	
	public double getGrowthFactor(String tag) {
        return getResource(tag).getGrowthFactor();
    }
	
	public void addQuantity(String tag, int a) {
		for(MarketResource r : resources) {
			if(r.getName().equals(tag)) {
				r.add(a);
			}
		}
	}
	
	public MarketResource getResource(String tag) {
		for(MarketResource r : resources) {
			if(r.getName().equals(tag)) {
				return r;
			}
		}
		
		System.err.println("Resource not found: " + tag);
		
		return null;
	}

	public MarketResource[] getAllResources() {
		return resources;
	}
	
	public double getBuildingCost(String name) {
		double p = 0;
		BuildingTemplate t = GameLoader.getBuildingInfo(name);
		for(int i = 0; i < t.getNcost().length; i++) {
			p += t.getVcost()[i] * getPrice(t.getNcost()[i]);
		}
		return p;
	}

}

package com.simaflux.spacetrade.objects.buildings;

import java.util.ArrayList;
import java.util.List;

import com.simaflux.spacetrade.objects.resources.StaticResource;

public class BuildingTemplate {
	
	private final StaticResource[] cost;
	private final List<BuildingComponent> components;
	
	public BuildingTemplate(StaticResource[] cost) {
		this.cost = cost;
		
		components = new ArrayList<>();
	}
	
	public void addComponent(BuildingComponent c) {
		components.add(c);
	}
	
	public List<BuildingComponent> getComponents() {
		return components;
	}
	
	public StaticResource[] getCost() {
		return cost;
	}
	
}

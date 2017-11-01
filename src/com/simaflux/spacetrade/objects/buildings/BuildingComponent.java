package com.simaflux.spacetrade.objects.buildings;

public abstract class BuildingComponent {
	
	protected Building building;
	
	public BuildingComponent(Building building) {
		this.building = building;
	}
	
	public abstract void tick();
	public abstract BuildingComponent copy();

}

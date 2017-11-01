package com.simaflux.spacetrade.objects.buildings;

import java.util.List;

import com.simaflux.spacetrade.objects.space.Planet;
import com.simaflux.spacetrade.players.Player;

public class Building {
	
	private String name;
	private List<BuildingComponent> components;
	
	private Planet planet;
	private Player owner;
	
	public Building(String name, Planet planet, Player owner) {
		this.name = name;
		this.planet = planet;
		this.owner = owner;
	}
	
	public void tick() {
		for(BuildingComponent c : components) {
			c.tick();
		}
	}
	
	public void addComponent(BuildingComponent c) {
		components.add(c);
	}
	
	public String getName() {
		return name;
	}
	
	public Planet getPlanet() {
		return planet;
	}
	
	public Player getOwner() {
		return owner;
	}

}

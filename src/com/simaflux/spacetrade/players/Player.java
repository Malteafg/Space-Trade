package com.simaflux.spacetrade.players;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.objects.buildings.Building;
import com.simaflux.spacetrade.objects.resources.PlayerResource;
import com.simaflux.spacetrade.objects.resources.StaticResource;
import com.simaflux.spacetrade.objects.space.Planet;
import com.simaflux.spacetrade.players.utils.PowerManager;
import com.simaflux.spacetrade.players.utils.PowerManager.PlanetPower;
import com.simaflux.spacetrade.players.utils.ResourceManager;
import com.simaflux.spacetrade.utils.Maths;
import com.simaflux.spacetrade.utils.math.Vector3f;

public abstract class Player implements Serializable {
	
	private static final long serialVersionUID = -2976983028306012874L;
	
	protected ResourceManager rm;
	public PowerManager pm;
	
	protected double cash;
	
	protected String name;
	protected Vector3f color;

	private List<Building> buildings;
	
	public Player(String name) {
		color = new Vector3f(Maths.random(), Maths.random(), Maths.random());
		
		rm = new ResourceManager(this);
		pm = new PowerManager(this);
		
		buildings = new ArrayList<>();
		
		cash = 100000;
	}
	
	public void tick() {
		rm.tick();
		pm.tick();
		
		for(PlanetPower p : pm.getPlanets()) {
			Planet planet = p.getPlanet();
			
			if(p.getState() != 0 || p.hasExcessivePower()) {
				for(Building b : planet.getBuildings(this)) {
					b.setEnoughPower(true);
				}
			} else {
				int power = p.getPowerProduced();
				for(Building b : planet.getBuildings(this)) {
					if(b.getPower() < 0) {
						if(-b.getPower() > power) {
							b.setEnoughPower(false);
						} else {
							b.setEnoughPower(true);
							if(b.isOpen()) power -= -b.getPower();
						}
					} else {
						b.setEnoughPower(true);
					}
				}
			}
			
			for(Building b : buildings) {
				if(b.hasEnoughPower() && b.isOpen()) {
					boolean u = true;
					for(StaticResource r : b.getConsumed()) {
						if(r.getAmount() > rm.getQuantity(r.getName())) u = false;
					}
					
					if(u) {
						b.setEnoughResources(true);
						b.tick();
					} else {
						/*
						 * TODO
						 * Building lower level functionality
						 */
						b.setEnoughResources(false);
					}
				}
			}
		}
	}
	
	/*
	 * Actions
	 */
	public final void setResourceState(String tag, int s, int a) {
		rm.getResource(tag).setResourceState(s, a);
	}

	public final void setPowerState(Planet currPlanet, int state) {
		pm.setPowerState(currPlanet, state);
	}

	public void buyBuilding(String b, Planet planet) {
		double price = GameHandler.game.market.getBuildingCost(b);
		
		if(cash < price) return;
		
		addCash(-price);
		buildings.add(new Building(this, planet, b));
		planet.addBuilding(this, buildings.get(buildings.size() - 1));
		if(pm.getPlanetPower(planet) == null) pm.addPlanet(planet);
		calculatePower(planet);
	}
	
	public void claimLand(int a) {
		GameHandler.game.getSelectedPlanet().cm.claimLand(this, a);
	}
	
	/*
	 * Getters and setters
	 */	
	public final void addQuantity(String tag, int a) {
		rm.addQuantity(tag, a);
	}
	
	public final int getQuantity(String tag) {
		return rm.getQuantity(tag);
	}
	
	public final PlayerResource getResource(String tag) {
		return rm.getResource(tag);
	}
	
	public final double getCash() {
		return cash;
	}
	
	public final void addCash(double d) {
		cash += d;
	}

	public final String getName() {
		return name;
	}
	
	public final Vector3f getColor() {
		return color;
	}

	public final void calculatePower(Planet planet) {
		pm.calculatePower(planet);
	}

	public final int getPowerConsumptionOfPlanet(Planet planet) {
		return pm.getPowerConsumptionOfPlanet(planet);
	}
	
	public final int getPowerProductionOfPlanet(Planet planet) {
		return pm.getPowerProductionOfPlanet(planet);
	}

	public boolean isImportingPower(Planet planet) {
		return pm.isImportingPower(planet);
	}
	
}

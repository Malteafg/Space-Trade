package com.simaflux.spacetrade.players;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.simaflux.spacetrade.empires.Empire;
import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.objects.buildings.Building;
import com.simaflux.spacetrade.objects.resources.PlayerResource;
import com.simaflux.spacetrade.objects.resources.StaticResource;
import com.simaflux.spacetrade.objects.space.Planet;
import com.simaflux.spacetrade.players.utils.PowerManager;
import com.simaflux.spacetrade.players.utils.PowerManager.PlanetPower;
import com.simaflux.spacetrade.players.utils.ResourceManager;
import com.simaflux.spacetrade.relations.PlayerEmpireRelation;
import com.simaflux.spacetrade.relations.PlayerRelation;
import com.simaflux.spacetrade.utils.Maths;
import com.simaflux.spacetrade.utils.math.Vector3f;

public abstract class Player implements Serializable {
	
	private static final long serialVersionUID = -2976983028306012874L;
	
	protected ResourceManager rm;
	public PowerManager pm;
	
	protected double cash;
	
	protected String name;
	protected Vector3f color;

	private Map<Planet, List<Building>> buildings;
	
	private Map<Player, PlayerRelation> playerRelations;
	private Map<Empire, PlayerEmpireRelation> empireRelations;
	
	public Player(String name) {
		color = new Vector3f(Maths.random(), Maths.random(), Maths.random());
		
		rm = new ResourceManager(this);
		pm = new PowerManager(this);
		
		buildings = new HashMap<>();
		
		cash = 100000;

		playerRelations = new HashMap<>();
		empireRelations = new HashMap<>();
	}
	
	public void tick() {
		rm.tick();
		pm.tick();
		
		for(PlanetPower pp : pm.getPlanets()) {
			Planet planet = pp.getPlanet();
			
			if(pp.getState() != 0 || pp.hasExcessivePower()) {
				for(Building b : buildings.get(planet)) {
					b.setEnoughPower(true);
				}
			} else {
				int power = pp.getPowerProduced();
				for(Building b : buildings.get(planet)) {
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
			
			for(Planet p : buildings.keySet()) {
				for(Building b : buildings.get(p)) {
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
	}
	
	/*
	 * Actions
	 */
	public final void setResourceState(String tag, int s, int a) {
		if(rm.getResource(tag).getDelta() < 0) 
			GameHandler.game.market.getResource(tag).setImportFrom(GameHandler.game.market.getResource(tag).getImportFrom() - rm.getResource(tag).getDelta()); 
		else
			GameHandler.game.market.getResource(tag).setExportTo(GameHandler.game.market.getResource(tag).getExportTo() - rm.getResource(tag).getDelta());
		
		if(s > 0) 
			GameHandler.game.market.getResource(tag).setImportFrom(GameHandler.game.market.getResource(tag).getImportFrom() + a); 
		else
			GameHandler.game.market.getResource(tag).setExportTo(GameHandler.game.market.getResource(tag).getExportTo() + a);
		
		rm.getResource(tag).setResourceState(s, a);
	}

	public final void setPowerState(Planet currPlanet, int state) {
		pm.setPowerState(currPlanet, state);
	}

	public final void buyBuilding(String b, Planet planet) {
		double price = GameHandler.game.market.getBuildingCost(b);
		
		if(cash < price) return;
		
		addCash(-price);
		if(!buildings.containsKey(planet)) buildings.put(planet, new ArrayList<>());
		buildings.get(planet).add(new Building(this, planet, b));
		if(pm.getPlanetPower(planet) == null) pm.addPlanet(planet);
		calculatePower(planet);
	}
	
	public final void claimLand(int a) {
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

	public final boolean isImportingPower(Planet planet) {
		return pm.isImportingPower(planet);
	}
	
	public final List<Building> getBuildings(Planet planet) {
		return buildings.get(planet);
	}
	
	public final List<Building> getAllBuildings() {
		List<Building> b = new ArrayList<>();
		
		for(Planet p : buildings.keySet()) {
			b.addAll(getBuildings(p));
		}
		
		return b;
	}
	
	public final Building getBuilding(Planet planet, String name) {
		Building r = null;
		for(Building b : buildings.get(planet)) {
			if(b.getName().equals(name)) {
				r = b;
				break;
			}
		}
		return r;
	}
	
	public final boolean hasCash(double price) {
		return cash >= price;
	}
	
	public final boolean planetHasResource(String resource, Planet planet) {
		if(resource != null ) {
			for(StaticResource r : planet.getResources()) {
				if(r.getName().equals(resource)) return true;
			}
			return false;
		}
		return true;
	}

	public boolean hasResource(String name, int amount) {
		return rm.getQuantity(name) >= amount;
	}
	
	public void addEmpireRelation(PlayerEmpireRelation r, Empire e) {
		empireRelations.put(e, r);
	}
	
	public void addPlayerRelation(PlayerRelation r, Player p) {
		playerRelations.put(p, r);
	}
	
	public PlayerRelation getRelationshipWith(Player p) {
		return playerRelations.get(p);
	}
	
	public PlayerEmpireRelation getRelationshipWith(Empire e) {
		return empireRelations.get(e);
	}
	
}

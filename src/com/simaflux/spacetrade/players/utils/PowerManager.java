package com.simaflux.spacetrade.players.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.simaflux.spacetrade.objects.buildings.Building;
import com.simaflux.spacetrade.objects.space.Planet;
import com.simaflux.spacetrade.players.Player;

public class PowerManager implements Serializable {
	
	private static final long serialVersionUID = 1533432363659175215L;
	
	private Player player;
	
	private List<PlanetPower> planets;
	
	public PowerManager(Player player) {
		this.player = player;
		planets = new ArrayList<>();
	}
	
	public void tick() {
		for(PlanetPower p : planets) {
			if(p.getState() == 1) {
				if(p.getPowerProduced() > p.getPowerConsumed()) {
					player.addCash((p.getPowerProduced() - p.getPowerConsumed()) * p.getPlanet().getPowerPrice());
				}
			}
			if(p.getState() == -1) {
				if(p.getPowerConsumed() > p.getPowerProduced()) {
					player.addCash(-(p.getPowerConsumed() - p.getPowerProduced()) * p.getPlanet().getPowerPrice());
				}
			}
		}
	}
	
	public void addPlanet(Planet planet) {
		planets.add(new PlanetPower(planet));
	}

	public void calculatePower(Planet planet) {		
		PlanetPower power = getPlanetPower(planet);
		
		power.resetPower();
		
		for(Building b : player.getBuildings(planet)) {
			if(b.isRunning()) {
				if(b.getPower() < 0) power.addPowerConsumption(-b.getPower());
				if(b.getPower() > 0) power.addPowerProduction(b.getPower());
			}
		}
	}
	
	public PlanetPower getPlanetPower(Planet planet) {
		PlanetPower power = null;
		for(PlanetPower p : planets) {
			if(p.getPlanet().equals(planet)) {
				power = p;
			}
		}
		
		return power;
	}
	
	public class PlanetPower implements Serializable {
		
		private static final long serialVersionUID = -248640862020372513L;

		private Planet planet;
		
		private int powerConsumed, powerProduced;
		
		private int state; // 1=export, 0=nothing, -1=import
		
		private PlanetPower(Planet planet) {
			this.planet = planet;
			
			powerConsumed = powerProduced = state = 0;;
		}

		public void resetPower() {
			powerConsumed = powerProduced = 0;
		}

		public Planet getPlanet() {
			return planet;
		}

		public int getPowerConsumed() {
			return powerConsumed;
		}

		public int getPowerProduced() {
			return powerProduced;
		}
		
		public void addPowerConsumption(int a) {
			powerConsumed += a;
		}
		
		public void addPowerProduction(int a) {
			powerProduced += a;
		}

		public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}
		
		public boolean hasExcessivePower() {
			return powerProduced >= powerConsumed;
		}
		
	}

	public int getPowerProductionOfPlanet(Planet planet) {
		PlanetPower p = getPlanetPower(planet);
		if(p == null) {
			return 0;
		} else return p.getPowerProduced();
	}

	public int getPowerConsumptionOfPlanet(Planet planet) {
		PlanetPower p = getPlanetPower(planet);
		if(p == null) {
			return 0;
		} else return p.getPowerConsumed();
	}

	public void setPowerState(Planet planet, int state) {
		for(PlanetPower p : planets) {
			if(p.getPlanet().equals(planet)) {
				p.setState(state);
			}
		}
	}

	public boolean isImportingPower(Planet planet) {
		for(PlanetPower p : planets) {
			if(p.getPlanet().equals(planet)) {
				if(p.getState() == -1) return true;
			}
		}
		
		return false;
	}
	
	public List<PlanetPower> getPlanets() {
		return planets;
	}
	
}

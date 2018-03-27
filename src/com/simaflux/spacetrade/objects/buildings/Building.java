package com.simaflux.spacetrade.objects.buildings;

import com.simaflux.spacetrade.loader.GameLoader;
import com.simaflux.spacetrade.objects.resources.StaticResource;
import com.simaflux.spacetrade.objects.space.Planet;
import com.simaflux.spacetrade.players.Player;
import com.simaflux.spacetrade.utils.Maths;
import com.simaflux.spacetrade.utils.math.Vector3f;

public class Building {
	
	protected final StaticResource[] consumed, produced;
	
	protected Player player;
	protected Planet planet;
	
	protected int level;
	
	protected String name;
	
	protected boolean open, enoughPower, enoughResources;

	protected int power;
	
	protected Vector3f position, rotation;
	
	public Building(Player player, Planet planet, String name) {
		this.name = name;
		this.planet = planet;
		setPlayer(player);
		
		BuildingTemplate t = GameLoader.getBuildingInfo(name);
		
		consumed = new StaticResource[t.getNconsumed().length];
		for(int i = 0; i < consumed.length; i++) {
			consumed[i] = new StaticResource(t.getNconsumed()[i], t.getVconsumed()[i]);
		}
		
		produced = new StaticResource[t.getNproduced().length];
		for(int i = 0; i < produced.length; i++) {
			produced[i] = new StaticResource(t.getNproduced()[i], t.getVproduced()[i]);
		}
		
		open = enoughPower = enoughResources = true;
		
		power = t.getPower();
		
		level = 1;
		
		position = new Vector3f(0.5f - Maths.random(), 0.5f - Maths.random(), 0.5f - Maths.random());
		position = position.normalize();
		
		rotation = new Vector3f(- Maths.atan(position.y, position.z) + Maths.PI / 2, 0, + Maths.atan(Maths.sqrt(Maths.pow(position.y, 2) + Maths.pow(position.z, 2)), position.x) - Maths.PI / 2);
		
	}
	
	public void tick() {
		if(player != null) {
			for(StaticResource r : consumed) {
				player.addQuantity(r.getName(), -r.getAmount());
			}
			for(StaticResource r : produced) {
				player.addQuantity(r.getName(), r.getAmount());
			}
		}
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Player owner() {
		return player;
	}
	
	public Planet getPlanet() {
		return planet;
	}
	
	public int getPower() {
		return power;
	}
	
	public int getLevel() {
		return level;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean hasEnoughPower() {
		return enoughPower;
	}

	public void setEnoughPower(boolean enoughPower) {
		this.enoughPower = enoughPower;
	}

	public boolean hasEnoughResources() {
		return enoughResources;
	}

	public void setEnoughResources(boolean enoughResources) {
		this.enoughResources = enoughResources;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public boolean isRunning() {
		return open && enoughPower && enoughResources;
	}

	public StaticResource[] getConsumed() {
		return consumed;
	}

	public StaticResource[] getProduced() {
		return produced;
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

}

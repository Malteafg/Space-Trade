package com.simaflux.spacetrade.objects.space;

import java.util.ArrayList;
import java.util.List;

import com.simaflux.spacetrade.objects.buildings.Building;
import com.simaflux.spacetrade.objects.resources.StaticResource;
import com.simaflux.spacetrade.objects.space.utils.ClaimManager;
import com.simaflux.spacetrade.players.Player;
import com.simaflux.spacetrade.utils.Maths;
import com.simaflux.spacetrade.utils.Vars;
import com.simaflux.spacetrade.utils.math.Vector3f;

public class Planet extends AstronomicalObject {
	
	private static final long serialVersionUID = -3768360803016795053L;

	private Vector3f color;
	
	private final float starDist, speed;
	private float angle;
	private final Vector3f starPos;
	
	private final int size;
	
	private List<StaticResource> resources;
	private List<Building> buildings;
	
	private String type;
	
	private double powerPrice;
	
	public ClaimManager cm;
	
	public Planet(SolarSystem system, String name, Vector3f starPos, float starDist) {
		super(system, name, new Vector3f(0, 0, 0), 1);
		this.starDist = starDist;
		this.starPos = starPos;
		
		resources = new ArrayList<>();
		buildings = new ArrayList<>();
		
		float typedice = Maths.random();
		if(typedice > 0.9) {
			type = Vars.GAS_GIANT;
			scale = Maths.random() * 2 + 4;
			size = (int) (scale * 10);
			cm = new ClaimManager(size, 0);
		} else if(typedice > 0.7) {
			type = Vars.NATURALLY_HABITABLE;
			scale = Maths.random() * 2 + 2;
			size = (int) (scale * 40);
			cm = new ClaimManager(size, (int) (size * 0.8)); 
			
			if(Maths.random() < 0.3) resources.add(new StaticResource("Iron", (int) (scale * 3 + Maths.random() * 4)));
			if(Maths.random() < 0.2) resources.add(new StaticResource("Aluminum", (int) (scale * 3 + Maths.random() * 4)));
			if(Maths.random() < 0.1) resources.add(new StaticResource("Gold", (int) (scale * 2 + Maths.random() * 3)));
			if(Maths.random() < 0.3) resources.add(new StaticResource("Carbon", (int) (scale * 4 + Maths.random() * 4)));
			if(Maths.random() < 0.2) resources.add(new StaticResource("Oil", (int) (scale * 4 + Maths.random() * 4)));
		} else if(typedice > 0.2) {
			type = Vars.TERRESTRIAL;
			scale = Maths.random() * 2 + 2;
			size = (int) (scale * 40);
			cm = new ClaimManager(size, (int) (size * 0.4));
			
			if(Maths.random() < 0.32) resources.add(new StaticResource("Iron", (int) (scale * 4 + Maths.random() * 4)));
			if(Maths.random() < 0.25) resources.add(new StaticResource("Aluminum", (int) (scale * 4 + Maths.random() * 4)));
			if(Maths.random() < 0.15) resources.add(new StaticResource("Gold", (int) (scale * 2 + Maths.random() * 3)));
			if(Maths.random() < 0.2) resources.add(new StaticResource("Carbon", (int) (scale * 2 + Maths.random() * 3)));
			if(Maths.random() < 0.1) resources.add(new StaticResource("Oil", (int) (scale * 2 + Maths.random() * 2)));
		} else if(typedice > 0.1) {
			type = Vars.ICE;
			scale = Maths.random() * 2 + 1;
			size = (int) (scale * 20);
			cm = new ClaimManager(size, (int) (size * 0.1));
			
			if(Maths.random() < 0.03) resources.add(new StaticResource("Iron", (int) (scale * 6 + Maths.random() * 10)));
			if(Maths.random() < 0.02) resources.add(new StaticResource("Aluminum", (int) (scale * 5 + Maths.random() * 8)));
			if(Maths.random() < 0.01) resources.add(new StaticResource("Gold", (int) (scale * 3 + Maths.random() * 6)));
		} else {
			type = Vars.LAVA;
			scale = Maths.random() * 2 + 1;
			size = (int) (scale * 20);
			cm = new ClaimManager(size, 0);
		}
		
		powerPrice = 10;
		
		speed = Maths.random() * 0.001f + 0.0015f;
		angle = Maths.random() * Maths.PI * 2;
		
		color = new Vector3f(Maths.random(), Maths.random(), Maths.random());
	}

	public void tick() {
		for(Building b : buildings) {
			b.tick();
		}
		powerPrice *= (Math.random() * 0.01) + 1;
	}
	
	public void update() {
		angle += speed;
    	
    	position.set(Maths.cos(angle) * starDist + starPos.x, starPos.y, Maths.sin(angle) * starDist + starPos.z);
	}

	@Override
	public Vector3f getExpectedPos(int time) {
		float a = angle + speed * time;
		return new Vector3f(Maths.cos(a) * starDist + starPos.x, starPos.y, Maths.sin(a) * starDist + starPos.z);
	}
	
	public Vector3f getColor() {
		return color;
	}
	
	public List<StaticResource> getResources() {
		return resources;
	}
	
	public void addResource(StaticResource r) {
		resources.add(r);
	}

	public String getPlanetType() {
		return type;
	}
	
	public double getPowerPrice() {
		return powerPrice;
	}
	
	public List<Building> getBuildings() {
		return buildings;
	}
	
	public List<Building> getBuildings(Player p) {
		List<Building> r = new ArrayList<>();
		for(Building b : buildings) {
			if(b.owner().equals(p)) {
				r.add(b);
			}
		}
		return r;
	}
	
	public void addBuilding(Player p, String building) {
		buildings.add(new Building(p, this, building));
		cm.addBuilding(p, buildings.get(buildings.size() - 1));
	}

	public int getSize() {
		return size;
	}

}

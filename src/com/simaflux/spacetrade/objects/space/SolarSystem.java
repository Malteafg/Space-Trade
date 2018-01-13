package com.simaflux.spacetrade.objects.space;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.simaflux.spacetrade.loader.GameLoader;
import com.simaflux.spacetrade.utils.Maths;
import com.simaflux.spacetrade.utils.math.Vector3f;

public class SolarSystem implements Serializable {

	private static final long serialVersionUID = -6891361638532761189L;

	private Star star;
	private Map<String, Planet> planets;

	private String name;

	public SolarSystem(String name, Vector3f starPos, float scale, int num) {
		star = new Star(this, name, starPos, scale);
		planets = new HashMap<>();

		this.name = star.getName() + " System";

		generatePlanets(starPos, num);
	}

	private void generatePlanets(Vector3f starPos, int num) {
		for (int i = 0; i < num; i++) {
			String name = GameLoader.getName(GameLoader.planetNames);
			planets.put(name, new Planet(this, name, star.getPosition(),
					(float) (Math.pow(Maths.random(), 2) * 3 + 20 * i + 30)));
		}
	}

	public void update() {
		for (String s : planets.keySet()) {
			planets.get(s).update();
		}
	}

	public Star getStar() {
		return star;
	}

	public Map<String, Planet> getPlanets() {
		return planets;
	}

	public String getName() {
		return name;
	}

}

package com.simaflux.spacetrade.empires;

import java.util.ArrayList;
import java.util.List;

import com.simaflux.spacetrade.loader.GameLoader;
import com.simaflux.spacetrade.objects.space.Planet;
import com.simaflux.spacetrade.objects.space.SolarSystem;
import com.simaflux.spacetrade.utils.Maths;
import com.simaflux.spacetrade.utils.math.Vector3f;

public class Empire {
	
	private String name;
	private Vector3f color;
	
	private List<SolarSystem> systems;
	private Planet capital;
	
	public Empire(Planet capital) {
		this.capital = capital;
		name = GameLoader.getName(GameLoader.empireNames);
		color = new Vector3f(Maths.random(), Maths.random(), Maths.random());
		
		systems = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public Vector3f getColor() {
		return color;
	}

}

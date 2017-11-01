package com.simaflux.spacetrade.objects.space;

import com.simaflux.spacetrade.graphics.Light;
import com.simaflux.spacetrade.utils.Maths;
import com.simaflux.spacetrade.utils.math.Vector3f;

public class Star extends AstronomicalObject {

	private static final long serialVersionUID = 1217137334345035981L;
	
	private Light light;

	public Star(SolarSystem system, String name, Vector3f position, float scale) {
		super(system, name, position, Maths.random() * 5 + 8);
		
		light = new Light(position, new Vector3f(1, 1, 1));
	}

	public Light getLight() {
		return light;
	}

}

package com.simaflux.spacetrade.objects.space;

import java.io.Serializable;

import com.simaflux.spacetrade.utils.math.Vector3f;

public abstract class AstronomicalObject implements Serializable {
	
	private static final long serialVersionUID = -4877919996837592434L;
	
	protected Vector3f position, rotation;
	protected float scale;
	
	protected SolarSystem system;
	protected String name;
	
	protected AstronomicalObject(SolarSystem system, String name, Vector3f position, float scale) {
		this.system = system;
		this.name = name;
		this.position = position;
		this.rotation = new Vector3f();
		this.scale = scale;
	}
	
	public void increasePosition(float dx, float dy, float dz) {
		position.x += dx; 
		position.y += dy;
		position.z += dz;
	}
	
	public void increaseRotation(float dx, float dy, float dz) {
		rotation.x += dx;
		rotation.y += dy;
		rotation.z += dz;
	}

	public String getName() {
		return name;
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public float getScale() {
		return scale;
	}
	
	public SolarSystem getSystem() {
		return system;
	}

}

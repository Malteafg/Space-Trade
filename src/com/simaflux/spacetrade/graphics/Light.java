package com.simaflux.spacetrade.graphics;

import java.io.Serializable;

import com.simaflux.spacetrade.utils.math.Vector3f;

public class Light implements Serializable {
	
	private static final long serialVersionUID = 889270303856666503L;
	
	private Vector3f position;
	private Vector3f color;

	public Light(Vector3f position, Vector3f color) {
		this.position = position;
		this.color = color;
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getColor() {
		return color;
	}

}

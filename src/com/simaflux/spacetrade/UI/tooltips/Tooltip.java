package com.simaflux.spacetrade.UI.tooltips;

import com.simaflux.spacetrade.utils.Vars;
import com.simaflux.spacetrade.utils.math.Vector2f;

public class Tooltip {
	
	private Vector2f size, glsize;
	
	private int time;
	
	public Tooltip(float width, float height) {
		this(width, height, 60);
	}
	
	public Tooltip(float width, float height, int time) {
		this.size = new Vector2f(width, height);
		this.glsize = new Vector2f(width / Vars.WIDTH * 2, height / Vars.HEIGHT * 2);
		this.time = time;
	}
	
	public Vector2f getSize() {
		return size;
	}
	
	public Vector2f getGlsize() {
		return glsize;
	}
	
	public int getTime() {
		return time;
	}

}

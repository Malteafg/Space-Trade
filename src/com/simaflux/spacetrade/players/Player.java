package com.simaflux.spacetrade.players;

import java.io.Serializable;

import com.simaflux.spacetrade.objects.resources.PlayerResource;
import com.simaflux.spacetrade.players.interactions.OpinionManager;
import com.simaflux.spacetrade.players.utils.ResourceManager;
import com.simaflux.spacetrade.utils.Maths;
import com.simaflux.spacetrade.utils.math.Vector3f;

public abstract class Player implements Serializable {
	
	private static final long serialVersionUID = -2976983028306012874L;
	
	protected ResourceManager rm;
	
	protected double cash;
	
	protected String name;
	protected Vector3f color;
	
	protected OpinionManager theirOpinions;
	
	public Player(String name) {
		color = new Vector3f(Maths.random(), Maths.random(), Maths.random());
		
		rm = new ResourceManager(this);
		
		cash = 1000;
	}
	
	public void tick() {
		rm.tick();
		cash--;
	}
	
	/*
	 * Actions
	 */
	public final void setResourceState(String tag, int s) {
		rm.getResource(tag).setResourceState(s);
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

	public OpinionManager getTheirOpinions() {
		return theirOpinions;
	}
	
}

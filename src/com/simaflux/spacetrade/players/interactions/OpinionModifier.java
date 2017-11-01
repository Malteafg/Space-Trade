package com.simaflux.spacetrade.players.interactions;

import java.io.Serializable;

public class OpinionModifier implements Serializable {
	
	private static final long serialVersionUID = -596599801311144517L;

	// ALL MODIFIERS ARE STATIC AND FINAL
	public static final OpinionModifier wasAtWar = new OpinionModifier("Was at war", -5);
	
	public static final OpinionModifier borderFriction = new OpinionModifier("Border Friction"); 
	
	// opinion modifier
	private final String reason;
	private int value;
	
	public OpinionModifier(String reason, int value) {
		this.reason = reason;
		this.value = value;
	}
	
	public OpinionModifier(String reason) {
		this.reason = reason;
	}
	
	public OpinionModifier(OpinionModifier modifier, int value) {
		reason = modifier.getReason();
		this.value = value;
	}
	
	public String getReason() {
		return reason;
	}
	
	public int getValue() {
		return value;
	}

}

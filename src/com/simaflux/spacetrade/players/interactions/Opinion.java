package com.simaflux.spacetrade.players.interactions;

import java.io.Serializable;
import java.util.ArrayList;

import com.simaflux.spacetrade.players.Player;

public class Opinion implements Serializable {
	
	private static final long serialVersionUID = 3085769576322998145L;
	
	private ArrayList<OpinionModifier> modifiers;
	
	private Player actor;
	
	public Opinion(Player actor) {
		setActor(actor);
		modifiers = new ArrayList<>();
	}
	
	public void addModifier(OpinionModifier modifier) {
		modifiers.add(modifier);
	}
	
	public int getOpinion() {
		int n = 0;
		
		for(OpinionModifier m : modifiers) {
			n += m.getValue();
		}
		
		return n;
	}

	public ArrayList<OpinionModifier> getModifiers() {
		return modifiers;
	}

	public Player getActor() {
		return actor;
	}

	public void setActor(Player actor) {
		this.actor = actor;
	}

}

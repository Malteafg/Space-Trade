package com.simaflux.spacetrade.players.interactions;

import java.io.Serializable;
import java.util.ArrayList;

import com.simaflux.spacetrade.players.Player;

public class OpinionManager implements Serializable {
	
	private static final long serialVersionUID = -5630196897070512424L;
	
	private ArrayList<Opinion> opinions;
	
	public OpinionManager() {
		opinions = new ArrayList<>();
	}
	
	public void addOpinionModifier(Player who, OpinionModifier modifier) {
		for(Opinion o : opinions) {
			if(who == o.getActor()) {
				o.addModifier(modifier);
			}
		}
	}
	
	public int getTheirOpinion(String who) {
		return 0;
	}
	
	public ArrayList<Opinion> getOpinions() {
		return opinions;
	}

}

package com.simaflux.spacetrade.relations;

import com.simaflux.spacetrade.empires.Empire;
import com.simaflux.spacetrade.players.Player;

public class PlayerEmpireRelation extends Relation {
	
	private Player player;
	private Empire empire;
	
	public PlayerEmpireRelation(Player player, Empire empire) {
		this.player = player;
		this.empire = empire;
	}

}

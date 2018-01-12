package com.simaflux.spacetrade.relations;

import com.simaflux.spacetrade.players.Player;

public class PlayerRelation extends Relation {
	
	private Player player1, player2;
	
	public PlayerRelation(Player player1, Player player2) {
		super(player1.getName());
		this.player1 = player1;
		this.player2 = player2;
	}

}

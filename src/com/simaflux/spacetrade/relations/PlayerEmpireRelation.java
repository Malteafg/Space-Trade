package com.simaflux.spacetrade.relations;

import java.util.ArrayList;
import java.util.List;

import com.simaflux.spacetrade.empires.Empire;
import com.simaflux.spacetrade.players.Player;
import com.simaflux.spacetrade.relations.contracts.Contract;

public class PlayerEmpireRelation extends Relation {
	
	private Player player;
	private Empire empire;
	
	private List<Contract> contracts;
	
	public PlayerEmpireRelation(Player player, Empire empire) {
		super(player.getName());
		this.player = player;
		this.empire = empire;
		
		contracts = new ArrayList<>();
	}
	
	public void makeContract(Contract contract) {
		contracts.add(contract);
	}
	
	public void terminateContract(Contract contract) {
		contracts.remove(contract);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Empire getEmpire() {
		return empire;
	}

}

package com.simaflux.spacetrade.relations;

import java.util.ArrayList;
import java.util.List;

import com.simaflux.spacetrade.empires.Empire;
import com.simaflux.spacetrade.players.Player;

public class RelationManager {
	
	private List<Relation> relations;
	
	public RelationManager() {
		relations = new ArrayList<>();
	}
	
	public void createPlayerEmpireRelations(Player[] players, List<Empire> empires) {
		for(int i = 0; i < players.length; i++) {
			for(int j = i + 1; j < empires.size(); j++) {
				PlayerEmpireRelation r = new PlayerEmpireRelation(players[i], empires.get(i));

				players[i].addEmpireRelation(r);
				empires.get(j).addPlayerRelation(r);
				
				relations.add(r);
			}			
		}
	}

	public void createPlayerRelations(Player[] players) {
		for(int i = 0; i < players.length; i++) {
			for(int j = i + 1; j < players.length; j++) {
				PlayerRelation r = new PlayerRelation(players[i], players[j]);
				
				players[i].addPlayerRelation(r);
				players[j].addPlayerRelation(r);

				relations.add(r);
			}
		}
	}

	public void createEmpireRelations(List<Empire> empires) {
		for(int i = 0; i < empires.size(); i++) {
			for(int j = i + 1; j < empires.size(); j++) {
				EmpireRelation r = new EmpireRelation(empires.get(i), empires.get(j));
				
				empires.get(i).addEmpireRelation(r);
				empires.get(j).addEmpireRelation(r);

				relations.add(r);
			}
		}
	}

	public void update() {
		
	}

}

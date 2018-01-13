package com.simaflux.spacetrade.empires;

import java.util.HashMap;
import java.util.Map;

import com.simaflux.spacetrade.loader.GameLoader;
import com.simaflux.spacetrade.objects.space.Planet;
import com.simaflux.spacetrade.players.Player;
import com.simaflux.spacetrade.relations.EmpireRelation;
import com.simaflux.spacetrade.relations.PlayerEmpireRelation;
import com.simaflux.spacetrade.utils.Maths;
import com.simaflux.spacetrade.utils.math.Vector3f;

public class Empire {
	
	private String name;
	private Vector3f color;
	
	private Planet capital;
	
	private Map<Empire, EmpireRelation> empireRelations;
	private Map<Player, PlayerEmpireRelation> playerRelations;
	
	public Empire(Planet capital) {
		this.capital = capital;
		name = GameLoader.getName(GameLoader.empireNames);
		color = new Vector3f(Maths.random(), Maths.random(), Maths.random());
		
		empireRelations = new HashMap<>();
		playerRelations = new HashMap<>();
	}
	
	public String getName() {
		return name;
	}
	
	public Vector3f getColor() {
		return color;
	}
	
	public void addEmpireRelation(EmpireRelation r, Empire e) {
		empireRelations.put(e, r);
	}
	
	public void addPlayerRelation(PlayerEmpireRelation r, Player p) {
		playerRelations.put(p, r);
	}
	
	public EmpireRelation getRelationshipWith(Empire e) {
		return empireRelations.get(e);
	}
	
	public PlayerEmpireRelation getRelationshipWith(Player p) {
		return playerRelations.get(p);
	}

}

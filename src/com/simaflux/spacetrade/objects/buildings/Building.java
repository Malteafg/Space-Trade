package com.simaflux.spacetrade.objects.buildings;

import com.simaflux.spacetrade.loader.GameLoader;
import com.simaflux.spacetrade.objects.resources.StaticResource;
import com.simaflux.spacetrade.players.Player;

public class Building {
	
	protected final StaticResource[] consumed, produced;
	
	protected Player player;
	
	protected int level;
	
	protected String name;
	
	public Building(Player player, String name) {
		this.name = name;
		setPlayer(player);
		
		BuildingTemplate t = GameLoader.getBuildingInfo(name);
		
		consumed = new StaticResource[t.getNconsumed().length];
		for(int i = 0; i < consumed.length; i++) {
			consumed[i] = new StaticResource(t.getNconsumed()[i], t.getVconsumed()[i]);
		}
		
		produced = new StaticResource[t.getNproduced().length];
		for(int i = 0; i < produced.length; i++) {
			produced[i] = new StaticResource(t.getNproduced()[i], t.getVproduced()[i]);
		}
		
		level = 1;
	}
	
	public void tick() {
		if(player != null) {
			for(StaticResource r : consumed) {
				player.addQuantity(r.getName(), -r.getAmount());
			}
			for(StaticResource r : produced) {
				player.addQuantity(r.getName(), r.getAmount());
			}
		}
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

}

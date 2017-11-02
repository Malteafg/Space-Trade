package com.simaflux.spacetrade.objects.buildings;

import com.simaflux.spacetrade.objects.resources.StaticResource;
import com.simaflux.spacetrade.players.Player;

public abstract class Building {
	
	protected final StaticResource[] consumed, produced;
	
	protected Player player;
	
	protected int level;
	
	public Building(Player player, int consumed, int produced) {
		setPlayer(player);
		this.consumed = new StaticResource[consumed];
		this.produced = new StaticResource[produced];
		
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

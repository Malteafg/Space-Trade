package com.simaflux.spacetrade.players.utils;

import java.io.Serializable;

import com.simaflux.spacetrade.game.GameHandler;
import com.simaflux.spacetrade.loader.GameLoader;
import com.simaflux.spacetrade.objects.resources.PlayerResource;
import com.simaflux.spacetrade.players.Player;

public class ResourceManager implements Serializable {
	
	private static final long serialVersionUID = 8864930538575881273L;
	
	private PlayerResource[] resources;
	private Player player;
	
	public ResourceManager(Player player) {
		this.player = player;
		
		resources = GameLoader.playerResources;
	}
	
	public void tick() {
		for(PlayerResource r : resources) {
			if(r.isExporting()) {
				player.addCash(r.getDelta() * GameHandler.game.market.getResource(r.getName()).getPrice());
				r.add(r.getDelta());
			}
			
			if(r.isImporting()) {
				player.addCash(-r.getDelta() * GameHandler.game.market.getResource(r.getName()).getPrice());
				r.add(r.getDelta());
			}
		}
	}
	
	public int getQuantity(String tag) {
		for(PlayerResource r : resources) {
			if(r.getName().equals(tag)) {
				return r.getAmount();
			}
		}
		return -1;
	}
	
	public void addQuantity(String tag, int a) {
		for(PlayerResource r : resources) {
			if(r.getName().equals(tag)) {
				r.add(a);
			}
		}
	}
	
	public PlayerResource getResource(String tag) {
		for(PlayerResource r : resources) {
			if(r.getName().equals(tag)) {
				return r;
			}
		}
		
		System.err.println("No resource " + tag);
		return null;
	}

}

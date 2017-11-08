package com.simaflux.spacetrade.objects.space.utils;

import java.util.ArrayList;
import java.util.List;

import com.simaflux.spacetrade.objects.buildings.Building;
import com.simaflux.spacetrade.players.Player;

public class ClaimManager {
	
	private int empireOwned, notClaimed, playerOwned;
	private final int size;
	
	private final List<PlayerClaim> players = new ArrayList<>();
	
	public ClaimManager(int size, int eo) {
		this.size = size;
		empireOwned = eo;
		notClaimed = size - eo;
		playerOwned = 0;
	}
	
	public void claimLand(Player player, int a) {
		boolean b = true;
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getPlayer() == player) {
				players.get(i).addLand(a);
				b = false;
			}
		}
		
		if(b) {
			players.add(new PlayerClaim(player));
			players.get(players.size() - 1).addLand(a);
		}
		
		notClaimed -= a;
		playerOwned += a;
	}

	public void addBuilding(Player player, Building b) {
		for(PlayerClaim c : players) {
			if(c.getPlayer().equals(player)) c.addBuilding(b);
		}
	}
	
	public int getNotClaimed() {
		return notClaimed;
	}

	public int getEmpireOwned() {
		return empireOwned;
	}

	public int getSize() {
		return size;
	}
	
	public int getPlayerOwned() {
		return playerOwned;
	}
	
	public int getPlayerOwned(Player p) {
		for(PlayerClaim c : players) {
			if(c.getPlayer().equals(p)) return c.getSize();
		}
		return 0;
	}

	public class PlayerClaim {
		
		private Player player;
		private int size, buildSize;
		
		private final List<Building> buildings = new ArrayList<>();
		
		public PlayerClaim(Player player) {
			this.player = player;
			
			size = buildSize = 0;
		}
		
		public void addLand(int a) {
			size += a;
		}
		
		public void addBuilding(Building b) {
			buildings.add(b);
			
			countBuildingSize();
		}
		
		public void countBuildingSize() {
			buildSize = 0;
			for(Building b : buildings) {
				buildSize += b.getLevel();
			}			
		}
		
		public int getSpareLand() {
			return size - buildSize;
		}
		
		public int getSize() {
			return size;
		}
		
		public Player getPlayer() {
			return player;
		}
		
	}

}

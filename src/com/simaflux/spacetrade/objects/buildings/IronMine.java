package com.simaflux.spacetrade.objects.buildings;

import com.simaflux.spacetrade.objects.resources.StaticResource;
import com.simaflux.spacetrade.players.Player;

public class IronMine extends Building {

	public IronMine(Player player) {
		super(player, 0, 1);
		
		produced[0] = new StaticResource("Iron", 3);
	}

}

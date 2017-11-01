package com.simaflux.spacetrade.objects.resources;

import java.io.Serializable;

public final class StaticResource implements Serializable {
	
	private static final long serialVersionUID = 7966227952926633352L;
	
	public final String name;
	public final int amount;

	public StaticResource(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}
	
	public final int getAmount() {
		return amount;
	}
	
	public final String getName() {
		return name;
	}
	
}

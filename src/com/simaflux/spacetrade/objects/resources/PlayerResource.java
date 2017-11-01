package com.simaflux.spacetrade.objects.resources;

import java.io.Serializable;

public class PlayerResource implements Serializable {

	private static final long serialVersionUID = 5543794402091713399L;
	
	private String name;
	private int amount;
	
	private int state; // 1=export, 0=nothing, -1=import

	public PlayerResource(String name) {
		this.setName(name);
		this.setAmount(0);
		
		state = 0;
	}
	
	public void add(int a) {
		setAmount(getAmount() + a);
	}

	public boolean isExporting() {
		return state == 1;
	}

	public boolean isImporting() {
		return state == -1;
	}
	
	public void setResourceState(int s) {
		state = s;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}

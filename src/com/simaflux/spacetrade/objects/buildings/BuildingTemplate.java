package com.simaflux.spacetrade.objects.buildings;

public class BuildingTemplate {
	
	private final String[] ncost, nproduced, nconsumed;
	private final int[] vcost, vproduced, vconsumed;
	private final int power;
	private final String naturalResource;
	
	public BuildingTemplate(String[] ncost, String[] nproduced, String[] nconsumed, int[] vcost, int[] vproduced, int[] vconsumed, int power, String naturalResource) {
		this.ncost = ncost;
		this.nproduced = nproduced;
		this.nconsumed = nconsumed;
		this.vcost = vcost;
		this.vproduced = vproduced;
		this.vconsumed = vconsumed;
		this.power = power;
		this.naturalResource = naturalResource;
	}

	public String[] getNcost() {
		return ncost;
	}

	public String[] getNproduced() {
		return nproduced;
	}

	public String[] getNconsumed() {
		return nconsumed;
	}

	public int[] getVcost() {
		return vcost;
	}

	public int[] getVproduced() {
		return vproduced;
	}

	public int[] getVconsumed() {
		return vconsumed;
	}

	public int getPower() {
		return power;
	}

	public String getNaturalResource() {
		return naturalResource;
	}

}

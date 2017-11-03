package com.simaflux.spacetrade.objects.buildings;

public class BuildingTemplate {
	
	private final String[] ncost, nproduced, nconsumed;
	private final int[] vcost, vproduced, vconsumed;
	
	public BuildingTemplate(String[] ncost, String[] nproduced, String[] nconsumed, int[] vcost, int[] vproduced, int[] vconsumed) {
		this.ncost = ncost;
		this.nproduced = nproduced;
		this.nconsumed = nconsumed;
		this.vcost = vcost;
		this.vproduced = vproduced;
		this.vconsumed = vconsumed;
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

}

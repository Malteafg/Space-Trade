package com.simaflux.spacetrade.relations.opinions;

public class OpinionTemplate {
	
	private final float value;
	private final String reason;
	
	public OpinionTemplate(String reason, float value) {
		this.value = value;
		this.reason = reason;
	}
	
	public float getValue() {
		return value;
	}
	
	public String getReason() {
		return reason;
	}

}

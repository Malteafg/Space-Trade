package com.simaflux.spacetrade.relations;

import java.util.Map;

import com.simaflux.spacetrade.relations.opinions.OpinionManager;
import com.simaflux.spacetrade.relations.opinions.OpinionModifier;

public abstract class Relation {
	
	protected OpinionManager om;
	protected String nullName;
	
	protected Relation(String nullName) {
		this.nullName = nullName;
		
		om = new OpinionManager();
	}
	
	public float getOpinionValue(String name) {
		return om.getOpinionValue(name == nullName);
	}
	
	public void addOpinionModifier(OpinionModifier m, String name) {
		om.addModifier(m, name == nullName);
	}
	
	public Map<String, OpinionModifier> getOpinionModifiers(String name) {
		return om.getOpinionModifiers(name == nullName);
	}

}

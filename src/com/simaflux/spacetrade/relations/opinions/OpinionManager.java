package com.simaflux.spacetrade.relations.opinions;

import java.util.HashMap;
import java.util.Map;

public class OpinionManager {
	
	private Map<String, OpinionModifier> o1, o2;
	
	private float opinion1, opinion2;
	
	public OpinionManager() {
		o1 = new HashMap<>();
		o2 = new HashMap<>();
	}
	
	public float getOpinionValue(boolean b) {
		if(b) return opinion1;
		else return opinion2;
	}
	
	public void addModifier(OpinionModifier o, boolean b) {
		if(b) o1.put(o.getReason(), o);
		else o2.put(o.getReason(), o);
	}

	public Map<String, OpinionModifier> getOpinionModifiers(boolean b) {
		if(b) return o1;
		return o2;
	}
	
}

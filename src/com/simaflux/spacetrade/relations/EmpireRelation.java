package com.simaflux.spacetrade.relations;

import com.simaflux.spacetrade.empires.Empire;

public class EmpireRelation extends Relation {
	
	private Empire empire1, empire2;
	
	public EmpireRelation(Empire empire1, Empire empire2) {
		super(empire1.getName());
		this.empire1 = empire1;
		this.empire2 = empire2;
	}
	
}

package com.simaflux.spacetrade.relations.opinions;

import com.simaflux.spacetrade.game.date.Date;

public class OpinionModifier {
	
	private float value;
	private final String reason;
	private Date endDate;
	
	public OpinionModifier(OpinionTemplate t) {
		this(t, null);
	}
	
	public OpinionModifier(OpinionTemplate t, Date d) {
		value = t.getValue();
		reason = t.getReason();
		endDate = d;
	}

	public float getValue() {
		return value;
	}

	public String getReason() {
		return reason;
	}

	public Date getEndDate() {
		return endDate;
	}
	
}

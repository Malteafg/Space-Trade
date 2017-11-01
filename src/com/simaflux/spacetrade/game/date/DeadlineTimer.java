package com.simaflux.spacetrade.game.date;

import java.io.Serializable;

public class DeadlineTimer implements Serializable {

	private static final long serialVersionUID = -4791862085912059272L;
	
	private Deadline deadline;
	private Date date;
	private int daysUntilDeadline;
	private String tag;
	
	public DeadlineTimer(Deadline deadline, Date date, String tag) {
		this.deadline = deadline;
		this.date = date;
		this.tag = tag;
		
		daysUntilDeadline = DateManager.getDaysUntil(date);
	}
	
	public DeadlineTimer(Deadline deadline, int days, String tag) {
		this.deadline = deadline;
		daysUntilDeadline = days;
		this.tag = tag;
		
		date = DateManager.getDateIn(days);
	}
	
	public boolean tickDay() {
		daysUntilDeadline--;
		
		if(daysUntilDeadline == 0) {
			deadline.deadlineReached(tag);
			return true;
		}
		return false;
	}
	
	public Date getEndDate() {
		return date;
	}
	
}

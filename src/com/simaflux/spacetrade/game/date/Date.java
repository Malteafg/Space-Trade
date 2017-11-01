package com.simaflux.spacetrade.game.date;

import java.io.Serializable;

public class Date implements Serializable {
	
	private static final long serialVersionUID = 7587649619565984361L;
	
	private int day, month, year;
	
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public Date(Date date) {
		this.day = date.getDay();
		this.month = date.getMonth();
		this.year = date.getYear();
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public void addDay() {
		day++;
	}
	
	public void addMonth() {
		month++;
	}
	
	public void addYear() {
		year++;
	}
	
	public void addDays(int days) {
		for(int i = 0; i < days; i++) {
			day++;
			
			if(day >= DateManager.MAX_DAYS) {
				month++;
				day = 1;
			
				if(month >= DateManager.MAX_MONTHS) {
					year++;
					month = 1;
				}
			}
		}
	}
	
}

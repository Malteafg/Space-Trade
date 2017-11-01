package com.simaflux.spacetrade.game.date;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class DateManager implements Serializable {
	
	private static final long serialVersionUID = -6737706297525354567L;
	
	private int ticks;
	private static Date date;
	public static final int MAX_TICKS = 1, MAX_DAYS = 30, MAX_MONTHS = 12;
	
	private static ArrayList<DeadlineTimer> deadlines;
	
	public DateManager() {
		ticks = 0;
		date = new Date(Calendar.getInstance().get(Calendar.DAY_OF_MONTH), 
				Calendar.getInstance().get(Calendar.MONTH), 
				Calendar.getInstance().get(Calendar.YEAR) + 1000);
		
		deadlines = new ArrayList<>();
	}
	
	public void tick() {
		ticks++;
		
		if(ticks >= MAX_TICKS) {
			date.addDay();
			ticks = 0;
			
			for(int i = 0; i < deadlines.size(); i++) {
				if(deadlines.get(i).tickDay()) {
					deadlines.remove(i);
					i--;
				}
			}
			
			if(date.getDay() >= MAX_DAYS) {
				date.addMonth();
				date.setDay(1);
				
				if(date.getMonth() >= MAX_MONTHS) {
					date.addYear();
					date.setMonth(1);
				}
			}
		}
	}
	
	public static void addDeadline(DeadlineTimer timer) {
		deadlines.add(timer);
	}
	
	public static int getDaysUntil(Date d) {
		int days = 0;
		
		days += d.getDay() - date.getDay();
		days += (d.getMonth() - date.getMonth()) * MAX_DAYS;
		days += (d.getYear() - date.getYear()) * MAX_MONTHS * MAX_DAYS;
		
		return days;
	}
	
	public static Date getDateIn(int days) {
		Date d = new Date(date);
		d.addDays(days);
		return d;
	}

	public int getTicks() {
		return ticks;
	}

	public int getDay() {
		return date.getDay();
	}

	public int getMonth() {
		return date.getMonth();
	}

	public int getYear() {
		return date.getYear();
	}

}

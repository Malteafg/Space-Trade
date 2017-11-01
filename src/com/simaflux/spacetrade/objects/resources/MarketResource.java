package com.simaflux.spacetrade.objects.resources;

import java.io.Serializable;
import java.util.ArrayList;

public class MarketResource implements Serializable {
	
	private static final long serialVersionUID = -8126990840958272414L;
	
	private String name;
	
	private double price;
	private double growthFactor;
	
	private int exportTo, importFrom;
	
	private int worldUse, worldProduction;
	private int periodsWorldUse, periodsWorldProduction;
//	private double growthWorldUse, growthWorldProduction;
	
	private ArrayList<Double> prices;
	
	public MarketResource(String name, double p, int worldBase) {
		this.name = name;
		
		price = p;
		
		prices = new ArrayList<>();
		prices.add(p);
		prices.add(p);
		
		worldUse = worldProduction = worldBase;
		
		growthFactor = 1;
		
		resetVariables();
	}

	public void tick() {
		prices.add(price);
		
		if(periodsWorldUse == 0) {
			periodsWorldUse = (int) (Math.random() * 20) + 20;
			
//			double rateOverPeriods = 0.5 + Math.random();
//			growthWorldUse = Math.pow(rateOverPeriods, 1 / periodsWorldUse);
		}
		
		if(periodsWorldProduction == 0) {
			periodsWorldProduction = (int) (Math.random() * 20) + 20;
			
//			double rateOverPeriods = 0.5 + Math.random();
	//		growthWorldProduction = Math.pow(rateOverPeriods, 1 / periodsWorldProduction);
		}
		
//		System.out.println(growthFactor);
		
		double randomFactor = 0.005;
		worldUse *= ((1 + randomFactor / 2) - randomFactor * Math.random());
		worldProduction *= ((1 + randomFactor / 2) - randomFactor * Math.random());
		
		growthFactor = (worldUse + importFrom + 0.0) / (worldProduction + exportTo + 0.0);
		
		price *= growthFactor;
		
//		System.out.println(name + " " + worldUse + " " + importFrom + " " + worldProduction + " " + exportTo);
//		System.out.println(name + " | " + worldUse + " " + worldProduction + " " + prices.get(prices.size() - 2) + " " + prices.get(prices.size() - 1));
		
		resetVariables();
	}
	
	private void resetVariables() {
		exportTo = importFrom = 0;
		periodsWorldUse -= 1;
		periodsWorldProduction -= 1;
	}
	
	public double getPrice() {
		return price;
	}
	
	public double getGrowthFactor() {
        return growthFactor;
    }
	
	public void add(int a) {
		if(a > 0) exportTo += a;
		if(a < 0) importFrom -= a;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Double> getAllPrices() {
		return prices;
	}

}

package com.simaflux.spacetrade.objects.resources;

import java.io.Serializable;
import java.util.ArrayList;

public class MarketResource implements Serializable {
	
	private static final long serialVersionUID = -8126990840958272414L;
	
	private String name;
	
	private double price;
	private double growthFactor;
	
	private int inMarket, exportTo, importFrom;
	
	private ArrayList<Double> prices;
	
	public MarketResource(String name, double p, int worldBase) {
		this.name = name;
		
		price = p;
		
		prices = new ArrayList<>();
		prices.add(p);
		prices.add(p);
		
		growthFactor = 1;
	}

	public void tick() {
		prices.add(price);
		
//		System.out.println(exportTo + " " + importFrom + " " + name);
		
		price *= growthFactor;
	}
	
	public double getPrice() {
		return price;
	}
	
	public double getGrowthFactor() {
        return growthFactor;
    }
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Double> getAllPrices() {
		return prices;
	}

	public int getExportTo() {
		return exportTo;
	}

	public void setExportTo(int exportTo) {
		this.exportTo = exportTo;
	}

	public int getImportFrom() {
		return importFrom;
	}

	public void setImportFrom(int importFrom) {
		this.importFrom = importFrom;
	}
	
}

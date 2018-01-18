package com.simaflux.spacetrade.game;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.simaflux.spacetrade.empires.EmpireName;
import com.simaflux.spacetrade.utils.FileHandler;
import com.simaflux.spacetrade.utils.Maths;

public class NameManager {
	
	private static final String 
		PLANET_LOCATION = "data/names/planets.txt", 
		EMPIRE_LOCATION = "data/names/empires.txt", 
		STAR_LOCATION = "data/names/stars.txt";
	
	private final Map<String, EmpireName> empireNames;
	private final ArrayList<String> planetNames;
	private final String starName;
	
	public NameManager(int planetNum) {
		empireNames = new HashMap<String, EmpireName>();
		planetNames = new ArrayList<>();
		
		/*
		 * PLANET/EMPIRE NAMING
		 */
		File planetFile = new File(PLANET_LOCATION);
		File empireFile = new File(EMPIRE_LOCATION);
		
		String[] planetNaming = FileHandler.read(planetFile);
		String[] empireNaming = FileHandler.read(empireFile);
		
		for(int i = 0; i < planetNum; i++) {
			String planetName;
			String naming;
			
			do {
				naming = planetNaming[(int) (Maths.random() * FileHandler.getLines(planetFile))];
				planetName = naming.substring(0, naming.indexOf(';'));
			} while(planetNames.contains(planetName));
			
			planetNames.add(planetName);
			
			String adjective = naming.substring(naming.indexOf(';') + 1, naming.indexOf(':'));
			String people = naming.substring(naming.indexOf(':') + 1, naming.length());
			
			String name = empireNaming[(int) (Math.random() * empireNaming.length)];
			
			if(name.contains("0")) {
				name = name.replace("0", planetName);
			} else if(name.contains("1")) {
				name = name.replace("1", adjective);
			} else if(name.contains("2")) {
				name = name.replace("2", people);
			}	
			
			empireNames.put(name, new EmpireName(name, planetName, adjective, people));
		}
		
		/*
		 * STAR NAMING
		 */
		File starFile = new File(STAR_LOCATION);
		starName = FileHandler.read(starFile)[(int) (Maths.random() * FileHandler.getLines(starFile))];
	}
	
	public EmpireName getEmpireNaming(String empireName) {
		return empireNames.get(empireName);
	}

	public String getEmpireName(String planet) {
		for(String s : empireNames.keySet()) {
			if(planet.equals(empireNames.get(s).getPlanet())) {
				return s;
			}
		}
		
		return null;
	}
	
	public String getPlanetName(int n) {
		return planetNames.get(n);
	}
	
	public String getStarName() {
		return starName;
	}

}

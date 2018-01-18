package com.simaflux.spacetrade.empires;

public class EmpireName {
	
	private final String name, planet, adjective, people;

	public EmpireName(String name, String planet, String adjective, String people) {
		this.name = name;
		this.planet = planet;
		this.adjective = adjective;
		this.people = people;
	}

	public String getName() {
		return name;
	}

	public String getPlanet() {
		return planet;
	}

	public String getAdjective() {
		return adjective;
	}

	public String getPeople() {
		return people;
	}
	
}
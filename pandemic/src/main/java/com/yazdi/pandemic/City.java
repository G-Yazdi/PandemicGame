package com.yazdi.pandemic;

import java.util.ArrayList;

/**
 *
 *
 */
public class City 
{
	private ArrayList<City> neighbours;
	private ArrayList<InfectionCube> infectionCubes;
	private String name;
	private Boolean hasResearchStation;
	
	public City(String name) {
		
		this.setName(name);
		this.setHasResearchStation(false);
		this.neighbours = new ArrayList<City>();
		this.infectionCubes = new ArrayList<InfectionCube>();
	}
	
	public ArrayList<City> getNeighbours() {
		return this.neighbours;
	}
	
	public void addNeighbour(City city) {
		this.neighbours.add(city);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<InfectionCube> getInfectionCubes() {
		return infectionCubes;
	}

	public void addInfectionCube(InfectionCube infectionCube) {
		this.infectionCubes.add(infectionCube);
	}

	public Boolean getHasResearchStation() {
		return hasResearchStation;
	}

	public void setHasResearchStation(Boolean hasResearchStation) {
		this.hasResearchStation = hasResearchStation;
	}
	
    
}

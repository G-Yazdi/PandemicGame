package com.yazdi.pandemic;

import java.util.ArrayList;

/**
 *
 *
 */
public class City 
{
	private ArrayList<City> neighbours = new ArrayList<City>();
	private ArrayList<InfectionCube> infectionCubes = new ArrayList<InfectionCube>();
	private String name;
	
	public City(String name) {
		this.setName(name);
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
	
    
}

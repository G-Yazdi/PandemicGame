package com.yazdi.pandemic;

import java.util.ArrayList;

/**
 *
 *
 */
public class City 
{
	ArrayList<City> neighbours = new ArrayList<City>();
	String name;
	
	public City(String name) {
		this.name = name;
	}
	
	public ArrayList<City> getNeighbours() {
		return this.neighbours;
	}
	
	public void addNeighbour(City city) {
		this.neighbours.add(city);
	}
	
    
}

package com.yazdi.pandemic.model;


import java.util.List;

import com.yazdi.pandemic.utils.CustomArrayList;

/**
 *
 *
 */
public class City 
{
	private CustomArrayList<City> neighbours;
	private CustomArrayList<InfectionCube> infectionCubes;
	private String name;
	private boolean hasResearchStation;
	
	public City(String name) {
		
		this.setName(name);
		this.setHasResearchStation(false);
		this.neighbours = new CustomArrayList<City>();
		this.infectionCubes = new CustomArrayList<InfectionCube>();
	}
	
	public CustomArrayList<City> getNeighbours() {
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

	public CustomArrayList<InfectionCube> getInfectionCubes() {
		return infectionCubes;
	}

	public void addInfectionCube(InfectionCube infectionCube) {
		this.infectionCubes.add(infectionCube);
	}
	public void removeInfectionCube(String diseaseName) {
		this.infectionCubes.removeIfCustom(cube->cube.getDisease().getName() == diseaseName);
	}
	public void removeAllInfectionCube(String diseaseName) {
		this.infectionCubes.removeAllIfCustom(cube->cube.getDisease().getName() == diseaseName);
	}
	public List<InfectionCube> getInfectionCubes(String diseaseName){
		return this.getInfectionCubes().stream().filter(
				i->i.getDisease().getName() == diseaseName).toList();
	}

	public Boolean getHasResearchStation() {
		return hasResearchStation;
	}

	public void setHasResearchStation(Boolean hasResearchStation) {
		this.hasResearchStation = hasResearchStation;
	}
	
    
}

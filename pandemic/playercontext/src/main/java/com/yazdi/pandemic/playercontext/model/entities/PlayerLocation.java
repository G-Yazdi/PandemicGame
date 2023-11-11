package com.yazdi.pandemic.playercontext.model.entities;

import com.yazdi.pandemic.playercontext.model.utils.CustomArrayList;

public class PlayerLocation 
{
	private CustomArrayList<PlayerLocation> neighbours;
	private CustomArrayList<Cube> cubes;
	private String name;
	private boolean hasResearchStation;
	
	public PlayerLocation(String name) {
		
		this.setName(name);
		this.setHasResearchStation(false);
		this.neighbours = new CustomArrayList<PlayerLocation>();
		this.cubes = new CustomArrayList<Cube>();
	}
	
	public CustomArrayList<PlayerLocation> getNeighbours() {
		return this.neighbours;
	}
	
	public void addNeighbour(PlayerLocation city) {
		this.neighbours.add(city);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CustomArrayList<Cube> getInfectionCubes() { 
		return this.cubes; 
	}
	public void addCube(Cube cube) {
	  this.cubes.add(cube); 
	} 
	public void removeCube(String diseaseName) {
	  this.cubes.removeIfCustom(cube->cube.getDisease().getName() ==
	  diseaseName); 
	} 
	public void removeAllInfectionCube(String diseaseName) {
	  this.cubes.removeAllIfCustom(cube->cube.getDisease().getName() ==
	  diseaseName); 
	} 
	public CustomArrayList<Cube> getCubes(Disease disease){ 
		return 
			this.cubes.findElementsIfCustom(cube -> cube.getDisease().getName() == disease.getName());
	}
	 

	public Boolean getHasResearchStation() {
		return hasResearchStation;
	}

	public void setHasResearchStation(Boolean hasResearchStation) {
		this.hasResearchStation = hasResearchStation;
	}
    
}


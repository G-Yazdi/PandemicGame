package com.yazdi.pandemic.playercontext.model.entities;

import java.util.concurrent.atomic.AtomicInteger;

import com.yazdi.pandemic.sharedkernel.utils.CustomArrayList;

public class PlayerLocation 
{
	private int id;
	private static AtomicInteger uniqueId=new AtomicInteger();
	private CustomArrayList<Integer> neighbours;
	private CustomArrayList<Cube> cubes;
	private String name;
	private boolean hasResearchStation;
	
	public PlayerLocation(String name) {
		this.id = uniqueId.getAndIncrement();
		this.setName(name);
		this.setHasResearchStation(false);
		this.neighbours = new CustomArrayList<Integer>();
		this.cubes = new CustomArrayList<Cube>();
	}
	
	public CustomArrayList<Integer> getNeighbours() {
		return this.neighbours;
	}
	
	public void addNeighbour(int cityId) {
		this.neighbours.add(cityId);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public CustomArrayList<Cube> getCubes() {
		return this.cubes;
	}

	public void setCubes(CustomArrayList<Cube> cubes) {
		this.cubes = cubes;
	}
    
}
package com.yazdi.pandemic.service;

import java.util.List;

import com.yazdi.pandemic.model.City;
import com.yazdi.pandemic.model.InfectionCube;
import com.yazdi.pandemic.utils.CustomArrayList;

public interface CityService {
	
	public CustomArrayList<City> getNeighbours();
	public void addNeighbour(City city);
	public String getName();
	public void setName(String name);
	public CustomArrayList<InfectionCube> getInfectionCubes();
	public void addInfectionCube(InfectionCube infectionCube);
	public void removeInfectionCube(String diseaseName);
	public void removeAllInfectionCube(String diseaseName);
	public List<InfectionCube> getInfectionCubes(String diseaseName);
	public Boolean getHasResearchStation();
	public void setHasResearchStation(Boolean hasResearchStation);

}

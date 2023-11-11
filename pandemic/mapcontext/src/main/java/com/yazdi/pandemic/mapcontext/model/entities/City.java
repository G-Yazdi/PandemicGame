package com.yazdi.pandemic.mapcontext.model.entities;

import com.yazdi.pandemic.mapcontext.utils.CustomArrayList;

public class City {
	private String name;
	private CustomArrayList<City> neighbours;
	private boolean hasResearchStation;
	
	public City(String name) {
		this.name = name;
	}

	public boolean isHasResearchStation() {
		return hasResearchStation;
	}

	public void setHasResearchStation(boolean hasResearchStation) {
		this.hasResearchStation = hasResearchStation;
	}
	

}

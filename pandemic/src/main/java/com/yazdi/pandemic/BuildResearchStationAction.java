package com.yazdi.pandemic;

public class BuildResearchStationAction implements Action {
	
	private City city;
	
	public BuildResearchStationAction(City city) {
		this.city = city;
	}

	@Override
	public void act() {
		this.city.setHasResearchStation(true);
	}

}

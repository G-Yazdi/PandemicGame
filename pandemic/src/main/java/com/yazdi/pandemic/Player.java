package com.yazdi.pandemic;

public class Player {
	
	private City currentLocation;

	public City getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(City currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	public void act(Action action) {
		action.act();
	}
	
	
}

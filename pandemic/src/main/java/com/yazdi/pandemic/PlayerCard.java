package com.yazdi.pandemic;

public class PlayerCard implements Card{
	
	private String cityName;
	private String diseaseName;
	
	
	public PlayerCard(String cityName, String diseaseName) {
		this.setCity(cityName);
		this.setDisease(diseaseName);
	}
	public String getDisease() {
		return diseaseName;
	}
	public void setDisease(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public String getCity() {
		return cityName;
	}
	public void setCity(String cityName) {
		this.cityName = cityName;
	}
	@Override
	public void discard() {
		// TODO Auto-generated method stub
		
	}

}

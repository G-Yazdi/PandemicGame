package com.yazdi.pandemic.playercontext.model;

public class PlayerCard implements Card{
	
	private String cityName;
	private String diseaseName;
	
	
	public PlayerCard(String cityName, String diseaseName) {
		this.setCityName(cityName);
		this.setDiseaseName(diseaseName);
	}
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}



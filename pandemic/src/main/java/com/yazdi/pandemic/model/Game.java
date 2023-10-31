package com.yazdi.pandemic.model;

import com.yazdi.pandemic.utils.CustomArrayList;

public class Game {
	
	private CustomArrayList<City> cities;
	private CustomArrayList<Disease> diseases;
	private CustomArrayList<Card> discardedPlayerCards;
	
	public Game() {
		this.cities = new CustomArrayList<City>();
		this.diseases = new CustomArrayList<Disease>();
		this.discardedPlayerCards = new CustomArrayList<Card>();
	}

	public CustomArrayList<City> getCities() {
		return cities;
	}

	public void setCities(CustomArrayList<City> cities) {
		this.cities = cities;
	}
	
	public void addCity(City city) {
		this.cities.add(city);
	}

	public CustomArrayList<Disease> getDiseases() {
		return diseases;
	}

	public void setDiseases(CustomArrayList<Disease> diseases) {
		this.diseases = diseases;
	}
	public void addDisease(Disease disease) {
		this.diseases.add(disease);
	}

	public CustomArrayList<Card> getDiscardedPlayerCards() {
		return discardedPlayerCards;
	}

	public void setDiscardedPlayerCards(CustomArrayList<Card> discardedPlayerCards) {
		this.discardedPlayerCards = discardedPlayerCards;
	}
	public void addToDiscardedPlayerCards(Card playerCard) {
		this.discardedPlayerCards.add(playerCard);
	}

	
	

}

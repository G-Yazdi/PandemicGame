package com.yazdi.pandemic;

import java.util.ArrayList;

public class Player {
	
	private City currentLocation;
	private ArrayList<Card> hand;
	
	public Player() {
		this.hand = new ArrayList<Card>();
	}

	public City getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(City currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	public void act(Action action) {
		action.act();
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> playerCards) {
		this.hand = playerCards;
	}
	
	public void addToHand(Card playerCard) {
		this.hand.add(playerCard);
	}
	
	
}

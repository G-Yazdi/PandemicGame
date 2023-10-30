package com.yazdi.pandemic;


import com.yazdi.pandemic.utils.CustomArrayList;

public class Player {
	
	private City currentLocation;
	private CustomArrayList<Card> hand;
	private Role role;
	
	public Player(Role role) {
		this.hand = new CustomArrayList<Card>();
		this.role = role;
	}
	public Player() {
		this.hand = new CustomArrayList<Card>();
	}

	public City getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(City currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	public void act(Action action) {
		
		if(this.role != null && this.role.getAction().getType() == action.getType())
			this.role.getAction().act();
		else
			action.act();
	}

	public CustomArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(CustomArrayList<Card> playerCards) {
		this.hand = playerCards;
	}
	
	public void addToHand(Card playerCard) {
		this.hand.add(playerCard);
	}
	public void removeFromHand(Card playerCard) {
		this.hand.remove(playerCard);
	}
	public Card removeFromHandByCity(String cityName) {
		
		Card removedCard = this.hand.removeIfCustom(card->((PlayerCard) card).getCityName() == cityName);
		return removedCard;
	
	}

	public Role getRole() {
		return role;
	}
	
	
}

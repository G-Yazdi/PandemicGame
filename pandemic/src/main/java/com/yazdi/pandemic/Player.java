package com.yazdi.pandemic;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	public void removeFromHand(Card playerCard) {
		this.hand.remove(playerCard);
	}
	public Card removeFromHandByCity(String cityName) {
		Iterator<Card> cardIterator = this.hand.iterator();
		Card removedCard = null;
		while(cardIterator.hasNext()) {
				Card tempCard = cardIterator.next();
				if(((PlayerCard) tempCard).getCityName() == cityName) {
					removeFromHand(tempCard);
					removedCard = tempCard;
					break;
				}		
		}
		return removedCard;
	
	}
	
	
}

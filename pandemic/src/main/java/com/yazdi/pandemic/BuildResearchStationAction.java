package com.yazdi.pandemic;

import java.util.ArrayList;
import java.util.Iterator;

public class BuildResearchStationAction implements Action {
	
	private City city;
	private ArrayList<Card> playerHand;
	private ArrayList<Card> discardedPlayerCards;
	
	public BuildResearchStationAction(ArrayList<Card> playerHand, City city, ArrayList<Card> discardedPlayerCards) {
		
		Iterator<Card> cardIterator = playerHand.iterator();
		while(cardIterator.hasNext()) {
			Card card = cardIterator.next();
			if(((PlayerCard) card).getCityName() == city.getName()) {
				this.city = city;
				this.playerHand = playerHand;
				this.discardedPlayerCards = discardedPlayerCards;
				return;
			}	
		}
		throw new RuntimeException("Illegal build request!");
		
	}
	
	private Card removeFromHand() {
		Iterator<Card> cardIterator = playerHand.iterator();
		Card removedCard = null;
		while(cardIterator.hasNext()) {
				Card tempCard = cardIterator.next();
				if(((PlayerCard) tempCard).getCityName() == city.getName()) {
					playerHand.remove(tempCard);
					removedCard = tempCard;
					break;
				}		
		}
		return removedCard;
	
	}
	@Override
	public void act() {
		Card removedCard = removeFromHand();
		removedCard.discard(discardedPlayerCards);
		this.city.setHasResearchStation(true);
	}

}

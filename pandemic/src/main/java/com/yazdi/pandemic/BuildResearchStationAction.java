package com.yazdi.pandemic;

import java.util.ArrayList;

import com.yazdi.pandemic.utils.CustomArrayList;

public class BuildResearchStationAction implements Action {
	
	private City city;
	private ArrayList<Card> playerHand;
	private ArrayList<Card> discardedPlayerCards;
	
	public BuildResearchStationAction(ArrayList<Card> playerHand, City city, ArrayList<Card> discardedPlayerCards) {
		
		if(((CustomArrayList<Card>) playerHand).findIfCustom(c-> ((PlayerCard) c).getCityName() == city.getName()) != null) {
			this.city = city;
			this.playerHand = playerHand;
			this.discardedPlayerCards = discardedPlayerCards;
			return;
		}
		throw new RuntimeException("Illegal build request!");
		
	}
	@Override
	public void act() {
		Card removedCard = ((CustomArrayList<Card>) playerHand).removeIfCustom(c-> ((PlayerCard) c).getCityName() == city.getName());
		removedCard.discard(discardedPlayerCards);
		this.city.setHasResearchStation(true);
	}

}

package com.yazdi.pandemic;


import com.yazdi.pandemic.utils.CustomArrayList;

public class BuildResearchStationAction implements Action {
	
	private City city;
	private CustomArrayList<Card> playerHand;
	private CustomArrayList<Card> discardedPlayerCards;
	
	public BuildResearchStationAction(CustomArrayList<Card> playerHand, City city, CustomArrayList<Card> discardedPlayerCards) {
		
		if(playerHand.findIfCustom(c-> ((PlayerCard) c).getCityName() == city.getName()) != null) {
			this.city = city;
			this.playerHand = playerHand;
			this.discardedPlayerCards = discardedPlayerCards;
		}
		else throw new RuntimeException("Illegal build request!");
		
	}
	@Override
	public void act() {
		Card removedCard = playerHand.removeIfCustom(c-> ((PlayerCard) c).getCityName() == city.getName());
		removedCard.discard(discardedPlayerCards);
		this.city.setHasResearchStation(true);
	}

}

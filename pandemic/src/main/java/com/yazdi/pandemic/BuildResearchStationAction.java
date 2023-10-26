package com.yazdi.pandemic;

import java.util.Iterator;

public class BuildResearchStationAction implements Action {
	
	private City city;
	private Player player;
	
	public BuildResearchStationAction(Player player, City city) {
		
		Iterator<Card> cardIterator = player.getHand().iterator();
		while(cardIterator.hasNext()) {
			PlayerCard card = (PlayerCard)cardIterator.next();
			if(card.getCity() == city.getName()) {
				this.city = city;
				this.player = player;
				return;
			}	
		}
		throw new RuntimeException("Illegal build request!");
		
	}

	@Override
	public void act() {
		this.city.setHasResearchStation(true);
	}

}

package com.yazdi.pandemic;


import com.yazdi.pandemic.utils.CustomArrayList;

public class BuildResearchStationAction extends Action {
	
	private Player player;
	private CustomArrayList<Card> discardedPlayerCards;
	
	public BuildResearchStationAction(Player player, CustomArrayList<Card> discardedPlayerCards) {
		super(ActionType.Build);
		if(player.getHand().findIfCustom(c-> ((PlayerCard) c).getCityName() == player.getCurrentLocation().getName()) != null) {
			
			this.player = player;
			this.discardedPlayerCards = discardedPlayerCards;
			
		}
		else throw new RuntimeException("Illegal build request: The player has no card whose city is the one that he is located on!");
		
	}
	@Override
	public void perform() {
		Card removedCard = player.getHand().removeIfCustom(c-> ((PlayerCard) c).getCityName() == player.getCurrentLocation().getName());
		removedCard.discard(discardedPlayerCards);
		player.getCurrentLocation().setHasResearchStation(true);
	}

}

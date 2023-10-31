package com.yazdi.pandemic;


import com.yazdi.pandemic.utils.CustomArrayList;

public class BuildResearchStationAction extends Action {
	
	private Player player;
	private CustomArrayList<Card> discardedPlayerCards;
	
	public BuildResearchStationAction(Player player, CustomArrayList<Card> discardedPlayerCards) {
		super(ActionType.Build);
		this.player = player;
		this.discardedPlayerCards = discardedPlayerCards;
	}
	@Override
	public void perform() {
		validate();
		Card removedCard = player.getHand().removeIfCustom(c-> ((PlayerCard) c).getCityName() == player.getCurrentLocation().getName());
		removedCard.discard(discardedPlayerCards);
		player.getCurrentLocation().setHasResearchStation(true);
	}
	@Override
	protected void validate() {
		if(player.getHand().findIfCustom(c-> ((PlayerCard) c).getCityName() == player.getCurrentLocation().getName()) == null) {
			throw new RuntimeException("Illegal build request: The player has no card whose city is the one that he is located on!");
		}
		
	}

}

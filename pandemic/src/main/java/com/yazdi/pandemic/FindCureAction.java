package com.yazdi.pandemic;

import com.yazdi.pandemic.utils.CustomArrayList;

public class FindCureAction implements Action {
	
	private Disease disease;
	private CustomArrayList<Disease> diseases;
	private Player player;
	private CustomArrayList<Card> pileOfDiscarded;
	
	public FindCureAction(Disease disease, Game game, Player player) {
		
		CustomArrayList<Card> playerCardsOfSameDisease = player.getHand()
				.findElementsIfCustom(c-> ((PlayerCard) c).getDiseaseName() == disease.getName());
		if(playerCardsOfSameDisease.size() < 5) {
			throw new RuntimeException("Illegal find cure request: There is not enough player cards of the same disease in the player's hand!");		
		}
		if(!player.getCurrentLocation().getHasResearchStation()) {
			throw new RuntimeException("Illegal find cure request: There is no research station in the player's city!");
		}
		this.disease = disease;
		this.diseases = game.getDiseases();
		this.player = player;
		this.pileOfDiscarded = game.getDiscardedPlayerCards();
	}

	@Override
	public void act() {
		Disease disease = this.diseases.findIfCustom(d->d.getName() == this.disease.getName());
		if(disease != null) {
			disease.setHasCure(true);
			CustomArrayList<Card> removedCards = player.getHand().removeNElementsIfCustom(c-> ((PlayerCard) c).getDiseaseName() == disease.getName(), 5);
			this.pileOfDiscarded.addAll(removedCards);
			
		}
		
	}

}

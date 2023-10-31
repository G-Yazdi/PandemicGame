package com.yazdi.pandemic.model;

import com.yazdi.pandemic.utils.CustomArrayList;

public class ScientistFindCureAction extends Action{
	
	private Disease disease;
	private Player player;
	private Game game;

	public ScientistFindCureAction(Disease disease, Game game, Player player) {
		super(ActionType.FindCure);
		this.disease = disease;
		this.game = game;
		this.player = player;
	}

	@Override
	protected void validate() {
		CustomArrayList<Card> playerCardsOfSameDisease = player.getHand()
				.findElementsIfCustom(c-> ((PlayerCard) c).getDiseaseName() == disease.getName());
		if(playerCardsOfSameDisease.size() < 4) {
			throw new RuntimeException("Illegal find cure request: There is not enough player cards of the same disease in the player's hand!");		
		}
		if(!player.getCurrentLocation().getHasResearchStation()) {
			throw new RuntimeException("Illegal find cure request: There is no research station in the player's city!");
		}
	}

	@Override
	public void perform() {
		validate();
		Disease disease = game.getDiseases().findIfCustom(d->d.getName() == this.disease.getName());
		if(disease != null) {
			disease.setHasCure(true);
			CustomArrayList<Card> removedCards = player.getHand().removeNElementsIfCustom(c-> ((PlayerCard) c).getDiseaseName() == disease.getName(), 5);
			game.getDiscardedPlayerCards().addAll(removedCards);
			
		}
	}

}

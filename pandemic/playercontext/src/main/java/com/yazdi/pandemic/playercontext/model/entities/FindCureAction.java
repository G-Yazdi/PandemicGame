package com.yazdi.pandemic.playercontext.model.entities;

import com.yazdi.pandemic.playercontext.model.contracts.Action;
import com.yazdi.pandemic.playercontext.model.contracts.Card;
import com.yazdi.pandemic.playercontext.model.enums.ActionType;
import com.yazdi.pandemic.sharedkernel.utils.CustomArrayList;

public class FindCureAction extends Action {
	
	private Player player;
	private Disease disease;
	
	public FindCureAction(Player player, Disease disease) {
		super(ActionType.FindCure);
		this.disease = disease;
		this.player = player;
	}

	@Override
	public void perform() {
		validate();
		player.getCurrentLocation().getCubes(disease).forEach(c->((Cube) c).getDisease().setHasCure(true));
		player.getHand().removeNElementsIfCustom(c-> ((PlayerCard) c).getDiseaseName() == disease.getName(), 5);
			
	}

	@Override
	protected void validate() {
		CustomArrayList<Card> playerCardsOfSameDisease = player.getHand()
				.findElementsIfCustom(c-> ((PlayerCard) c).getDiseaseName() == disease.getName());
		if(playerCardsOfSameDisease.size() < 5) {
			throw new RuntimeException("Illegal find cure request: There is not enough player cards of the same disease in the player's hand!");		
		}
		if(!player.getCurrentLocation().getHasResearchStation()) {
			throw new RuntimeException("Illegal find cure request: There is no research station in the player's city!");
		}
		
	}

}

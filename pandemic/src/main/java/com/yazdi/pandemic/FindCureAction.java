package com.yazdi.pandemic;

import com.yazdi.pandemic.utils.CustomArrayList;

public class FindCureAction implements Action {
	
	private Disease disease;
	private CustomArrayList<Disease> diseases;
	
	public FindCureAction(Disease disease, CustomArrayList<Disease> diseases, Player player) {
		
		CustomArrayList<Card> playerCardsWithSameDisease = player.getHand()
				.findElementsIfCustom(c-> ((PlayerCard) c).getDiseaseName() == disease.getName());
		if(playerCardsWithSameDisease.size() < 5) {
			throw new RuntimeException("Illegal find cure request: There is not enough player cards with the same disease in the player's hand!");
			
		}
		this.disease = disease;
		this.diseases = diseases;;
		
		
	}

	@Override
	public void act() {
		Disease disease = this.diseases.findIfCustom(d->d.getName() == this.disease.getName());
		disease.setHasCure(true);
	}

}

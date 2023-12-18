package com.yazdi.pandemic.playercontext.model.contracts;

import com.yazdi.pandemic.playercontext.model.entities.Cube;
import com.yazdi.pandemic.playercontext.model.entities.Disease;
import com.yazdi.pandemic.playercontext.model.entities.Player;
import com.yazdi.pandemic.playercontext.model.entities.PlayerCard;
import com.yazdi.pandemic.playercontext.model.enums.ActionType;
import com.yazdi.pandemic.sharedkernel.utils.CustomArrayList;

public abstract class AbstractFindCureAction extends Action {

	private Disease disease;
	private Player player;
	private int validDiseaseCardsCounts;

	public AbstractFindCureAction(Player player, Disease disease, int validDiseaseCardsCounts) {
		super(ActionType.FindCure);
		this.disease = disease;
		this.player = player;
		this.validDiseaseCardsCounts = validDiseaseCardsCounts;
	}
	public void validate() {
        CustomArrayList<Card> playerCardsOfSameDisease = player.getHand()
                .findElementsIfCustom(c-> ((PlayerCard) c).getDiseaseName() == disease.getName());
        if(playerCardsOfSameDisease.size() < this.validDiseaseCardsCounts) {
            throw new RuntimeException("Illegal find cure request: There is not enough player cards of the same disease in the player's hand!");        
        }
        if(!player.getCurrentLocation().getHasResearchStation()) {
            throw new RuntimeException("Illegal find cure request: There is no research station in the player's city!");
        }
    }
	public void perform() {
		validate();
		player.getCurrentLocation().getCubes(disease).forEach(c->((Cube) c).getDisease().setHasCure(true));
		player.getHand().removeNElementsIfCustom(c-> ((PlayerCard) c).getDiseaseName() == disease.getName(), validDiseaseCardsCounts);			
	}
	

}

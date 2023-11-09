package com.yazdi.pandemic.playercontext.model;

public class ScientistFindCureAction extends Action{
	
	private Disease disease;
	private Player player;

	public ScientistFindCureAction(Player player, Disease disease) {
		super(ActionType.FindCure);
		this.disease = disease;
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
		disease.setHasCure(true);
		player.getHand().removeNElementsIfCustom(c-> ((PlayerCard) c).getDiseaseName() == disease.getName(), 4);			
	}
}



package com.yazdi.pandemic;

import com.yazdi.pandemic.utils.CustomArrayList;

public class Scientist extends Action implements Role {
	
	private Player player;
	private Disease disease;
	private Game game;
	

	public Scientist() {
		super(ActionType.FindCure);
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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
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

}

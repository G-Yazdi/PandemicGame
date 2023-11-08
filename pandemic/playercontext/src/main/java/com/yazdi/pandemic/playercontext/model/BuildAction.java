package com.yazdi.pandemic.playercontext.model;

public class BuildAction extends Action {
	
	private Player player;
	
	public BuildAction(Player player) {
		super(ActionType.Build);
		this.player = player;
	}
	@Override
	public void perform() {
		validate();
		player.getHand().removeIfCustom(c-> ((PlayerCard) c).getCityName() == player.getCurrentLocation().getName());
		player.getCurrentLocation().setHasResearchStation(true);
	}
	@Override
	protected void validate() {
		if(player.getHand().findIfCustom(c-> ((PlayerCard) c).getCityName() == player.getCurrentLocation().getName()) == null) {
			throw new RuntimeException("Illegal build request: The player has no card whose city is the one that he is located on!");
		}
		
	}

}


package com.yazdi.pandemic;

import com.yazdi.pandemic.utils.CustomArrayList;

public class BuildResearchStationCommand implements Command {

	private Action action;
	
	public BuildResearchStationCommand(Player player, CustomArrayList<Card> discardedPlayerCards) {
		if(player.getRole()!= null 
				&& ((Action) player.getRole()).getType() == ActionType.Build) {
			action = (Action) player.getRole();
			((Expert) action).setPlayer(player);		
		}
		else
			this.action = new BuildResearchStationAction(player, discardedPlayerCards);
	}

	@Override
	public void execute() {
		this.action.perform();

	}

}

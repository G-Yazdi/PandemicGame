package com.yazdi.pandemic.model;


public class FindCureCommand implements Command {
	
	private Action action;
	
	public FindCureCommand(Disease disease, Game game, Player player) {
		if(player.getRole()!= null 
				&& player.getRole().getType() == ActionType.FindCure) {
			action = new ScientistFindCureAction(disease, game, player);
			
		}
		else
			action = new FindCureAction(disease, game, player);
	}

	@Override
	public void execute() {
		action.perform();

	}

}

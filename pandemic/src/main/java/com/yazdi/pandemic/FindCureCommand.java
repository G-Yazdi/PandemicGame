package com.yazdi.pandemic;


public class FindCureCommand implements Command {
	
	private Action action;
	
	public FindCureCommand(Disease disease, Game game, Player player) {
		if(player.getRole()!= null 
				&& ((Action) player.getRole()).getType() == ActionType.FindCure) {
			action = (Action) player.getRole();
			((Scientist) action).setDisease(disease);
			((Scientist) action).setGame(game);
			((Scientist) action).setPlayer(player);
			
		}
		else
			action = new FindCureAction(disease, game, player);
	}

	@Override
	public void execute() {
		action.perform();

	}

}

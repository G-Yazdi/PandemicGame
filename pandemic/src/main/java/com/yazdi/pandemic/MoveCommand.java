package com.yazdi.pandemic;

public class MoveCommand implements Command {
	
	private Action action;
	
	public MoveCommand(Player player, City destination) {
		if(player.getRole()!= null 
				&& ((Action) player.getRole()).getType() == ActionType.Move) {
			action = (Action) player.getRole();
			((Globetrotter) action).setPlayer(player);
			((Globetrotter) action).setDestination(destination);
		}
		else
			this.action = new MoveAction(player, destination);
	}

	@Override
	public void execute() {
		this.action.perform();

	}

}

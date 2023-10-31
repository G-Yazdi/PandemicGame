package com.yazdi.pandemic;

public class MoveCommand implements Command {
	
	private Action action;
	
	public MoveCommand(Player player, City destination) {
		if(player.getRole()!= null && player.getRole().getType() == ActionType.Move) {
			action = new GlobetrotterMoveAction(player, destination);
		}
		else
			this.action = new MoveAction(player, destination);
	}

	@Override
	public void execute() {
		this.action.perform();

	}

}

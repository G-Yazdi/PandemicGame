package com.yazdi.pandemic;

public abstract class Action {
	
	private ActionType type;
	public abstract void perform();
	
	
	public ActionType getType() {
		return type;
	}
	public void setType(ActionType type) {
		this.type = type;
	}

}

package com.yazdi.pandemic;

public abstract class Role {
	
	private Action action;
	
	public Role(Action action) {
		this.setAction(action);
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

}

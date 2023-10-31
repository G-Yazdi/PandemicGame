package com.yazdi.pandemic.model;

public abstract class Role {
	
	private ActionType type;
	
	public Role(ActionType type) {
		this.setType(type);
	}
	public ActionType getType() {
		return type;
	}
	public void setType(ActionType type) {
		this.type = type;
	}

}

package com.yazdi.pandemic.playercontext.model;

public class Disease {
	
	private String name;
	private boolean hasCure;
	
	public Disease(String name, boolean hasCure) {
		this.setName(name);
		this.setHasCure(hasCure);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getHasCure() {
		return hasCure;
	}
	public void setHasCure(boolean hasCure) {
		this.hasCure = hasCure;
	}

}


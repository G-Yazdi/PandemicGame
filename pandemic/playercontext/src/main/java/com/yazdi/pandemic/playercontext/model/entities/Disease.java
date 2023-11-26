package com.yazdi.pandemic.playercontext.model.entities;

import com.yazdi.pandemic.sharedkernel.contracts.IDisease;

public class Disease implements IDisease {
	
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


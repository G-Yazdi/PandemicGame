package com.yazdi.pandemic.playercontext.model.entities;

import com.yazdi.pandemic.playercontext.model.contracts.Role;
import com.yazdi.pandemic.playercontext.model.enums.ActionType;

public class GlobetrotterRole extends Role {

	public GlobetrotterRole() {
		super(ActionType.Move);
	}
}

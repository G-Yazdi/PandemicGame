package com.yazdi.pandemic.playercontext.model.entities;

import com.yazdi.pandemic.playercontext.model.contracts.Role;
import com.yazdi.pandemic.playercontext.model.enums.ActionType;

public class DoctorRole extends Role {

	public DoctorRole() {
		super(ActionType.Treat);
	}

}

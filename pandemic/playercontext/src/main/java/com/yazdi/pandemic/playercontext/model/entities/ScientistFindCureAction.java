package com.yazdi.pandemic.playercontext.model.entities;

import com.yazdi.pandemic.playercontext.model.contracts.AbstractFindCureAction;
import com.yazdi.pandemic.playercontext.model.contracts.Action;
import com.yazdi.pandemic.playercontext.model.contracts.Card;
import com.yazdi.pandemic.playercontext.model.enums.ActionType;
import com.yazdi.pandemic.sharedkernel.utils.CustomArrayList;

public class ScientistFindCureAction extends AbstractFindCureAction{
	
	private static int validDiseaseCardsCounts = 4;
	
	public ScientistFindCureAction(Player player, Disease disease) {
		super(player, disease, validDiseaseCardsCounts);
	}
}



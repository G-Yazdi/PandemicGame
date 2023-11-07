package com.yazdi.pandemic.api;

import com.yazdi.pandemic.playercontext.model.City;
import com.yazdi.pandemic.playercontext.model.Player;
import com.yazdi.pandemic.playercontext.service.IPlayerService;

public class Api {
private IPlayerService playerService;
	
	
	public Api(IPlayerService playerService) {
		this.playerService = playerService;
	}
	
	public void movePlayerService(Player player, City destination) {
		 playerService.movePlayer(player, destination);
	}

}

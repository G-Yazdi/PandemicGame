package com.yazdi.pandemic.api;

import com.yazdi.pandemic.playercontext.model.City;
import com.yazdi.pandemic.playercontext.model.Disease;
import com.yazdi.pandemic.playercontext.model.Player;
import com.yazdi.pandemic.playercontext.service.IPlayerService;

public class PlayerServiceApi {
private IPlayerService playerService;
	
	
	public PlayerServiceApi(IPlayerService playerService) {
		this.playerService = playerService;
	}
	
	public void moveService(Player player, City destination) {
		 playerService.movePlayer(player, destination);
	}
	public void buildService(Player player) {
		 playerService.buildResearchStation(player);
	}

	public void findCureService(Player player, Disease disease) {
		playerService.findCure(player, disease);
		
	}

	public void treatDiseaseService(Player player, Disease disease) {
		playerService.treatDisease(player, disease);
		
	}

}

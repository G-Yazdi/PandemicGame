package com.yazdi.pandemic.playercontext.service;

import com.yazdi.pandemic.playercontext.model.City;
import com.yazdi.pandemic.playercontext.model.Player;
import com.yazdi.pandemic.playercontext.repository.PlayerRepository;
import com.yazdi.pandemic.sharedkernel.service.ApplicationService;

public interface IPlayerService extends ApplicationService {
	
    void movePlayer(Player player, City destination);
    void buildResearchStation(Player player);

	void setPlayerRepository(PlayerRepository playerRepository);
}

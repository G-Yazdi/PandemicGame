package com.yazdi.pandemic.playercontext.service;

import com.yazdi.pandemic.playercontext.model.City;
import com.yazdi.pandemic.playercontext.model.Disease;
import com.yazdi.pandemic.playercontext.model.Player;
import com.yazdi.pandemic.playercontext.repository.PlayerRepository;
import com.yazdi.pandemic.sharedkernel.service.ApplicationService;

public interface IPlayerService extends ApplicationService {
	
    public void movePlayer(Player player, City destination);
    public void buildResearchStation(Player player);
    public void findCure(Player player, Disease disease);
    

	public void setPlayerRepository(PlayerRepository playerRepository);
}

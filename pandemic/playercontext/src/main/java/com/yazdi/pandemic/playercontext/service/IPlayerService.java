package com.yazdi.pandemic.playercontext.service;

import com.yazdi.pandemic.playercontext.model.entities.PlayerLocation;
import com.yazdi.pandemic.playercontext.model.entities.Disease;
import com.yazdi.pandemic.playercontext.model.entities.Player;
import com.yazdi.pandemic.playercontext.repository.PlayerRepository;
import com.yazdi.pandemic.sharedkernel.service.ApplicationService;

public interface IPlayerService extends ApplicationService {
	
    public void movePlayer(Player player, PlayerLocation destination);
    public void buildResearchStation(Player player);
    public void findCure(Player player, Disease disease);
    

	public void setPlayerRepository(PlayerRepository playerRepository);
	public void treatDisease(Player player, Disease disease);
}

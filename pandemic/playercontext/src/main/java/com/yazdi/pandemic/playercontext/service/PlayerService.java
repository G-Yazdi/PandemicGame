package com.yazdi.pandemic.playercontext.service;

import com.yazdi.pandemic.playercontext.model.ActionType;

import java.util.HashMap;
import java.util.Map;

import com.yazdi.pandemic.playercontext.model.Action;
import com.yazdi.pandemic.playercontext.model.City;
import com.yazdi.pandemic.playercontext.model.GlobetrotterMoveAction;
import com.yazdi.pandemic.playercontext.model.MoveAction;
import com.yazdi.pandemic.playercontext.model.Player;
import com.yazdi.pandemic.playercontext.repository.PlayerRepository;
import com.yazdi.pandemic.sharedkernel.events.ApplicationEvent;
import com.yazdi.pandemic.sharedkernel.events.EventBus;

public class PlayerService implements IPlayerService {
    public static final String EVENT_Player_Moved = "PlayerMovedEvent";

    private PlayerRepository playerRepository;
    private EventBus eventBus;

    @Override
    public EventBus getEventBus() {
        return eventBus;
    }
    @Override
    public void setPlayerRepository(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

	@Override
	public void movePlayer(Player player, City destination) {
		if(player.getRole().getType() == ActionType.Move) {
			Action action = new GlobetrotterMoveAction(player, destination);
			action.perform();
		}
		else {
			Action action = new MoveAction(player, destination);
			action.perform();
		}
		this.playerRepository.updatePlayerLocation(player.getId(), destination);
		
	    Map<String, String> payload = new HashMap<>();
	    payload.put("player_id", String.valueOf(player.getId()));
	    ApplicationEvent event = new ApplicationEvent(payload) {
	    	@Override
	    	public String getType() {
	    		return EVENT_Player_Moved;
	    	}
	    };
	    this.eventBus.publish(event);
		
	}
}

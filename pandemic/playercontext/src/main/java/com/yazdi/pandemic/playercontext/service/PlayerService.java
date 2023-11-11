package com.yazdi.pandemic.playercontext.service;

import java.util.HashMap;
import java.util.Map;

import com.yazdi.pandemic.playercontext.model.contracts.Action;
import com.yazdi.pandemic.playercontext.model.entities.BuildAction;
import com.yazdi.pandemic.playercontext.model.entities.City;
import com.yazdi.pandemic.playercontext.model.entities.Disease;
import com.yazdi.pandemic.playercontext.model.entities.DoctorTreatAction;
import com.yazdi.pandemic.playercontext.model.entities.ExpertBuildAction;
import com.yazdi.pandemic.playercontext.model.entities.FindCureAction;
import com.yazdi.pandemic.playercontext.model.entities.GlobetrotterMoveAction;
import com.yazdi.pandemic.playercontext.model.entities.MoveAction;
import com.yazdi.pandemic.playercontext.model.entities.Player;
import com.yazdi.pandemic.playercontext.model.entities.ScientistFindCureAction;
import com.yazdi.pandemic.playercontext.model.entities.TreatAction;
import com.yazdi.pandemic.playercontext.model.enums.ActionType;
import com.yazdi.pandemic.playercontext.repository.PlayerRepository;
import com.yazdi.pandemic.sharedkernel.events.ApplicationEvent;
import com.yazdi.pandemic.sharedkernel.events.EventBus;

public class PlayerService implements IPlayerService {
    public static final String EVENT_Player_Moved = "PlayerMovedEvent";
    public static final String EVENT_Player_Built_A_Research_Station = "PlayerBuiltAResearchStationEvent";
    public static final String EVENT_Player_Found_A_Cure_For_A_Disease = "PlayerFoundACureForADiseaseEvent";
    public static final String EVENT_Player_Treated_A_Disease = "PlayerTreatedADiseaseEvent";

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
		Action action;
		if(player.getRole().getType() == ActionType.Move) {
			action = new GlobetrotterMoveAction(player, destination);
			action.perform();
		}
		else {
			action = new MoveAction(player, destination);
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
	@Override
	public void buildResearchStation(Player player) {
		Action action;
		
		if(player.getRole().getType() == ActionType.Build) {
			action = new ExpertBuildAction(player);
			action.perform();
		}
		else {
			action = new BuildAction(player);
			action.perform();
		}
		this.playerRepository.updatePlayerLocation(player.getId(), player.getCurrentLocation());
		
	    Map<String, String> payload = new HashMap<>();
	    payload.put("player_id", String.valueOf(player.getId()));
	    ApplicationEvent event = new ApplicationEvent(payload) {
	    	@Override
	    	public String getType() {
	    		return EVENT_Player_Built_A_Research_Station;
	    	}
	    };
	    this.eventBus.publish(event);
		
	}
	@Override
	public void findCure(Player player, Disease disease) {
		Action action;
		
		if(player.getRole().getType() == ActionType.FindCure) {
			action = new ScientistFindCureAction(player, disease);
			action.perform();
		}
		else {
			action = new FindCureAction(player, disease);
			action.perform();
		}
		this.playerRepository.updatePlayerHand(player.getId(), player.getHand());
		this.playerRepository.updatePlayerLocation(player.getId(), player.getCurrentLocation());
		
	    Map<String, String> payload = new HashMap<>();
	    payload.put("player_id", String.valueOf(player.getId()));
	    ApplicationEvent event = new ApplicationEvent(payload) {
	    	@Override
	    	public String getType() {
	    		return EVENT_Player_Found_A_Cure_For_A_Disease;
	    	}
	    };
	    this.eventBus.publish(event);
		
	}
	@Override
	public void treatDisease(Player player, Disease disease) {
		Action action;
		
		if(player.getRole().getType() == ActionType.Treat) {
			action = new DoctorTreatAction(player, disease);
			action.perform();
		}
		else {
			action = new TreatAction(player, disease);
			action.perform();
		}
		this.playerRepository.updatePlayerLocation(player.getId(), player.getCurrentLocation());
		
	    Map<String, String> payload = new HashMap<>();
	    payload.put("player_id", String.valueOf(player.getId()));
	    ApplicationEvent event = new ApplicationEvent(payload) {
	    	@Override
	    	public String getType() {
	    		return EVENT_Player_Treated_A_Disease;
	    	}
	    };
	    this.eventBus.publish(event);
		
	}
}

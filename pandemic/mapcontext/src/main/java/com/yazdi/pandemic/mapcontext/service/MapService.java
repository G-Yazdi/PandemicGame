package com.yazdi.pandemic.mapcontext.service;

import java.util.Optional;

import com.yazdi.pandemic.mapcontext.model.entities.City;
import com.yazdi.pandemic.mapcontext.model.entities.WorldMap;
import com.yazdi.pandemic.mapcontext.repository.MapRepository;
import com.yazdi.pandemic.sharedkernel.events.ApplicationEvent;
import com.yazdi.pandemic.sharedkernel.events.EventBus;
import com.yazdi.pandemic.sharedkernel.events.EventSubscriber;

public class MapService implements IMapService {
	
	private MapRepository mapRepository;
	private EventBus eventBus;
	private WorldMap map;
	
	@Override
    public void setMapRepository(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }
	@Override
	public EventBus getEventBus() {
		return this.eventBus;
	}

	@Override
	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
		
	}
	@Override
    public void setMap(WorldMap map) {
        this.map = map;
    }
	@Override
	public void updatePlayerLocationInMap(int playerId, City newLocation, WorldMap map) {
		City prevLocationInMap = (City) map.getCities().findIfCustom(city -> ((City) city).getPlayers().contains(Integer.valueOf(playerId)));
		prevLocationInMap.removePlayer(playerId);
		City cityInMap = (City) map.getCities().findIfCustom(city -> ((City) city).getId() == newLocation.getId());
		cityInMap.addPlayer(playerId);
		
	}

	@Override
	public void listenToPlayerEventService() {
		final String EVENT_Player_Moved = "PlayerMovedEvent";
		this.eventBus.subscribe(EVENT_Player_Moved, new EventSubscriber() {
			@Override
            public <E extends ApplicationEvent> void onEvent(E event) {
            	Optional<City> newLocation = mapRepository.findCityByPlayerId(Integer.parseInt(event.getPayloadValue("player_id")));
            	updatePlayerLocationInMap(Integer.parseInt(event.getPayloadValue("player_id")), newLocation.get(), map);
            }
        });
		
	}

}

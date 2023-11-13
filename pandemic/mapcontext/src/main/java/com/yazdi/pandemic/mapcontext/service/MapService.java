package com.yazdi.pandemic.mapcontext.service;

import com.yazdi.pandemic.mapcontext.model.entities.City;
import com.yazdi.pandemic.mapcontext.model.entities.WorldMap;
import com.yazdi.pandemic.mapcontext.repository.MapRepository;
import com.yazdi.pandemic.sharedkernel.events.EventBus;

public class MapService implements IMapService {
	
	private MapRepository mapRepository;
	
	@Override
    public void setMapRepository(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

	@Override
	public EventBus getEventBus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEventBus(EventBus eventBus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePlayerLocationInMap(int playerId, City newLocation, WorldMap map) {
		City prevLocationInMap = (City) map.getCities().findIfCustom(city -> ((City) city).getPlayers().contains(Integer.valueOf(playerId)));
		prevLocationInMap.removePlayer(playerId);
		City cityInMap = (City) map.getCities().findIfCustom(city -> ((City) city).getId() == newLocation.getId());
		cityInMap.addPlayer(playerId);
		
	}

}

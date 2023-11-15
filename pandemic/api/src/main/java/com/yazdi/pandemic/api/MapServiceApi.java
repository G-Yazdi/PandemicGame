package com.yazdi.pandemic.api;

import com.yazdi.pandemic.mapcontext.model.entities.City;
import com.yazdi.pandemic.mapcontext.model.entities.WorldMap;
import com.yazdi.pandemic.mapcontext.service.IMapService;

public class MapServiceApi {
private IMapService mapService;
	
	
	public MapServiceApi(IMapService mapService) {
		this.mapService = mapService;
	}
	
	public void updatePlayerLocationInMap(int playerId, City newLocation, WorldMap map) {
		
		mapService.updatePlayerLocationInMap(playerId, newLocation, map);
	}
	public void listenToPlayerEventService() {
		
		mapService.listenToPlayerEventService();
	}

}

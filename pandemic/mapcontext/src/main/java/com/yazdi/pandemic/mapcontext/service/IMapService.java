package com.yazdi.pandemic.mapcontext.service;

import com.yazdi.pandemic.mapcontext.model.entities.City;
import com.yazdi.pandemic.mapcontext.model.entities.WorldMap;
import com.yazdi.pandemic.mapcontext.repository.MapRepository;
import com.yazdi.pandemic.sharedkernel.service.ApplicationService;

public interface IMapService extends ApplicationService {
	
	void setMapRepository(MapRepository mapRepository);
	void updatePlayerLocationInMap(int playerId, City newLocation, WorldMap map);
	void listenToPlayerEventService();
	void setMap(WorldMap map);
	

}

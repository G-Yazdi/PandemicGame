package com.yazdi.pandemic.api;

import com.yazdi.pandemic.playercontext.service.IPlayerService;

public class MapServiceApi {
private IMapService mapService;
	
	
	public MapServiceApi(IMapService mapService) {
		this.mapService = mapService;
	}
	
	public void updateMapService(City updatedCity) {
		mapService.updateMap(updatedCity);
	}

}

package com.yazdi.pandemic.api;

import com.yazdi.pandemic.mapcontext.service.IMapService;

public class MapServiceApi {
private IMapService mapService;
	
	
	public MapServiceApi(IMapService mapService) {
		this.mapService = mapService;
	}
	
	public void updateMapService(PlayerLocation updatedCity) {
		mapService.updateMap(updatedCity);
	}

}

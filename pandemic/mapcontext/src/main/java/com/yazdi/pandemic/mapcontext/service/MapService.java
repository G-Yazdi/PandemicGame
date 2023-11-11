package com.yazdi.pandemic.mapcontext.service;

import com.yazdi.pandemic.mapcontext.model.entities.City;
import com.yazdi.pandemic.mapcontext.repository.MapRepository;

public class MapService implements IMapService {
	
	private MapRepository mapRepository;

	@Override
	public void updateMap(City updatedCity) {
		
		
	}
	@Override
    public void setMapRepository(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

}

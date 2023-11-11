package com.yazdi.pandemic.mapcontext.service;

import com.yazdi.pandemic.mapcontext.model.entities.City;
import com.yazdi.pandemic.mapcontext.repository.MapRepository;

public interface IMapService {
	
	public void setMapRepository(MapRepository mapRepository);
	public void updateMap(City updatedCity);

}

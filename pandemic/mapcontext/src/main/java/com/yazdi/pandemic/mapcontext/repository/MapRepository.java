package com.yazdi.pandemic.mapcontext.repository;

import java.util.Optional;

import com.yazdi.pandemic.mapcontext.model.entities.City;

public interface MapRepository {

	Optional<City> findCityByPlayerId(int playerId);

}

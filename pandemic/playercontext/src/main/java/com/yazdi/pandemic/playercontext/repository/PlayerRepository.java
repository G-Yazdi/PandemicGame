package com.yazdi.pandemic.playercontext.repository;

import com.yazdi.pandemic.playercontext.model.City;

public interface PlayerRepository {
	void updatePlayerLocation(int playerId, City destination);
	void updatePlayerLocationResearchStationStatus(int playerId, boolean hasResearchStation);
}

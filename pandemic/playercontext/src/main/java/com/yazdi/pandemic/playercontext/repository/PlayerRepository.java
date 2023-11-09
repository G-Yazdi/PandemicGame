package com.yazdi.pandemic.playercontext.repository;

import com.yazdi.pandemic.playercontext.model.Card;
import com.yazdi.pandemic.playercontext.model.City;
import com.yazdi.pandemic.playercontext.model.CustomArrayList;
import com.yazdi.pandemic.playercontext.model.Disease;

public interface PlayerRepository {
	void updatePlayerLocation(int playerId, City destination);
	void updatePlayerLocationResearchStationStatus(int playerId, boolean hasResearchStation);
	void updatePlayerHand(int playerId, CustomArrayList<Card> playerHand);
	void updatePlayerLocationDiseaseStatus(int playerId, Disease disease, boolean hasCure);
}

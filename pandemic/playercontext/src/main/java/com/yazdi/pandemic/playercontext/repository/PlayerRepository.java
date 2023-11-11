package com.yazdi.pandemic.playercontext.repository;

import com.yazdi.pandemic.playercontext.model.contracts.Card;
import com.yazdi.pandemic.playercontext.model.entities.City;
import com.yazdi.pandemic.playercontext.model.entities.Disease;
import com.yazdi.pandemic.playercontext.model.utils.CustomArrayList;

public interface PlayerRepository {
	void updatePlayerLocation(int playerId, City playerLocation);
	void updatePlayerHand(int playerId, CustomArrayList<Card> playerHand);
}

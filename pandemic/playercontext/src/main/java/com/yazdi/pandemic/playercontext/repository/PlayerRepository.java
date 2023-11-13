package com.yazdi.pandemic.playercontext.repository;

import com.yazdi.pandemic.playercontext.model.contracts.Card;
import com.yazdi.pandemic.playercontext.model.entities.PlayerLocation;
import com.yazdi.pandemic.sharedkernel.utils.CustomArrayList;

public interface PlayerRepository {
	void updatePlayerLocation(int playerId, PlayerLocation playerLocation);
	void updatePlayerHand(int playerId, CustomArrayList<Card> playerHand);
}

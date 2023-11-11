package com.yazdi.pandemic.infrastructure.db;

import java.util.HashMap;
import java.util.Map;

import com.yazdi.pandemic.playercontext.model.contracts.Card;
import com.yazdi.pandemic.playercontext.model.entities.PlayerLocation;
import com.yazdi.pandemic.playercontext.model.entities.Disease;
import com.yazdi.pandemic.playercontext.model.utils.CustomArrayList;
import com.yazdi.pandemic.playercontext.repository.PlayerRepository;

public class InMemoryGameStore implements PlayerRepository {
    private Map<Integer, PersistencePlayer> gameDb = new HashMap<>();
    private volatile static InMemoryGameStore instance = new InMemoryGameStore();

    @Override
    public void updatePlayerLocation(int playerId, PlayerLocation playerLocation){
    	PersistencePlayer player = this.gameDb.get(playerId);
    	if(player != null) {
    		player.playerLocation = playerLocation;
    		this.gameDb.put(playerId, player);
    	}
    }

    public static InMemoryGameStore provider() {
        return instance;
    }

	@Override
	public void updatePlayerHand(int playerId, CustomArrayList<Card> playerHand) {
		PersistencePlayer player = this.gameDb.get(playerId);
    	if(player != null) {
    		player.playerHand = playerHand;
    		this.gameDb.put(playerId, player);
    	}
		
	}
	
	public static class PersistencePlayer {
		public int playerId;
	    public PlayerLocation playerLocation;
	    public CustomArrayList<Card> playerHand;

	    public PersistencePlayer(int playerId, PlayerLocation playerLocation, CustomArrayList<Card> playerHand) {
	    	this.playerId = playerId;
	        this.playerLocation = playerLocation;
	        this.playerHand = playerHand;
	    }
	 }
}

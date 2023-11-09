package com.yazdi.pandemic.infrastructure.db;

import java.util.HashMap;
import java.util.Map;

import com.yazdi.pandemic.playercontext.model.Card;
import com.yazdi.pandemic.playercontext.model.City;
import com.yazdi.pandemic.playercontext.model.CustomArrayList;
import com.yazdi.pandemic.playercontext.model.Disease;
import com.yazdi.pandemic.playercontext.repository.PlayerRepository;

public class InMemoryGameStore implements PlayerRepository {
    private Map<Integer, PersistencePlayer> gameDb = new HashMap<>();
    private volatile static InMemoryGameStore instance = new InMemoryGameStore();

    @Override
    public void updatePlayerLocation(int playerId, City newCity){
    	PersistencePlayer player = this.gameDb.get(playerId);
    	if(player != null) {
    		player.playerLocation = newCity;
    		this.gameDb.put(playerId, player);
    	}
    }

    public static InMemoryGameStore provider() {
        return instance;
    }

	@Override
	public void updatePlayerLocationResearchStationStatus(int playerId, boolean hasResearchStation) {
		PersistencePlayer player = this.gameDb.get(playerId);
    	if(player != null) {
    		player.playerLocation.setHasResearchStation(hasResearchStation);
    		this.gameDb.put(playerId, player);
    	}
	}

	@Override
	public void updatePlayerHand(int playerId, CustomArrayList<Card> playerHand) {
		PersistencePlayer player = this.gameDb.get(playerId);
    	if(player != null) {
    		player.playerHand = playerHand;
    		this.gameDb.put(playerId, player);
    	}
		
	}

	@Override
	public void updatePlayerLocationDiseaseStatus(int playerId, Disease disease, boolean hasCure) {
		PersistencePlayer player = this.gameDb.get(playerId);
    	if(player != null) {
    		player.playerLocation.getCubes(disease).forEach(c -> c.getDisease().setHasCure(hasCure));
    		this.gameDb.put(playerId, player);
    	}
		
	}
	
	public static class PersistencePlayer {
		public int playerId;
	    public City playerLocation;
	    public CustomArrayList<Card> playerHand;

	    public PersistencePlayer(int playerId, City playerLocation, CustomArrayList<Card> playerHand) {
	    	this.playerId = playerId;
	        this.playerLocation = playerLocation;
	        this.playerHand = playerHand;
	    }
	 }
}

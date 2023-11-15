package com.yazdi.pandemic.infrastructure.db;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.yazdi.pandemic.mapcontext.model.entities.City;
import com.yazdi.pandemic.mapcontext.repository.MapRepository;
import com.yazdi.pandemic.playercontext.model.contracts.Card;
import com.yazdi.pandemic.playercontext.model.entities.PlayerLocation;
import com.yazdi.pandemic.playercontext.repository.PlayerRepository;
import com.yazdi.pandemic.sharedkernel.contracts.ICube;
import com.yazdi.pandemic.sharedkernel.utils.CustomArrayList;

public class InMemoryGameStore implements PlayerRepository, MapRepository {
    private Map<Integer, PersistencePlayer> playerTable = new HashMap<>();
    private Map<Integer, PersistenceCity> cityTable = new HashMap<>();
    private volatile static InMemoryGameStore instance = new InMemoryGameStore();

    @Override
    public void updatePlayerLocation(int playerId, PlayerLocation newLocation){
    	PersistencePlayer player = this.playerTable.get(playerId);
    	if(player != null) {
    		player.playerLocationId = newLocation.getId();
    		this.playerTable.put(playerId, player);
    	}
    }

    public static InMemoryGameStore provider() {
        return instance;
    }

	@Override
	public void updatePlayerHand(int playerId, CustomArrayList<Card> playerHand) {
		PersistencePlayer player = this.playerTable.get(playerId);
    	if(player != null) {
    		player.playerHand = playerHand;
    		this.playerTable.put(playerId, player);
    	}
		
	}
	
	public static class PersistencePlayer {
		public int playerId;
	    public CustomArrayList<Card> playerHand;
	    public int playerLocationId;

	    public PersistencePlayer(int playerId, int playerLocationId, CustomArrayList<Card> playerHand) {
	    	this.playerId = playerId;
	        this.playerLocationId = playerLocationId;
	        this.playerHand = playerHand;
	    }
	    
	 }
	public static class PersistenceCity {
		public int cityId;
		public String cityName;
		public CustomArrayList<Integer> neighbors;
		public CustomArrayList<ICube> cubes;
		public boolean hasResearchStation;

	    public PersistenceCity(int cityId, String cityName, boolean hasResearchStation, CustomArrayList<Integer> neighbors, CustomArrayList<ICube> cubes) {
	    	this.cityId = cityId;
	    	this.cityName = cityName;
	    	this.hasResearchStation = hasResearchStation;
	    	this.neighbors = neighbors;
	    	this.cubes = cubes;
	    }
	 }
	
	@Override
	public Optional<City> findCityByPlayerId(int playerId) {
		if (!this.playerTable.containsKey(playerId)) return Optional.empty();
		PersistencePlayer playerRecord = this.playerTable.get(playerId);
		int cityId = playerRecord.playerLocationId;
		if (!this.cityTable.containsKey(cityId)) return Optional.empty();
		PersistenceCity cityRecord = this.cityTable.get(cityId);
		CustomArrayList<Integer> playersLocatedInCity = this.findPlayersLocatedInCity(cityRecord.cityId);
		
        return Optional.of(
          new City(cityRecord.cityId, cityRecord.cityName, cityRecord.neighbors, cityRecord.cubes, playersLocatedInCity, cityRecord.hasResearchStation));
	}
	private CustomArrayList<Integer> findPlayersLocatedInCity(int cityId){
		CustomArrayList<Integer> playersLocatedInCity = new CustomArrayList<Integer>();
		for (PersistencePlayer playerRecord : playerTable.values()) {
	        if(playerRecord.playerLocationId == cityId) {
	        	playersLocatedInCity.add(playerRecord.playerId);
	        }
	    }
		return playersLocatedInCity;
	}
}

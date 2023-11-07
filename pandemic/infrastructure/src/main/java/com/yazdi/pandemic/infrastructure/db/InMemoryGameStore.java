package com.yazdi.pandemic.infrastructure.db;

import java.util.HashMap;
import java.util.Map;

import com.yazdi.pandemic.playercontext.model.City;
import com.yazdi.pandemic.playercontext.repository.PlayerRepository;

public class InMemoryGameStore implements PlayerRepository {
    private Map<Integer, City> gameDb = new HashMap<>();
    private volatile static InMemoryGameStore instance = new InMemoryGameStore();

    @Override
    public void updatePlayerLocation(int playerId, City destination){
        this.gameDb.put(playerId, destination);
    }

    public static InMemoryGameStore provider() {
        return instance;
    }
}

package com.yazdi.pandemic.api;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.yazdi.pandemic.mapcontext.model.entities.City;
import com.yazdi.pandemic.mapcontext.model.entities.WorldMap;
import com.yazdi.pandemic.mapcontext.repository.MapRepository;
import com.yazdi.pandemic.mapcontext.service.IMapService;
import com.yazdi.pandemic.playercontext.repository.PlayerRepository;
import com.yazdi.pandemic.playercontext.service.IPlayerService;
import com.yazdi.pandemic.sharedkernel.events.EventBus;
import com.yazdi.pandemic.sharedkernel.utils.CustomArrayList;
import com.yazdi.pandemic.playercontext.model.entities.GlobetrotterRole;
import com.yazdi.pandemic.playercontext.model.entities.Player;
import com.yazdi.pandemic.playercontext.model.entities.PlayerLocation;
import com.yazdi.pandemic.playercontext.model.contracts.Role;

public class GameApiTest {
	private static IPlayerService playerService;
	private static IMapService mapService;
	
	public static Map<Class<?>, Object> createContainer() {
        EventBus eventBus = ServiceLoader.load(EventBus.class).findFirst().get();
        MapRepository mapRepository = ServiceLoader.load(MapRepository.class).findFirst().get();
        IMapService mapService = ServiceLoader.load(IMapService.class).findFirst().get();
        mapService.setEventBus(eventBus);
        mapService.setMapRepository(mapRepository);
        
        PlayerRepository playerRepository = ServiceLoader.load(PlayerRepository.class).findFirst().get();
        IPlayerService playerService = ServiceLoader.load(IPlayerService.class).findFirst().get();
        playerService.setEventBus(eventBus);
        playerService.setPlayerRepository(playerRepository);
        
        HashMap<Class<?>, Object> container = new HashMap<>();
        container.put(IMapService.class, mapService);
        container.put(IPlayerService.class, playerService);
        return container;
    }
	
	  @BeforeAll 
	  public static void init() {
		  Map<Class<?>, Object> container = createContainer();
		  mapService = (IMapService) container.get(IMapService.class);
		  playerService = (IPlayerService) container.get(IPlayerService.class);
	  }
	  @Test 
	  public void movePlayerInGameServiceTest() {
		  /*Player Context*/
		  Role role = new GlobetrotterRole();
		  Player player = new Player(role);
		  PlayerLocation currentLocation = new PlayerLocation("Mashhad");
		  player.setCurrentLocation(currentLocation);
		  PlayerLocation destination = new PlayerLocation("Zahedan");
		  currentLocation.addNeighbour(destination.getId());
		  PlayerServiceApi playerServiceApi = new PlayerServiceApi(playerService);
		  
		  /*Map Context*/
		  WorldMap map = new WorldMap();
		  CustomArrayList<Integer> prevLocationPlayers = new CustomArrayList<Integer>();
		  prevLocationPlayers.add(player.getId());
		  CustomArrayList<Integer> prevLocationNeighbors = new CustomArrayList<Integer>();
		  prevLocationNeighbors.add(destination.getId());
		  CustomArrayList<Integer> newLocationNeighbors = new CustomArrayList<Integer>();
		  newLocationNeighbors.add(currentLocation.getId());
		  City prevLocation = new City(currentLocation.getId(), currentLocation.getName(), prevLocationNeighbors, null, prevLocationPlayers, false);
		  City newLocation = new City(destination.getId(), destination.getName(), newLocationNeighbors, null, null, false);
		  map.addCity(prevLocation);
		  map.addCity(newLocation);
		  mapService.setMap(map);
		  MapServiceApi mapServiceApi = new MapServiceApi(mapService);
		  
		  mapServiceApi.listenToPlayerEventService();
		  playerServiceApi.moveService(player, destination);
		  
		  /*
		   Sees if changing a player's location causes updating the world map*/
		  assertAll(
		            () -> assertTrue(!((City) map.getCity(prevLocation.getId())).getPlayers().contains(player.getId())),
		            () -> assertTrue(((City) map.getCity(newLocation.getId())).getPlayers().contains(player.getId())));
	  }
}
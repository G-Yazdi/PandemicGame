package com.yazdi.pandemic.api;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.yazdi.pandemic.mapcontext.repository.MapRepository;
import com.yazdi.pandemic.mapcontext.service.IMapService;
import com.yazdi.pandemic.sharedkernel.events.EventBus;

public class MapServiceApiTest {
	private static IMapService mapService;
	
	public static Map<Class<?>, Object> createContainer() {
        EventBus eventBus = ServiceLoader.load(EventBus.class).findFirst().get();
        MapRepository mapRepository = ServiceLoader.load(MapRepository.class).findFirst().get();
        IMapService mapService = ServiceLoader.load(IMapService.class).findFirst().get();
        mapService.setEventBus(eventBus);
        mapService.setMapRepository(mapRepository);
        
        HashMap<Class<?>, Object> container = new HashMap<>();
        container.put(IMapService.class, mapService);
        return container;
    }
	
	  @BeforeAll 
	  public static void init() {
		  Map<Class<?>, Object> container = createContainer();
		  mapService = (IMapService) container.get(IMapService.class);
	  }
	  
	  @Test 
	  public void updatePlayerLocationInMapServiceTest() {
		  MapServiceApi api = new MapServiceApi(mapService); 
		  WorldMap map = new WorldMap();
		  City prevLocation = new City("Zahedan");
		  City newLocation = new City("Mashhad");
		  map.addCity(prevLocation);
		  map.addCity(newLocation);
		  
		  int playerId = 1;
		  prevLocation.addPlayer(playerId);
		  api.updatePlayerLocationInMap(playerId, newLocation, map);
		  
		  assertAll(
		            () -> assertTrue(!((City) map.getCity(prevLocation.getId())).getPlayers().contains(playerId)),
		            () -> assertTrue(((City) map.getCity(newLocation.getId())).getPlayers().contains(playerId)));
	  }
	 

}

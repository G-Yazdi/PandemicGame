package com.yazdi.pandemic.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.yazdi.pandemic.playercontext.model.contracts.Role;
import com.yazdi.pandemic.playercontext.model.entities.PlayerLocation;
import com.yazdi.pandemic.playercontext.model.entities.GlobetrotterRole;
import com.yazdi.pandemic.playercontext.model.entities.Player;
import com.yazdi.pandemic.playercontext.repository.PlayerRepository;
import com.yazdi.pandemic.playercontext.service.IPlayerService;
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
	public void updateMapServiceTest() {
		
		MapServiceApi api = new MapServiceApi(mapService);
		api.updateMapService(updatedCity);
		assertEquals(map.getCity(updatedCity), updatedCity);
		
	}

}

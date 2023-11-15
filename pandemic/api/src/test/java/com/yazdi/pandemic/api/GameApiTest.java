package com.yazdi.pandemic.api;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import org.junit.jupiter.api.BeforeAll;

import com.yazdi.pandemic.mapcontext.repository.MapRepository;
import com.yazdi.pandemic.mapcontext.service.IMapService;
import com.yazdi.pandemic.playercontext.service.IPlayerService;
import com.yazdi.pandemic.sharedkernel.events.EventBus;

public class GameApiTest {
	private static IPlayerService playerService;
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

}

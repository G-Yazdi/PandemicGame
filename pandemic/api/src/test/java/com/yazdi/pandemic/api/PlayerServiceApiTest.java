package com.yazdi.pandemic.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.yazdi.pandemic.playercontext.model.City;
import com.yazdi.pandemic.playercontext.model.GlobetrotterRole;
import com.yazdi.pandemic.playercontext.model.Player;
import com.yazdi.pandemic.playercontext.model.Role;
import com.yazdi.pandemic.playercontext.repository.PlayerRepository;
import com.yazdi.pandemic.playercontext.service.IPlayerService;
import com.yazdi.pandemic.sharedkernel.events.EventBus;

public class PlayerServiceApiTest {
	
	private static IPlayerService playerService;
	
	public static Map<Class<?>, Object> createContainer() {
        EventBus eventBus = ServiceLoader.load(EventBus.class).findFirst().get();
        PlayerRepository playerRepository = ServiceLoader.load(PlayerRepository.class).findFirst().get();
        IPlayerService playerService = ServiceLoader.load(IPlayerService.class).findFirst().get();
        playerService.setEventBus(eventBus);
        playerService.setPlayerRepository(playerRepository);
        
        HashMap<Class<?>, Object> container = new HashMap<>();
        container.put(IPlayerService.class, playerService);
        return container;
    }
	
	
	  @BeforeAll 
	  public static void init() {
		  Map<Class<?>, Object> container = createContainer();
		  playerService = (IPlayerService) container.get(IPlayerService.class);
	  }
	 
	
	@Test
	public void moveServiceTest() {
		Role role = new GlobetrotterRole();
		Player player1 = new Player(role);
		City city = new City("Mashhad");
		City destination = new City("Zahedan");
		city.addNeighbour(destination);
		PlayerServiceApi api = new PlayerServiceApi(playerService);
		api.moveService(player1, destination);
		assertEquals(player1.getCurrentLocation().getName(), destination.getName());
		
	}
	
	@Test
	public void buildServiceTest() {
		Role role = new ExpertRole();
		Player player1 = new Player(role);
		PlayerServiceApi api = new PlayerServiceApi(playerService);
		api.buildService(player1);
		assertTrue(player1.getCurrentLocation().getHasResearchStation());
	}
	

}
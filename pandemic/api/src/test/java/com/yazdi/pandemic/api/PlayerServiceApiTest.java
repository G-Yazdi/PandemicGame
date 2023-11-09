package com.yazdi.pandemic.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.yazdi.pandemic.playercontext.model.City;
import com.yazdi.pandemic.playercontext.model.ExpertRole;
import com.yazdi.pandemic.playercontext.model.GlobetrotterRole;
import com.yazdi.pandemic.playercontext.model.Player;
import com.yazdi.pandemic.playercontext.model.PlayerCard;
import com.yazdi.pandemic.playercontext.model.Role;
import com.yazdi.pandemic.playercontext.model.Card;
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
	public void moveAsGlobetrotterServiceTest() {
		Role role = new GlobetrotterRole();
		Player player1 = new Player(role);
		City city = new City("Mashhad");
		player1.setCurrentLocation(city);
		City destination = new City("Zahedan");
		city.addNeighbour(destination);
		PlayerServiceApi api = new PlayerServiceApi(playerService);
		api.moveService(player1, destination);
		assertEquals(player1.getCurrentLocation().getName(), destination.getName());
		
	}
	
	
	  @Test 
	  public void illegalMoveExceptionTest() { 
		  Role role = new ExpertRole(); 
		  Player  player1 = new Player(role); 
		  City city1 = new City("Mashhad");
		  player1.setCurrentLocation(city1); 
		  City destination = new City("Zahedan");
		  
		  PlayerServiceApi api = new PlayerServiceApi(playerService); 
		  Throwable exception = assertThrows(RuntimeException.class, () -> api.moveService(player1, destination));
		  assertEquals("Illegal move request: The player can not move to a non-neighbor city!", exception.getMessage());
	  }
	 
	 
	
	@Test
	public void moveAsNotGlobetrotterServiceTest() {
		Role role = new ExpertRole();
		Player player1 = new Player(role);
		City city1 = new City("Mashhad");
		player1.setCurrentLocation(city1);
		City destination = new City("Zahedan");
		city1.addNeighbour(destination);
		
		PlayerServiceApi api = new PlayerServiceApi(playerService);
		api.moveService(player1, destination);
		assertEquals(player1.getCurrentLocation().getName(), destination.getName());
		
	}
	
	@Test
	public void buildAsExpertServiceTest() {
		Role role = new ExpertRole();
		Player player1 = new Player(role);
		City city = new City("Mashhad");
		player1.setCurrentLocation(city);
		
		PlayerServiceApi api = new PlayerServiceApi(playerService);
		api.buildService(player1);
		assertTrue(player1.getCurrentLocation().getHasResearchStation());
	}

}
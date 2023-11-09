package com.yazdi.pandemic.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.yazdi.pandemic.playercontext.model.City;
import com.yazdi.pandemic.playercontext.model.Disease;
import com.yazdi.pandemic.playercontext.model.ExpertRole;
import com.yazdi.pandemic.playercontext.model.GlobetrotterRole;
import com.yazdi.pandemic.playercontext.model.Player;
import com.yazdi.pandemic.playercontext.model.PlayerCard;
import com.yazdi.pandemic.playercontext.model.Role;
import com.yazdi.pandemic.playercontext.model.ScientistRole;
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
	@Test
	void illegalBuildExceptionTest() {
		Role role = new GlobetrotterRole();
		Player player1 = new Player(role);
		City city = new City("Mashhad");
		player1.setCurrentLocation(city);
		
		PlayerServiceApi api = new PlayerServiceApi(playerService);
	    Throwable exception = assertThrows(RuntimeException.class, 
	    		() -> api.buildService(player1));
	    assertEquals("Illegal build request: The player has no card whose city is the one that he is located on!", exception.getMessage());
	}
	@Test
	public void buildAsNotExpertServiceTest() {
		Role role = new GlobetrotterRole();
		Player player1 = new Player(role);
		City city1 = new City("Mashhad");
		player1.setCurrentLocation(city1);
		
		Disease disease = new Disease("Influenza", false);
		Card playerCard1 = new PlayerCard(player1.getCurrentLocation().getName(), disease.getName());
		player1.addToHand(playerCard1);
		
		int previousSize = player1.getHand().size();
		
		PlayerServiceApi api = new PlayerServiceApi(playerService);
		api.buildService(player1);
		
		int currentSize = player1.getHand().size();
		
		assertAll(
	            () -> assertTrue(city1.getHasResearchStation()),
	            () -> assertEquals(previousSize - 1, currentSize)); // check if the card is removed from the player's hand
		
	}
	@Test
	public void findCureAsScientistServiceTest(){
		Role scientist = new ScientistRole();
		Player player1 = new Player(scientist);
		City city1 = new City("Mashhad");
		player1.setCurrentLocation(city1);
		Disease disease = new Disease("Influenza", false);
		
		Card playerCard1 = new PlayerCard(player1.getCurrentLocation().getName(), disease.getName());
		player1.addToHand(playerCard1);
		player1.addToHand(playerCard1);
		player1.addToHand(playerCard1);
		player1.addToHand(playerCard1);
		player1.getCurrentLocation().setHasResearchStation(true);
		
		int previousSize = player1.getHand().size();
		
		PlayerServiceApi api = new PlayerServiceApi(playerService);
		api.findCureService(player1, disease);
		
		int currentSize = player1.getHand().size();
		assertAll(
		            () -> assertTrue(disease.getHasCure()),
		            () -> assertEquals(previousSize - 4, currentSize) //check if 4 cards are removed from the player's hand
		    );
	}
	
}
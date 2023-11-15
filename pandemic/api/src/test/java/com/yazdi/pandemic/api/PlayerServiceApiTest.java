package com.yazdi.pandemic.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.yazdi.pandemic.playercontext.model.contracts.Card;
import com.yazdi.pandemic.playercontext.model.contracts.Role;
import com.yazdi.pandemic.playercontext.model.entities.PlayerLocation;
import com.yazdi.pandemic.playercontext.model.entities.Cube;
import com.yazdi.pandemic.playercontext.model.entities.Disease;
import com.yazdi.pandemic.playercontext.model.entities.DoctorRole;
import com.yazdi.pandemic.playercontext.model.entities.ExpertRole;
import com.yazdi.pandemic.playercontext.model.entities.GlobetrotterRole;
import com.yazdi.pandemic.playercontext.model.entities.Player;
import com.yazdi.pandemic.playercontext.model.entities.PlayerCard;
import com.yazdi.pandemic.playercontext.model.entities.ScientistRole;
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
		PlayerLocation city = new PlayerLocation("Mashhad");
		player1.setCurrentLocation(city);
		PlayerLocation destination = new PlayerLocation("Zahedan");
		city.addNeighbour(destination.getId());
		PlayerServiceApi api = new PlayerServiceApi(playerService);
		api.moveService(player1, destination);
		assertEquals(player1.getCurrentLocation().getName(), destination.getName());
		
	}
	@Test 
	public void illegalMoveExceptionTest() {
		Role role = new ExpertRole(); 
		Player  player1 = new Player(role); 
		PlayerLocation city1 = new PlayerLocation("Mashhad");
		player1.setCurrentLocation(city1); 
		PlayerLocation destination = new PlayerLocation("Zahedan");  
		PlayerServiceApi api = new PlayerServiceApi(playerService); 
		Throwable exception = assertThrows(RuntimeException.class, () -> api.moveService(player1, destination));
		assertEquals("Illegal move request: The player can not move to a non-neighbor city!", exception.getMessage());
	  }
	@Test
	public void moveAsNotGlobetrotterServiceTest() {
		Role role = new ExpertRole();
		Player player1 = new Player(role);
		PlayerLocation city1 = new PlayerLocation("Mashhad");
		player1.setCurrentLocation(city1);
		PlayerLocation destination = new PlayerLocation("Zahedan");
		city1.addNeighbour(destination);
		
		PlayerServiceApi api = new PlayerServiceApi(playerService);
		api.moveService(player1, destination);
		assertEquals(player1.getCurrentLocation().getName(), destination.getName());
		
	}
	@Test
	public void buildAsExpertServiceTest() {
		Role role = new ExpertRole();
		Player player1 = new Player(role);
		PlayerLocation city = new PlayerLocation("Mashhad");
		player1.setCurrentLocation(city);
		
		PlayerServiceApi api = new PlayerServiceApi(playerService);
		api.buildService(player1);
		assertTrue(player1.getCurrentLocation().getHasResearchStation());
	}
	@Test
	void illegalBuildExceptionTest() {
		Role role = new GlobetrotterRole();
		Player player1 = new Player(role);
		PlayerLocation city = new PlayerLocation("Mashhad");
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
		PlayerLocation city1 = new PlayerLocation("Mashhad");
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
	void notEnoughCardsForFindingCureAsScientistExceptionTest() {
		Role scientist = new ScientistRole();
		Player player1 = new Player(scientist);
		PlayerLocation city1 = new PlayerLocation("Mashhad");
		player1.setCurrentLocation(city1);
		Disease disease = new Disease("Influenza", false);
		
		PlayerServiceApi api = new PlayerServiceApi(playerService);
	    Throwable exception = assertThrows(RuntimeException.class, 
	    		() -> api.findCureService(player1, disease));
	    assertEquals("Illegal find cure request: There is not enough player cards of the same disease in the player's hand!", exception.getMessage());
	   
	}
	@Test
	void noResearchStationFoundForFindingCureAsScientistExceptionTest() {
		Role scientist = new ScientistRole();
		Player player1 = new Player(scientist);
		PlayerLocation city1 = new PlayerLocation("Mashhad");
		player1.setCurrentLocation(city1);
		Disease disease = new Disease("Influenza", false);
		
		PlayerServiceApi api = new PlayerServiceApi(playerService);
		Card playerCard1 = new PlayerCard(player1.getCurrentLocation().getName(), disease.getName());
		player1.addToHand(playerCard1);
		player1.addToHand(playerCard1);
		player1.addToHand(playerCard1);
		player1.addToHand(playerCard1);
	    Throwable exception = assertThrows(RuntimeException.class, 
	    		() -> api.findCureService(player1, disease));
	    assertEquals("Illegal find cure request: There is no research station in the player's city!", exception.getMessage());
	}
	@Test
	public void findCureAsScientistServiceTest(){
		Role scientist = new ScientistRole();
		Player player1 = new Player(scientist);
		PlayerLocation city1 = new PlayerLocation("Mashhad");
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
		            () -> {
						player1.getCurrentLocation().getCubes(disease)
						.forEach(cube->assertTrue(cube.getDisease().getHasCure()));
					},
		            () -> assertEquals(previousSize - 4, currentSize) //check if 4 cards are removed from the player's hand
		    );
	}
	
	@Test
	void notEnoughCardsForFindingCureAsNotScientistExceptionTest() {
		Role role = new GlobetrotterRole();
		Player player1 = new Player(role);
		Disease disease = new Disease("Influenza", false);
		
		PlayerServiceApi api = new PlayerServiceApi(playerService);
	    Throwable exception = assertThrows(RuntimeException.class, 
	    		() -> api.findCureService(player1, disease));
	    assertEquals("Illegal find cure request: There is not enough player cards of the same disease in the player's hand!", exception.getMessage());
	   
	}
	@Test
	void noResearchStationFoundForFindingCureAsNotScientistExceptionTest() {
		Role role = new GlobetrotterRole();
		Player player1 = new Player(role);
		PlayerLocation city1 = new PlayerLocation("Mashhad");
		player1.setCurrentLocation(city1);
		Disease disease = new Disease("Influenza", false);
		
		Card playerCard1 = new PlayerCard(player1.getCurrentLocation().getName(), disease.getName());
		player1.addToHand(playerCard1);
		player1.addToHand(playerCard1);
		player1.addToHand(playerCard1);
		player1.addToHand(playerCard1);
		player1.addToHand(playerCard1);
		
		PlayerServiceApi api = new PlayerServiceApi(playerService);
	    Throwable exception = assertThrows(RuntimeException.class, 
	    		() -> api.findCureService(player1, disease));
	    assertEquals("Illegal find cure request: There is no research station in the player's city!", exception.getMessage());
	}
	@Test
	public void findCureAsNotScientistServiceTest(){
		Role role = new GlobetrotterRole();
		Player player1 = new Player(role);
		PlayerLocation city1 = new PlayerLocation("Mashhad");
		player1.setCurrentLocation(city1);
		Disease disease = new Disease("Influenza", false);
		
		Card playerCard1 = new PlayerCard(player1.getCurrentLocation().getName(), disease.getName());
		player1.addToHand(playerCard1);
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
				() -> {
					player1.getCurrentLocation().getCubes(disease)
					.forEach(cube->assertTrue(cube.getDisease().getHasCure()));
				},
		        () -> assertEquals(previousSize - 5, currentSize) //check if 5 cards are removed from the player's hand
		    );
	}
	@Test
	public void treatDiseaseAsDoctorServiceTest(){
		Role role = new DoctorRole();
		Player player1 = new Player(role);
		PlayerLocation city1 = new PlayerLocation("Mashhad");
		player1.setCurrentLocation(city1);
		Disease disease = new Disease("Influenza", false);
		city1.addCube(new Cube(disease));
		city1.addCube(new Cube(disease));
		
		PlayerServiceApi api = new PlayerServiceApi(playerService);
		api.treatDiseaseService(player1, disease); 
		
		List<Cube> cubesOfDisease = city1.getCubes(disease);// In this case, all the cubes of the disease should be removed from the city1
		int currentSize = cubesOfDisease.size();
		
		assertEquals(0, currentSize);	
	}
	@Test
	public void treatDiseaseAsNotDoctorWithNoRemedyServiceTest(){
		Role role = new ScientistRole();
		Player player1 = new Player(role);
		PlayerLocation city1 = new PlayerLocation("Mashhad");
		player1.setCurrentLocation(city1);
		Disease disease = new Disease("Influenza", false);
		city1.addCube(new Cube(disease));
		city1.addCube(new Cube(disease));
		
		List<Cube> cubesOfDisease = city1.getCubes(disease);
		int previousSize = cubesOfDisease.size();
		
		PlayerServiceApi api = new PlayerServiceApi(playerService);
		api.treatDiseaseService(player1, disease);
		
		cubesOfDisease = city1.getCubes(disease);
		int currentSize = cubesOfDisease.size();
		
		assertEquals(previousSize - 1, currentSize); 
	}
	@Test
	public void treatDiseaseWithRemedyAsNotDoctorServiceTest(){
		
		Role role = new ScientistRole();
		Player player1 = new Player(role);
		PlayerLocation city1 = new PlayerLocation("Mashhad");
		player1.setCurrentLocation(city1);
		Disease disease = new Disease("Influenza", true);
		city1.addCube(new Cube(disease));
		city1.addCube(new Cube(disease));
		
		
		PlayerServiceApi api = new PlayerServiceApi(playerService);
		api.treatDiseaseService(player1, disease);
		
		List<Cube> cubesOfDisease = city1.getCubes(disease);// In this case, all the cubes of the disease should be removed from the city1
		int currentSize = cubesOfDisease.size();
		
		assertEquals(0, currentSize);	
	}
	
	
}
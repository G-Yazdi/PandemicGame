package com.yazdi.pandemic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class ActionTest {
	
	City city1, city2, city3;
	Player player;
	Game game;
	Action action;
	Disease disease;
	
	

	@BeforeEach
	public void init(){
		city1 = new City("Mashhad");
		city2 = new City("Zahedan");
		city3 = new City("Tehran");
		disease = new Disease("Influenza", false);
		player = new Player();
        player.setCurrentLocation(city1);
       
        game = new Game();
        game.addDisease(disease);
	}                                        
	
	@Test
	void illegalMoveRequestExceptionTest() {
		
	    Throwable exception = assertThrows(RuntimeException.class, 
	    		() -> new MoveAction(player, city2));
	    assertEquals("Illegal move request: The player can not move to a non-neighbor city!", exception.getMessage());
	}
	
	@Test
	public void moveActionTest(){
		city1.addNeighbour(city2);
		
		action = new MoveAction(player, city2);
		player.act(action);
		   
		assertTrue(player.getCurrentLocation() == city2);
	}
	
	@Test
	void illegalBuildResearchStationRequestExceptionTest() {
	    Throwable exception = assertThrows(RuntimeException.class, 
	    		() -> new BuildResearchStationAction(player, game.getDiscardedPlayerCards()));
	    assertEquals("Illegal build request: The player has no card whose city is the one that he is located on!", exception.getMessage());
	}
	
	@Test
	public void buildResearchStationActionTest(){
		Card playerCard1 = new PlayerCard(player.getCurrentLocation().getName(), disease.getName());
		player.addToHand(playerCard1);
		
		int previousSize = game.getDiscardedPlayerCards().size();
		int previousCount = player.getHand().size();
		
		action = new BuildResearchStationAction(player, game.getDiscardedPlayerCards());
		player.act(action);
		
		int currentSize = game.getDiscardedPlayerCards().size();
		int currentCount = player.getHand().size();
		
		assertAll(
	            () -> assertTrue(city1.getHasResearchStation()),
	            () -> assertEquals(previousSize + 1, currentSize), // check if the card is discarded
	            () -> assertEquals(previousCount - 1, currentCount) //check if the card is removed from the player's hand
	    );
	}
	
	@Test
	void notEnoughCardsForRequestingFindCureActionExceptionTest() {
		
	    Throwable exception = assertThrows(RuntimeException.class, 
	    		() -> new FindCureAction(disease, game, player));
	    assertEquals("Illegal find cure request: There is not enough player cards of the same disease in the player's hand!", exception.getMessage());
	   
	}
	
	@Test
	void noResearchStationFoundExceptionTest() {
		Card playerCard1 = new PlayerCard(player.getCurrentLocation().getName(), disease.getName());
		player.addToHand(playerCard1);
		player.addToHand(playerCard1);
		player.addToHand(playerCard1);
		player.addToHand(playerCard1);
		player.addToHand(playerCard1);
	    Throwable exception = assertThrows(RuntimeException.class, 
	    		() -> new FindCureAction(disease, game, player));
	    assertEquals("Illegal find cure request: There is no research station in the player's city!", exception.getMessage());
	}
	
	@Test
	public void findCureActionTest(){
		Card playerCard1 = new PlayerCard(player.getCurrentLocation().getName(), disease.getName());
		player.addToHand(playerCard1);
		player.addToHand(playerCard1);
		player.addToHand(playerCard1);
		player.addToHand(playerCard1);
		player.addToHand(playerCard1);
		player.getCurrentLocation().setHasResearchStation(true);
		
		int previousSize = player.getHand().size();
		int previousCount = game.getDiscardedPlayerCards().size();
		
		action = new FindCureAction(disease, game, player);
		player.act(action);
		
		int currentSize = player.getHand().size();
		int currentCount = game.getDiscardedPlayerCards().size();
		
		Disease disease = game.getDiseases().findIfCustom(d->((Disease) d).getName() == this.disease.getName());
		if(disease != null) {
			assertAll(
		            () -> assertTrue(disease.getHasCure()),
		            () -> assertEquals(previousSize - 5, currentSize), //check if 5 cards are removed from the player's hand
		            () -> assertEquals(previousCount + 5, currentCount) //check if 5 cards are added to pile of discarded cards
		    );
		}
		else
			assertTrue(false);
	}
	
	@Test
	public void treatDiseaseWithNoRemedyActionTest(){
		
		city1.addInfectionCube(new InfectionCube(disease));
		city1.addInfectionCube(new InfectionCube(disease));
		
		List<InfectionCube> cubesOfDisease = city1.getInfectionCubes(disease.getName());
		int previousSize = cubesOfDisease.size();
		
		action = new TreatDiseaseAction(disease, player.getCurrentLocation(), game.getDiseases());
		player.act(action);
		
		cubesOfDisease = city1.getInfectionCubes(disease.getName());
		int currentSize = cubesOfDisease.size();
		
		assertEquals(previousSize - 1, currentSize); 
	}
	@Test
	public void treatDiseaseWithRemedyActionTest(){
		
		city1.addInfectionCube(new InfectionCube(disease));
		city1.addInfectionCube(new InfectionCube(disease));
		
		Disease disease = game.getDiseases().findIfCustom(d->d.getName() == this.disease.getName());
		disease.setHasCure(true);
		
		action = new TreatDiseaseAction(disease, player.getCurrentLocation(), game.getDiseases());
		player.act(action); 
		
		List<InfectionCube> cubesOfDisease = city1.getInfectionCubes(disease.getName());// In this case, all the cubes of the disease should be removed from the city1
		int currentSize = cubesOfDisease.size();
		
		assertEquals(0, currentSize); 
		
		
	}
	
	

}

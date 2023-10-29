package com.yazdi.pandemic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeAll;


import java.util.List;

public class ActionTest {
	
	static City city1, city2, city3;
	static Player player;
	static Card playerCard;
	static Game game;
	Action action;
	static Disease disease;
	
	

	@BeforeAll
	public static void init(){
		city1 = new City("Mashhad");
		city2 = new City("Zahedan");
		/*
		 * 
		 City1 and city2 are neighbors*/
		 
		city1.addNeighbour(city2);
		city2.addNeighbour(city1);
		
		city3 = new City("Tehran");
		
		disease = new Disease("Influenza", false);
	    city1.addInfectionCube(new InfectionCube(disease));
	    city2.addInfectionCube(new InfectionCube(disease));
	    
        player = new Player();
        player.setCurrentLocation(city1);
        
        playerCard = new PlayerCard(city1.getName(), disease.getName());
        player.addToHand(playerCard);
        
       
        game = new Game();
        game.addCity(city1);
        game.addCity(city2);
        game.addCity(city3);
        game.addDisease(disease);
	}                                        
	
	@Test
	void illegalMoveRequestExceptionTest() {
	    Throwable exception = assertThrows(RuntimeException.class, 
	    		() -> new MoveAction(player, city3));
	    assertEquals("Illegal move request!", exception.getMessage());
	}
	
	@Test
	public void moveActionTest(){
		
		action = new MoveAction(player, city1);
		ActionTest.player.act(action);
		   
		assertTrue(player.getCurrentLocation() == city1);
	}
	
	@Test
	void illegalBuildResearchStationRequestExceptionTest() {
	    Throwable exception = assertThrows(RuntimeException.class, 
	    		() -> new BuildResearchStationAction(player.getHand(), city3, game.getDiscardedPlayerCards()));
	    assertEquals("Illegal build request!", exception.getMessage());
	}
	
	@Test
	public void buildResearchStationActionTest(){
		
		int previousSize = game.getDiscardedPlayerCards().size();
		int previousCount = player.getHand().size();
		
		action = new BuildResearchStationAction(player.getHand(), city1, game.getDiscardedPlayerCards());
		ActionTest.player.act(action);
		
		int currentSize = game.getDiscardedPlayerCards().size();
		int currentCount = player.getHand().size();
		
		assertAll(
	            () -> assertTrue(city1.getHasResearchStation()),
	            () -> assertEquals(previousSize + 1, currentSize), // check if the card is discarded
	            () -> assertEquals(previousCount - 1, currentCount) //check if the card is removed from the player's hand
	    );
	}
	
	@Test
	void notEnoughCardsExceptionTest() {
	    Throwable exception = assertThrows(RuntimeException.class, 
	    		() -> new FindCureAction(ActionTest.disease, ActionTest.game.getDiseases(), player));
	    assertEquals("Illegal find cure request: There is not enough player cards with the same disease in the player's hand!", exception.getMessage());
	}
	
	@Test
	public void findCureActionTest(){
		
		action = new FindCureAction(ActionTest.disease, ActionTest.game.getDiseases(), player);
		ActionTest.player.act(action);
		
		Disease disease = game.getDiseases().findIfCustom(d->((Disease) d).getName() == ActionTest.disease.getName());
		if(disease != null) {
			assertTrue(disease.getHasCure());
		}
		else
			assertTrue(false);
	}
	
	@Test
	public void treatDiseaseActionTest(){
		
		List<InfectionCube> cubesOfDisease = city1.getInfectionCubes(disease.getName());
		int previousSize = cubesOfDisease.size();
		
		action = new TreatDiseaseAction(disease, city1, ActionTest.game.getDiseases());
		ActionTest.player.act(action);
		
		cubesOfDisease = city1.getInfectionCubes(disease.getName());
		int currentSize = cubesOfDisease.size();
		
		assertEquals(previousSize - 1, currentSize);
	}
	
	

}

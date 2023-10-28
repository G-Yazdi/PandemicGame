package com.yazdi.pandemic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.Iterator;
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
	    		() -> new BuildResearchStationAction(player, city3));
	    assertEquals("Illegal build request!", exception.getMessage());
	}
	
	@Test
	public void buildResearchStationActionTest(){
		
		int previousSize = game.getDiscardedPlayerCards().size();
		int previousCount = player.getHand().size();
		
		action = new BuildResearchStationAction(player, city1);
		ActionTest.player.act(action);
		
		int currentSize = game.getDiscardedPlayerCards().size();
		int currentCount = player.getHand().size();
		
		assertAll(
	            () -> assertTrue(city1.getHasResearchStation()),
	            () -> assertEquals(previousSize + 1, currentSize), // check if card is discarded
	            () -> assertEquals(previousCount - 1, currentCount) //check if card is removed from the player's hand
	    );
	}
	
	@Test
	public void findCureActionTest(){
		
		action = new FindCureAction(ActionTest.disease, ActionTest.game.getDiseases());
		ActionTest.player.act(action);
		
		List<Disease> diseases = ActionTest.game.getDiseases();
		Iterator<Disease> diseaseIterator = diseases.iterator();
		
		while(diseaseIterator.hasNext()) {
			Disease d = diseaseIterator.next();
				if(d.getName() == ActionTest.disease.getName()) {
					assertTrue(d.getHasCure());
				}		
		}
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

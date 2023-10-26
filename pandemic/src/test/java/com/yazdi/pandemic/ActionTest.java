package com.yazdi.pandemic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

public class ActionTest {
	
	static City city1, city2;
	static Player player;
	static Game game;
	Action action;
	static Disease disease;
	

	@BeforeAll
	public static void init(){
		city1 = new City("mashhad");
		city2 = new City("zahedan");
        player = new Player();
        disease = new Disease("Influenza", false);
        city1.addInfectionCube(new InfectionCube(disease));
        city2.addInfectionCube(new InfectionCube(disease));
        game = new Game();
        game.addCity(city1);
        game.addCity(city2);
        game.addDisease(disease);
	}                                        
	
	@Test
	public void moveActionTest(){
		
		action = new MoveAction(player, city1);
		ActionTest.player.act(action);
		   
		assertTrue(player.getCurrentLocation() == city1);
	}
	
	@Test
	public void buildResearchStationActionTest(){
		
		action = new BuildResearchStationAction(city1);
		ActionTest.player.act(action);
		   
		assertTrue(city1.getHasResearchStation());
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
		
		List<InfectionCube> cubesOfDisease = 
				city1.getInfectionCubes().stream().filter(
				i->i.getDisease().getName() == ActionTest.disease.getName()).toList();
		int previousSize = cubesOfDisease.size();
		
		action = new TreatDiseaseAction(disease, city1, ActionTest.game.getDiseases());
		ActionTest.player.act(action);
		
		cubesOfDisease = city1.getInfectionCubes().stream().filter(
				i->i.getDisease().getName() == ActionTest.disease.getName()).toList();
		int currentSize = cubesOfDisease.size();
		
		assertEquals(previousSize - 1, currentSize);
	}
	
	

}

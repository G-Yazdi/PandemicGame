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
		
		action = new FindCureAction(disease);
		ActionTest.player.act(action);
		
		List<City> cities = ActionTest.game.getCities();
		Iterator<City> cityIterator = cities.iterator();
		
		while(cityIterator.hasNext()) {
			((City) cityIterator.next()).getInfectionCubes()
			.forEach((InfectionCube cube)->{
				if(cube.getDisease().getName() == ActionTest.disease.getName())
					assertFalse(!cube.getDisease().getHasCure());
					});
		}
		assertTrue(true);
	}
	
	@Test
	public void treatDiseaseActionTest(){
		
		List<InfectionCube> cubesOfCuredDisease = 
				city1.getInfectionCubes().stream().filter(
				i->i.getDisease().getName() == ActionTest.disease.getName() 
				&& i.getDisease().getHasCure() == true).toList();
		int previousSize = cubesOfCuredDisease.size();
		
		action = new TreatDiseaseAction(disease, city1);
		ActionTest.player.act(action);
		
		cubesOfCuredDisease = city1.getInfectionCubes().stream().filter(
				i->i.getDisease().getName() == ActionTest.disease.getName() 
				&& i.getDisease().getHasCure() == true).toList();
		int currentSize = cubesOfCuredDisease.size();
		
		   
		assertEquals(previousSize - 1, currentSize);
	}
	
	

}

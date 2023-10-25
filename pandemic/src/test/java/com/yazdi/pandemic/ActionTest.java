package com.yazdi.pandemic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

public class ActionTest {
	
	static City city;
	static Player player;
	Action action;
	static Disease disease;
	

	@BeforeAll
	public static void init(){
		city = new City("mashhad");
        player = new Player();
        disease = new Disease("Influenza", false);
        city.addInfectionCube(new InfectionCube(disease));
	}                                        
	
	@Test
	public void moveActionTest(){
		
		action = new MoveAction(player, city);
		ActionTest.player.act(action);
		   
		assertTrue(player.getCurrentLocation() == city);
	}
	
	@Test
	public void buildResearchStationActionTest(){
		
		action = new BuildResearchStationAction(city);
		ActionTest.player.act(action);
		   
		assertTrue(city.getHasResearchStation());
	}
	
	@Test
	public void findCureActionTest(){
		
		action = new FindCureAction(disease);
		ActionTest.player.act(action);
		   
		assertTrue(disease.getHasCure());
	}
	
	@Test
	public void treatDiseaseActionTest(){
		
		List<InfectionCube> cubesOfCuredDisease = 
				city.getInfectionCubes().stream().filter(
				i->i.getDisease().getName() == ActionTest.disease.getName() 
				&& i.getDisease().getHasCure() == true).toList();
		int previousSize = cubesOfCuredDisease.size();
		
		action = new TreatDiseaseAction(disease, city);
		ActionTest.player.act(action);
		
		cubesOfCuredDisease = city.getInfectionCubes().stream().filter(
				i->i.getDisease().getName() == ActionTest.disease.getName() 
				&& i.getDisease().getHasCure() == true).toList();
		int currentSize = cubesOfCuredDisease.size();
		
		   
		assertEquals(previousSize - 1, currentSize);
	}
	
	

}

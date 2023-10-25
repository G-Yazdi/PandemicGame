package com.yazdi.pandemic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ActionTest {
	
	City city;
	Player player;
	Action action;
	Disease disease;

    @BeforeEach                                         
    void setUp() {
    	city = new City("mashhad");
        player = new Player();
        disease = new Disease("Influenza", false);
        
        
    }
	
	@Test
	public void moveActionTest(){
		
		action = new MoveAction(player, city);
		this.player.act(action);
		   
		assertTrue(player.getCurrentLocation() == city);
	}
	
	@Test
	public void buildResearchStationActionTest(){
		
		action = new BuildResearchStationAction(city);
		this.player.act(action);
		   
		assertTrue(city.getHasResearchStation());
	}
	
	@Test
	public void findCureActionTest(){
		
		action = new FindCureAction(disease);
		this.player.act(action);
		   
		assertTrue(disease.getHasCure());
	}
	
	@Test
	public void treatDiseaseActionTest(){
		
		List<InfectionCube> cubesOfCuredDisease = city.getInfectionCubes().stream().filter(
				i->i.getDisease().getName() == this.disease.getName() 
				&& i.getDisease().getHasCure() == true).toList();
		int previousSize = cubesOfCuredDisease.size();
		
		action = new TreatDiseaseAction(disease, city);
		this.player.act(action);
		
		cubesOfCuredDisease = city.getInfectionCubes().stream().filter(
				i->i.getDisease().getName() == this.disease.getName() 
				&& i.getDisease().getHasCure() == true).toList();
		int currentSize = cubesOfCuredDisease.size();
		
		   
		assertEquals(previousSize - 1, currentSize);
	}
	
	

}

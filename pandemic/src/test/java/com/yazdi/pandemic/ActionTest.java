package com.yazdi.pandemic;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ActionTest {
	
	City city;
	Player player;
	Action action;

    @BeforeEach                                         
    void setUp() {
    	city = new City("mashhad");
        player = new Player();
        
    }
	
	@Test
	public void moveActionTest(){
		
		action = new MoveAction(player, city);
		action.act();
		   
		assertTrue(player.getCurrentLocation() == city);
	}
	
	@Test
	public void buildResearchStationActionTest(){
		
		action = new BuildResearchStationAction(city);
		action.act();
		   
		assertTrue(city.hasResearchStation());
	}
	
	

}

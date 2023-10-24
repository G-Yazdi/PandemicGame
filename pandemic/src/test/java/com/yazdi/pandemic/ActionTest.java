package com.yazdi.pandemic;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ActionTest {
	
	City destination;
	Player player;
	Action action;

    @BeforeEach                                         
    void setUp() {
    	destination = new City("mashhad");
        player = new Player();
        
    }
	
	@Test
	public void moveActionTest(){
		
		action = new MoveAction(player, destination);
		action.act();
		   
		assertTrue(player.getCurrentLocation() == destination);
	}
	
	

}

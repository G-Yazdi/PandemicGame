package com.yazdi.pandemic;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ActionTest {
	
	City destination;
	Player player;
	ArrayList<Action> actions;

    @BeforeEach                                         
    void setUp() {
    	destination = new City("mashhad");
        player = new player();
        actions = new ArrayList<Action>();
        actions.add(new moveAction ());
    }
	
	@Test
	public void moveActionTest(){
		
		player.setPossibleActions(actions);
		player.actions[0].act(destination);
		   
		assertEquals(destination.getName(), player.getCurrentLocation());
		}
	
	

}

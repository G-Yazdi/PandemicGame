package com.yazdi.pandemic;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * Unit test for City.
 */
public class CityTest
{
	City city;

    @BeforeEach                                         
    void setUp() {
        city = new City("mashhad");
    }

   @Test
   public void addNeighbourTest()
   {
	   int previousSize = city.getNeighbours().size();
	   
	   City newCity = new City("zahedan");
	   city.addNeighbour(newCity);
	   
	   assertEquals(previousSize + 1, city.getNeighbours().size());
       
   }
   
   @Test
   public void addInfectionCubeTest()
   {
	   int previousSize = city.getInfectionCubes().size();
	   
	   InfectionCube newInfectionCube = new InfectionCube("Flue");
	   city.addInfectionCube(newInfectionCube);
	   
	   assertEquals(previousSize + 1, city.getInfectionCubes().size());
       
   }
}

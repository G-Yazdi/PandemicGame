package com.yazdi.pandemic;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import com.yazdi.pandemic.model.City;
import com.yazdi.pandemic.model.Disease;
import com.yazdi.pandemic.model.InfectionCube;

import org.junit.jupiter.api.BeforeEach;

/**
 * Unit test for City.
 */
public class CityTest
{
	City city;
	Disease disease;

    @BeforeEach                                         
    void setUp() {
        city = new City("mashhad");
        disease = new Disease("Influenza", false);
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
	   
	   InfectionCube newInfectionCube = new InfectionCube(disease);
	   city.addInfectionCube(newInfectionCube);
	   
	   assertEquals(previousSize + 1, city.getInfectionCubes().size());
       
   }
}

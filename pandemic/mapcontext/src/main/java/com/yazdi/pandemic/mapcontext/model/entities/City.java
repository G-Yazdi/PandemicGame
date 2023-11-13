package com.yazdi.pandemic.mapcontext.model.entities;

import java.util.concurrent.atomic.AtomicInteger;

import com.yazdi.pandemic.sharedkernel.contracts.ICity;
import com.yazdi.pandemic.sharedkernel.contracts.ICube;
import com.yazdi.pandemic.sharedkernel.utils.CustomArrayList;

public class City implements ICity {
	private int id;
	private static AtomicInteger uniqueId=new AtomicInteger();
	private String name;
	private CustomArrayList<Integer> neighbours;
	private CustomArrayList<ICube> cubes;
	private CustomArrayList<Integer> players;
	private boolean hasResearchStation;
	
	
	public City(String name) {
		this.id = uniqueId.getAndIncrement();
		this.setName(name);
		this.setHasResearchStation(false);
		this.neighbours = new CustomArrayList<Integer>();
		this.cubes = new CustomArrayList<ICube>();
		this.players = new CustomArrayList<Integer>();
	}
	public City(int id, String name, CustomArrayList<Integer> neighbours, CustomArrayList<ICube> cubes, CustomArrayList<Integer> players, boolean hasResearchStation) {
		this.setId(id);
		this.setName(name);
		this.setNeighbours(neighbours);
		this.setCubes(cubes);
		this.setPlayers(players);
		this.setHasResearchStation(hasResearchStation);
	}
	public void setId(int id) {
		this.id = id;
	}

	public boolean isHasResearchStation() {
		return hasResearchStation;
	}

	public void setHasResearchStation(boolean hasResearchStation) {
		this.hasResearchStation = hasResearchStation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CustomArrayList<Integer> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(CustomArrayList<Integer> neighbours) {
		this.neighbours = neighbours;
	}

	public void removePlayer(int playerId) {
		this.players.remove(Integer.valueOf(playerId)); 
	}
	public void addPlayer(int playerId) {
		this.players.add(Integer.valueOf(playerId));
	}
	public int getId() {
		return this.id;
	}

	public CustomArrayList<ICube> getCubes() {
		return cubes;
	}

	public void setCubes(CustomArrayList<ICube> cubes) {
		this.cubes = cubes;
	}
	public CustomArrayList<Integer> getPlayers() {
		return players;
	}
	public void setPlayers(CustomArrayList<Integer> players) {
		this.players = players;
	}
}


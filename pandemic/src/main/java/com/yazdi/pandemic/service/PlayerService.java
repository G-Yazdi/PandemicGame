package com.yazdi.pandemic.service;

import com.yazdi.pandemic.model.Card;
import com.yazdi.pandemic.model.City;
import com.yazdi.pandemic.model.Command;
import com.yazdi.pandemic.model.Role;
import com.yazdi.pandemic.utils.CustomArrayList;

public interface PlayerService {
	
	City getCurrentLocation();
	void setCurrentLocation(City currentLocation);
	void performAction(Command command);
	CustomArrayList<Card> getHand();
	void setHand(CustomArrayList<Card> playerCards);
	void addToHand(Card playerCard);
	void removeFromHand(Card playerCard);
	Card removeFromHandByCity(String cityName);
	Role getRole();

}

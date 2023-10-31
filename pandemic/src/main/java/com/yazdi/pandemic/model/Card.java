package com.yazdi.pandemic.model;

import com.yazdi.pandemic.utils.CustomArrayList;

public interface Card {
	
	public void discard(CustomArrayList<Card> discarded);

}

package com.yazdi.pandemic.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

public class CustomArrayList<E> extends ArrayList<E> {
	
	public E removeIfCustom(Predicate<? super E> filter){
		E element = findIfCustom(filter);
		if(element != null) {
			this.remove(element);
		}
		return element;
		
	}
	public void removeAllIfCustom(Predicate<? super E> filter){
		Iterator<E> iterator = iterator();
		do {
			E element = iterator.next();
			if(filter.test(element)) {
				iterator.remove();
			}
		}while(iterator.hasNext());
		
	}
	public CustomArrayList<E> removeNElementsIfCustom(Predicate<? super E> filter, int n){
		Iterator<E> iterator = iterator();
		CustomArrayList<E> removedElements = new CustomArrayList<E>();
		int count = 0;
		do {
			E element = iterator.next();
			if(filter.test(element)) {
				iterator.remove();
				removedElements.add(element);
				count++;
			}
		}while(iterator.hasNext() && count < n);
		return removedElements;
		
	}
	
	public E findIfCustom(Predicate<? super E> filter){
		Iterator<E> iterator = iterator();
		while(iterator.hasNext()) {
			E element = iterator.next();
			if(filter.test(element)) {
				return element;
			}
		}
		return null;
		
	}
	public CustomArrayList<E> findElementsIfCustom(Predicate<? super E> filter){
		Iterator<E> iterator = iterator();
		CustomArrayList<E> elements = new CustomArrayList<E>();
		while(iterator.hasNext()) {
			E element = iterator.next();
			if(filter.test(element)) {
				elements.add(element);
			}
		}
		return elements;
		
	}
	

}

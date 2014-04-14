package fr.enib.cai.springframework.model.business;

import java.util.Collection;

import fr.enib.cai.springframework.pojo.Beer;
import fr.enib.cai.springframework.pojo.BeerStock;

public interface BeerStore {

	public abstract Collection<Beer> getBeers();
	
	public abstract Collection<Beer> 	  getBeers( final String pseudo);
	
	public abstract Collection<BeerStock> getBar( final String pseudo );

	public abstract Beer addBeer(Beer beer, final String pseudo);

	public abstract Beer removeBeer(int id, final String pseudo);

	public abstract int checkin(String pseudo, int id, int amount);
	
	public abstract int checkout(String pseudo, int id, int amount);



}
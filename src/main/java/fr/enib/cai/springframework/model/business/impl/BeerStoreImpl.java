package fr.enib.cai.springframework.model.business.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import fr.enib.cai.springframework.model.business.BeerStore;
import fr.enib.cai.springframework.pojo.Beer;
import fr.enib.cai.springframework.pojo.BeerStock;

public class BeerStoreImpl implements BeerStore {

	private static ConcurrentMap<Integer, Beer> beers = new ConcurrentHashMap<Integer,Beer>();
	private static ConcurrentMap<Integer, Set<String>> memberBeers = new ConcurrentHashMap<Integer,Set<String>>();
	private static ConcurrentMap<String, Integer> memberBeerStock = new ConcurrentHashMap<String, Integer>();
	
	private static AtomicInteger idSequence = new AtomicInteger(1);
	
	public BeerStoreImpl() {
	}
		
	/* (non-Javadoc)
	 * @see fr.enib.cai.springframework.model.beer.BeerStore#getBeers()
	 */
	@Override
	public Collection<Beer> getBeers() {
		return beers.values();
	}
	
	
	@Override
	public Collection<Beer> getBeers(String pseudo) {
		List<Beer> userBeers = new ArrayList<Beer>();
		
		for( Entry<Integer, Set<String>> entry : memberBeers.entrySet() ) {
			if( entry.getValue().contains(pseudo)) {
				userBeers.add(beers.get(entry.getKey()));
			}
		}
		return userBeers;
	}	
	
	
	@Override
	public Collection<BeerStock> getBar(final String pseudo) {
		List<BeerStock> userBeers = new ArrayList<BeerStock>();
		
		for( Entry<Integer, Set<String>> entry : memberBeers.entrySet() ) {
			if( entry.getValue().contains(pseudo)) {
				userBeers.add(new BeerStock(beers.get(entry.getKey()), memberBeerStock.get(pseudo + entry.getKey()) ) );
			}
		}
		return userBeers;
	}
	
	/* (non-Javadoc)
	 * @see fr.enib.cai.springframework.model.beer.BeerStore#addBeer(fr.enib.cai.springframework.pojo.Beer)
	 */
	@Override
	public Beer addBeer( final Beer beer, final String pseudo) {		
		Beer returnBeer = null;
		// If the beer already exists retrieve it
		for( Entry<Integer, Beer> entry : beers.entrySet() ) {
			if( entry.getValue().equals(beer) ) {
				returnBeer = entry.getValue();
				break;
			}
		}		
	
		// Create the beer
		if ( returnBeer == null) {
				int beerId = idSequence.incrementAndGet();
				beer.setId(beerId);
		       beers.put(beerId, beer);
		       returnBeer = beer;
		}
		
		// adds the beer id to the user
		Set<String> members = memberBeers.get(returnBeer.getId());
		
		// first beer creation
		if( members == null ) {
			members = new HashSet<String>();
			members.add(pseudo);
			
			memberBeers.put(returnBeer.getId(), members);
			memberBeerStock.put(pseudo + returnBeer.getId(), 0);
		} else {
			members.add(pseudo);
		}
		
		return returnBeer;
	}
	
	/* (non-Javadoc)
	 * @see fr.enib.cai.springframework.model.beer.BeerStore#removeBeer(int)
	 */
	@Override
	public Beer removeBeer( int id, final String pseudo) {
		Set<String> members = memberBeers.get(id);
		Beer returnBeer = null;
		
		if( members != null ) {
			members.remove(pseudo);
			memberBeerStock.remove(pseudo + id);
		}
		
		returnBeer = beers.get(id);
		
		if( members.isEmpty()) {
			memberBeers.remove(id);
			beers.remove(id);
		}
		return returnBeer;
	}

	@Override
	public int checkin(String pseudo, int id, int amount) {
		Integer currentStock = memberBeerStock.get( pseudo+id);
		currentStock = currentStock + amount;
		memberBeerStock.put( pseudo+id, currentStock);
		return currentStock;
	}
	
	@Override
	public int checkout(String pseudo, int id, int amount) {
		Integer currentStock = memberBeerStock.get( pseudo+id);
		currentStock = currentStock - amount;
		if ( currentStock < 0 ) {
			currentStock = 0;
		}
		memberBeerStock.put( pseudo+id, currentStock);
		return currentStock;
	}
}

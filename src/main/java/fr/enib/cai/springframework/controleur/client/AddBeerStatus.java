package fr.enib.cai.springframework.controleur.client;

import java.util.Map;

import fr.enib.cai.springframework.pojo.Beer;


public final class AddBeerStatus {

	public boolean isCreated() {
		return created;
	}
	
	public Map<String,String> getFailures() {
		return failures;
	}
	
	public Beer getBeer() {
		return beer;
	}
	
	public static AddBeerStatus created( Beer beer  ) {
		return new AddBeerStatus(true, beer, null);
	}

	public static AddBeerStatus notCreated( Map<String,String> failures) {
		return new AddBeerStatus(false, new Beer(), failures);
	}
		
	private boolean created;
	
	private Beer beer;
	
	private Map<String,String> failures;
	
	private AddBeerStatus(boolean created, Beer beer, Map<String,String> failures) {
		this.created = created;
		this.beer = beer;
		this.failures = failures;
	}
	
}
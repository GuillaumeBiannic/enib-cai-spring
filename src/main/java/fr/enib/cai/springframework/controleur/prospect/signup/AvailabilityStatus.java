package fr.enib.cai.springframework.controleur.prospect.signup;

import java.util.Map;


public final class AvailabilityStatus {

	public boolean isAvailable() {
		return available;
	}
	
	public String[] getSuggestions() {
		return suggestions;
	}
	
	public Map<String,String> getFailures() {
		return failures;
	}
	
	public static AvailabilityStatus available( Map<String,String> failures ) {
		return new AvailabilityStatus(true,  new String[0], failures);
	}

	public static AvailabilityStatus notAvailable(String name, String[] suggestions, Map<String,String> failures) {
		return new AvailabilityStatus(false, suggestions, failures);
	}
		
	private boolean available;
	
	private String[] suggestions;
	
	private Map<String,String> failures;
	
	private AvailabilityStatus(boolean available, String[] suggestions, Map<String,String> failures) {
		this.available = available;
		this.suggestions = suggestions;
		this.failures = failures;
	}
	
}
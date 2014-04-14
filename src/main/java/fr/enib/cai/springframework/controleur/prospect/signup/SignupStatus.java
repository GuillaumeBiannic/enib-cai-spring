package fr.enib.cai.springframework.controleur.prospect.signup;

import java.util.Map;


public final class SignupStatus {
	
	public boolean isCreated() {
		return created;
	}
	
	public Map<String,String> getFailures() {
		return failures;
	}
	
	public static SignupStatus error( Map<String,String> failures ) {
		return new SignupStatus(false, failures);
	}

	public static SignupStatus created() {
		return new SignupStatus(true, null);
	}
		
	private boolean created;
	private Map<String,String> failures;
	
	private SignupStatus(boolean created, Map<String,String> failures) {
		this.created=created;
		this.failures = failures;
	}

}
package fr.enib.cai.springframework.model.core;

public class Acces {
	private String uid;
	private String signInPseudo;
	private Credentials credentials;

	
	public Acces( String uid, String signInPseudo, Credentials credentials ) {
		this.uid = uid;
		this.signInPseudo = signInPseudo;
		this.credentials = credentials;
	}
	
	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSignInPseudo() {
		return signInPseudo;
	}

	public void setSignInPseudo(String signInPseudo) {
		this.signInPseudo = signInPseudo;
	}



	public Object getAuthorities() {
		return null;
	}
}

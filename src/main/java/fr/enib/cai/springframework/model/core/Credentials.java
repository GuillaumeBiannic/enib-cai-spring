package fr.enib.cai.springframework.model.core;

import java.util.Date;

public class Credentials {
	private String passwordHash;
	private String hashAglorithm;
	private Date   creation;
	
	public Credentials( String passwordHash, String algorithm) {
		this.passwordHash = passwordHash;
		this.hashAglorithm = algorithm;
		this.creation = new Date();
	}
	
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public String getHashAglorithm() {
		return hashAglorithm;
	}
	public void setHashAglorithm(String hashAglorithm) {
		this.hashAglorithm = hashAglorithm;
	}
	public Date getCreation() {
		return creation;
	}
	public void setCreation(Date creation) {
		this.creation = creation;
	} 
}

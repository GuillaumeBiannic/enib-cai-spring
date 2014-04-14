package fr.enib.cai.springframework.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.core.style.ToStringCreator;

public class Account {
	private Long id;

	@NotNull
	@Size(min = 6, max = 26)
	private String pseudo;
	
	@NotNull
	@NotBlank
	private String firstname;
	
	@NotNull
	@NotBlank	
	private String lastname;

	@NotNull 
	@NotBlank	
	private String password;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}	

	public String toString() {
		return new ToStringCreator(this).append("id", id).append("pseudo", pseudo)
				.toString();
	}	
}

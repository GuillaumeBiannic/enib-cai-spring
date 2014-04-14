package fr.enib.cai.springframework.pojo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.NumberFormat;

// Beer POJO
public class Beer {

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	@NotBlank
	private String brewery;

	@NotNull
	@NotBlank
	private String country;

	@NotNull
	@NumberFormat
	private double alcohol;

	private int id;

	public Beer() {
		super();
	}

	public Beer(String name, String brewery, String country, double alcohol) {
		super();
		this.name = name;
		this.brewery = brewery;
		this.country = country;
		this.alcohol = alcohol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrewery() {
		return brewery;
	}

	public void setBrewery(String brewery) {
		this.brewery = brewery;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(double alcohol) {
		this.alcohol = alcohol;
	}

	public boolean equals(Object otherBeer) {
		String beerstring = name + brewery + country + alcohol;
		beerstring = beerstring.toUpperCase();
		beerstring.replaceAll("\\s", "");

		String otherbeerstring = ((Beer) otherBeer).getName() + ((Beer) otherBeer).getBrewery() + ((Beer) otherBeer).getCountry() + ((Beer) otherBeer).getAlcohol();
		otherbeerstring = otherbeerstring.toUpperCase();
		otherbeerstring.replaceAll("\\s", "");

		return (beerstring.equals(otherbeerstring));
	}

	public String toString() {
		return new ToStringCreator(this).append("id", id).toString();
	}
}

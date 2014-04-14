package fr.enib.cai.springframework.pojo;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

// Beer POJO
public class Stock {

	@NotNull
	@NumberFormat
	private int amount;

	@NotNull
	@NumberFormat
	private int id;
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public Stock() {
		super();
	}


}

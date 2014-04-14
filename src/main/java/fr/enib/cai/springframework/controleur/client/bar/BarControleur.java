package fr.enib.cai.springframework.controleur.client.bar;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import fr.enib.cai.springframework.model.business.BeerStore;

@Controller
public class BarControleur {
	private Validator validator;
	
	@Autowired
	private BeerStore beerBusiness;
	
	@Autowired
	public BarControleur(Validator validator) {
		this.validator = validator;
	}
	

}

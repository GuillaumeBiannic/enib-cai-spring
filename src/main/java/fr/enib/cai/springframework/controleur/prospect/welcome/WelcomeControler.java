package fr.enib.cai.springframework.controleur.prospect.welcome;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.enib.cai.springframework.model.business.BeerStore;
import fr.enib.cai.springframework.pojo.Beer;

@Controller
@RequestMapping(value="/")
public class WelcomeControler {
	@Autowired
	private BeerStore beerBusiness;

	@RequestMapping(method=RequestMethod.GET)
	public String getWelcomePage(ModelMap model) {
		Collection<Beer> beers = beerBusiness.getBeers();
		
		model.addAttribute("beers", beers);
		return "welcome";
	}
	
	public void setBeerBusiness(BeerStore beerBusiness) {
		this.beerBusiness = beerBusiness;
	}
}

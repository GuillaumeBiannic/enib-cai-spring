package fr.enib.cai.springframework.controleur.client.home;

import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.enib.cai.springframework.controleur.client.AddBeerStatus;
import fr.enib.cai.springframework.controleur.client.CheckInCheckOutStatus;
import fr.enib.cai.springframework.model.business.BeerStore;
import fr.enib.cai.springframework.pojo.Beer;
import fr.enib.cai.springframework.pojo.Stock;

@Controller
public class HomeControleur {
	private Validator validator;
	
	@Autowired
	private BeerStore beerBusiness;
	
	@Autowired
	public HomeControleur(Validator validator) {
		this.validator = validator;
	}
	
	@RequestMapping(value = "/private/home", method = RequestMethod.GET)
	public String printHome(ModelMap model, Principal principal) {

		String name = principal.getName();

		model.addAttribute("username", name);
		model.addAttribute("message", "Spring Security Custom Form example");
		
		model.addAttribute("beer",  new Beer());
		Collection<Beer> beers = beerBusiness.getBeers(name);	
		model.addAttribute("beers", beers);
		
		return "/private/home";
	}
	
	@RequestMapping(value = "/private/beer/add", method = RequestMethod.POST)
	public @ResponseBody
	AddBeerStatus create(@RequestBody Beer beer, Principal principal, HttpServletResponse response) {
		try {
			Set<ConstraintViolation<Beer>> failures = validator.validate(beer);
			if (!failures.isEmpty()) {
				return AddBeerStatus.notCreated(validationMessages(failures));
			} else {
				Beer newBeer = beerBusiness.addBeer(beer, principal.getName());
				return AddBeerStatus.created(newBeer );			}
			
		} catch (Exception exp) {
			// oh someting bad appends
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return AddBeerStatus.notCreated(null);
		}
	}
	
	@RequestMapping(value = "/private/beer/validate/{field}", method = RequestMethod.POST)
	public @ResponseBody
	AddBeerStatus validateField(@PathVariable String field, @RequestBody Beer beer, HttpServletResponse response) {
		try {
			Set<ConstraintViolation<Beer>> failures = validator.validateProperty(beer, field);
			if (!failures.isEmpty()) {
				return AddBeerStatus.notCreated(validationMessages(failures));
			} else {
				return AddBeerStatus.notCreated(null);
			}
		} catch (Exception exp) {
			// oh someting bad appends
			exp.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return AddBeerStatus.notCreated(null);
		}
	}	
	
	
	@RequestMapping(value = "/private/beer/delete", method = RequestMethod.POST)
	public @ResponseBody
	Beer delete(@RequestBody Beer beer, Principal principal, HttpServletResponse response) {
		try {
			Beer deletedBeer = beerBusiness.removeBeer(beer.getId(), principal.getName());
			return deletedBeer;
		} catch (Exception exp) {
			// oh someting bad appends
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return new Beer();
		}
	}	
	// internal helpers
	private Map<String, String> validationMessages(Set<ConstraintViolation<Beer>> failures) {
		Map<String, String> failureMessages = new HashMap<String, String>();
		for (ConstraintViolation<Beer> failure : failures) {
			failureMessages.put(failure.getPropertyPath().toString(), failure.getMessage());
		}
		return failureMessages;
	}	
}

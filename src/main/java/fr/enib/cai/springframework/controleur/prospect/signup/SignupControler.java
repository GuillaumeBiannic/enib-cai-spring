package fr.enib.cai.springframework.controleur.prospect.signup;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.enib.cai.springframework.model.business.AccesStore;
import fr.enib.cai.springframework.pojo.Account;

@Controller
@RequestMapping(value = "/signup")
public class SignupControler {


	@Autowired
	private AccesStore accesStore;

	@RequestMapping(method = RequestMethod.GET)
	public String getCreateAccountForm(Model model) {
		model.addAttribute(new Account());
		return "signup/createAccountForm";
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public 
	String create( @Valid Account account, BindingResult result, ModelMap model, HttpServletResponse response) {
		try {
			// validation errors
			if( result.hasErrors()) {
				return "signup/createAccountForm";
			} 
			
			// create the acces
			boolean accescreated = accesStore.create(account.getPseudo(), account.getPassword());
			
			if( accescreated == false ) {
				// adds a message
				model.addAttribute("message", "Unable to create the acces, try again");
				return "signup/createAccountForm";
			}
			return "signup/done";
		} catch (Exception exp) {
			// oh someting bad appends
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			
		}
		return "signup/createAccountForm";
	}
	
	public void setAccesStore(AccesStore accesStore) {
		this.accesStore = accesStore;
	}
}

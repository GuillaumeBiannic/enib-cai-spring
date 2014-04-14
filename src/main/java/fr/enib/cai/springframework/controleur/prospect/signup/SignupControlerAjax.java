package fr.enib.cai.springframework.controleur.prospect.signup;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.enib.cai.springframework.model.business.AccesStore;
import fr.enib.cai.springframework.pojo.Account;

@Controller
@RequestMapping(value = "/signup-ajax")
public class SignupControlerAjax {

	private Validator validator;

	@Autowired
	private AccesStore accesStore;

	@Autowired
	public SignupControlerAjax(Validator validator) {
		this.validator = validator;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getCreateAccountForm(Model model) {
		model.addAttribute(new Account());
		return "signup/createAccountFormAjax";
	}

	@RequestMapping(value = "/availability", method = RequestMethod.POST)
	// Do the Jackson JSON deserialisation -> Account
	public @ResponseBody
	AvailabilityStatus getAvailability(@RequestBody Account account, HttpServletResponse response) {
		try {
			Set<ConstraintViolation<Account>> failures = validator.validateProperty(account, "pseudo");
			String[] suggestions = null;
			boolean isUserNameAvailable = accesStore.isAvailable(account.getPseudo());
			if (isUserNameAvailable) {
				return AvailabilityStatus.available(validationMessages(failures));
			} else {
				suggestions = accesStore.getSuggestions(account.getPseudo());
				return AvailabilityStatus.notAvailable(account.getPseudo(), suggestions, validationMessages(failures));
			}
		} catch (Exception exp) {
			// oh someting bad appends
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}

	@RequestMapping(value = "/validate/{field}", method = RequestMethod.POST)
	public @ResponseBody
	SignupStatus validateField(@PathVariable String field, @RequestBody Account account, HttpServletResponse response) {
		try {
			Set<ConstraintViolation<Account>> failures = validator.validateProperty(account, field);
			if (!failures.isEmpty()) {
				return SignupStatus.error(validationMessages(failures));
			} else {
				return SignupStatus.error(null);
			}
		} catch (Exception exp) {
			// oh someting bad appends
			exp.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return SignupStatus.error(null);
		}
	}		
	
	
	@RequestMapping(method = RequestMethod.POST)
	public  @ResponseBody
	SignupStatus create( @RequestBody Account account, HttpServletResponse response) {
		try {
			Set<ConstraintViolation<Account>> failures = validator.validate(account);
			if (!failures.isEmpty()) {
				return SignupStatus.error(validationMessages(failures));
			} else {
				accesStore.create(account.getPseudo(), account.getPassword());
				return SignupStatus.created();
			}
		} catch (Exception exp) {
			// oh someting bad appends
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}

	// internal helpers
	private Map<String, String> validationMessages(Set<ConstraintViolation<Account>> failures) {
		Map<String, String> failureMessages = new HashMap<String, String>();
		for (ConstraintViolation<Account> failure : failures) {
			failureMessages.put(failure.getPropertyPath().toString(), failure.getMessage());
		}
		return failureMessages;
	}

	public void setAccesStore(AccesStore accesStore) {
		this.accesStore = accesStore;
	}
}

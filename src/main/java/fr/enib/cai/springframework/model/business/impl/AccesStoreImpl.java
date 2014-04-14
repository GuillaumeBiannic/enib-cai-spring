package fr.enib.cai.springframework.model.business.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;

import fr.enib.cai.springframework.model.business.AccesStore;
import fr.enib.cai.springframework.model.core.Acces;

public class AccesStoreImpl implements AccesStore {
	private static Map<String, Acces> accounts = new ConcurrentHashMap<String, Acces>();
	private static Map<String, User> users = new ConcurrentHashMap<String, User>();

	@Autowired
	private UserDetailsManager userDetailsManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public boolean create(String pseudo, String password) throws Exception {
		try {
			// 1 checks of the pseudo is available
			if (userDetailsManager.userExists(pseudo)) {
				return false;
			}

			String hashedPassword = passwordEncoder.encodePassword( password, null );
			
			// create a new user with the hashed password 
			UserDetails userHashedPassword = new User( pseudo, 
														 hashedPassword, 
	                                    				 true, // enabled, 
	                                    				 true, // accountNonExpired, 
	                                    				 true, // credentialsNonExpired, 
	                                    				 true, // accountNonLocked, 
	                                    				 getAuthorities(0) );
		
			userDetailsManager.createUser(userHashedPassword);

			return true;
		} catch (Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
	}

	@Override
	public boolean isAvailable(String pseudo) {
		return (userDetailsManager.userExists(pseudo))?false:true;
	}

	@Override
	public String[] getSuggestions(String pseudo) {
		String[] suggestions = new String[3];
		for (int i = 0; i < suggestions.length; i++) {
			suggestions[i] = pseudo + (i + 1);
		}
		return suggestions;
	}	
		
	public Collection<GrantedAuthority> getAuthorities(Integer access) {
		   // Create a list of grants for this user
		   List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
		    
		   // All users are granted with ROLE_USER access
		   // Therefore this user gets a ROLE_USER by default
		   authList.add(new GrantedAuthorityImpl("ROLE_USER"));
		    
		   // Check if this user has admin access 
		   // We interpret Integer(1) as an admin user
		   if ( access.compareTo(1) == 0) {
		    // User has admin access
		    authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		   }
		 
		   // Return list of granted authorities
		   return authList;
		   }		
}

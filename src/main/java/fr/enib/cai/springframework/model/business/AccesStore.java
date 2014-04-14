package fr.enib.cai.springframework.model.business;


public interface AccesStore {

	public boolean create(String pseudo, String password) throws Exception;
	
	public boolean isAvailable(final String pseudo);
	
	public String[] getSuggestions( final String pseudo );

}

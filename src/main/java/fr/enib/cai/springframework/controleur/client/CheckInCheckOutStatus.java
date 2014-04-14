package fr.enib.cai.springframework.controleur.client;



public final class CheckInCheckOutStatus {

	public boolean isAdded() {
		return added;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public int getBeerId() {
		return beerId;
	}
	
	public static CheckInCheckOutStatus stockChanged( int beerId, int amount  ) {
		return new CheckInCheckOutStatus(true, beerId, amount);
	}

	public static CheckInCheckOutStatus stockNoAdded( int id) {
		return new CheckInCheckOutStatus(false, id, 0);
	}
	
	public static CheckInCheckOutStatus stockNotChanged() {
		return new CheckInCheckOutStatus(false, 0, 0);
	}
	
	private boolean added;
	
	private int amount;
	
	private int beerId;
	
	private CheckInCheckOutStatus(boolean added, int beerId, int amount) {
		this.added = added;
		this.beerId = beerId;
		this.amount = amount;
	}
}
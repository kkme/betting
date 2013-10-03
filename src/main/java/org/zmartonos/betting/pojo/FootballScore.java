package org.zmartonos.betting.pojo;

public class FootballScore {
	private int hostGoals= 0;
	private int guestGoals= 0;

	/**
	 * 
	 */
	public FootballScore(){
	}
	
	/**
	 * 
	 * @param hostGoals
	 * @param guestGoals
	 */
	public FootballScore(int hostGoals, int guestGoals) {
		this.hostGoals = hostGoals;
		this.guestGoals = guestGoals;
	}
	
	public int getHostGoals() {
		return hostGoals;
	}

	public void setHostGoals(int hostGoals) {
		this.hostGoals = hostGoals;
	}

	public int getGuestGoals() {
		return guestGoals;
	}

	public void setGuestGoals(int guestGoals) {
		this.guestGoals = guestGoals;
	}
}

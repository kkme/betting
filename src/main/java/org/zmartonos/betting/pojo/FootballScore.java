package org.zmartonos.betting.pojo;

public class FootballScore {
	private int homeGoals= 0;
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
	public FootballScore(int homeGoals, int guestGoals) {
		this.homeGoals = homeGoals;
		this.guestGoals = guestGoals;
	}
	
	public int getHomeGoals() {
		return homeGoals;
	}

	public void setHomeGoals(int homeGoals) {
		this.homeGoals = homeGoals;
	}

	public int getGuestGoals() {
		return guestGoals;
	}

	public void setGuestGoals(int guestGoals) {
		this.guestGoals = guestGoals;
	}
}

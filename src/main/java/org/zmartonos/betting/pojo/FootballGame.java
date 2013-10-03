package org.zmartonos.betting.pojo;

/**
 * 
 * @author zootanka
 *
 */
public final class FootballGame {
	private final FootballTeam host;
	private final FootballTeam guest;
	private FootballScore finalScore;
	private FootballScore halfTimeScore;
	
	/**
	 * @param host
	 * @param guest
	 */
	public FootballGame(FootballTeam host, FootballTeam guest) {
		this.host = host;
		this.guest = guest;
	}

	public FootballTeam getHost() {
		return host;
	}

	public FootballTeam getGuest() {
		return guest;
	}

	public FootballScore getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(FootballScore finalScore) {
		this.finalScore = finalScore;
	}

	public FootballScore getHalfTimeScore() {
		return halfTimeScore;
	}

	public void setHalfTimeScore(FootballScore halfTimeScore) {
		this.halfTimeScore = halfTimeScore;
	}
}

package org.zmartonos.betting.pojo;

/**
 * 
 * @author zootanka
 *
 */
public final class FootballGame {
	private FootballTeam host;
	private FootballTeam guest;
	private FootballScore finalScore;
	private FootballScore halfTimeScore;
	
	@SuppressWarnings("unused")
	private FootballGame(){
	}

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

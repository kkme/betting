package org.zmartonos.betting.pojo;

public class FootballGameBetResult {
	private FootballGameBet footballGameBet;
	private FootballScore score;
	private float totalWin;
	private float totalStake;

	private FootballGameBetResult(){	
	}

	public FootballGameBetResult(FootballGameBet footballGameBet,
			FootballScore score, float totalWin, float totalStake) {
		super();
		this.footballGameBet = footballGameBet;
		this.score = score;
		this.totalWin = totalWin;
		this.totalStake = totalStake;
	}

	public FootballGameBet getFootballGameBet() {
		return footballGameBet;
	}

	public void setFootballGameBet(FootballGameBet footballGameBet) {
		this.footballGameBet = footballGameBet;
	}

	public FootballScore getScore() {
		return score;
	}

	public void setScore(FootballScore score) {
		this.score = score;
	}

	public float getTotalWin() {
		return totalWin;
	}

	public void setTotalWin(float totalWin) {
		this.totalWin = totalWin;
	}

	public float getTotalStake() {
		return totalStake;
	}

	public void setTotalStake(float totalStake) {
		this.totalStake = totalStake;
	}
}

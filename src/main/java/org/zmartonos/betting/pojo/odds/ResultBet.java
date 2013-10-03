package org.zmartonos.betting.pojo.odds;

import org.zmartonos.betting.pojo.FootballScore;

public class ResultBet implements BetOption {
	private FootballScore score;
	private float homeOdds;
	private float drawOdds;
	private float guestOdds;
	private TeamOption option;

	public TeamOption getOption() {
		return option;
	}

	public void setOption(TeamOption option) {
		this.option = option;
	}

	protected ResultBet(){
	}
	
	public ResultBet(FootballScore score, float home, float draw, float guest, TeamOption option) {
		super();
		this.score = score;
		this.homeOdds = home;
		this.drawOdds = draw;
		this.guestOdds = guest;
		this.option= option;
	}
	
	public FootballScore getScore() {
		return score;
	}

	public void setScore(FootballScore score) {
		this.score = score;
	}
	
	public float getHome() {
		return homeOdds;
	}

	public void setHome(float home) {
		this.homeOdds = home;
	}

	public float getDraw() {
		return drawOdds;
	}

	public void setDraw(float draw) {
		this.drawOdds = draw;
	}

	public float getGuest() {
		return guestOdds;
	}

	public void setGuest(float guest) {
		this.guestOdds = guest;
	}
}

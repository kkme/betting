package org.zmartonos.betting.pojo.bet.types;

import org.zmartonos.betting.pojo.bet.AbstractBet;
import org.zmartonos.betting.pojo.bet.options.TeamOption;

public class HalfTimeStandingBet extends AbstractBet{
	private TeamOption team;
	
	@SuppressWarnings("unused")
	private HalfTimeStandingBet(){
	}

	public HalfTimeStandingBet(float odds, float stake,
			float guestOdds, TeamOption team) {
		super(odds,stake);
		this.team= team;
	}

	public TeamOption getTeam() {
		return team;
	}

	public void setTeam(TeamOption team) {
		this.team = team;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return String.format("HalfTimeStandingBet: Team: %s Odds: %2.2f Stake: %2.2f Won: %s",
				team, odds, stake, won);
	}
}

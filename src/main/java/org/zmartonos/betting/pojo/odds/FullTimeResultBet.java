package org.zmartonos.betting.pojo.odds;

import org.zmartonos.betting.pojo.FootballScore;

public class FullTimeResultBet extends ResultBet{

	@SuppressWarnings("unused")
	private FullTimeResultBet(){
	}

	public FullTimeResultBet(FootballScore score, float homeOdds, float drawOdds,
			float guestOdds) {
		super(score, homeOdds, drawOdds, guestOdds);
	}	
}

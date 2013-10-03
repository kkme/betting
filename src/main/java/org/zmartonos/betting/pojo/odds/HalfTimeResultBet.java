package org.zmartonos.betting.pojo.odds;

import org.zmartonos.betting.pojo.FootballScore;

public class HalfTimeResultBet extends ResultBet{

	@SuppressWarnings("unused")
	private HalfTimeResultBet(){
	}

	public HalfTimeResultBet(FootballScore score, float homeOdds, float drawOdds,
			float guestOdds) {
		super(score, homeOdds, drawOdds, guestOdds);
	}	
}

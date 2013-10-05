package org.zmartonos.betting.pojo.bet.types;

import org.zmartonos.betting.pojo.FootballScore;
import org.zmartonos.betting.pojo.bet.AbstractBet;
import org.zmartonos.betting.pojo.bet.options.TeamOption;

public class HalfTimeResultBet extends AbstractBet{

	@SuppressWarnings("unused")
	private HalfTimeResultBet(){
	}

	public HalfTimeResultBet(FootballScore score, float odds, float stake,
			float guestOdds, TeamOption team) {
		super(odds,stake);
	}	
}

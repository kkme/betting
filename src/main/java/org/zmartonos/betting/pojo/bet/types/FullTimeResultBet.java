package org.zmartonos.betting.pojo.bet.types;

import org.zmartonos.betting.pojo.FootballScore;
import org.zmartonos.betting.pojo.bet.AbstractBet;
import org.zmartonos.betting.pojo.bet.options.TeamOption;

public class FullTimeResultBet extends AbstractBet{

	@SuppressWarnings("unused")
	private FullTimeResultBet(){
	}

	public FullTimeResultBet(FootballScore score, float odds, float stake, TeamOption team) {
		super(odds,stake);
	}	
}

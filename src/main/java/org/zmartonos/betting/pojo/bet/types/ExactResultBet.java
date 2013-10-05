package org.zmartonos.betting.pojo.bet.types;

import org.zmartonos.betting.pojo.FootballScore;
import org.zmartonos.betting.pojo.bet.AbstractBet;
import org.zmartonos.betting.pojo.bet.options.TeamOption;

/**
 * 
 * @author zoli
 *
 */
public class ExactResultBet extends AbstractBet{
	private FootballScore score;
	
	public ExactResultBet(FootballScore score, float odds, float stake, TeamOption option) {
		super(odds,stake);
	}

	public FootballScore getScore() {
		return score;
	}

	public void setScore(FootballScore score) {
		this.score = score;
	}
}

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
	
	@SuppressWarnings("unused")
	private ExactResultBet(){
	}

	public ExactResultBet(FootballScore score, float odds, float stake) {
		super(odds,stake);
	}

	public FootballScore getScore() {
		return score;
	}

	public void setScore(FootballScore score) {
		this.score = score;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return String.format("ExactResultBet: Score: %s Odds: %2.2f Stake: %2.2f Won: %s",
				score.toString(), odds, stake, won);
	}
}

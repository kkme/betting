package org.zmartonos.betting.pojo.bet.types;

import org.zmartonos.betting.pojo.bet.AbstractBet;
import org.zmartonos.betting.pojo.bet.options.YesNoOption;

public class BothTeamsToScoreBet extends AbstractBet{
	private YesNoOption option;

	@SuppressWarnings("unused")
	private BothTeamsToScoreBet(){
	}
	
	public BothTeamsToScoreBet(float odds, float stake, YesNoOption option){
		super(odds,stake);
		this.option= option;
	}
	
	public YesNoOption getOption() {
		return option;
	}

	public void setOption(YesNoOption option) {
		this.option = option;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return String.format("BothTeamsToScoreBet: Option: %s Odds: %2.2f Stake: %2.2f Won: %s",
				option, odds, stake, won);
	}
}

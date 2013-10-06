package org.zmartonos.betting.pojo.bet.types;

import org.zmartonos.betting.pojo.bet.AbstractBet;
import org.zmartonos.betting.pojo.bet.options.DoubleChanceOption;

public class DoubleChanceBet extends AbstractBet{
	private DoubleChanceOption option;
	
	@SuppressWarnings("unused")
	private DoubleChanceBet(){
	}
	
	public DoubleChanceBet(float odds, float stake, DoubleChanceOption option){
		super(odds,stake);
	}
	
	public DoubleChanceOption getOption() {
		return option;
	}

	public void setOption(DoubleChanceOption option) {
		this.option = option;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return String.format("DoubleChanceBet: Option: %s Odds: %2.2f Stake: %2.2f Won: %s",
				option, odds, stake, won);
	}
}

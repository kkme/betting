package org.zmartonos.betting.pojo.bet.types;

import org.zmartonos.betting.pojo.bet.AbstractBet;
import org.zmartonos.betting.pojo.bet.options.OverUnderOption;

public class OverUnderGoalBet extends AbstractBet {
	private float margin;
	private OverUnderOption option;

	@SuppressWarnings("unused")
	private OverUnderGoalBet(){
	}
	
	public OverUnderGoalBet(float margin, float odds, float stake, OverUnderOption option){
		super(odds,stake);
	}
	
	public float getMargin() {
		return margin;
	}

	public void setMargin(float margin) {
		this.margin = margin;
	}

	public OverUnderOption getOption() {
		return option;
	}

	public void setOption(OverUnderOption option) {
		this.option = option;
	}
	
	@Override
	public boolean isValidWithResult() {
		// TODO Auto-generated method stub
		return false;
	}
}

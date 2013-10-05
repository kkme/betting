package org.zmartonos.betting.pojo.bet.types;

import org.zmartonos.betting.pojo.bet.AbstractBet;
import org.zmartonos.betting.pojo.bet.options.TeamOption;
import org.zmartonos.betting.pojo.bet.options.YesNoOption;

public class CleanSheetBet extends AbstractBet{
	private TeamOption team;
	private YesNoOption option;

	@SuppressWarnings("unused")
	private CleanSheetBet(){
	}
	
	public CleanSheetBet(float odds, float stake, TeamOption team, YesNoOption option){
		super(odds,stake);
	}
	
	public TeamOption getTeam() {
		return team;
	}

	public void setTeam(TeamOption team) {
		this.team = team;
	}

	public YesNoOption getOption() {
		return option;
	}

	public void setOption(YesNoOption option) {
		this.option = option;
	}
}

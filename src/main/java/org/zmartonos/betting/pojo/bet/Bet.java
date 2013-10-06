package org.zmartonos.betting.pojo.bet;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.zmartonos.betting.pojo.bet.types.BothTeamsToScoreBet;
import org.zmartonos.betting.pojo.bet.types.CleanSheetBet;
import org.zmartonos.betting.pojo.bet.types.DoubleChanceBet;
import org.zmartonos.betting.pojo.bet.types.DrawNoBetBet;
import org.zmartonos.betting.pojo.bet.types.ExactResultBet;
import org.zmartonos.betting.pojo.bet.types.FullTimeStandingBet;
import org.zmartonos.betting.pojo.bet.types.HalfTimeStandingBet;
import org.zmartonos.betting.pojo.bet.types.OverUnderGoalBet;
import org.zmartonos.betting.pojo.bet.types.WinToNilBet;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @Type(name = "cleanSheet", value = CleanSheetBet.class),
    @Type(name = "exactResult", value = ExactResultBet.class),
    @Type(name = "fullTimeStanding", value = FullTimeStandingBet.class),
    @Type(name = "halfTimeStanding", value = HalfTimeStandingBet.class),
    @Type(name = "overUnderGoal", value = OverUnderGoalBet.class),
    @Type(name = "bothTeamsToScore", value = BothTeamsToScoreBet.class),
    @Type(name = "winToNil", value = WinToNilBet.class),
    @Type(name = "doubleChance", value = DoubleChanceBet.class),
    @Type(name = "drawNoBet", value = DrawNoBetBet.class),
    @Type(name = "exactResult", value = ExactResultBet.class)
})

public interface Bet {
	public float getOdds();
	public void setOdds(float odds);
	public float getStake();
	public void setStake(float stake);
	public void setWon(boolean won);
	public boolean hasWon();
}

package org.zmartonos.betting.pojo.bet;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.zmartonos.betting.pojo.bet.types.CleanSheetBet;
import org.zmartonos.betting.pojo.bet.types.ExactResultBet;
import org.zmartonos.betting.pojo.bet.types.FullTimeResultBet;
import org.zmartonos.betting.pojo.bet.types.HalfTimeResultBet;
import org.zmartonos.betting.pojo.bet.types.OverUnderGoalBet;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @Type(name = "cleanSheet", value = CleanSheetBet.class),
    @Type(name = "exactResult", value = ExactResultBet.class),
    @Type(name = "fullTimeResult", value = FullTimeResultBet.class),
    @Type(name = "halfTimeResult", value = HalfTimeResultBet.class),
    @Type(name = "overUnderGoal", value = OverUnderGoalBet.class)
})

public interface Bet {
	public boolean isValidWithResult();
}

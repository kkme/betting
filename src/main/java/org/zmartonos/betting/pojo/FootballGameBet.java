package org.zmartonos.betting.pojo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.zmartonos.betting.pojo.bet.Bet;
import org.zmartonos.betting.pojo.bet.options.DoubleChanceOption;
import org.zmartonos.betting.pojo.bet.options.OverUnderOption;
import org.zmartonos.betting.pojo.bet.options.TeamOption;
import org.zmartonos.betting.pojo.bet.options.YesNoOption;
import org.zmartonos.betting.pojo.bet.types.BothTeamsToScoreBet;
import org.zmartonos.betting.pojo.bet.types.CleanSheetBet;
import org.zmartonos.betting.pojo.bet.types.DoubleChanceBet;
import org.zmartonos.betting.pojo.bet.types.DrawNoBetBet;
import org.zmartonos.betting.pojo.bet.types.ExactResultBet;
import org.zmartonos.betting.pojo.bet.types.FullTimeStandingBet;
import org.zmartonos.betting.pojo.bet.types.HalfTimeStandingBet;
import org.zmartonos.betting.pojo.bet.types.OverUnderGoalBet;
import org.zmartonos.betting.pojo.bet.types.WinToNilBet;

public class FootballGameBet {
	private FootballGame game;
	private List<Bet> bets;
	private List<Float> stakes;
	
	@JsonIgnore
	private float totalWin;
	@JsonIgnore
	private float totalStake;
	
	@SuppressWarnings("unused")
	private FootballGameBet(){
	}

	public FootballGameBet(FootballGame game, List<Bet> bets) {
		super();
		this.game = game;
		this.bets = bets;
	}

	public FootballGame getGame() {
		return game;
	}

	public void setGame(FootballGame game) {
		this.game = game;
	}

	public List<Bet> getBets() {
		return bets;
	}

	public void setBets(List<Bet> bets) {
		this.bets = bets;
	}
	
	public List<Float> getStakes() {
		return stakes;
	}

	public void setStakes(List<Float> stakes) {
		this.stakes = stakes;
	}
	
	public void updateBetStakes(List<Float> stakes){
		this.stakes= stakes;
		for(int i=0; i< stakes.size(); i++ ){
			bets.get(i).setStake(stakes.get(i));
		}
	}

	public void scanBets(FootballScore halfTimeScore, FootballScore fullTimeScore){
		for(Bet bet: bets){
			if(bet instanceof CleanSheetBet){
				CleanSheetBet tmpBet= (CleanSheetBet)bet;

				if(tmpBet.getTeam().equals(TeamOption.HOME)){
					if(tmpBet.getOption().equals(YesNoOption.YES) && fullTimeScore.getGuestGoals()==0)
						bet.setWon(true);
					else
						if(tmpBet.getOption().equals(YesNoOption.NO) && fullTimeScore.getGuestGoals()>0)
							bet.setWon(true);
				}
				else{
					if(tmpBet.getOption().equals(YesNoOption.YES) && fullTimeScore.getHomeGoals()==0)
						bet.setWon(true);
					else
						if(tmpBet.getOption().equals(YesNoOption.NO) && fullTimeScore.getHomeGoals()>0)
							bet.setWon(true);
				}
			}
			else 
			if(bet instanceof HalfTimeStandingBet){
				HalfTimeStandingBet tmpBet= (HalfTimeStandingBet)bet;
				if(tmpBet.getTeam()==TeamOption.HOME){
					if(halfTimeScore.getHomeGoals()>halfTimeScore.getGuestGoals())
						bet.setWon(true);
				}else
				if(tmpBet.getTeam()==TeamOption.GUEST){
					if(halfTimeScore.getHomeGoals()<halfTimeScore.getGuestGoals())
						bet.setWon(true);
				}else
				if(tmpBet.getTeam()==TeamOption.DRAW){
					if(halfTimeScore.getHomeGoals()==halfTimeScore.getGuestGoals())
						bet.setWon(true);
				}
			}
			else
			if(bet instanceof FullTimeStandingBet){
				FullTimeStandingBet tmpBet= (FullTimeStandingBet)bet;
				if(tmpBet.getTeam()==TeamOption.HOME){
					if(fullTimeScore.getHomeGoals()>fullTimeScore.getGuestGoals())
						bet.setWon(true);
				}else
				if(tmpBet.getTeam()==TeamOption.GUEST){
					if(fullTimeScore.getHomeGoals()<fullTimeScore.getGuestGoals())
						bet.setWon(true);
				}else
				if(tmpBet.getTeam()==TeamOption.DRAW){
					if(fullTimeScore.getHomeGoals()==fullTimeScore.getGuestGoals())
						bet.setWon(true);
				}
			}
			else
			if(bet instanceof OverUnderGoalBet){
				OverUnderGoalBet tmpBet= (OverUnderGoalBet)bet;
				if(tmpBet.getOption()==OverUnderOption.OVER){
					bet.setWon(((float)fullTimeScore.getTotalGoals())>tmpBet.getMargin());
				}
				else
				if(tmpBet.getOption()==OverUnderOption.UNDER){
					bet.setWon(fullTimeScore.getTotalGoals()<tmpBet.getMargin());
				}
			}
			else
			if(bet instanceof BothTeamsToScoreBet){
				BothTeamsToScoreBet tmpBet= (BothTeamsToScoreBet)bet;
				if(tmpBet.getOption()==YesNoOption.YES){
					if(fullTimeScore.getHomeGoals()>0 && fullTimeScore.getGuestGoals()>0)
						bet.setWon(true);
				}
				else
				if(tmpBet.getOption()==YesNoOption.NO){
					if(fullTimeScore.getHomeGoals()==0 || fullTimeScore.getGuestGoals()==0)
						bet.setWon(true);
				}
			}
			else
			if(bet instanceof WinToNilBet){
				WinToNilBet tmpBet= (WinToNilBet)bet;
				if(tmpBet.getTeam()==TeamOption.HOME){
					if(fullTimeScore.getHomeGoals()>0 && fullTimeScore.getGuestGoals()==0)
						bet.setWon(true);
				}
				else
				if(tmpBet.getTeam()==TeamOption.GUEST){
					if(fullTimeScore.getHomeGoals()==0 && fullTimeScore.getGuestGoals()>0)
						bet.setWon(true);
				}
			}
			else
			if(bet instanceof DoubleChanceBet){
				DoubleChanceBet tmpBet= (DoubleChanceBet)bet;
				if(tmpBet.getOption()==DoubleChanceOption.HOME_OR_DRAW){
					if(fullTimeScore.getHomeGoals()>=fullTimeScore.getGuestGoals())
						bet.setWon(true);
				}
				else
				if(tmpBet.getOption()==DoubleChanceOption.DRAW_OR_GUEST){
					if(fullTimeScore.getHomeGoals()<=fullTimeScore.getGuestGoals())
						bet.setWon(true);
				}
				else
				if(tmpBet.getOption()==DoubleChanceOption.GUEST_OR_HOME){
					if(fullTimeScore.getHomeGoals()!=fullTimeScore.getGuestGoals())
						bet.setWon(true);
				}
			}
			else
			if(bet instanceof DrawNoBetBet){
				DrawNoBetBet tmpBet= (DrawNoBetBet)bet;
				if(tmpBet.getTeam()==TeamOption.HOME){
					if(fullTimeScore.getHomeGoals()>fullTimeScore.getGuestGoals())
						bet.setWon(true);
					else
					if(fullTimeScore.getHomeGoals()==fullTimeScore.getGuestGoals()){
						bet.setOdds(1);
						bet.setWon(true);
					}
				}
				else
				if(tmpBet.getTeam()==TeamOption.GUEST){
					if(fullTimeScore.getHomeGoals()<fullTimeScore.getGuestGoals())
						bet.setWon(true);
					else
					if(fullTimeScore.getHomeGoals()==fullTimeScore.getGuestGoals()){
						bet.setOdds(1);
						bet.setWon(true);
					}
				}
			}
			if(bet instanceof ExactResultBet){
				ExactResultBet tmpBet= (ExactResultBet)bet;
				if(tmpBet.getScore().equals(fullTimeScore)){
					bet.setWon(true);
				}
			}
		}
	}
	
	public void initBets(){
		for(Bet bet: bets){
			bet.setWon(false);
		}
	}
	
	public float calculateWinningForScore(FootballScore halfTime, FootballScore fullTime){
		initBets();
		scanBets(halfTime,fullTime);

		float tax= 0.05f;
		float tmp;
		totalStake= totalWin= 0.0f;

		for(Bet bet: bets){
			tmp=0.0f;
			totalStake+=bet.getStake();
			if(bet.hasWon()){
				tmp= bet.getOdds()*bet.getStake();
				tmp-= tmp*tax;
				totalWin+= tmp;
			}
			//System.out.println(bet.toString()+" Amount: "+tmp);
		}

		String message= String.format("%5s OutCome: %s, Total Stake: %2.2f Total Win:%2.2f",
				totalWin > totalStake, fullTime.toString(),totalStake,totalWin);
		//System.out.println(message);
		
		return totalWin;
	}
	
	public float getTotalStake(){
		return totalStake;
	}
	
	public float getTotalWin(){
		return totalWin;
	}
}

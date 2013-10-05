package org.zmartonos.betting.pojo;

import java.util.List;

import org.zmartonos.betting.pojo.bet.Bet;
import org.zmartonos.betting.pojo.bet.options.TeamOption;
import org.zmartonos.betting.pojo.bet.types.CleanSheetBet;

public class FootballGameBet {
	private FootballGame game;
	private List<Bet> bets;
	
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
	
	public void scanBets(FootballScore halfTime, FootballScore fullTime){
		for(Bet bet: bets){
			if(bet instanceof CleanSheetBet){
				CleanSheetBet tmpBet= (CleanSheetBet)bet;
				int goals;
				if(tmpBet.getTeam().equals(TeamOption.HOME)){
					goals= fullTime.getHomeGoals();
				}
				else{
					goals= fullTime.getGuestGoals();
				}
			}
		}
	}
}

package org.zmartonos.betting;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.zmartonos.betting.pojo.FootballGameBet;
import org.zmartonos.betting.pojo.FootballGameBetResult;
import org.zmartonos.betting.pojo.FootballScore;
import org.zmartonos.zlib.utils.Utils;

public class SureBet {
	public static void main(String[] args)throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		FootballGameBet footballGameBet= mapper.readValue(new File("src/test/resources/match02.json"), FootballGameBet.class);
		
		//FootballScore fullTimeScore= new FootballScore(1,0);
		FootballScore halfTimeScore= new FootballScore(0,0);
		List<FootballScore> scores= new ArrayList<FootballScore>();
		List<ArrayList<Float>> permutations= Utils.getPermutationsWithRepetitions(footballGameBet.getStakes(), footballGameBet.getBets().size());
		
		//scores.add(new FootballScore(0,0));
		scores.add(new FootballScore(1,1));
		scores.add(new FootballScore(1,0));
		scores.add(new FootballScore(2,0));
		scores.add(new FootballScore(2,1));
		scores.add(new FootballScore(0,1));
		scores.add(new FootballScore(0,2));
		scores.add(new FootballScore(1,2));
		scores.add(new FootballScore(2,2));

		for(List<Float> permutation: permutations){
			footballGameBet.setStakes(permutation);
			List<FootballGameBetResult> betOutcomes= new ArrayList();
			for(FootballScore score: scores){
				footballGameBet.calculateWinningForScore(halfTimeScore,score);
				
				FootballGameBetResult betResult= new FootballGameBetResult(footballGameBet,score,footballGameBet.getTotalWin(),footballGameBet.getTotalStake());
				betOutcomes.add(betResult);
			}
			
			Collections.sort(betOutcomes, new Comparator<Object>(){
				@Override
				public int compare(Object o1, Object o2) {
					FootballGameBetResult f1= (FootballGameBetResult)o1;
					FootballGameBetResult f2= (FootballGameBetResult)o2;				
					
					if(f2.getTotalWin()>f1.getTotalWin())
						return 1;
					if(f2.getTotalWin()<f1.getTotalWin())
						return -1;
					
					return 0;
				}});
			
			float totalWin=0, totalStake=0;
			int totalScenarios= betOutcomes.size(), winScenarios=0;
			for(FootballGameBetResult betOutcome: betOutcomes){
				totalWin+=betOutcome.getTotalWin();
				totalStake+=betOutcome.getTotalStake();
				
				if(betOutcome.getTotalWin() > betOutcome.getTotalStake()){
					winScenarios++;
				/*System.out.println(String.format("Score: %s Stake: %2.2f Win: %2.2f",
						betOutcome.getScore().toString(), betOutcome.getTotalStake(), betOutcome.getTotalWin()));*/
				}
			}

			if(winScenarios>3)
				System.out.println(String.format("Stakes: %s TotalStake: %4.4f TotalWin: %4.4f Scenarios won: %d/%d",permutation, totalStake, totalWin, winScenarios,totalScenarios));
		}
	}
}

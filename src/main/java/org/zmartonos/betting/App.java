package org.zmartonos.betting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.zmartonos.zlib.utils.Utils;

/**
 * Hello world!
 * 
 */
public class App {
	public static enum Team{
		MANCHESTER("Manchester United"),
		CHELSEA("Chelsea"),
		LIVERPOOL("Liverpool"),
		BARCELONA("Barcelona"),
		GALATASARAY("Galatasaray"),
		ARSENAL("Arsenal"),
		TOTTENHAM("Tottenham"),
		STEAUA("Steaua Bucuresti"),
		PORTO("FC Porto"),
		JUVENTUS("Juventus"),
		BAYERN("Bayern Munich"),
		DORTMUND("Dortmund"),
		FLUMINENSE("Fluminense"),
		OLYMPIACOS("Olympiakos Piraeus"),
		AJAX("Ajax"),
		PSV("PSV"),
		REALMADRID("Real Madrid"),
		PSG("Paris SG");
		
		String value="";
		
		private Team(String value){
			this.value= value;
		}
		
		public String getName(){
			return value;
		}
	}
	
	public static enum TeamLink{
		MANCHESTER("http://www.betexplorer.com/soccer/england/premier-league-2012-2013/teaminfo.php?team_id=ppjDR086"),
		CHELSEA("http://www.betexplorer.com/soccer/england/premier-league-2012-2013/teaminfo.php?team_id=4fGZN2oK"),
		LIVERPOOL("http://www.betexplorer.com/soccer/england/premier-league-2012-2013/teaminfo.php?team_id=lId4TMwf"),
		BARCELONA("http://www.betexplorer.com/soccer/spain/primera-division-2012-2013/teaminfo.php?team_id=SKbpVP5K"),
		GALATASARAY("http://www.betexplorer.com/soccer/turkey/superliga-2012-2013/teaminfo.php?team_id=riaqqurF"),
		ARSENAL("http://www.betexplorer.com/soccer/england/premier-league-2012-2013/teaminfo.php?team_id=hA1Zm19f"),
		TOTTENHAM("http://www.betexplorer.com/soccer/england/premier-league-2012-2013/teaminfo.php?team_id=UDg08Ohm"),
		STEAUA("http://www.betexplorer.com/soccer/romania/liga-i-2012-2013/teaminfo.php?team_id=Gv2kjDpN"),
		PORTO("http://www.betexplorer.com/soccer/portugal/portuguese-liga-2012-2013/teaminfo.php?team_id=S2NmScGp"),
		JUVENTUS("http://www.betexplorer.com/soccer/italy/serie-a-2012-2013/teaminfo.php?team_id=C06aJvIB"),
		BAYERN("http://www.betexplorer.com/soccer/germany/bundesliga-2012-2013/teaminfo.php?team_id=nVp0wiqd"),
		DORTMUND("http://www.betexplorer.com/soccer/germany/bundesliga-2012-2013/teaminfo.php?team_id=nP1i5US1"),
		FLUMINENSE("http://www.betexplorer.com/soccer/brazil/serie-a-2012/teaminfo.php?team_id=EV9L3kU4"),
		OLYMPIACOS("http://www.betexplorer.com/soccer/greece/super-league-2012-2013/teaminfo.php?team_id=hnzvnHPS"),
		AJAX("http://www.betexplorer.com/soccer/netherlands/eredivisie-2012-2013/teaminfo.php?team_id=8UOvIwnb"),
		PSV("http://www.betexplorer.com/soccer/netherlands/eredivisie-2012-2013/teaminfo.php?team_id=M9UEHJWi"),
		REALMADRID("http://www.betexplorer.com/soccer/spain/primera-division-2012-2013/teaminfo.php?team_id=W8mj7MDD"),
		PSG("http://www.betexplorer.com/soccer/france/ligue-1-2012-2013/teaminfo.php?team_id=CjhkPw0k");
			
		String link="";
		
		private TeamLink(String link){
			this.link= link;
		}
		
		public String getLink(){
			return link;
		}
	}
	
	public static enum BetOn{
		ALL(0),HOME(1),AWAY(2);

		int mode;
		
		private BetOn(int mode){
			this.mode=mode;
		}
		
		public int getMode(){
			return mode;
		}
	}
	
	public static String getCachedUrlContent(final String url, final String cacheLink) throws IOException{
		String content= "";
		if(Utils.fileExists(cacheLink))
			content= Utils.getFileContent(cacheLink);
		else{
			content= Utils.getUrlContent(url);
			Utils.writeContentToFile(content, cacheLink);
		}
		return content;
	}
	
	public static float season(Team team, boolean dc,BetOn mode, float initialBet)throws Exception{
		String tmpCache= "/var/htmlcache/";
		float bankroll= 10;
		float tax= 0.05f;
		String teamName= team.value;
		String host=TeamLink.valueOf(team.name()).getLink();
		float lostCount= 0;
		float bet= initialBet;
		float prevBet=0f;

		//System.out.println(teamName+" :: "+host);
		host= host.substring(0,host.lastIndexOf("/")+1);

		Pattern pattern= Pattern.compile("(matchdetails[^\"]*)");
		
		String link= TeamLink.valueOf(team.name()).getLink();
		String cacheLink= tmpCache+Utils.getMd5Hash(link);

		String content= getCachedUrlContent(link, cacheLink);
		
		Matcher matcher= pattern.matcher(content);
		Pattern pattern2= Pattern.compile("<tr\\s[^>].*?>.*?</span>bet365(.*?)</tr");//<tr\s[^>].*?>.*?Tipico<.*</tr
		Pattern pattern3= Pattern.compile("data-odd=\"(.*?)\"");
		Pattern pattern4= Pattern.compile("<strong>(.*?)</strong>.*?-.*?<strong>(.*?)</strong>");
		
		Pattern patternContainer= Pattern.compile("<table\\sclass=\"result-table\\steam-matches\"\\s.*?>(.*)?</table>");
		
		matcher= patternContainer.matcher(content);
		matcher.find();
		String container= matcher.group(0);
		Pattern itemPattern= Pattern.compile("class=\"form-bg\\sform-([wdl])\".*?href=\"(match.*?)\"");
		
		Matcher matcherItem= itemPattern.matcher(container);
		
		float total= bet;

		while(matcherItem.find()){
	    	String teamHome="", teamAway="";
	    	String result= matcherItem.group(1);

	    	//get html content
			String matchLink= host+matcherItem.group(2);
			if(dc)
				matchLink +="&section=dc-odds";
			String matchCacheLink= tmpCache+Utils.getMd5Hash(matchLink);

	        String contentMatch= getCachedUrlContent(matchLink, matchCacheLink);
	        Matcher matcher2= pattern2.matcher(contentMatch);
	        matcher2.find();
	        //System.out.println(matcher2.group(1));
	        String odds= matcher2.group(1);
	        Matcher matcher3= pattern3.matcher(odds);
	        List<Float> oddsList= new ArrayList<>();
	        while(matcher3.find()){
	        	//System.out.println(matcher3.group(1));
	        	oddsList.add(Float.valueOf(matcher3.group(1)));
	        }
	        
	        Matcher matcher4= pattern4.matcher(contentMatch);
	        while(matcher4.find()){
	        	//System.out.println(matcher4.group(1));
	        	//System.out.println(matcher4.group(2));
	        	teamHome= matcher4.group(1);
	        	teamAway= matcher4.group(2);
	        }

	        float win= 0;
	        float odd;

	        if(oddsList.size()>2){
		        switch(mode){
		        	case ALL:
		        		if(teamHome.equals(teamName)){
		        			odd=oddsList.get(0);
		        		}
		        		else{
		        			odd=oddsList.get(2);
		        		}
		        		//if(!teamHome.equals(teamName))
		        			//bet= total *( ((90 *odd) - 1) / ((odd - 1) * 100)) /100;
		        		//else{
		        			bet= 1;
		        			if(lostCount>0){
		        				bet+= lostCount/odd;
		        			//}
		        		}
		        		win= odd * bet;
		        		win= win - win * tax;
	
		        		total-=bet;
		        		if (result.equals("w")){
		    	        	total += win;
		    	        	lostCount= 0;
		    	        	System.out.println(total+" :: "+teamHome+" :: "+teamAway+" odd:"+odd+" bet: "+bet+" win: "+win);
		    	        }
		    	        else 
		    	        	if(result.equals("d") && dc){
		    	        		total += win;
		    	        		lostCount= 0;
		    	        	}
		    	        	else {
		    	        		System.out.println(teamHome+" :: "+teamAway+" odd:"+odd+" bet: "+bet+" loss: -"+bet);
		    	        		lostCount= bet;
		    	        	}
		        	break;
					
		        	case HOME:
		        		if (teamHome.equals(teamName)){
		        			total-=bet;
		        			if (result.equals("w") || (dc && (result.equals("w") || result.equals("d")))){
		        				win= oddsList.get(0) * bet;
		        				win= win - win * tax;
		        				total += win;
		        				System.out.println(total+" :: "+teamHome+" :: "+teamAway+" ::"+oddsList.get(0)+" :: "+win+" :: "+bet);
		        				bet= initialBet;
		        			}
		        			else{
		        				System.out.println(total+" loss: "+bet);
		        				bet= -1;
		        			}
		        		}
		        	break;
		        	
		        	case AWAY:
		        		total-= bet;
		        		if (teamAway.equals(teamName)){
		        			if (result.equals("w") || (dc && (result.equals("w") || result.equals("d"))))
		        				total += oddsList.get(2) * bet;
		        		}
		        	break;
	
		        	default:
						break;
		        
		        }
	        }

	        /*
	        if( teamHome.equals(teamName)){
	        	if(result.equals("w"))
	        		winning += oddsList.get(0) * bet;
	        	else
	        		winning -=bet;
	        }*/

	        //System.out.println(String.format("Bet: %s - %s, Result: %s, Odds: %s Stat: %f",teamHome,teamAway,result,oddsList.toString(),total));
	        //break;
		}
		
		System.out.println(String.format("Winning for: %s %2.2f dc:%s",team.getName(),total,dc));
		
		return total;
	}

	public static void main(String[] args) throws Exception {
		/*
		Team teamEnum= Team.BAYERN;
		FootballTeam team= new FootballTeam(teamEnum.getName(),teamEnum.value);
		String statSheetLocation=TeamLink.valueOf(teamEnum.name()).getLink();
		System.out.println(teamEnum+" :: "+statSheetLocation);
		FootballTeamStatSheetAnalizer analizer= new FootballTeamStatSheetAnalizer(team, statSheetLocation);
		analizer.analyzeStatSheet();*/
		/*
		float totalDc=0f, total= 0f;

		for(Team team : Team.values()){
			total += season(team,false);
			totalDc += season(team,true);
		}
		
		System.out.println(String.format("Teams: %d Total: %2.2f TotalDc: %2.2f", Team.values().length, total, totalDc));
		*/
		season(Team.CHELSEA,false,BetOn.HOME,1);
	}
}

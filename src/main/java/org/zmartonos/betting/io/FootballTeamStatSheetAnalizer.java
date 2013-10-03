package org.zmartonos.betting.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.zmartonos.betting.pojo.FootballGame;
import org.zmartonos.betting.pojo.FootballTeam;
import org.zmartonos.zlib.utils.Utils;

public class FootballTeamStatSheetAnalizer {
	private final FootballTeam team;
	private final String statSheetLocation;
	private final String statSheetHost;
	private List<FootballGame> games;
	
	private String tmpCache= "/var/bet/cache/";

	/**
	 * @param team
	 * @param statSheetLocation
	 */
	public FootballTeamStatSheetAnalizer(FootballTeam team,
			String statSheetLocation) {
		this.team = team;
		this.statSheetLocation = statSheetLocation;
		this.statSheetHost= statSheetLocation.substring(0,statSheetLocation.lastIndexOf("/")+1);
	}
	
	public void analyzeStatSheet(){
		String cacheLink= tmpCache+Utils.getMd5Hash(statSheetLocation);

		String content= "";
		try {
			content = getCachedUrlContent(statSheetLocation, cacheLink);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Pattern pattern= Pattern.compile("(matchdetails[^\"]*)");
		
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
		
		while(matcherItem.find()){
	    	String teamHome="", teamAway="";
	    	String result= matcherItem.group(1);
	        
	    	//get html content
			String matchLink= statSheetHost+matcherItem.group(2);
			String matchCacheLink= tmpCache+Utils.getMd5Hash(matchLink);

	        String contentMatch= "";
			try {
				contentMatch = getCachedUrlContent(matchLink, matchCacheLink);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
	        
	        System.out.println(teamHome+" :: "+teamAway+" :: "+oddsList.toString());
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
}

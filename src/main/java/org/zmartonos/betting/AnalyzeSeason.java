package org.zmartonos.betting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.zmartonos.zlib.db.MongoDb;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class AnalyzeSeason {
	
	class Match{
		String home;
		String guest;
		String halfTime;
		String fullTime;
		
		public Match(String home, String guest, String halfTime, String fullTime){
			this.home= home;
			this.guest= guest;
			this.halfTime= halfTime;
			this.fullTime= fullTime;
		}
		
		public boolean isHalfTimeDraw(){
			String[] parts= halfTime.split("-");
			int homeGoals= Integer.valueOf(parts[0].trim());
			int guestGoals= Integer.valueOf(parts[1].trim());
			
			return homeGoals== guestGoals;
		}
	}

	public class Stat{
		String club;
		List<Match> matches= new ArrayList();
		int halfTimeDraw= 0;
		
		public Stat(String club){
			this.club= club;
		}

		public void calculateHalfTime(){
			for(Match match : matches){
				if(match.isHalfTimeDraw())
					halfTimeDraw++;
			}
		}
	}
	
	public AnalyzeSeason()throws Exception{
	  	MongoDb mongo= new MongoDb("localhost",27017,"","","betting");
    	mongo.connect();
    	Map<String,Stat> stats= new HashMap();
    	
    	DBCollection collection = mongo.getDb().getCollection("france2_2012_2013");

    	DBCursor cursor= collection.find();
    	DBObject object;

    	String date, home, score, guest, overOneAndaHalf, overTwoAndaHalf, overThreeAndaHalf, halfTime;
    	List<String> exludeTeams= new ArrayList();
    	exludeTeams.add("Le Havre");
    	//exludeTeams.add("Sedan");
    	//exludeTeams.add("FC Sevilla");
    	//exludeTeams.add("Real Madrid");
    	try {
    	   while(cursor.hasNext()) {
    	       object= cursor.next();
    	       home= object.get("home").toString();
    	       guest= object.get("guest").toString();
    	       halfTime= object.get("halfTimeScore").toString();
    	       score= object.get("fullTimeScore").toString();
    	       
    	       if (!exludeTeams.contains(home) && !exludeTeams.contains(guest)){
	    	       Match match= new Match(home,guest,halfTime,score);
	    	       if(!stats.containsKey(home))
	    	    	   stats.put(home, new Stat(home));
	    	       
	    	       if(!stats.containsKey(guest))
	    	    	   stats.put(guest, new Stat(guest));
	    	       
	    	       stats.get(home).matches.add(match);
	    	       stats.get(guest).matches.add(match);
    	       }
    	   }
    	} finally {
    	   cursor.close();
    	}
    	
    	Iterator<String> it= stats.keySet().iterator();
    	String key;
    	Stat stat;
    	List<Stat> sortedList= new ArrayList<Stat>();
    	while(it.hasNext()){
    		key= it.next();
    		stat= stats.get(key);
    		stat.calculateHalfTime();
    		sortedList.add(stat);
    	}
    	
    	Collections.sort(sortedList,new Comparator<Object>(){
            public int compare(Object o1, Object o2){
            	Stat s1= (Stat)o1;
            	Stat s2= (Stat)o2;

                return s2.halfTimeDraw - s1.halfTimeDraw ;
            } });

    	int matches= 0;
    	int halfTimeDraws= 0;

    	for(Stat cstat: sortedList){
    		System.out.println(cstat.club+": "+cstat.halfTimeDraw+"/"+cstat.matches.size());
    		matches += cstat.matches.size();
    		halfTimeDraws += cstat.halfTimeDraw;
    	}
    	
    	System.out.println(halfTimeDraws/2+"/"+matches/2);
	}
	
	public static void main(String argv[])throws Exception{
		new AnalyzeSeason();
	}
}

package org.zmartonos.betting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.zmartonos.zlib.db.MongoDb;
import org.zmartonos.zlib.selenium.FirefoxBrowser;
import org.zmartonos.zlib.selenium.FirefoxBrowserController;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

public class CrawlSeason {
	public final static By findAllMatches= 
			By.xpath(".//div[@id='matches_by_month']//div[@class='tabberlive']//div[@class='tabbertab ']//table[@class='stat' and @cellspacing=0 and @cellpadding=0]//tr");
	public final static By monthXpath= By.xpath(".//div[@id='matches_by_month']//div[@class='tabberlive']//ul[@class='tabbernav']//li/a");
	public final static By findTds= By.xpath(".//td");
	
	public static void addResultToMongo(MongoDb mongo, Map<String,String> values)throws Exception{
		DBCollection collection = mongo.getDb().getCollection("france2_2012_2013");
		
		collection.insert(new BasicDBObject(values));
	}
	
	public static void main(String argv[])throws Exception{
    	MongoDb mongo= new MongoDb("localhost",27017,"","","betting");
    	mongo.connect();

    	FirefoxBrowser browser= new FirefoxBrowser("/usr/bin/firefox","/home/zootanka/.mozilla/firefox/7g7enit9.default/");
    	FirefoxBrowserController controller= new FirefoxBrowserController(browser);
    	browser.getDriver().get("http://www.soccerstats.com/allmatches.asp?league=france2_2013");
    	
    	List<WebElement> months= controller.findElements(monthXpath);
    	
    	for(WebElement month: months){
    		month.click();
    		List<WebElement> elements= controller.findElements(findAllMatches);
	    	//date, home, score, guest, >1.5, >2.5, >3,5, HT
	    	for(WebElement element: elements){
	    		List<WebElement> tds= element.findElements(findTds);
	    		
	    		String date="", home="", score="", guest="", overOneAndaHalf="", overTwoAndaHalf="", overThreeAndaHalf="", halfTime="";
	    		
	    		try{
	    			date= tds.get(0).getText().trim();
	    			home= tds.get(1).getText().trim();
	    			score= tds.get(2).getText().trim();
	    			guest= tds.get(3).getText().trim();
	    			overOneAndaHalf= tds.get(4).getText().trim();
	    			overTwoAndaHalf= tds.get(5).getText().trim();
	    			overThreeAndaHalf= tds.get(6).getText().trim();
	    			halfTime= tds.get(7).getText().trim();
	    			
	    			Map<String,String> values= new HashMap();
	    			
	    			values.put("date", date);
	    			values.put("home", home);
	    			values.put("guest", guest);
	    			values.put("fullTimeScore", score);
	    			values.put("halfTimeScore", halfTime);
	    			values.put("over1/5Goals", overOneAndaHalf);
	    			values.put("over2/5Goals", overTwoAndaHalf);
	    			values.put("over3/5Goals", overThreeAndaHalf);
	    			
	    			addResultToMongo(mongo,values);
	    			
	    			System.out.println(String.format("date: %s %s %s %s %s %s %s %s", 
	        				date, home, score, guest, overOneAndaHalf, overTwoAndaHalf, overThreeAndaHalf, halfTime));
	    		}
	    		catch(Exception e){
	    			e.printStackTrace();
	    		}
	    	}
    	}
	}
}

package org.zmartonos.betting;

import java.io.File;

import org.codehaus.jackson.map.ObjectMapper;
import org.zmartonos.betting.pojo.FootballGameBet;

public class SureBet {
	public static void main(String[] args)throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		FootballGameBet fgb= mapper.readValue(new File("src/test/resources/match01.json"), FootballGameBet.class);
	}

}

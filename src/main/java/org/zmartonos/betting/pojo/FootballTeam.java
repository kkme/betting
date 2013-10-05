package org.zmartonos.betting.pojo;

/**
 * 
 * @author zootanka
 *
 */
public final class FootballTeam {
	private String name;
	private String constant;
	
	@SuppressWarnings("unused")
	private FootballTeam(){
	}

	/**
	 * @param name
	 * @param constant
	 */
	public FootballTeam(String name, String constant) {
		this.name = name;
		this.constant = constant;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return
	 */
	public String getConstant() {
		return constant;
	}
}

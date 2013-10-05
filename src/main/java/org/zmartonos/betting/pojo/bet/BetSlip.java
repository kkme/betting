package org.zmartonos.betting.pojo.bet;

public class BetSlip {
	private float stake= 0f;
	private Bet bet;
	
	@SuppressWarnings("unused")
	private BetSlip(){
	}
	
	public BetSlip(float stake, Bet bet) {
		super();
		this.stake = stake;
		this.bet = bet;
	}

	public float getStake() {
		return stake;
	}

	public void setStake(float stake) {
		this.stake = stake;
	}

	public Bet getBet() {
		return bet;
	}

	public void setBet(Bet bet) {
		this.bet = bet;
	}
}

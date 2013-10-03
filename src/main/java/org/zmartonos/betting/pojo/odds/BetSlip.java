package org.zmartonos.betting.pojo.odds;

public class BetSlip {
	private float stake= 0f;
	private BetOption bet;
	
	@SuppressWarnings("unused")
	private BetSlip(){
	}
	
	public BetSlip(float stake, BetOption bet) {
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

	public BetOption getBet() {
		return bet;
	}

	public void setBet(BetOption bet) {
		this.bet = bet;
	}
}

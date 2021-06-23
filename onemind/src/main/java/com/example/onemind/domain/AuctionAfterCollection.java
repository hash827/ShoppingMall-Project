package com.example.onemind.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AuctionAfterCollection implements Serializable{
	private String stateAfter;
	private String statePaid;
	private String stateFail;
	private String stateGiveUp;
	private String statePayWait;
	
	public AuctionAfterCollection(String stateAfter, String statePaid, String stateFail, String stateGiveUp,
			String statePayWait) {
		super();
		this.stateAfter = stateAfter;
		this.statePaid = statePaid;
		this.stateFail = stateFail;
		this.stateGiveUp = stateGiveUp;
		this.statePayWait = statePayWait;
	}
	
	public String getStateAfter() {
		return stateAfter;
	}
	public void setStateAfter(String stateAfter) {
		this.stateAfter = stateAfter;
	}
	public String getStatePaid() {
		return statePaid;
	}
	public void setStatePaid(String statePaid) {
		this.statePaid = statePaid;
	}
	public String getStateFail() {
		return stateFail;
	}
	public void setStateFail(String stateFail) {
		this.stateFail = stateFail;
	}
	public String getStateGiveUp() {
		return stateGiveUp;
	}
	public void setStateGiveUp(String stateGiveUp) {
		this.stateGiveUp = stateGiveUp;
	}
	public String getStatePayWait() {
		return statePayWait;
	}
	public void setStatePayWait(String statePayWait) {
		this.statePayWait = statePayWait;
	}
	
	
}
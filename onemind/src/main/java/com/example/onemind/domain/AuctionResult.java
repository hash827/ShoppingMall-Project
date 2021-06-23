package com.example.onemind.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AuctionResult implements Serializable{
	private String auctionId;

	private String processId;
	
	private int price;

	public String getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public AuctionResult(String auctionId, String processId, int price) {
		super();
		this.auctionId = auctionId;
		this.processId = processId;
		this.price = price;
	}
	
	


}
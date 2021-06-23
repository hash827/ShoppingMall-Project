package com.example.onemind.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AuctionProcess implements Serializable{
	private String auctionId;
	
	private String processId;
	
	private String userId;
	
	private int auctionPrice;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getAuctionPrice() {
		return auctionPrice;
	}

	public void setAuctionPrice(int auctionPrice) {
		this.auctionPrice = auctionPrice;
	}
	
	public AuctionProcess(String auctionId, String processId, String userId, int auctionPrice) {
		super();
		this.auctionId = auctionId;
		this.processId = processId;
		this.userId = userId;
		this.auctionPrice = auctionPrice;
	}
	
	
}

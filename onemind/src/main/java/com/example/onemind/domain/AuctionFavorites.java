package com.example.onemind.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AuctionFavorites implements Serializable {
	private String auctionId;

	private String userId;

	public AuctionFavorites(String auctionId, String userId) {
		super();
		this.auctionId = auctionId;
		this.userId = userId;
	}

	public String getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserid(String userId) {
		this.userId = userId;
	}

}
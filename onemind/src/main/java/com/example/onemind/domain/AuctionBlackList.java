package com.example.onemind.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
public class AuctionBlackList implements Serializable{
	private String userId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")	
	private Date giveUpDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getGiveUpDate() {
		return giveUpDate;
	}

	public void setGiveUpDate(Date giveUpDate) {
		this.giveUpDate = giveUpDate;
	}
}

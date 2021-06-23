package com.example.onemind.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
public class Auction implements Serializable{

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")	
	@NotNull
	@Future
	private Date auctionDate;

	private int auctionTime;

	private int startPrice;

	private String shoeSize;

	private String shoesState;

	@NotEmpty
	private String wearingPeriod;

	private String auctionImage;

	@NotEmpty
	private String auctionName;

	@NotEmpty
	private String auctionColor;

	private String employeeNumber;

	private String auctionId;

	private String auctionState;

	private String userId;

	public int getAuctionTime() {
		return auctionTime;
	}

	public Date getAuctionDate() {
		return auctionDate;
	}

	public void setAuctionDate(Date auctionDate) {
		this.auctionDate = auctionDate;
	}

	public void setAuctionTime(int auctionTime) {
		this.auctionTime = auctionTime;
	}

	public int getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(int startPrice) {
		this.startPrice = startPrice;
	}


	public String getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(String shoeSize) {
		this.shoeSize = shoeSize;
	}

	public String getShoesState() {
		return shoesState;
	}

	public void setShoesState(String shoesState) {
		this.shoesState = shoesState;
	}

	public String getWearingPeriod() {
		return wearingPeriod;
	}

	public void setWearingPeriod(String wearingPeriod) {
		this.wearingPeriod = wearingPeriod;
	}

	public String getAuctionImage() {
		return auctionImage;
	}

	public void setAuctionImage(String auctionImage) {
		this.auctionImage = auctionImage;
	}

	public String getAuctionName() {
		return auctionName;
	}

	public void setAuctionName(String auctionName) {
		this.auctionName = auctionName;
	}

	public String getAuctionColor() {
		return auctionColor;
	}

	public void setAuctionColor(String auctionColor) {
		this.auctionColor = auctionColor;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}

	public String getAuctionState() {
		return auctionState;
	}

	public void setAuctionState(String auctionState) {
		this.auctionState = auctionState;
	}



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Auction [auctionDate=" + auctionDate + ", auctionTime=" + auctionTime + ", startPrice=" + startPrice
				+ ", shoeSize=" + shoeSize + ", shoesState=" + shoesState + ", wearingPeriod=" + wearingPeriod
				+ ", auctionImage=" + auctionImage + ", auctionName=" + auctionName + ", auctionColor=" + auctionColor
				+ ", employeeNumber=" + employeeNumber + ", auctionId=" + auctionId + ", auctionState=" + auctionState
				+ ", userId=" + userId + "]";
	}

	

	
}
package com.example.onemind.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AuctionPayData  implements Serializable{
	private String bank;
	private String cardNumber;
	private String cvc;
	private String expiryDate;
	private String cardPassword;
	
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCvc() {
		return cvc;
	}
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getCardPassword() {
		return cardPassword;
	}
	public void setCardPassword(String cardPassword) {
		this.cardPassword = cardPassword;
	}
	
	

}

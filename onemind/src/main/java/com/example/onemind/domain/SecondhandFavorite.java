package com.example.onemind.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class SecondhandFavorite implements Serializable {

	/* Private Fields */
	private String secondhandFavoriteId;
	private String secondhandId;
	private String username;
	private String favoriteCheck;

	/* JavaBeans Properties */
	public String getSecondhandFavoriteId() {
		return secondhandFavoriteId;
	}

	public void setSecondhandFavoriteId(String secondhandFavoriteId) {
		this.secondhandFavoriteId = secondhandFavoriteId;
	}
	
	public String getSecondhandId() {
		return secondhandId;
	}
	
	public void setSecondhandId(String secondhandId) {
		this.secondhandId = secondhandId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFavoriteCheck() {
		return favoriteCheck;
	}

	public void setFavoriteCheck(String favoriteCheck) {
		this.favoriteCheck = favoriteCheck;
	}

	public void initSecondhandFavorite(Account account, Secondhand secondhand) {
		username = account.getUsername();
		setSecondhandId(secondhand.getSecondhandId());
		setFavoriteCheck("1");
		setSecondhandFavoriteId(username + secondhandId);
	}

	public String toString() {
		return getSecondhandFavoriteId();
	}
}
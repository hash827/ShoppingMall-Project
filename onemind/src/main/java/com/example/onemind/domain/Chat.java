package com.example.onemind.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

@SuppressWarnings("serial")
public class Chat implements Serializable {
	
	String chatId;
	String fromId;
	String chatContent;
	Date chatTime;
	int roomNumber;
	
	public String getChatId() {
		return chatId;
	}
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	public Date getChatTime() {
		return chatTime;
	}
	public void setChatTime(Date chatTime) {
		this.chatTime = chatTime;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public void initChat(Account account, int roomNumber, String chatContent) {
		setRoomNumber(roomNumber);
		setChatId(RandomStringUtils.randomAlphanumeric(5));
		setChatContent(chatContent);
		setFromId(account.getUsername());
	}

	public String toString() {
		return getChatId();
	}
}

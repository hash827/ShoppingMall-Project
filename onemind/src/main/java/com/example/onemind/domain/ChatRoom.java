package com.example.onemind.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ChatRoom implements Serializable{
	private int roomNumber;
	private String roomName;
	private String chatRoomState;
	private String username;
	private String secondhandId;
	private String shUsername;
	private String shState;
	private String rfl;
	
	public String getRfl() {
		return rfl;
	}
	public void setRfl(String rfl) {
		this.rfl = rfl;
	}
	public String getShState() {
		return shState;
	}
	public void setShState(String shState) {
		this.shState = shState;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getChatRoomState() {
		return chatRoomState;
	}
	public void setChatRoomState(String chatRoomState) {
		this.chatRoomState = chatRoomState;
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
	public String getShUsername() {
		return shUsername;
	}
	public void setShUsername(String shUsername) {
		this.shUsername = shUsername;
	}
	
	public void initChatRoom(Account account, Secondhand secondhand) {
		setRoomNumber((int)(Math.random()*1000000));
		// roomName
		setChatRoomState("SEND");
		setUsername(account.getUsername());
		setSecondhandId(secondhand.getSecondhandId());
		setShUsername(secondhand.getUsername());
		setShState(secondhand.getSecondhandState());
	}
	
	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + ", roomName=" + roomName + "]";
	}	
}

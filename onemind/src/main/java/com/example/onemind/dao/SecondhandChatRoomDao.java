package com.example.onemind.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.onemind.domain.ChatRoom;

public interface SecondhandChatRoomDao {
	ChatRoom getChatRoom(int roomNumber) throws DataAccessException;
	
	ChatRoom getChatRoomBySecondhandAndUsername(String username, String secondhandId) throws DataAccessException;
	
	List<ChatRoom> getChatRoomsById(String username) throws DataAccessException;
	
	List<ChatRoom> getChatRoomsByShUsername(String shUsername) throws DataAccessException;
	
	List<ChatRoom> getChatRoomsByIdWithRfl(String username, String rfl) throws DataAccessException;

	List<ChatRoom> getChatRoomsByShUsernameWithRfl(String shUsername, String rfl) throws DataAccessException;
	
	void insertChatRoom(ChatRoom chatRoom) throws DataAccessException;
	
	void updateChatRoomRfl(ChatRoom chatRoom) throws DataAccessException;
	
	void deleteChatRoom(ChatRoom chatRoom) throws DataAccessException;
}

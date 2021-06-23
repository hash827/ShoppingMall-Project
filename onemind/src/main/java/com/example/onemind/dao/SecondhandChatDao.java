package com.example.onemind.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.onemind.domain.Chat;

public interface SecondhandChatDao {
	
	List<Chat> getChatsById(String fromId, int roomNumber) throws DataAccessException;
	
	List<Chat> getChatsByRoomNumber(int roomNumber) throws DataAccessException;
	
	void insertChat(Chat chat) throws DataAccessException;
	
	void deleteChat(Chat chat) throws DataAccessException;
}

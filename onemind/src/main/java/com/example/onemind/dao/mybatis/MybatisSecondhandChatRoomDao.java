package com.example.onemind.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.onemind.dao.SecondhandChatRoomDao;
import com.example.onemind.dao.mybatis.mapper.SecondhandChatRoomMapper;
import com.example.onemind.domain.ChatRoom;
import com.example.onemind.domain.Secondhand;

@Repository
public class MybatisSecondhandChatRoomDao implements SecondhandChatRoomDao{

	@Autowired
	private SecondhandChatRoomMapper secondhandChatRoomMapper;
	
	@Transactional
	public ChatRoom getChatRoom(int roomNumber) throws DataAccessException {
		ChatRoom chatRoom = secondhandChatRoomMapper.getChatRoom(roomNumber);
	    return chatRoom;
	}
	
	@Transactional
	public ChatRoom getChatRoomBySecondhandAndUsername(String username, String secondhandId) throws DataAccessException {
		ChatRoom chatRoom = secondhandChatRoomMapper.getChatRoomBySecondhandAndUsername(username, secondhandId);
	    return chatRoom;
	}
	
	public List<ChatRoom> getChatRoomsById(String username) 
			throws DataAccessException {
	    return secondhandChatRoomMapper.getChatRoomsById(username);
	}
	
	public List<ChatRoom> getChatRoomsByShUsername(String shUsername) 
			throws DataAccessException {
	    return secondhandChatRoomMapper.getChatRoomsByShUsername(shUsername);
	}
	
	public List<ChatRoom> getChatRoomsByIdWithRfl(String username, String rfl) 
			throws DataAccessException {
	    return secondhandChatRoomMapper.getChatRoomsByIdWithRfl(username, rfl);
	}
	
	public List<ChatRoom> getChatRoomsByShUsernameWithRfl(String shUsername, String rfl) 
			throws DataAccessException {
	    return secondhandChatRoomMapper.getChatRoomsByShUsernameWithRfl(shUsername, rfl);
	}
	
	@Transactional
	public void insertChatRoom(ChatRoom chatRoom) throws DataAccessException {
		secondhandChatRoomMapper.insertChatRoom(chatRoom);
	}
	
	@Transactional
	public void updateChatRoomRfl(ChatRoom chatRoom) throws DataAccessException {
		secondhandChatRoomMapper.updateChatRoomRfl(chatRoom);
	}
	
	@Transactional
	public void deleteChatRoom(ChatRoom chatRoom) throws DataAccessException {
		secondhandChatRoomMapper.deleteChatRoom(chatRoom);
	}
}

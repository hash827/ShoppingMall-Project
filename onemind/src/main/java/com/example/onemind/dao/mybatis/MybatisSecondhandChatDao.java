package com.example.onemind.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.onemind.dao.SecondhandChatDao;
import com.example.onemind.dao.mybatis.mapper.SecondhandChatMapper;
import com.example.onemind.domain.Chat;
import com.example.onemind.domain.Secondhand;

@Repository
public class MybatisSecondhandChatDao implements SecondhandChatDao {

	@Autowired
	private SecondhandChatMapper secondhandChatMapper;
	
	public List<Chat> getChatsById(String fromId, int roomNumber) 
			throws DataAccessException {
	    return secondhandChatMapper.getChatsById(fromId, roomNumber);
	}
	
	public List<Chat> getChatsByRoomNumber(int roomNumber)
			throws DataAccessException {
	    return secondhandChatMapper.getChatsByRoomNumber(roomNumber);
	}
	
	@Transactional
	public void insertChat(Chat chat) throws DataAccessException {
		secondhandChatMapper.insertChat(chat);
	}
	
	@Transactional
	public void deleteChat(Chat chat) throws DataAccessException {
		secondhandChatMapper.deleteChat(chat);
	}
}

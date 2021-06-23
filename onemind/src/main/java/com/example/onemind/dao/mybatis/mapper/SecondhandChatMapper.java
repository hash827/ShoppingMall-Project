package com.example.onemind.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.onemind.domain.Chat;

@Mapper
public interface SecondhandChatMapper {
	
	List<Chat> getChatsById(String fromId, int roomNumber);

	List<Chat> getChatsByRoomNumber(int roomNumber);
	
	void insertChat(Chat chat);

	void deleteChat(Chat chat);
}

package com.example.onemind.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.onemind.domain.ChatRoom;

@Mapper
public interface SecondhandChatRoomMapper {
	ChatRoom getChatRoom(int roomNumber);
	
	ChatRoom getChatRoomBySecondhandAndUsername(String username, String secondhandId);
	
	List<ChatRoom> getChatRoomsById(String username);
	
	List<ChatRoom> getChatRoomsByShUsername(String shUsername);
	
	List<ChatRoom> getChatRoomsByIdWithRfl(String username, String rfl);

	List<ChatRoom> getChatRoomsByShUsernameWithRfl(String shUsername, String rfl);

	void insertChatRoom(ChatRoom chatRoom);
	
	void updateChatRoomRfl(ChatRoom chatRoom);

	void deleteChatRoom(ChatRoom chatRoom);
}

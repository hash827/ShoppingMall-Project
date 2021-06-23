package com.example.onemind.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.example.onemind.domain.Account;
import com.example.onemind.domain.Chat;
import com.example.onemind.domain.ChatRoom;
import com.example.onemind.domain.Secondhand;
import com.example.onemind.service.PetStoreFacade;

@Controller
public class ChatMainController {

	@Autowired
	private PetStoreFacade petStore;

	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

//	List<ChatRoom> roomList = new ArrayList<ChatRoom>();
//	static int roomNumber = 0;
	private String username = "";
	List<ChatRoom> roomList;
	List<ChatRoom> roomListByShun;
	private int chatRoomNumber = 0;
	List<Chat> chatList;

	@RequestMapping("/chat")
	public ModelAndView chat() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Chat");
		return mv;
	}

	/**
	 * 방 페이지
	 * 
	 * @return
	 */
	@RequestMapping("/shop/secondhandViewChattingList.do")
	public ModelAndView room(@RequestParam(value = "chatRoomState", defaultValue = "SEND") String chatRoomState,
			HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		Account account = petStore.getAccount(userSession.getAccount().getUsername());
		username = account.getUsername();
		roomList = new ArrayList<>();
		if (chatRoomState.equals("SEND")) {
//			roomList = petStore.getChatRoomsById(username);
			roomList.addAll(petStore.getChatRoomsByIdWithRfl(username, "3"));
			roomList.addAll(petStore.getChatRoomsByIdWithRfl(username, "2"));
		} else {
//			roomList = petStore.getChatRoomsByShUsername(username);
			roomList.addAll(petStore.getChatRoomsByShUsernameWithRfl(username, "3"));
			roomList.addAll(petStore.getChatRoomsByShUsernameWithRfl(username, "1"));
		}
//		roomList = petStore.getChatRoomsById(username);
//		roomListByShun = petStore.getChatRoomsByShUsername(username);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ChatRoom");
		mv.addObject(roomList);
		mv.addObject("chatRoomState", chatRoomState);
		String chatting = "chatting";
		mv.addObject("category", chatting);
//		return new ModelAndView("ChatRoom", "chatRoom", petStore.getChatRoomsById(account.getUsername()));
		return mv;
	}

	/**
	 * 방 생성하기
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("/createRoom")
	public @ResponseBody List<ChatRoom> createRoom(@RequestParam HashMap<Object, Object> params,
			HttpServletRequest request) {
		String roomName = (String) params.get("roomName");
		if (roomName != null && !roomName.trim().equals("")) {
			ChatRoom room = new ChatRoom();
			UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
			Account account = petStore.getAccount(userSession.getAccount().getUsername());
			// room.initChatRoom(account);
			room.setRoomName(roomName);
			petStore.insertChatRoom(room);
			roomList = petStore.getChatRoomsById(username);
//			roomListByShun = petStore.getChatRoomsByShUsername(username);
//			roomList.add(room);
		}
		return roomList;
	}

	/**
	 * 방 정보가져오기
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("/getRoom")
	public @ResponseBody List<ChatRoom> getRoom(@RequestParam HashMap<Object, Object> params) {
		return roomList;
	}

	@RequestMapping("/deleteRoom")
	public ModelAndView deleteRoom(@RequestParam HashMap<Object, Object> params, HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		Account account = petStore.getAccount(userSession.getAccount().getUsername());
		String username = account.getUsername(); // 현재 로그인한 사람

		int roomNumber = Integer.parseInt((String) params.get("roomNumber"));
		String un = (String) params.get("username"); // 구매자
		String shun = (String) params.get("shUsername"); // 판매자

		ChatRoom chatRoom = petStore.getChatRoom(roomNumber);
		if (chatRoom.getRfl().equals("3") && username.equals(un)) { // 아직 아무도 나가기를 안 했고 현재 로그인 한 사람이 구매자일 때(보낸채팅에서만 삭제)
			chatRoom.setRfl("1");
			petStore.updateChatRoomRfl(chatRoom);
		} else if (chatRoom.getRfl().equals("3") && username.equals(shun)) { // 아직 아무도 나가기를 안 했고 현재 로그인 한 사람이 판매자일
																				// 때(받은채팅에서만 삭제)
			chatRoom.setRfl("2");
			petStore.updateChatRoomRfl(chatRoom);
		} else if (chatRoom.getRfl().equals("1") || chatRoom.getRfl().equals("2")) { // 둘 중 한 명이 채팅방 나가기를 눌렀고 남은 사람이 채팅방
																			// 나가기를 누른 경우
			petStore.deleteChatRoom(chatRoom);
		}
//		petStore.deleteChatRoom(chatRoom);

		roomList = new ArrayList<>();
		if (chatRoom.getChatRoomState().equals("SEND")) {
			roomList.addAll(petStore.getChatRoomsByIdWithRfl(username, "3"));
			roomList.addAll(petStore.getChatRoomsByIdWithRfl(username, "2"));
			;
		} else {
			roomList.addAll(petStore.getChatRoomsByShUsernameWithRfl(username, "3"));
			roomList.addAll(petStore.getChatRoomsByShUsernameWithRfl(username, "1"));
		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName("ChatRoom");
		mv.addObject(roomList);
		mv.addObject("chatRoomState", chatRoom.getChatRoomState());
		return mv;
	}

	/**
	 * 채팅방
	 * 
	 * @return
	 */
	@RequestMapping("/moveChating")
	public ModelAndView chating(@RequestParam HashMap<Object, Object> params) {
		ModelAndView mv = new ModelAndView();

		int roomNumber = Integer.parseInt((String) params.get("roomNumber"));

		List<ChatRoom> new_list = roomList.stream().filter(o -> o.getRoomNumber() == roomNumber)
				.collect(Collectors.toList());
		if (new_list != null && new_list.size() > 0) {
			chatList = petStore.getChatsByRoomNumber(roomNumber);
			String shUsername = petStore.getChatRoom(roomNumber).getShUsername();

			// secondhand state 받아오기 위한 작업
			ChatRoom cr = petStore.getChatRoom(roomNumber);
			Secondhand sd = petStore.getSecondhand(cr.getSecondhandId());
			String shState = sd.getSecondhandState();

			mv.addObject("roomName", params.get("roomName"));
			mv.addObject("roomNumber", params.get("roomNumber"));
			mv.addObject("username", username);
			mv.addObject("shUsername", shUsername);
			mv.addObject("shState", shState);
			mv.addObject("chatList", chatList);
			mv.setViewName("Chat");
			chatRoomNumber = roomNumber;

		} else {
			mv.setViewName("ChatRoom");
		}
		return mv;
	}

	// https://ttuk-ttak.tistory.com/21
	// https://opentutorials.org/course/53/49
	@RequestMapping("/sendChat")
	public void sendChat(@RequestParam HashMap<Object, Object> params, HttpServletRequest request) throws ParseException {
		String chatting = (String) params.get("chatting");
		System.out.println(chatting);

		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		Account account = petStore.getAccount(userSession.getAccount().getUsername());

		Chat chat = new Chat();
		chat.initChat(account, chatRoomNumber, chatting);
		String chatTime = (String)params.get("chatTime");
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd	HH:mm:ss");	//가운데 공백아니라 tab임

		Date to = transFormat.parse(chatTime);
		chat.setChatTime(to);
		petStore.insertChat(chat);
		
		// 한 쪽만 나갔을 때 다른 한 쪽에서 채팅 보내기를 누르면 다른 쪽 채팅 재생성 //실험용으로 넣어둠 나중에 필요없으면 빼든 하셈
		ChatRoom chatRoom = petStore.getChatRoom(chat.getRoomNumber());
		chatRoom.setRfl("3");
		petStore.updateChatRoomRfl(chatRoom);
	}

	@RequestMapping("/completeChat")
	public void completeChat(@RequestParam HashMap<Object, Object> params, HttpServletRequest request) {
		int roomNumber = Integer.parseInt((String) params.get("roomNumber"));
		ChatRoom chatRoom = petStore.getChatRoom(roomNumber);
		Secondhand secondhand = petStore.getSecondhand(chatRoom.getSecondhandId());
		secondhand.setSecondhandState("COMPLETED");
		Date pd = new Date();
		secondhand.setpDate(pd);
		secondhand.setBuyer(chatRoom.getUsername());
		petStore.updateSecondhandState(secondhand);

		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		Account account = petStore.getAccount(userSession.getAccount().getUsername());

//		Chat chat = new Chat();
//		chat.initChat(account, chatRoomNumber, chatting);
//		petStore.insertChat(chat);
	}

	// SecondhandDetail에서 채팅하기 눌렀을 때
	@RequestMapping("/shop/chatFromSecondhandDetail.do")
	public ModelAndView chatFromDetail(HttpServletRequest request, ModelMap model,
			@RequestParam("secondhandId") String secondhandId) {
		room("SEND", request);
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		Account account = petStore.getAccount(userSession.getAccount().getUsername());
		Secondhand secondhand = petStore.getSecondhand(secondhandId);
		ModelAndView mv = new ModelAndView();

/*		if (secondhand.getSecondhandState().equals("RESERVE") || secondhand.getSecondhandState().equals("COMPLETED")) {
			ChatRoom room = petStore.getChatRoomBySecondhandAndUsername(account.getUsername(), secondhandId);
			if (room != null) {
				int roomNumber = room.getRoomNumber();
				List<ChatRoom> new_list = roomList.stream().filter(o -> o.getRoomNumber() == roomNumber)
						.collect(Collectors.toList());
				if (new_list != null && new_list.size() > 0) {
					chatList = petStore.getChatsByRoomNumber(roomNumber);
					mv.addObject("roomName", room.getRoomName());
					mv.addObject("roomNumber", roomNumber);
					mv.addObject("username", username);
					mv.addObject("chatList", chatList);
					mv.setViewName("Chat");
					chatRoomNumber = roomNumber;
				}
			}
			else {
				// 예약중, 거래완료는 채팅방생성 안됨
				mv.setViewName("redirect:/shop/secondhandViewDetail.do?secondhandId=" + secondhand.getSecondhandId());
			}
		}

		else {*/
			// id랑 secondhand id가 같은 chatroom 있으면 가져오기
			ChatRoom room = petStore.getChatRoomBySecondhandAndUsername(account.getUsername(), secondhandId);
			String roomName = secondhand.getTitle() + " by " + account.getUsername();
			if (roomName != null && !roomName.trim().equals("") && room == null) {
				room = new ChatRoom();
				room.initChatRoom(account, secondhand);
				room.setRoomName(roomName);
				/*
				 * 3: 둘 다 나가기를 안 누름 2: 판매자가 나가기를 누름 1: 구매자가 나가기를 누름 0: 둘 다 나가기를 누름
				 */
				room.setRfl("3");
				petStore.insertChatRoom(room);
				roomList = petStore.getChatRoomsById(username);
//				roomListByShun = petStore.getChatRoomsByShUsername(room.getShUsername());
			}
			int roomNumber = room.getRoomNumber();
			List<ChatRoom> new_list = roomList.stream().filter(o -> o.getRoomNumber() == roomNumber)
					.collect(Collectors.toList());
			if (new_list != null && new_list.size() >= 0) {
				chatList = petStore.getChatsByRoomNumber(roomNumber);
				mv.addObject("roomName", room.getRoomName());
				mv.addObject("roomNumber", roomNumber);
				mv.addObject("username", username);
				mv.addObject("chatList", chatList);
				mv.setViewName("Chat");
				chatRoomNumber = roomNumber;
			} else {
				mv.setViewName("ChatRoom");
			}
		//}
		return mv;
	}

	@RequestMapping("/getChat")
	public @ResponseBody List<Chat> getChat(@RequestParam HashMap<Object, Object> params) {
		return chatList;
	}
}

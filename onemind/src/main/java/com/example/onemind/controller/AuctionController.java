package com.example.onemind.controller;

import java.io.File; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.onemind.domain.Auction;
import com.example.onemind.domain.AuctionAfterCollection;
import com.example.onemind.domain.AuctionBlackList;
import com.example.onemind.domain.AuctionBuyer;
import com.example.onemind.domain.AuctionFavorites;
import com.example.onemind.domain.AuctionPayData;
import com.example.onemind.domain.AuctionProcess;
import com.example.onemind.domain.AuctionResult;
import com.example.onemind.service.PetStoreFacade;

@Controller
@SessionAttributes({ "userSession", "auctionList" })
public class AuctionController {
	//@Autowired
	//private AuctionService auctionService;
	@Autowired
	private PetStoreFacade petStore;

	@RequestMapping("/shop/main/auctionList.do")
	public String auctionMainList(@RequestParam(value = "auctionState", defaultValue = "ALL") String auctionState,
			Model model, HttpServletRequest request) {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		List<String> myFavoriteAuctions = null;
		if (userSession != null) {
			myFavoriteAuctions = petStore.getMyAuctionFavoritesId(userSession.getAccount().getUsername());
		}
		System.out.println(userSession.getAccount().getUsername());

//		List<Auction> auctionList = null;
		PagedListHolder<Auction> auctionList = null;
		if (auctionState.equals("ALL")) {	//case: ALL
			auctionList = new PagedListHolder<Auction>(petStore.getAllAuctionList());
		} else if (auctionState.equals("AFTER")) {	//case: AFTER/PAID/FAIL
			AuctionAfterCollection auctionAfters = new AuctionAfterCollection("AFTER", "PAID", "FAIL", "GIVEUP", "PAYWAIT");
			auctionList = new PagedListHolder<Auction>(petStore.getAuctionListByStateAfter(auctionAfters));
		} else {
			auctionList = new PagedListHolder<Auction>(petStore.getAuctionListByState(auctionState));
		}
		auctionList.setPageSize(10);
		
		model.addAttribute("auctionState", auctionState);
		model.addAttribute("auctionList", auctionList);
		model.addAttribute("myFavoriteAuctions", myFavoriteAuctions);
		model.addAttribute("category", "main");
		return "AuctionMainList";
	}
	@RequestMapping("/shop/main/auctionList2.do")
	public String handleRequestMainList(@RequestParam("page") String page, 
			@RequestParam(value = "auctionState", defaultValue = "ALL") String auctionState,
			@ModelAttribute("auctionList") PagedListHolder<Auction> auctionList, BindingResult result, Model model, HttpServletRequest request)
			throws Exception {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		List<String> myFavoriteAuctions = null;
		if (userSession != null) {
			myFavoriteAuctions = petStore.getMyAuctionFavoritesId(userSession.getAccount().getUsername());
		}
		System.out.println(userSession.getAccount().getUsername());
		
		if (auctionList == null) {
			throw new IllegalStateException("Cannot find pre-loaded");
		}
		if ("next".equals(page)) {
			auctionList.nextPage();
		} else if ("previous".equals(page)) {
			auctionList.previousPage();
		}
		
		model.addAttribute("auctionState", auctionState);
		model.addAttribute("auctionList", auctionList);
		model.addAttribute("myFavoriteAuctions", myFavoriteAuctions);
		model.addAttribute("category", "main");
		return "AuctionMainList";
	}

	@RequestMapping("/shop/auction/list.do")
	public String auctionMyList(HttpServletRequest request, Model model) {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		PagedListHolder<Auction> auctions = new PagedListHolder<Auction>(petStore.getMyAuctionList(userSession.getAccount().getUsername()));
		auctions.setPageSize(10);
		/*
		 * List<Auction> auctions =
		 * petStore.getMyAuctionList(userSession.getAccount().getUsername());
		 */
		
		//블랙리스트에 있는 사용자인지 확인
		AuctionBlackList blackListUser = petStore.getAuctionBlackListUser(userSession.getAccount().getUsername());
		if (blackListUser == null) 
			model.addAttribute("isBlackUser", false);
		else
			model.addAttribute("isBlackUser", true);
		
		model.addAttribute("auctionList", auctions);
		model.addAttribute("category", "list");
		return "AuctionList";
	}
	@RequestMapping("/shop/auction/list2.do")
	public String handleRequest2(@RequestParam("page") String page,
			@ModelAttribute("auctionList") PagedListHolder<Auction> auctionList, BindingResult result,Model model)
			throws Exception {
		if (auctionList == null) {
			throw new IllegalStateException("Cannot find pre-loaded");
		}
		if ("next".equals(page)) {
			auctionList.nextPage();
		} else if ("previous".equals(page)) {
			auctionList.previousPage();
		}
		model.addAttribute("auctionList", auctionList);
		model.addAttribute("category", "list");
		return "AuctionList";
	}
	@RequestMapping("/shop/auction/createForm.do")
	public String auctionCreateForm() {
		return "AuctionCreateForm";
	}

	@RequestMapping("/shop/auction/create.do")
	public String auctionCreate(@Valid Auction auction, BindingResult bindingResult, Model model,
			MultipartHttpServletRequest request) throws IOException {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		if (bindingResult.hasErrors()) {
			return "AuctionCreateForm";
		}
		if (userSession != null) {
			auction.setUserId(userSession.getAccount().getUsername());
		}
		String id = UUID.randomUUID().toString();
		auction.setAuctionId(id);
		auction.setAuctionState("BEFORE");
		if (auction.getAuctionImage() == null) {
			auction.setAuctionImage("imsi");
		}
		auction.setEmployeeNumber(userSession.getAccount().getUsername());

		petStore.insertAuction(auction);
		AuctionResult auctionResult = new AuctionResult(id, null, auction.getStartPrice());
		petStore.insertAuctionResult(auctionResult);

		/*
		 * List<Auction> auctions =
		 * petStore.getMyAuctionList(userSession.getAccount().getUsername());
		 */
		PagedListHolder<Auction> auctions = new PagedListHolder<Auction>(petStore.getMyAuctionList(userSession.getAccount().getUsername()));
		auctions.setPageSize(10);
		model.addAttribute("auctionList", auctions);
		model.addAttribute("category", "list");
		return "AuctionList";
	}

	@RequestMapping("/shop/auction/view.do")
	public String auctionView(@RequestParam String auctionId, Model model, HttpServletRequest request) {
		Auction auction = petStore.getAuction(auctionId);
		AuctionResult auctionResult = petStore.getAuctionResult(auctionId);
		int fi = auctionResult.getPrice();
		model.addAttribute("auction",auction);
		model.addAttribute("fi",fi);
		return "AuctionView";
	}

	@ResponseBody
	@RequestMapping("/shop/auction/upload.do")
	public String auctionUpload(@RequestParam("file1") MultipartFile multi, HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {

		String base_path = "D:\\HSH\\4. 기타\\pro1\\onemind\\src\\main\\resources\\static\\images\\auction";
		String fileName = multi.getOriginalFilename();
		String id = UUID.randomUUID().toString();
		long fileSize = multi.getSize();
		byte[] fileData = multi.getBytes();
		File folder = new File(base_path);
		if (!folder.exists())
			folder.mkdir();
		FileOutputStream fos = new FileOutputStream(base_path + "\\" + id + fileName);
		fos.write(fileData);
		fos.close();

		System.out.println(multi.getOriginalFilename());

		return id + fileName;
	}

	@RequestMapping("/shop/auction/delete.do")
	public String auctionDelete(@RequestParam String auctionId, Model model, HttpServletRequest request) {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		petStore.deleteAuctionFavoriteByAuctionId(auctionId);
		petStore.deleteAuctionResult(auctionId);
		petStore.deleteAuction(auctionId);
		PagedListHolder<Auction> auctions = new PagedListHolder<Auction>(petStore.getMyAuctionList(userSession.getAccount().getUsername()));
		auctions.setPageSize(10);
		model.addAttribute("auctionList", auctions);
		model.addAttribute("category", "list");
		return "AuctionList";
	}

	@RequestMapping("/shop/auction/favorite/delete.do")
	public String auctionFavoriteDelete(@RequestParam String auctionId, Model model, HttpServletRequest request) {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		petStore.deleteMyFavoriteAcution(new AuctionFavorites(auctionId, userSession.getAccount().getUsername()));

		List<AuctionFavorites> favoriteList = petStore.getMyAuctionFavoritesList(userSession.getAccount().getUsername());
		List<Auction> auctionList = new ArrayList<Auction>();
		for (AuctionFavorites auctionFav : favoriteList) {
			Auction auction = petStore.getAuction(auctionFav.getAuctionId());
			auctionList.add(auction);
		}
		
		PagedListHolder<Auction> auctions = new PagedListHolder<Auction>(auctionList);
		auctions.setPageSize(10);
		
		model.addAttribute("auctionList", auctions);
		model.addAttribute("category", "favorite");
		return "AuctionFavoriteListView";
	}

	@RequestMapping("/shop/auction/favorite/add.do")
	public String auctionFavoriteAdd(@RequestParam String auctionId, Model model, HttpServletRequest request) {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		petStore.addMyFavoriteAuction(new AuctionFavorites(auctionId, userSession.getAccount().getUsername()));

		List<AuctionFavorites> favoriteList = petStore.getMyAuctionFavoritesList(userSession.getAccount().getUsername());
		List<Auction> auctionList = new ArrayList<Auction>();
		for (AuctionFavorites auctionFav : favoriteList) {
			Auction auction = petStore.getAuction(auctionFav.getAuctionId());
			auctionList.add(auction);
		}
		
		PagedListHolder<Auction> auctions = new PagedListHolder<Auction>(auctionList);
		auctions.setPageSize(10);
		
		model.addAttribute("auctionList", auctions);
		model.addAttribute("category", "favorite");
		return "AuctionFavoriteListView";
	}

	@RequestMapping("/shop/auction/favorite/list.do")
	public String auctionFavoritesList(Model model, HttpServletRequest request) {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		List<AuctionFavorites> favoriteList = petStore.getMyAuctionFavoritesList(userSession.getAccount().getUsername());
		
		List<Auction> auctionList = new ArrayList<Auction>();
		for (AuctionFavorites auctionFav : favoriteList) {
			Auction auction = petStore.getAuction(auctionFav.getAuctionId());
			auctionList.add(auction);
		}
		PagedListHolder<Auction> auctions = new PagedListHolder<Auction>(auctionList);
		auctions.setPageSize(10);
		
		model.addAttribute("auctionList", auctions);
		model.addAttribute("category", "favorite");
		return "AuctionFavoriteListView";
	}
	@RequestMapping("/shop/auction/favorite/list2.do")
	public String handleRequestFavoritesList(@RequestParam("page") String page,
			@ModelAttribute("auctionList") PagedListHolder<Auction> auctionList, BindingResult result, Model model)
			throws Exception {
		if (auctionList == null) {
			throw new IllegalStateException("Cannot find pre-loaded");
		}
		if ("next".equals(page)) {
			auctionList.nextPage();
		} else if ("previous".equals(page)) {
			auctionList.previousPage();
		}
		model.addAttribute("auctionList", auctionList);
		model.addAttribute("category", "favorite");
		return "AuctionFavoriteListView";
	}

	@RequestMapping("/shop/auction/mainView.do")
	public String auctionMainView(@RequestParam String auctionId, Model model, HttpServletRequest request) {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		Auction auction = petStore.getAuction(auctionId);
		model.addAttribute("auction", auction);
		
		//경매 남은 시간 구하기
		long beginTime = auction.getAuctionDate().getTime()/1000;
		long auctionTime = auction.getAuctionTime()*60;
		long nowTime = System.currentTimeMillis()/1000;	// 현재 시스템 시간 구하기 
		long timeRemaining = auctionTime - (nowTime - beginTime);
		System.out.println(beginTime+ ", " + auctionTime);
		model.addAttribute("timeRemaining", timeRemaining);
		
		//현재 나의 최고금액 전달
		int tmpPrice = 0;
		int myBestPrice = 0;
		AuctionProcess auctionProcess = new AuctionProcess(auctionId, "tmpId", userSession.getAccount().getUsername(), tmpPrice);
		if (petStore.getAuctionProcessExist(auctionProcess) != null) {
			myBestPrice = petStore.getAuctionProcessMyPrice(auctionProcess);
		}
		
		//블랙리스트에 있는 사용자인지 확인
		AuctionBlackList blackListUser = petStore.getAuctionBlackListUser(userSession.getAccount().getUsername());
		if (blackListUser == null) 
			model.addAttribute("isBlackUser", false);
		else
			model.addAttribute("isBlackUser", true);
		
		model.addAttribute("myBestPrice", myBestPrice);
		model.addAttribute("userName", userSession.getAccount().getUsername());
		
		return "AuctionMainView";
	}
	
	@ResponseBody
	@RequestMapping("/shop/auction/auctionResult/get.do")
	public int auctionResult(@RequestParam String auctionId, Model model, HttpServletRequest request) {
		AuctionResult auctionResult = petStore.getAuctionResult(auctionId);
		return auctionResult.getPrice();
	}

	@ResponseBody
	@RequestMapping("/shop/auction/auctionState/get.do")
	public String auctionState(@RequestParam String auctionId, Model model, HttpServletRequest request) {
		Auction auction = petStore.getAuction(auctionId);
		return auction.getAuctionState();
	}
	
	@ResponseBody
	@RequestMapping("/shop/auction/auctionProcess/add.do")
	public void setAuctionProcess(@RequestParam String auctionId, @RequestParam String myPrice, Model model, HttpServletRequest request) {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		int price = Integer.parseInt(myPrice);
		
		//전에 입력한 사용자인지 확인
		AuctionProcess auctionProcess = new AuctionProcess(auctionId, "tmpId", userSession.getAccount().getUsername(), price);
		String processId = petStore.getAuctionProcessExist(auctionProcess);
		
		if (processId == null) {	//insert
			processId = UUID.randomUUID().toString();
			auctionProcess.setProcessId(processId);
			petStore.insertAuctionProcess(auctionProcess);
		} else {	//update
			auctionProcess.setProcessId(processId);
			petStore.updateAuctionProcessPrice(auctionProcess);
		}
		
		//경매최고금액과 processId 변해야 함(result 테이블)
		AuctionResult auctionResult = new AuctionResult(auctionId, processId, price);
		petStore.updateAuctionResult(auctionResult);
	}
	

	@RequestMapping("/shop/auction/myBidList.do")
	public String auctionMyBidList(@RequestParam(value = "auctionState", defaultValue = "ALL") String auctionState, 
			HttpServletRequest request, Model model) {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		List<String> myBidAuctionIds = petStore.getMyAuctionBidList(userSession.getAccount().getUsername());
		
		List<Auction> auctionListName = new ArrayList<Auction>();
		for (String auctionId : myBidAuctionIds) {
			Auction auction = petStore.getAuction(auctionId);
			auctionListName.add(auction);
		}
		
		//전체:ALL/결제대기:PAYWAIT/결제완료:PAID/낙찰포기:GIVEUP
		PagedListHolder<AuctionBuyer> auctionList = null;
		List<AuctionBuyer> auctionBuyerList = new ArrayList<AuctionBuyer>();
		
		if (auctionState.equals("ALL")) {	//case: ALL
			AuctionBuyer auctionBuyer = null;
			for (String auctionId : myBidAuctionIds) {
				auctionBuyer = petStore.getAuctionBuyer(auctionId);
				auctionBuyerList.add(auctionBuyer);
			}
			auctionList = new PagedListHolder<AuctionBuyer>(auctionBuyerList);
		} else {
			AuctionBuyer tmpBuyer = new AuctionBuyer();
			tmpBuyer.setState(auctionState);
			AuctionBuyer auctionBuyer = null;
			for (String auctionId : myBidAuctionIds) {
				tmpBuyer.setAuctionId(auctionId);
				auctionBuyer = petStore.getAuctionBuyerByState(tmpBuyer);
				if (auctionBuyer != null) auctionBuyerList.add(auctionBuyer);
			}
			auctionList = new PagedListHolder<AuctionBuyer>(auctionBuyerList);
		}
		auctionList.setPageSize(10);
		
		System.out.println("개수: " + auctionBuyerList.size());
		
		model.addAttribute("auctionState", auctionState);
		model.addAttribute("auctionList", auctionList);
		model.addAttribute("auctionListName", auctionListName);
		model.addAttribute("category", "bid");
		return "AuctionMyBidList";
	}
	@RequestMapping("/shop/auction/myBidList2.do")
	public String handleRequestMyBidList(@RequestParam("page") String page, 
			@RequestParam(value = "auctionState", defaultValue = "ALL") String auctionState,
			@ModelAttribute("auctionList") PagedListHolder<Auction> auctionList, BindingResult result, Model model, HttpServletRequest request)
			throws Exception {
		if (auctionList == null) {
			throw new IllegalStateException("Cannot find pre-loaded");
		}
		if ("next".equals(page)) {
			auctionList.nextPage();
		} else if ("previous".equals(page)) {
			auctionList.previousPage();
		}
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		
		List<String> myBidAuctionIds = petStore.getMyAuctionBidList(userSession.getAccount().getUsername());
		List<Auction> auctionListName = new ArrayList<Auction>();
		for (String auctionId : myBidAuctionIds) {
			Auction auction = petStore.getAuction(auctionId);
			auctionListName.add(auction);
		}

		model.addAttribute("auctionState", auctionState);
		model.addAttribute("auctionList", auctionList);
		model.addAttribute("auctionListName", auctionListName);
		model.addAttribute("category", "bid");
		return "AuctionMyBidList";
	}
	
	
	@RequestMapping("/shop/auction/bidView.do")
	public String auctionBidView(@RequestParam String auctionId, Model model, HttpServletRequest request) {
		Auction auction = petStore.getAuction(auctionId);
		AuctionBuyer auctionBuyer = petStore.getAuctionBuyer(auctionId);
		model.addAttribute(auction);
		model.addAttribute("auctionBuyer", auctionBuyer);
		return "AuctionBidView";
	}
	
	@RequestMapping("/shop/auction/paymentView.do")
	public String auctionPaymentView(@RequestParam String auctionId, Model model, HttpServletRequest request) {
		Auction auction = petStore.getAuction(auctionId);
		model.addAttribute(auction);
		return "AuctionPaymentView";
	}
	
	@RequestMapping("/shop/auction/auctionPay.do")
	public String auctionPay(@RequestParam String auctionId, AuctionPayData auctionPayData,  
			@RequestParam(value = "auctionState", defaultValue = "ALL") String auctionState, 
			Model model, HttpServletRequest request) {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		Auction auction = petStore.getAuction(auctionId);
		auction.setAuctionState("PAID");
		petStore.updateAuctionState(auction);
		
		AuctionBuyer auctionBuyer = new AuctionBuyer();
		auctionBuyer.setAuctionId(auctionId);
		auctionBuyer.setBank(auctionPayData.getBank());
		auctionBuyer.setCardNumber(auctionPayData.getCardNumber());
		auctionBuyer.setCvc(auctionPayData.getCvc());
		auctionBuyer.setExpiryDate(auctionPayData.getExpiryDate());
		auctionBuyer.setCardPassword(auctionPayData.getCardPassword());
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		auctionBuyer.setPaidDate(timestamp);
		auctionBuyer.setState("PAID");
		
		petStore.updateAuctionBuyer(auctionBuyer);
		
		model.addAttribute(auction);
		
//		//move to list
		List<String> myBidAuctionIds = petStore.getMyAuctionBidList(userSession.getAccount().getUsername());
		
		List<Auction> auctionListName = new ArrayList<Auction>();
		for (String auctionIdTmp : myBidAuctionIds) {
			Auction auctionTmp = petStore.getAuction(auctionIdTmp);
			auctionListName.add(auctionTmp);
		}
		
		//전체:ALL/결제대기:PAYWAIT/결제완료:PAID/낙찰포기:GIVEUP
		PagedListHolder<AuctionBuyer> auctionList = null;
		List<AuctionBuyer> auctionBuyerList = new ArrayList<AuctionBuyer>();
		
		if (auctionState.equals("ALL")) {	//case: ALL
			AuctionBuyer auctionBuyerTmp = null;
			for (String auctionIdTmp : myBidAuctionIds) {
				auctionBuyerTmp = petStore.getAuctionBuyer(auctionIdTmp);
				auctionBuyerList.add(auctionBuyerTmp);
			}
			auctionList = new PagedListHolder<AuctionBuyer>(auctionBuyerList);
		} else {
			AuctionBuyer tmpBuyer = new AuctionBuyer();
			tmpBuyer.setState(auctionState);
			AuctionBuyer auctionBuyerTmp = null;
			for (String auctionIdTmp : myBidAuctionIds) {
				tmpBuyer.setAuctionId(auctionIdTmp);
				auctionBuyerTmp = petStore.getAuctionBuyerByState(tmpBuyer);
				if (auctionBuyerTmp != null) auctionBuyerList.add(auctionBuyerTmp);
			}
			auctionList = new PagedListHolder<AuctionBuyer>(auctionBuyerList);
		}
		auctionList.setPageSize(10);
		
		System.out.println("개수: " + auctionBuyerList.size());
		
		model.addAttribute("auctionState", auctionState);
		model.addAttribute("auctionList", auctionList);
		model.addAttribute("auctionListName", auctionListName);
		model.addAttribute("category", "bid");
		return "AuctionMyBidList";
	}
	
	@RequestMapping("/shop/auction/auctionGiveUp.do")
	public String auctionGiveUp(@RequestParam String auctionId, 
			@RequestParam(value = "auctionState", defaultValue = "ALL") String auctionState, 
			Model model, HttpServletRequest request) {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		Auction auction = petStore.getAuction(auctionId);
		auction.setAuctionState("GIVEUP");
		petStore.updateAuctionState(auction);
		
		AuctionBuyer auctionBuyer = new AuctionBuyer();
		auctionBuyer.setAuctionId(auctionId);
		auctionBuyer.setState("GIVEUP");
		petStore.updateAuctionBuyerState(auctionBuyer);
		
		//블랙리스트에 있는 이미 있는 사용자인지 확인_이미 있으면 giveDate를 업데이트
		//(한 사용자가 한번에 많은 경매에 참여하고 나중에 전부 경매포기를 할 경우)
		AuctionBlackList blackListUser = petStore.getAuctionBlackListUser(userSession.getAccount().getUsername());
		
		AuctionBlackList blackList = new AuctionBlackList();
		blackList.setUserId(userSession.getAccount().getUsername());
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		blackList.setGiveUpDate(timestamp);
		if (blackListUser == null) {
			petStore.addAuctionBlackList(blackList);
		} else {
			petStore.updateAuctionBlackList(blackList);
		}
		
		
//		//move to list
		List<String> myBidAuctionIds = petStore.getMyAuctionBidList(userSession.getAccount().getUsername());
		
		List<Auction> auctionListName = new ArrayList<Auction>();
		for (String auctionIdTmp : myBidAuctionIds) {
			Auction auctionTmp = petStore.getAuction(auctionIdTmp);
			auctionListName.add(auctionTmp);
		}
		
		//전체:ALL/결제대기:PAYWAIT/결제완료:PAID/낙찰포기:GIVEUP
		PagedListHolder<AuctionBuyer> auctionList = null;
		List<AuctionBuyer> auctionBuyerList = new ArrayList<AuctionBuyer>();
		
		if (auctionState.equals("ALL")) {	//case: ALL
			AuctionBuyer auctionBuyerTmp = null;
			for (String auctionIdTmp : myBidAuctionIds) {
				auctionBuyerTmp = petStore.getAuctionBuyer(auctionIdTmp);
				auctionBuyerList.add(auctionBuyerTmp);
			}
			auctionList = new PagedListHolder<AuctionBuyer>(auctionBuyerList);
		} else {
			AuctionBuyer tmpBuyer = new AuctionBuyer();
			tmpBuyer.setState(auctionState);
			AuctionBuyer auctionBuyerTmp = null;
			for (String auctionIdTmp : myBidAuctionIds) {
				tmpBuyer.setAuctionId(auctionIdTmp);
				auctionBuyerTmp = petStore.getAuctionBuyerByState(tmpBuyer);
				if (auctionBuyerTmp != null) auctionBuyerList.add(auctionBuyerTmp);
			}
			auctionList = new PagedListHolder<AuctionBuyer>(auctionBuyerList);
		}
		auctionList.setPageSize(10);
		
		System.out.println("개수: " + auctionBuyerList.size());
		
		model.addAttribute("auctionState", auctionState);
		model.addAttribute("auctionList", auctionList);
		model.addAttribute("auctionListName", auctionListName);
		model.addAttribute("category", "bid");
		return "AuctionMyBidList";
	}
}
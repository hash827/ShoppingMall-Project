package com.example.onemind.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.example.onemind.domain.Auction;
import com.example.onemind.domain.AuctionBuyer;
import com.example.onemind.domain.AuctionResult;
import com.example.onemind.service.PetStoreFacade;

@Controller
public class AuctionScheduled {
	@Autowired
	private PetStoreFacade petStore;

	@Scheduled(fixedDelay = 1000)
	public void updateToNow() {
		List<Auction> auctionList = petStore.getAuctionListByState("BEFORE");
		Date time;
		for (Auction auction : auctionList) {
			time = new Date();
			if (time.after(auction.getAuctionDate())) {
				if ((time.getTime() - auction.getAuctionDate().getTime()) / 1000 < auction.getAuctionTime()*60) {
					auction.setAuctionState("NOW");
					petStore.updateAuctionState(auction);
					System.out.println(auction.getAuctionId() + " CHANGE FROM BEFORE TO NOW");
				}
			}
			if (time.after(auction.getAuctionDate())) {
				if ((time.getTime() - auction.getAuctionDate().getTime()) / 1000 >= auction.getAuctionTime()*60) {
					auction.setAuctionState("AFTER");
					petStore.updateAuctionState(auction);
					System.out.println(auction.getAuctionId() + " CHANGE FROM BEFORE TO AFTER");
				}
			}

		}
	}

	@Scheduled(fixedDelay = 1000)
	public void updateToAfter() {
		List<Auction> auctionList = petStore.getAuctionListByState("NOW");
		Date time;
		for (Auction auction : auctionList) {
			time = new Date();
			if (time.after(auction.getAuctionDate())) {
				if ((time.getTime() - auction.getAuctionDate().getTime()) / 1000 >= auction.getAuctionTime()*60) {
					AuctionResult auctionResult = petStore.getAuctionResult(auction.getAuctionId());
					if (auctionResult.getProcessId() == null) {
						auction.setAuctionState("FAIL");
						System.out.println(auction.getAuctionId() + " CHANGE FROM NOW TO FAIL");
					} else {
						  auction.setAuctionState("AFTER");
						  AuctionBuyer auctionBuyer = new AuctionBuyer();
						  auctionBuyer.setAuctionId(auctionResult.getAuctionId());
						  auctionBuyer.setPrice(auctionResult.getPrice());
						  auctionBuyer.setState("PAYWAIT"); 
						  String processId = auctionResult.getProcessId();
						  String userId = petStore.getAuctionProcessUser(processId);
						  auctionBuyer.setUserId(userId);
						  petStore.insertAuctionBuyer(auctionBuyer);
						 
						System.out.println(auction.getAuctionId() + " CHANGE FROM NOW TO AFTER");
					}
					petStore.updateAuctionState(auction);

				}
			}
		}

	}

	@Scheduled(fixedDelay = 1000)
	public void updateToFail() {
		List<Auction> auctionList = petStore.getAuctionListByState("AFTER");
		for (Auction auction : auctionList) {
			AuctionResult auctionResult = petStore.getAuctionResult(auction.getAuctionId());
			if (auctionResult.getProcessId()== null) {
				auction.setAuctionState("FAIL");
				petStore.updateAuctionState(auction);
				System.out.println(auction.getAuctionId() + " CHANGE FROM AFTER TO FAIL");
			}
		}

	}

}

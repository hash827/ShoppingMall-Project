package com.example.onemind.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import com.example.onemind.domain.Auction;
import com.example.onemind.domain.AuctionAfterCollection;
import com.example.onemind.domain.AuctionBlackList;
import com.example.onemind.domain.AuctionBuyer;
import com.example.onemind.domain.AuctionFavorites;
import com.example.onemind.domain.AuctionProcess;
import com.example.onemind.domain.AuctionResult;

@Mapper
public interface AuctionMapper {

	void insertAuction(Auction auction);
	void deleteAuction(String auctionId);
	List<Auction> getMyAuctionList(String auctionId);
	void insertAuctionResult(AuctionResult auctionResult);
	Auction getAuction(String auctionId);
	void deleteAuctionResult(String auctionId);
	AuctionResult getAuctionResult(String auctionId);
	
	List<Auction> getAllAuctionList();
	List<Auction> getAuctionListByState(String auctionState);
	List<Auction> getAuctionListByStateAfter(AuctionAfterCollection auctionAfters);
	
	List<String> getMyAuctionFavoritesId(String userId);
	List<AuctionFavorites> getMyAuctionFavoritesList(String userId);
	void addMyFavoriteAuction(AuctionFavorites auctionFavorite);
	void deleteMyFavoriteAcution(AuctionFavorites auctionFavorite);
	void updateAuctionState(Auction auction);
	void deleteAuctionFavoriteByAuctionId(String auctionId);
	void insertAuctionBuyer(AuctionBuyer auctionBuyer);
	void insertAuctionProcess(AuctionProcess auctionProcess);
	String getAuctionProcessExist(AuctionProcess auctionProcess);
	int getAuctionProcessMyPrice(AuctionProcess auctionProcess);
	void updateAuctionProcessPrice(AuctionProcess auctionProcess);
	void updateAuctionResult(AuctionResult auctionResult);
	String getAuctionProcessUser(String id);
	List<String> getMyAuctionBidList(String userId);
	void updateAuctionBuyer(AuctionBuyer auctionBuyer);
	void updateAuctionBuyerState(AuctionBuyer auctionBuyer);
	void addAuctionBlackList(AuctionBlackList auctionBlackList);
	AuctionBlackList getAuctionBlackListUser(String userId);
	void updateAuctionBlackList(AuctionBlackList auctionBlackList);
	AuctionBuyer getAuctionBuyer(String auctionId);
	AuctionBuyer getAuctionBuyerByState(AuctionBuyer auctionBuyer);
}
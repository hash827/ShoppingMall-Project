package com.example.onemind.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.onemind.domain.Auction;
import com.example.onemind.domain.AuctionAfterCollection;
import com.example.onemind.domain.AuctionBlackList;
import com.example.onemind.domain.AuctionBuyer;
import com.example.onemind.domain.AuctionFavorites;
import com.example.onemind.domain.AuctionProcess;
import com.example.onemind.domain.AuctionResult;

public interface AuctionDao {
	Auction getAuction(String auctionId) throws DataAccessException;

	void insertAuction(Auction auction) throws DataAccessException;
	
	void deleteAuction(String auctionId) throws DataAccessException;
	
	List<Auction> getMyAuctionList(String auctionId) throws DataAccessException;
	
	void insertAuctionResult(AuctionResult auctionResult) throws DataAccessException;
	 
	void deleteAuctionResult(String auctionId) throws DataAccessException;
	
	AuctionResult getAuctionResult(String auctionId) throws DataAccessException;
	
	List<Auction> getAllAuctionList() throws DataAccessException;

	List<Auction> getAuctionListByState(String auctionState) throws DataAccessException;
	
	List<Auction> getAuctionListByStateAfter(AuctionAfterCollection auctionAfters) throws DataAccessException;
	
	List<String> getMyAuctionFavoritesId(String userId) throws DataAccessException;
	
	List<AuctionFavorites> getMyAuctionFavoritesList(String userId) throws DataAccessException;
	
	void addMyFavoriteAuction(AuctionFavorites auctionFavorite) throws DataAccessException;
	
	void deleteMyFavoriteAcution(AuctionFavorites auctionFavorite) throws DataAccessException;

	 void updateAuctionState(Auction auction);
	 void deleteAuctionFavoriteByAuctionId(String auctionId);
	 void insertAuctionBuyer(AuctionBuyer auctionBuyer);
	 
	 void insertAuctionProcess(AuctionProcess auctionProcess) throws DataAccessException;
	 
	 String getAuctionProcessExist(AuctionProcess auctionProcess) throws DataAccessException;
	 
	 int getAuctionProcessMyPrice(AuctionProcess auctionProcess) throws DataAccessException;
	 
	 void updateAuctionProcessPrice(AuctionProcess auctionProcess) throws DataAccessException;
	 
	 void updateAuctionResult(AuctionResult auctionResult) throws DataAccessException;
	 String  getAuctionProcessUser(String id)throws DataAccessException;
	 
	 List<String> getMyAuctionBidList(String userId) throws DataAccessException;
	 
	 void updateAuctionBuyer(AuctionBuyer auctionBuyer) throws DataAccessException;
	 
	 void updateAuctionBuyerState(AuctionBuyer auctionBuyer) throws DataAccessException;
	 
	 void addAuctionBlackList(AuctionBlackList auctionBlackList) throws DataAccessException;
	 
	 AuctionBlackList getAuctionBlackListUser(String userId) throws DataAccessException;
	 
	 void updateAuctionBlackList(AuctionBlackList auctionBlackList) throws DataAccessException;
	 
	 AuctionBuyer getAuctionBuyer(String auctionId) throws DataAccessException;
	 
	 AuctionBuyer getAuctionBuyerByState(AuctionBuyer auctionBuyer);
}
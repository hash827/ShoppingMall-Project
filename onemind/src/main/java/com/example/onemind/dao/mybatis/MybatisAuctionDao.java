package com.example.onemind.dao.mybatis;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.onemind.dao.AuctionDao;
import com.example.onemind.dao.mybatis.mapper.AuctionMapper;
import com.example.onemind.domain.Auction;
import com.example.onemind.domain.AuctionAfterCollection;
import com.example.onemind.domain.AuctionBlackList;
import com.example.onemind.domain.AuctionBuyer;
import com.example.onemind.domain.AuctionFavorites;
import com.example.onemind.domain.AuctionProcess;
import com.example.onemind.domain.AuctionResult;
@Repository
public class MybatisAuctionDao implements AuctionDao {

	@Autowired 
	AuctionMapper auctionMapper;
	
	@Override
	public Auction getAuction(String auctionId) throws DataAccessException {
		// TODO Auto-generated method stub
		return auctionMapper.getAuction(auctionId);
	}

	@Override
	public void insertAuction(Auction auction) throws DataAccessException {
		// TODO Auto-generated method stub
		auctionMapper.insertAuction(auction);
	}
	@Override
	public void deleteAuction(String auctionId) throws DataAccessException {
		// TODO Auto-generated method stub
		auctionMapper.deleteAuction(auctionId);
	}
	@Override
	public List<Auction> getMyAuctionList(String userId) throws DataAccessException {
		// TODO Auto-generated method stub
		return auctionMapper.getMyAuctionList(userId);
	}
	@Override
	public void insertAuctionResult(AuctionResult auctionResult) throws DataAccessException {
		// TODO Auto-generated method stub
		auctionMapper.insertAuctionResult(auctionResult);
	}
	public void deleteAuctionResult(String auctionId) throws DataAccessException {
		// TODO Auto-generated method stub
		auctionMapper.deleteAuctionResult(auctionId);
	}
	@Override
	public AuctionResult getAuctionResult(String auctionId) throws DataAccessException {
		// TODO Auto-generated method stub
		return auctionMapper.getAuctionResult(auctionId);
	}
	
	@Override
	public List<Auction> getAllAuctionList() throws DataAccessException {
		return auctionMapper.getAllAuctionList();
	}
	
	@Override
	public List<Auction> getAuctionListByState(String auctionState) throws DataAccessException {
		return auctionMapper.getAuctionListByState(auctionState);
	}
	
	@Override
	public List<Auction> getAuctionListByStateAfter(AuctionAfterCollection auctionAfters) throws DataAccessException {
		return auctionMapper.getAuctionListByStateAfter(auctionAfters);
	}
	
	@Override
	public List<String> getMyAuctionFavoritesId(String userId) throws DataAccessException {
		return auctionMapper.getMyAuctionFavoritesId(userId);
	}
	
	@Override
	public List<AuctionFavorites> getMyAuctionFavoritesList(String userId) throws DataAccessException {
		return auctionMapper.getMyAuctionFavoritesList(userId);
	}
	
	@Override
	public void addMyFavoriteAuction(AuctionFavorites auctionFavorite) throws DataAccessException {
		auctionMapper.addMyFavoriteAuction(auctionFavorite);
	}
	
	@Override
	public void deleteMyFavoriteAcution(AuctionFavorites auctionFavorite) throws DataAccessException {
		auctionMapper.deleteMyFavoriteAcution(auctionFavorite);
	}
	@Override
	public void updateAuctionState(Auction auction) {
		// TODO Auto-generated method stub
		auctionMapper.updateAuctionState(auction);
		
	}

	@Override
	public void deleteAuctionFavoriteByAuctionId(String auctionId) {
		// TODO Auto-generated method stub
		auctionMapper.deleteAuctionFavoriteByAuctionId(auctionId);
	}
	public void insertAuctionBuyer(AuctionBuyer auctionBuyer) {
		auctionMapper.insertAuctionBuyer(auctionBuyer);
	}
	
	@Override
	public void insertAuctionProcess(AuctionProcess auctionProcess) throws DataAccessException {
		auctionMapper.insertAuctionProcess(auctionProcess);
	}
	@Override
	public String getAuctionProcessExist(AuctionProcess auctionProcess) throws DataAccessException {
		return auctionMapper.getAuctionProcessExist(auctionProcess);
	}
	@Override
	public int getAuctionProcessMyPrice(AuctionProcess auctionProcess) throws DataAccessException {
		return auctionMapper.getAuctionProcessMyPrice(auctionProcess);
	}
	@Override
	public void updateAuctionProcessPrice(AuctionProcess auctionProcess) throws DataAccessException {
		auctionMapper.updateAuctionProcessPrice(auctionProcess);
	}
	
	@Override
	public void updateAuctionResult(AuctionResult auctionResult) throws DataAccessException {
		auctionMapper.updateAuctionResult(auctionResult);
	}

	@Override
	public String getAuctionProcessUser(String id) throws DataAccessException {
		// TODO Auto-generated method stub
		return auctionMapper.getAuctionProcessUser(id);
	}
	

	@Override
	public List<String> getMyAuctionBidList(String userId) throws DataAccessException {
		return auctionMapper.getMyAuctionBidList(userId);
	}
	
	@Override
	public void updateAuctionBuyer(AuctionBuyer auctionBuyer) throws DataAccessException {
		auctionMapper.updateAuctionBuyer(auctionBuyer);
	}
	
	@Override
	public void updateAuctionBuyerState(AuctionBuyer auctionBuyer) throws DataAccessException {
		auctionMapper.updateAuctionBuyerState(auctionBuyer);
	}

	@Override
	public void addAuctionBlackList(AuctionBlackList auctionBlackList) throws DataAccessException {
		auctionMapper.addAuctionBlackList(auctionBlackList);
	}
	
	@Override
	public AuctionBlackList getAuctionBlackListUser(String userId) throws DataAccessException {
		return auctionMapper.getAuctionBlackListUser(userId);
	}
	@Override
	public void updateAuctionBlackList(AuctionBlackList auctionBlackList) throws DataAccessException {
		auctionMapper.updateAuctionBlackList(auctionBlackList);
	}
	
	@Override
	public AuctionBuyer getAuctionBuyer(String auctionId) throws DataAccessException {
		return auctionMapper.getAuctionBuyer(auctionId);
	}
	
	@Override
	public AuctionBuyer getAuctionBuyerByState(AuctionBuyer auctionBuyer) throws DataAccessException {
		return auctionMapper.getAuctionBuyerByState(auctionBuyer);
	}
}

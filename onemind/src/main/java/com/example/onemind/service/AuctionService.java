package com.example.onemind.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;



import org.springframework.stereotype.Service;

import com.example.onemind.domain.Auction;
import com.example.onemind.domain.AuctionFavorites;

@Service
public class AuctionService {
    private Map<String, Auction> auctionMap = new HashMap<String,Auction>();
    private ArrayList<AuctionFavorites> auctionFavoritesList = new ArrayList<AuctionFavorites>();
   
    public AuctionService() {
       
    }
    
    public Auction create(Auction auction) {
        String id = UUID.randomUUID().toString();
        auction.setAuctionId(id);
        auction.setAuctionState("BEFORE");
        auctionMap.put(id, auction);
        System.out.println(auction);
        return auction;
    }
    public List<Auction> getMyAcutions(String userId) {
    	System.out.print("userid is" + userId);
    	ArrayList<Auction> myAuctionList = new ArrayList<Auction>();
    	 Iterator<String> keys = auctionMap.keySet().iterator();
         while ( keys.hasNext() ) {
             String key = keys.next();
             if(auctionMap.get(key).getUserId().equals(userId)) {
            	 myAuctionList.add(auctionMap.get(key));
             } 
         } 
        return myAuctionList;
    }
    public List<Auction> deleteMyAcutions(String auctionId) {
        auctionMap.remove(auctionId);
        return new ArrayList<Auction>(auctionMap.values());
    }
    public List<AuctionFavorites> getMyAcutionFavoritesList(String userId) {
        ArrayList<AuctionFavorites> myFavorites = new ArrayList<>();
        for (AuctionFavorites auction: auctionFavoritesList) {
            if (auction.getUserId().equals(userId)) {
                myFavorites.add(auction);
            }
        }
        return myFavorites;
    }
    public List<String> getMyAcutionFavoritesId(String userId) {
        ArrayList<String> myFavorites = new ArrayList<>();
        for (AuctionFavorites auction: auctionFavoritesList) {
            if (auction.getUserId().equals(userId)) {
                myFavorites.add(auction.getAuctionId());
            }
        }
        return myFavorites;
    }
    
    public List<Auction> getAllAcutionList() {
        return new ArrayList<Auction>(auctionMap.values());
    }
    public List<AuctionFavorites> deleteMyFavoriteAcution(String auctionId,String userId) {
    	auctionFavoritesList.remove(new AuctionFavorites(auctionId, userId));
    	for (int i = 0; i < auctionFavoritesList.size(); i++) {
    		if (auctionFavoritesList.get(i).getAuctionId().equals(auctionId) && 
    				auctionFavoritesList.get(i).getUserId().equals(userId)) {
    			auctionFavoritesList.remove(i);
    			break;
    		}
    	}
    	return auctionFavoritesList;   
    }
    public List<AuctionFavorites> addMyFavoriteAcution(String auctionId,String userId) {
    	auctionFavoritesList.add(new AuctionFavorites(auctionId, userId));
    	return auctionFavoritesList;   
    }
    
    public Auction getAuctionFromId(String auctionId) {
    	Auction auctionTmp = new Auction();
    	Iterator<String> keys = auctionMap.keySet().iterator();
    	while(keys.hasNext()) {
    		String key = keys.next();
    		if (auctionMap.get(key).getAuctionId().equals(auctionId)) {
    			auctionTmp = auctionMap.get(key);
    			break;
    		}
    	}
    	return auctionTmp;
    }
    
    public List<Auction> getAuctionListByState(String auctionState) {
    	ArrayList<Auction> stateAuctionList = new ArrayList<Auction>();
    	Iterator<String> keys = auctionMap.keySet().iterator();
    	while(keys.hasNext()) {
    		String key = keys.next();
    		if (auctionMap.get(key).getAuctionState().equals(auctionState)) {
    			stateAuctionList.add(auctionMap.get(key));
    		}
    	}
    	return stateAuctionList;
    }
    
    public List<Auction> getAuctionListByFavorites(List<AuctionFavorites> myFavorites) {        
    	ArrayList<Auction> auctionList = new ArrayList<Auction>();
        
    	ArrayList<String> auctionIdList = new ArrayList<String>();
    	String tmpId = "";
        for (int i = 0; i < myFavorites.size(); i++) {
        	tmpId = myFavorites.get(i).getAuctionId();
        	auctionIdList.add(tmpId);
        	Iterator<String> keys = auctionMap.keySet().iterator();
            while ( keys.hasNext() ) {
                String key = keys.next();
                if(auctionMap.get(key).getAuctionId().equals(tmpId)) {
               	 auctionList.add(auctionMap.get(key));
                } 
            } 
    	}
        return auctionList;
    }
}

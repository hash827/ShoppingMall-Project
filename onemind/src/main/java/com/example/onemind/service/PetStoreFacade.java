package com.example.onemind.service;

import java.util.List;

import com.example.onemind.domain.Account;
import com.example.onemind.domain.Auction;
import com.example.onemind.domain.AuctionAfterCollection;
import com.example.onemind.domain.AuctionBlackList;
import com.example.onemind.domain.AuctionBuyer;
import com.example.onemind.domain.AuctionFavorites;
import com.example.onemind.domain.AuctionProcess;
import com.example.onemind.domain.AuctionResult;
import com.example.onemind.domain.Category;
import com.example.onemind.domain.Chat;
import com.example.onemind.domain.ChatRoom;
import com.example.onemind.domain.CompanyOrder;
import com.example.onemind.domain.CompanyShoes;
import com.example.onemind.domain.Item;
import com.example.onemind.domain.Order;
import com.example.onemind.domain.Product;
import com.example.onemind.domain.Secondhand;
import com.example.onemind.domain.SecondhandFavorite;

/**
 * JPetStore's central business interface.
 *
 * @author Juergen Hoeller
 * @since 30.11.2003
 */
public interface PetStoreFacade {

	Account getAccount(String username);

	Account getAccount(String username, String password);

	void insertAccount(Account account);

	void updateAccount(Account account);

	List<String> getUsernameList();

	List<Category> getCategoryList();

	Category getCategory(String categoryId);

	List<Product> getProductListByCategory(String categoryId);

	List<Product> searchProductList(String keywords);

	Product getProduct(String productId);

	List<Item> getItemListByProduct(String productId);

	Item getItem(String itemId);

	boolean isItemInStock(String itemId);

	void insertOrder(Order order);

	Order getOrder(int orderId);

	List<Order> getOrdersByUsername(String username);

	// secondhand
	Secondhand getSecondhand(String secondhandId);

	void insertSecondhand(Secondhand secondhand);

	void updateSecondhand(Secondhand secondhand);

	void updateSecondhandState(Secondhand secondhand);

	void deleteSecondhand(Secondhand secondhand);

	List<Secondhand> getSecondhandsByUsername(String username);

	List<Secondhand> getSecondhandsByBuyer(String buyer);

	List<Secondhand> getSecondhandsByState(String secondhandState);

	List<Secondhand> getSecondhandList();

	// secondhandFavorite
	SecondhandFavorite getSecondhandFavorite(String secondhandFavoriteId);

	void insertSecondhandFavorite(SecondhandFavorite secondhandFavorite);

	void deleteSecondhandFavorite(SecondhandFavorite secondhandFavorite);

	List<SecondhandFavorite> getSecondhandFavoritesByUsername(String username);

	// chat
	List<Chat> getChatsById(String fromId, int roomNumber);

	List<Chat> getChatsByRoomNumber(int roomNumber);

	void insertChat(Chat chat);

	void deleteChat(Chat chat);

	// chatRoom
	ChatRoom getChatRoom(int roomNumber);

	ChatRoom getChatRoomBySecondhandAndUsername(String username, String secondhandId);

	List<ChatRoom> getChatRoomsById(String username);

	List<ChatRoom> getChatRoomsByShUsername(String shUsername);
	
	List<ChatRoom> getChatRoomsByIdWithRfl(String username, String rfl);

	List<ChatRoom> getChatRoomsByShUsernameWithRfl(String shUsername, String rfl);

	void insertChatRoom(ChatRoom chatRoom);
	
	void updateChatRoomRfl(ChatRoom chatRoom);

	void deleteChatRoom(ChatRoom chatRoom);

	// auction
	Auction getAuction(String auctionId);

	void insertAuction(Auction auction);

	void insertAuctionResult(AuctionResult auctionResult);

	void deleteAuction(String auctionId);

	List<Auction> getMyAuctionList(String userId);

	void deleteAuctionResult(String auctionId);

	AuctionResult getAuctionResult(String auctionId);

	List<Auction> getAllAuctionList();

	List<Auction> getAuctionListByState(String auctionState);

	List<Auction> getAuctionListByStateAfter(AuctionAfterCollection auctionAfters);

	// auctionFavorites
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
	
	// company
	List<CompanyShoes> getCompanyShoesList();

	CompanyShoes getCompanyShoes(String shoesId);
	
	void insertCompanyOrder(CompanyOrder companyOrder);

	CompanyOrder getCompanyOrder(int orderId);

	List<CompanyOrder> getCompanyOrdersByUsername(String username);
}
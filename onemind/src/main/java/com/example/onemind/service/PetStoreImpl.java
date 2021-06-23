package com.example.onemind.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.onemind.dao.AccountDao;
import com.example.onemind.dao.AuctionDao;
import com.example.onemind.dao.CategoryDao;
import com.example.onemind.dao.CompanyOrderDao;
import com.example.onemind.dao.CompanyShoesDao;
import com.example.onemind.dao.ItemDao;
import com.example.onemind.dao.OrderDao;
import com.example.onemind.dao.ProductDao;
import com.example.onemind.dao.SecondhandChatDao;
import com.example.onemind.dao.SecondhandChatRoomDao;
import com.example.onemind.dao.SecondhandDao;
import com.example.onemind.dao.SecondhandFavoriteDao;
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

@Service
@Transactional
public class PetStoreImpl implements PetStoreFacade {
	@Autowired
	private AccountDao accountDao;

	@Autowired
	private CategoryDao mybatisCategoryDao;
	@Autowired
	private CategoryDao jdbcTemplateCategoryDao;
	@Autowired
	private CategoryDao namedParamJdbcTemplateCategoryDao;
	@Autowired
	private CategoryDao jdbcDaoSupportCategoryDao;
	@Autowired
	private CategoryDao pureJdbcCategoryDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private SecondhandDao secondhandDao;
	@Autowired
	private SecondhandFavoriteDao secondhandFavoriteDao;
	@Autowired
	private SecondhandChatDao secondhandChatDao;
	@Autowired
	private SecondhandChatRoomDao secondhandChatRoomDao;
	@Autowired
	private AuctionDao auctionDao;

	@Autowired
	private CompanyShoesDao companyShoesDao;

	@Autowired
	private CompanyOrderDao companyOrderDao;

	// -------------------------------------------------------------------------
	// Operation methods, implementing the PetStoreFacade interface
	// -------------------------------------------------------------------------

	public Account getAccount(String username) {
		return accountDao.getAccount(username);
	}

	public Account getAccount(String username, String password) {
		return accountDao.getAccount(username, password);
	}

	public void insertAccount(Account account) {
		accountDao.insertAccount(account);
	}

	public void updateAccount(Account account) {
		accountDao.updateAccount(account);
	}

	public List<String> getUsernameList() {
		return accountDao.getUsernameList();
	}

	public List<Category> getCategoryList() {
		return mybatisCategoryDao.getCategoryList();
	}

	public Category getCategory(String categoryId) {
		Category category = null;
		switch (categoryId) {
		case "FISH":
			category = mybatisCategoryDao.getCategory(categoryId);
			break;
		case "DOGS":
			category = jdbcTemplateCategoryDao.getCategory(categoryId);
			break;
		case "REPTILES":
			category = namedParamJdbcTemplateCategoryDao.getCategory(categoryId);
			break;
		case "CATS":
			category = jdbcDaoSupportCategoryDao.getCategory(categoryId);
			break;
		case "BIRDS":
			category = pureJdbcCategoryDao.getCategory(categoryId);
		}
		return category;
	}

	public List<Product> getProductListByCategory(String categoryId) {
		return productDao.getProductListByCategory(categoryId);
	}

	public List<Product> searchProductList(String keywords) {
		return productDao.searchProductList(keywords);
	}

	public Product getProduct(String productId) {
		return productDao.getProduct(productId);
	}

	public List<Item> getItemListByProduct(String productId) {
		return itemDao.getItemListByProduct(productId);
	}

	public Item getItem(String itemId) {
		return itemDao.getItem(itemId);
	}

	public boolean isItemInStock(String itemId) {
		return itemDao.isItemInStock(itemId);
	}

	public void insertOrder(Order order) {
		itemDao.updateQuantity(order);
		orderDao.insertOrder(order);
	}

	public Order getOrder(int orderId) {
		return orderDao.getOrder(orderId);
	}

	public List<Order> getOrdersByUsername(String username) {
		return orderDao.getOrdersByUsername(username);
	}

	// secondhand
	public Secondhand getSecondhand(String secondhandId) {
		return secondhandDao.getSecondhand(secondhandId);
	}

	public void insertSecondhand(Secondhand secondhand) {
		secondhandDao.insertSecondhand(secondhand);
	}

	public void updateSecondhand(Secondhand secondhand) {
		secondhandDao.updateSecondhand(secondhand);
	}

	public void updateSecondhandState(Secondhand secondhand) {
		secondhandDao.updateSecondhandState(secondhand);
	}

	public void deleteSecondhand(Secondhand secondhand) {
		secondhandDao.deleteSecondhand(secondhand);
	}

	public List<Secondhand> getSecondhandsByUsername(String username) {
		return secondhandDao.getSecondhandsByUsername(username);
	}

	public List<Secondhand> getSecondhandsByBuyer(String buyer) {
		return secondhandDao.getSecondhandsByBuyer(buyer);
	}

	public List<Secondhand> getSecondhandsByState(String secondhandState) {
		return secondhandDao.getSecondhandsByState(secondhandState);
	}

	public List<Secondhand> getSecondhandList() {
		return secondhandDao.getSecondhandList();
	}

	// secondhandFavorite
	public SecondhandFavorite getSecondhandFavorite(String secondhandFavoriteId) {
		return secondhandFavoriteDao.getSecondhandFavorite(secondhandFavoriteId);
	}

	public void insertSecondhandFavorite(SecondhandFavorite secondhandFavorite) {
		secondhandFavoriteDao.insertSecondhandFavorite(secondhandFavorite);
	}

	public void deleteSecondhandFavorite(SecondhandFavorite secondhandFavorite) {
		secondhandFavoriteDao.deleteSecondhandFavorite(secondhandFavorite);
	}

	public List<SecondhandFavorite> getSecondhandFavoritesByUsername(String username) {
		return secondhandFavoriteDao.getSecondhandFavoritesByUsername(username);
	}

	// chat
	public List<Chat> getChatsById(String fromId, int roomNumber) {
		return secondhandChatDao.getChatsById(fromId, roomNumber);
	}

	public List<Chat> getChatsByRoomNumber(int roomNumber) {
		return secondhandChatDao.getChatsByRoomNumber(roomNumber);
	}

	public void insertChat(Chat chat) {
		secondhandChatDao.insertChat(chat);
	}

	public void deleteChat(Chat chat) {
		secondhandChatDao.deleteChat(chat);
	}

	// chatRoom
	public ChatRoom getChatRoom(int roomNumber) {
		return secondhandChatRoomDao.getChatRoom(roomNumber);
	}

	public ChatRoom getChatRoomBySecondhandAndUsername(String username, String secondhandId) {
		return secondhandChatRoomDao.getChatRoomBySecondhandAndUsername(username, secondhandId);
	}

	public List<ChatRoom> getChatRoomsById(String username) {
		return secondhandChatRoomDao.getChatRoomsById(username);
	}

	public List<ChatRoom> getChatRoomsByShUsername(String shUsername) {
		return secondhandChatRoomDao.getChatRoomsByShUsername(shUsername);
	}

	public List<ChatRoom> getChatRoomsByIdWithRfl(String username, String rfl) {
		return secondhandChatRoomDao.getChatRoomsByIdWithRfl(username, rfl);
	}

	public List<ChatRoom> getChatRoomsByShUsernameWithRfl(String shUsername, String rfl) {
		return secondhandChatRoomDao.getChatRoomsByShUsernameWithRfl(shUsername, rfl);
	}

	public void insertChatRoom(ChatRoom chatRoom) {
		secondhandChatRoomDao.insertChatRoom(chatRoom);
	}

	public void updateChatRoomRfl(ChatRoom chatRoom) {
		secondhandChatRoomDao.updateChatRoomRfl(chatRoom);
	}

	public void deleteChatRoom(ChatRoom chatRoom) {
		secondhandChatRoomDao.deleteChatRoom(chatRoom);
	}

	// Auction
	public void insertAuction(Auction auction) {
		auctionDao.insertAuction(auction);
	}

	public void deleteAuction(String auctionId) {
		auctionDao.deleteAuction(auctionId);
	}

	@Override
	public Auction getAuction(String auctionId) {
		// TODO Auto-generated method stub
		return auctionDao.getAuction(auctionId);
	}

	public List<Auction> getMyAuctionList(String userId) {
		return auctionDao.getMyAuctionList(userId);
	}

	@Override
	public void insertAuctionResult(AuctionResult auctionResult) {
		// TODO Auto-generated method stub
		auctionDao.insertAuctionResult(auctionResult);

	}

	public void deleteAuctionResult(String auctionId) {
		auctionDao.deleteAuctionResult(auctionId);
	}

	@Override
	public AuctionResult getAuctionResult(String auctionId) {
		// TODO Auto-generated method stub
		return auctionDao.getAuctionResult(auctionId);
	}

	@Override
	public List<Auction> getAllAuctionList() {
		return auctionDao.getAllAuctionList();
	}

	@Override
	public List<Auction> getAuctionListByState(String auctionState) {
		return auctionDao.getAuctionListByState(auctionState);
	}

	@Override
	public List<Auction> getAuctionListByStateAfter(AuctionAfterCollection auctionAfters) {
		return auctionDao.getAuctionListByStateAfter(auctionAfters);
	}

	@Override
	public List<String> getMyAuctionFavoritesId(String userId) {
		return auctionDao.getMyAuctionFavoritesId(userId);
	}

	@Override
	public List<AuctionFavorites> getMyAuctionFavoritesList(String userId) {
		return auctionDao.getMyAuctionFavoritesList(userId);
	}

	@Override
	public void addMyFavoriteAuction(AuctionFavorites auctionFavorite) {
		auctionDao.addMyFavoriteAuction(auctionFavorite);
	}

	@Override
	public void deleteMyFavoriteAcution(AuctionFavorites auctionFavorite) {
		auctionDao.deleteMyFavoriteAcution(auctionFavorite);
	}

	public void updateAuctionState(Auction auction) {
		auctionDao.updateAuctionState(auction);
	}

	public void deleteAuctionFavoriteByAuctionId(String auctionId) {
		auctionDao.deleteAuctionFavoriteByAuctionId(auctionId);
	}

	public void insertAuctionBuyer(AuctionBuyer auctionBuyer) {
		auctionDao.insertAuctionBuyer(auctionBuyer);
	}

	@Override
	public void insertAuctionProcess(AuctionProcess auctionProcess) {
		auctionDao.insertAuctionProcess(auctionProcess);
	}

	@Override
	public String getAuctionProcessExist(AuctionProcess auctionProcess) {
		return auctionDao.getAuctionProcessExist(auctionProcess);
	}

	@Override
	public int getAuctionProcessMyPrice(AuctionProcess auctionProcess) {
		return auctionDao.getAuctionProcessMyPrice(auctionProcess);
	}

	@Override
	public void updateAuctionProcessPrice(AuctionProcess auctionProcess) {
		auctionDao.updateAuctionProcessPrice(auctionProcess);
	}

	@Override
	public void updateAuctionResult(AuctionResult auctionResult) {
		auctionDao.updateAuctionResult(auctionResult);
	}

	public String getAuctionProcessUser(String id) {
		return auctionDao.getAuctionProcessUser(id);
	}

	@Override
	public List<String> getMyAuctionBidList(String userId) {
		return auctionDao.getMyAuctionBidList(userId);
	}

	@Override
	public void updateAuctionBuyer(AuctionBuyer auctionBuyer) {
		auctionDao.updateAuctionBuyer(auctionBuyer);
	}

	@Override
	public void updateAuctionBuyerState(AuctionBuyer auctionBuyer) {
		auctionDao.updateAuctionBuyerState(auctionBuyer);
	}

	@Override
	public void addAuctionBlackList(AuctionBlackList auctionBlackList) {
		auctionDao.addAuctionBlackList(auctionBlackList);
	}

	@Override
	public AuctionBlackList getAuctionBlackListUser(String userId) {
		return auctionDao.getAuctionBlackListUser(userId);
	}

	@Override
	public void updateAuctionBlackList(AuctionBlackList auctionBlackList) {
		auctionDao.updateAuctionBlackList(auctionBlackList);
	}

	@Override
	public AuctionBuyer getAuctionBuyer(String auctionId) {
		return auctionDao.getAuctionBuyer(auctionId);
	}

	@Override
	public AuctionBuyer getAuctionBuyerByState(AuctionBuyer auctionBuyer) {
		return auctionDao.getAuctionBuyerByState(auctionBuyer);
	}

	// 자사 신발
	@Override
	public List<CompanyShoes> getCompanyShoesList() {
		return companyShoesDao.getCompanyShoesList();
	}

	@Override
	public CompanyShoes getCompanyShoes(String shoesId) {
		return companyShoesDao.getCompanyShoes(shoesId);
	}

	public void insertCompanyOrder(CompanyOrder companyOrder) {
		companyOrderDao.insertCompanyOrder(companyOrder);
	}

	public CompanyOrder getCompanyOrder(int orderId) {
		return companyOrderDao.getCompanyOrder(orderId);
	}

	public List<CompanyOrder> getCompanyOrdersByUsername(String username) {
		return companyOrderDao.getCompanyOrdersByUsername(username);
	}
}
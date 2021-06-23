package com.example.onemind.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.onemind.domain.Item;
import com.example.onemind.domain.Order;

public interface ItemDao {

  public void updateQuantity(Order order) throws DataAccessException;

  boolean isItemInStock(String itemId) throws DataAccessException;

  List<Item> getItemListByProduct(String productId) throws DataAccessException;

  Item getItem(String itemId) throws DataAccessException;

}

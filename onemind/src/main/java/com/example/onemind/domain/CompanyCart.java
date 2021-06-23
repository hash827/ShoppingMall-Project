package com.example.onemind.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.support.PagedListHolder;

@SuppressWarnings("serial")
public class CompanyCart implements Serializable {

  /* Private Fields */

  private final Map<String, CompanyCartItem> itemMap = Collections.synchronizedMap(new HashMap<String, CompanyCartItem>());
	
  private final PagedListHolder<CompanyCartItem> itemList = new PagedListHolder<CompanyCartItem>();

  /* JavaBeans Properties */

	public CompanyCart() {
		this.itemList.setPageSize(4);
	}

	public Iterator<CompanyCartItem> getAllCartItems() { return itemList.getSource().iterator(); }
  public PagedListHolder<CompanyCartItem> getCartItemList() { return itemList; }
  public int getNumberOfItems() { return itemList.getSource().size(); }

  /* Public Methods */

  public boolean containsItemId(String itemId) {
    return itemMap.containsKey(itemId);
  }

  public void addItem(CompanyShoes item) {
	CompanyCartItem cartItem = itemMap.get(item.getShoesId());
    if (cartItem == null) {
      cartItem = new CompanyCartItem();
      cartItem.setCompanyShoes(item);
      cartItem.setQuantity(0);
      itemMap.put(item.getShoesId(), cartItem);
      itemList.getSource().add(cartItem);
    }
    cartItem.incrementQuantity();
  }


  public void setQuantityByItemId(String itemId, int quantity) {
		// TODO Auto-generated method stub
		CompanyCartItem cartItem = itemMap.get(itemId);
		cartItem.setQuantity(quantity);
	}

	public CompanyShoes removeItemById(String itemId) {
		CompanyCartItem cartItem = itemMap.remove(itemId);
		if (cartItem == null) {
			return null;
		} else {
			itemList.getSource().remove(cartItem);
			return cartItem.getCompanyShoes();
		}
	}

	public int getSubTotal() {
		int subTotal = 0;
		Iterator<CompanyCartItem> items = getAllCartItems();
		while (items.hasNext()) {
			CompanyCartItem cartItem = (CompanyCartItem) items.next();
			CompanyShoes item = cartItem.getCompanyShoes();
			int listPrice = item.getPrice();
			int quantity = cartItem.getQuantity();
			subTotal += listPrice * quantity;
		}
		return (int) subTotal;
	}
}

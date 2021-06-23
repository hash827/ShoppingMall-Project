package com.example.onemind.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LineCompanyItem implements Serializable {

	/* Private Fields */

	private int orderId;
	private int lineNumber;
	private int quantity;
	private String shoesId;
	private double unitPrice;
	private String shoesSize;
	private CompanyShoes companyShoes;

	/* Constructors */

	public LineCompanyItem() {}

	public LineCompanyItem(int lineNumber, CompanyCartItem companyCartItem) {
	    this.lineNumber = lineNumber;
	    this.quantity = companyCartItem.getQuantity();
	    this.shoesId = companyCartItem.getCompanyShoes().getShoesId();
	    this.unitPrice = companyCartItem.getCompanyShoes().getPrice();
	    this.shoesSize = companyCartItem.getCompanyShoes().getShoesSize();
	    this.companyShoes = companyCartItem.getCompanyShoes();
	  }
	/* JavaBeans Properties */
	
	public int getOrderId() {
		return orderId;
	}

	public String getShoesSize() {
		return shoesSize;
	}

	public void setShoesSize(String shoesSize) {
		this.shoesSize = shoesSize;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getShoesId() {
		return shoesId;
	}

	public void setShoesId(String shoesId) {
		this.shoesId = shoesId;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitprice) {
		this.unitPrice = unitprice;
	}

	public CompanyShoes getCompanyShoes() {
		return companyShoes;
	}

	public void setCompanyShoes(CompanyShoes companyShoes) {
		this.companyShoes = companyShoes;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return this.unitPrice * this.quantity;
	}
}
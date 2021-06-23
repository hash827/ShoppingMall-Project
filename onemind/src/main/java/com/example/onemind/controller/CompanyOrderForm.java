package com.example.onemind.controller;

import java.io.Serializable;

import com.example.onemind.domain.CompanyOrder;

@SuppressWarnings("serial")
public class CompanyOrderForm implements Serializable {

	private final CompanyOrder order = new CompanyOrder();
	private boolean shippingAddressRequired = false;
	private boolean shippingAddressProvided = false;

	public CompanyOrder getCompanyOrder() {
		return order;
	}

	public void setShippingAddressRequired(boolean shippingAddressRequired) {
		this.shippingAddressRequired = shippingAddressRequired;
	}

	public boolean isShippingAddressRequired() {
		return shippingAddressRequired;
	}

	public void setShippingAddressProvided(boolean shippingAddressProvided) {
		this.shippingAddressProvided = shippingAddressProvided;
	}

	public boolean didShippingAddressProvided() {
		return shippingAddressProvided;
	}
}
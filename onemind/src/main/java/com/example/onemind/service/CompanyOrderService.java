package com.example.onemind.service;

import java.util.List;

import com.example.onemind.domain.CompanyOrder;

public interface CompanyOrderService {
	CompanyOrder getCompanyOrder(int orderId);

	public List<CompanyOrder> getCompanyOrdersByUsername(String username);
}

package com.example.onemind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onemind.dao.CompanyOrderDao;
import com.example.onemind.domain.CompanyOrder;

@Service("companyOrderServiceImpl")
public class CompanyOrderServiceImpl implements CompanyOrderService {

	@Autowired
	private CompanyOrderDao companyOrderDao;

	public CompanyOrder getCompanyOrder(int orderId) {
		return companyOrderDao.getCompanyOrder(orderId);
	}

	public List<CompanyOrder> getCompanyOrdersByUsername(String username) {
		return companyOrderDao.getCompanyOrdersByUsername(username);
	}
}

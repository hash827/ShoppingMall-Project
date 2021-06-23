package com.example.onemind.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.onemind.domain.CompanyOrder;

@Component
@WebService(serviceName = "CompanyOrderService")
public class CompanyOrderServiceEndpoint {
	@Autowired
	CompanyOrderService companyOrderService; // inject orderSeviceImpl

	@WebMethod
	public CompanyOrder getCompanyOrder(int orderId) {
		return companyOrderService.getCompanyOrder(orderId);
	}

	@WebMethod
	public List<CompanyOrder> getCompanyOrdersByUsername(String username) {
		return companyOrderService.getCompanyOrdersByUsername(username);
	}
}
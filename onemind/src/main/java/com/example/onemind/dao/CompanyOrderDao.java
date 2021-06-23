package com.example.onemind.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.onemind.domain.CompanyOrder;

public interface CompanyOrderDao {
	List<CompanyOrder> getCompanyOrdersByUsername(String username) throws DataAccessException;

	CompanyOrder getCompanyOrder(int orderId) throws DataAccessException;

	void insertCompanyOrder(CompanyOrder companyOrder) throws DataAccessException;
}

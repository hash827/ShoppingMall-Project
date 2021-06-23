package com.example.onemind.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.onemind.domain.CompanyOrder;

@Mapper
public interface CompanyOrderMapper {

	List<CompanyOrder> getCompanyOrdersByUsername(String username);

	CompanyOrder getCompanyOrder(int orderId);

	void insertCompanyOrder(CompanyOrder companyOrder);

	void insertCompanyOrderStatus(CompanyOrder companyOrder);

	int msSqlServerInsertCompanyOrder(CompanyOrder companyOrder);
}
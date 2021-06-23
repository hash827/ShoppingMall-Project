package com.example.onemind.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.onemind.domain.LineCompanyItem;

@Mapper
public interface LineCompanyItemMapper {

	List<LineCompanyItem> getLineCompanyItemsByOrderId(int orderId);

	void insertLineCompanyItem(LineCompanyItem lineCompanyItem);

}
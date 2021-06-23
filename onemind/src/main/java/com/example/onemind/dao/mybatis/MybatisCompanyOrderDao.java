package com.example.onemind.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.onemind.dao.CompanyOrderDao;
import com.example.onemind.dao.SequenceDao;
import com.example.onemind.dao.mybatis.mapper.CompanyOrderMapper;
import com.example.onemind.dao.mybatis.mapper.LineCompanyItemMapper;
import com.example.onemind.dao.mybatis.mapper.LineItemMapper;
import com.example.onemind.domain.CompanyOrder;
import com.example.onemind.domain.LineCompanyItem;
import com.example.onemind.domain.LineItem;

@Repository
public class MybatisCompanyOrderDao implements CompanyOrderDao {

	@Autowired
	protected CompanyOrderMapper companyOrderMapper;
	@Autowired
	protected LineCompanyItemMapper lineCompanyItemMapper;
	@Autowired
	private SequenceDao sequenceDao;

	public List<CompanyOrder> getCompanyOrdersByUsername(String username) throws DataAccessException {
		return companyOrderMapper.getCompanyOrdersByUsername(username);
	}

	@Transactional
	public CompanyOrder getCompanyOrder(int orderId) throws DataAccessException {
		CompanyOrder order = companyOrderMapper.getCompanyOrder(orderId);
		if (order != null) {
			order.setLineCompanyItems(lineCompanyItemMapper.getLineCompanyItemsByOrderId(orderId));
		}
		return order;
	}

	@Transactional
	public void insertCompanyOrder(CompanyOrder order) throws DataAccessException {
		order.setOrderId(sequenceDao.getNextId("ordernum"));
		companyOrderMapper.insertCompanyOrder(order);
		companyOrderMapper.insertCompanyOrderStatus(order);
		for (int i = 0; i < order.getLineCompanyItems().size(); i++) {
			LineCompanyItem lineCompanyItem = (LineCompanyItem) order.getLineCompanyItems().get(i);
			lineCompanyItem.setOrderId(order.getOrderId());
			lineCompanyItemMapper.insertLineCompanyItem(lineCompanyItem);
		}
	}
}
package com.example.onemind.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.onemind.dao.AccountDao;
import com.example.onemind.dao.SecondhandDao;
import com.example.onemind.dao.SequenceDao;
import com.example.onemind.dao.mybatis.mapper.AccountMapper;
import com.example.onemind.dao.mybatis.mapper.LineItemMapper;
import com.example.onemind.dao.mybatis.mapper.SecondhandMapper;
import com.example.onemind.domain.Account;
import com.example.onemind.domain.Category;
import com.example.onemind.domain.LineItem;
import com.example.onemind.domain.Order;
import com.example.onemind.domain.Secondhand;

@Repository
public class MybatisSecondhandDao implements SecondhandDao {

	@Autowired
	private SecondhandMapper secondhandMapper;
	
	@Transactional
	public Secondhand getSecondhand(String secondhandId) throws DataAccessException {
		Secondhand secondhand = secondhandMapper.getSecondhand(secondhandId);
	    return secondhand;
	}

	@Transactional
	public void insertSecondhand(Secondhand secondhand) throws DataAccessException {
		secondhandMapper.insertSecondhand(secondhand);
	}

	@Transactional
	public void updateSecondhand(Secondhand secondhand) throws DataAccessException {
		secondhandMapper.updateSecondhand(secondhand);
	}
	
	@Transactional
	public void updateSecondhandState(Secondhand secondhand) throws DataAccessException {
		secondhandMapper.updateSecondhandState(secondhand);
	}
	
	@Transactional
	public void deleteSecondhand(Secondhand secondhand) throws DataAccessException {
		secondhandMapper.deleteSecondhand(secondhand);
	}
 
	public List<Secondhand> getSecondhandsByUsername(String username) 
			throws DataAccessException {
	    return secondhandMapper.getSecondhandsByUsername(username);
	}
	
	public List<Secondhand> getSecondhandsByBuyer(String buyer) 
			throws DataAccessException {
	    return secondhandMapper.getSecondhandsByBuyer(buyer);
	}
	
	public List<Secondhand> getSecondhandsByState(String secondhandState) 
			throws DataAccessException {
	    return secondhandMapper.getSecondhandsByState(secondhandState);
	}
	
	public List<Secondhand> getSecondhandList() throws DataAccessException {
		return secondhandMapper.getSecondhandList();
	}
}
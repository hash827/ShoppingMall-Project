package com.example.onemind.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.onemind.domain.Secondhand;

public interface SecondhandDao {

	Secondhand getSecondhand(String secondhandId) throws DataAccessException;

	void insertSecondhand(Secondhand secondhand) throws DataAccessException;

	void updateSecondhand(Secondhand secondhand) throws DataAccessException;
	
	void updateSecondhandState(Secondhand secondhand) throws DataAccessException;
	
	void deleteSecondhand(Secondhand secondhand) throws DataAccessException;

	List<Secondhand> getSecondhandsByUsername(String username) throws DataAccessException;
	
	List<Secondhand> getSecondhandsByBuyer(String buyer) throws DataAccessException;
	
	List<Secondhand> getSecondhandsByState(String secondhandState) throws DataAccessException;
	
	List<Secondhand> getSecondhandList() throws DataAccessException;
}
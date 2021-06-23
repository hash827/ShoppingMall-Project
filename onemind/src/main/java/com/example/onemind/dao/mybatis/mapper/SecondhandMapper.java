package com.example.onemind.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.onemind.domain.Secondhand;

@Mapper
public interface SecondhandMapper {

	Secondhand getSecondhand(String secondhandId);

	List<Secondhand> getSecondhandsByUsername(String username);
	
	List<Secondhand> getSecondhandsByBuyer(String buyer);
	
	List<Secondhand> getSecondhandsByState(String secondhandState);

	void insertSecondhand(Secondhand secondhand);

	void updateSecondhand(Secondhand secondhand);
	
	void updateSecondhandState(Secondhand secondhand);
	
	void deleteSecondhand(Secondhand secondhand);
	
	List<Secondhand> getSecondhandList();

}

package com.example.onemind.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.onemind.dao.CompanyShoesDao;
import com.example.onemind.dao.mybatis.mapper.CompanyShoesMapper;
import com.example.onemind.domain.CompanyShoes;

@Repository
public class MybatisCompanyShoesDao implements CompanyShoesDao {

	@Autowired
	private CompanyShoesMapper companyShoesMapper;
	
	@Override
	public List<CompanyShoes> getCompanyShoesList() throws DataAccessException {
		// TODO Auto-generated method stub
		return companyShoesMapper.getCompanyShoesList();
	}

	@Override
	public CompanyShoes getCompanyShoes(String shoesId) throws DataAccessException {
		// TODO Auto-generated method stub
		return companyShoesMapper.getCompanyShoes(shoesId);
	}

}

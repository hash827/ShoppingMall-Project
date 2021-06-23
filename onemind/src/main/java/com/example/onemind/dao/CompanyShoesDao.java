package com.example.onemind.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;

import com.example.onemind.domain.CompanyShoes;

public interface CompanyShoesDao {
	List<CompanyShoes> getCompanyShoesList() throws DataAccessException;
	CompanyShoes getCompanyShoes(String shoesId) throws DataAccessException;
}

package com.example.onemind.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import com.example.onemind.domain.CompanyShoes;

@Mapper
public interface CompanyShoesMapper {
	List<CompanyShoes> getCompanyShoesList();
	CompanyShoes getCompanyShoes(String shoesId);
}

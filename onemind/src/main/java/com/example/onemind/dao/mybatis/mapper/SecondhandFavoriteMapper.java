package com.example.onemind.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.onemind.domain.SecondhandFavorite;

@Mapper
public interface SecondhandFavoriteMapper {
	
	SecondhandFavorite getSecondhandFavorite(String secondhandFavoriteId);

	List<SecondhandFavorite> getSecondhandFavoritesByUsername(String username);

	void insertSecondhandFavorite(SecondhandFavorite secondhandFavorite);
	
	void deleteSecondhandFavorite(SecondhandFavorite secondhandFavorite);
}

package com.example.onemind.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.onemind.domain.SecondhandFavorite;

public interface SecondhandFavoriteDao {
	SecondhandFavorite getSecondhandFavorite(String secondhandFavoriteId) throws DataAccessException;

	void insertSecondhandFavorite(SecondhandFavorite secondhandFavorite) throws DataAccessException;
	
	void deleteSecondhandFavorite(SecondhandFavorite secondhandFavorite) throws DataAccessException;

	List<SecondhandFavorite> getSecondhandFavoritesByUsername(String username) throws DataAccessException;
}

package com.example.onemind.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.onemind.dao.SecondhandFavoriteDao;
import com.example.onemind.dao.mybatis.mapper.SecondhandFavoriteMapper;
import com.example.onemind.domain.SecondhandFavorite;

@Repository
public class MybatisSecondhandFavoriteDao implements SecondhandFavoriteDao {

	@Autowired
	private SecondhandFavoriteMapper secondhandFavoriteMapper;
	
	@Transactional
	public SecondhandFavorite getSecondhandFavorite(String secondhandFavoriteId) throws DataAccessException {
		SecondhandFavorite secondhandFavorite = secondhandFavoriteMapper.getSecondhandFavorite(secondhandFavoriteId);
	    return secondhandFavorite;
	}

	@Transactional
	public void insertSecondhandFavorite(SecondhandFavorite secondhandFavorite) throws DataAccessException {
		secondhandFavoriteMapper.insertSecondhandFavorite(secondhandFavorite);
	}
	
	@Transactional
	public void deleteSecondhandFavorite(SecondhandFavorite secondhandFavorite) throws DataAccessException {
		secondhandFavoriteMapper.deleteSecondhandFavorite(secondhandFavorite);
	}
 
	public List<SecondhandFavorite> getSecondhandFavoritesByUsername(String username) 
			throws DataAccessException {
	    return secondhandFavoriteMapper.getSecondhandFavoritesByUsername(username);
	}
}

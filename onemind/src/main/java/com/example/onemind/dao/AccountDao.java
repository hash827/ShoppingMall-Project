package com.example.onemind.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;

import com.example.onemind.domain.Account;

public interface AccountDao {

  Account getAccount(String username) throws DataAccessException;

  Account getAccount(String username, String password) throws DataAccessException;

  void insertAccount(Account account) throws DataAccessException;

  void updateAccount(Account account) throws DataAccessException;

  List<String> getUsernameList() throws DataAccessException;

}

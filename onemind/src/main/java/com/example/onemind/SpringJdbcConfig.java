package com.example.onemind;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.onemind.dao.jdbc.JdbcDaoSupportCategoryDao;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

@Configuration
public class SpringJdbcConfig {

	@Autowired
	private DataSource dataSource;
	
	@Bean
	JdbcDaoSupportCategoryDao jdbcDaoSupportCategoryDao() {
		JdbcDaoSupportCategoryDao dao = new JdbcDaoSupportCategoryDao();
		dao.setDataSource(dataSource);
		return dao;
	}
}

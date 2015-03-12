package com.ht.b2attr.b2attr_service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.ht.b2attr.b2attr_service.schema.CloudTest;

@SuppressWarnings("deprecation")
public class JDBCOrecle {
	
	private SimpleJdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(SimpleJdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//新增记录
	public void add(int id, String attribute)
	{
		jdbcTemplate.update("insert into cloud_test(T_ID, T_ATTRIBUTE) values(?,?)", id, attribute);
	}
	
	
	//根据ID号查询记录
	public CloudTest getById(int id)
	{
		String sql="select * from cloud_test where T_ID = ?";
	    return jdbcTemplate.queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(CloudTest.class),id );
	}
}
	

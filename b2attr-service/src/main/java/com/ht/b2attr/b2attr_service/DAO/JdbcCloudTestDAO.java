package com.ht.b2attr.b2attr_service.DAO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;

import com.ht.b2attr.b2attr_service.schema.CloudTest;

@Service("jdbcCloudTestDAO")
public class JdbcCloudTestDAO implements CloudTestDao {
	@Autowired
	private SimpleJdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(SimpleJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public CloudTest queryById(long id) {
		String sql = "select * from cloud_test where T_ID = ?";
		return jdbcTemplate.queryForObject(sql, ParameterizedBeanPropertyRowMapper.newInstance(CloudTest.class), id);

	}

	public boolean insertCloudTest(CloudTest ct) {
		String sql = "insert into cloud_test (T_ID,T_ATTRIBUTE,T_DESC,T_DT) values (:T_ID,:T_ATTRIBUTE,:T_DESC,:T_DT)";
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("T_ID", ct.getTId());
		args.put("T_ATTRIBUTE", ct.getTAttribute());
		args.put("T_DESC", ct.getTDesc());
		args.put("T_DT", ct.getTDt());
		int i = jdbcTemplate.update(sql, args);
		return i > 0;

	}

	public boolean deleteCloudTestById(long id) {
		String sql = "delete cloud_test where T_ID=?";
		return jdbcTemplate.update(sql, id) > 0;

	}

	public boolean updateCloudTest(CloudTest ct) {
		String sql = "update cloud_test set T_ATTRIBUTE=(:T_ATTRIBUTE),T_DESC=(:T_DESC),T_DT=(:T_DT) where T_ID=(:T_ID)";
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("T_ID", ct.getTId());
		args.put("T_ATTRIBUTE", ct.getTAttribute());
		args.put("T_DESC", ct.getTDesc());
		args.put("T_DT", ct.getTDt());
		int i = jdbcTemplate.update(sql, args);
		return i > 0;
	}

}

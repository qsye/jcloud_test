package com.ht.b2attr.b2attr_service.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.ht.b2attr.b2attr_service.schema.CloudTest;

/**
 * The impl of the DAO which for jdbc.
 * 
 * @author Cloud_team
 *
 */
@Service("jdbcCloudTestDAO")
public class JdbcCloudTestDAO implements CloudTestDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<CloudTest> queryAll() {
		String sql = "select * from cloud_test";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CloudTest.class));
	}

	public CloudTest queryById(int id) {
		String sql = "select * from cloud_test where T_ID = :TId";
		CloudTest ct = new CloudTest();
		ct.setTId(id);
		return jdbcTemplate.query(sql, new BeanPropertySqlParameterSource(ct), new ResultSetExtractor<CloudTest>() {

			@Override
			public CloudTest extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					Date dt = rs.getDate("t_dt");
					CloudTest ct = new CloudTest(rs.getInt("t_id"), rs.getString("t_attribute"), rs.getString("t_desc"), dt);
					return ct;
				}
				throw new SQLException("Did not has any data was queryed");
			}
		});

	}

	public int insertCloudTest(CloudTest ct) {
		String sql = "insert into cloud_test (T_ID,T_ATTRIBUTE,T_DESC,T_DT) values (:T_ID,:T_ATTRIBUTE,:T_DESC,:T_DT)";
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("T_ID", ct.getTId());
		args.put("T_ATTRIBUTE", ct.getTAttribute());
		args.put("T_DESC", ct.getTDesc());
		args.put("T_DT", ct.getTDt());
		return jdbcTemplate.update(sql, args);

	}

	public int deleteCloudTestById(long id) {
		String sql = "delete cloud_test where T_ID=:T_ID";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("T_ID", id);
		return jdbcTemplate.update(sql, map);

	}

	public int updateCloudTest(CloudTest ct) {
		String sql = "update cloud_test set T_ATTRIBUTE=(:T_ATTRIBUTE),T_DESC=(:T_DESC),T_DT=(:T_DT) where T_ID=(:T_ID)";
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("T_ID", ct.getTId());
		args.put("T_ATTRIBUTE", ct.getTAttribute());
		args.put("T_DESC", ct.getTDesc());
		args.put("T_DT", ct.getTDt());
		return jdbcTemplate.update(sql, args);
	}

}

package com.ht.b2attr.b2attr_service.DAO;

import java.util.List;

import com.ht.b2attr.b2attr_service.schema.CloudTest;

public interface CloudTestDao {
	public List<CloudTest> queryAll();

	public CloudTest queryById(int id);

	public int insertCloudTest(CloudTest ct);

	public int deleteCloudTestById(long id);

	public int updateCloudTest(CloudTest ct);
}

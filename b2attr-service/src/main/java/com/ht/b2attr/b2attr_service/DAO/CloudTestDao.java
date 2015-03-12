package com.ht.b2attr.b2attr_service.DAO;

import com.ht.b2attr.b2attr_service.schema.CloudTest;

public interface CloudTestDao {
	public CloudTest queryById(long id);

	public boolean insertCloudTest(CloudTest ct);

	public boolean deleteCloudTestById(long id);

	public boolean updateCloudTest(CloudTest ct);
}

package com.ht.b2attr.b2attr_service.DAO;

import java.util.List;

import com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumn;
/**
 * It is the service of DB.
 * @author Cloud_team
 *
 */
public interface BltNoLeverageColumnDao {
	public List<BltNoLeverageColumn> queryAll();

	public BltNoLeverageColumn queryById(int id);

	public int insertCloudTest(BltNoLeverageColumn ct);

	public int deleteCloudTestById(long id);

	public int updateCloudTest(BltNoLeverageColumn ct);
}

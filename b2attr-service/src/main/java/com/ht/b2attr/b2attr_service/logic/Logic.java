package com.ht.b2attr.b2attr_service.logic;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ht.b2attr.b2attr_service.DAO.BltNoLeverageColumnDao;
import com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumn;
import com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumnList;
import com.ht.b2attr.b2attr_service.util.DateUtil;

@Component
public class Logic {
	// injection by spring
	@Autowired
	private BltNoLeverageColumnDao bltNoLeverageColumnDao;


	public BltNoLeverageColumnList retrieveAllCloudTest() throws IOException {

		System.out.print("Get a request to method:");
		System.out.println("retrieveAllCloudTest");
		List<BltNoLeverageColumn> list = bltNoLeverageColumnDao.queryAll();

		// for (int i = 1; i < 3; i++) {
		// CloudTest ct = new CloudTest();
		// ct.setTId(i);
		// ct.setTDt(new Date());
		// ct.setTAttribute("attr");
		// ct.setTDesc("desc");
		// list.add(ct);
		// }
		return new BltNoLeverageColumnList(list);

	}

	public BltNoLeverageColumn retrieveCloudTestById(int id) throws IOException {
		System.out.print("Get a request to method:");
		System.out.println("retrieveCloudTestById");
		// CloudTest ct = new CloudTest();
		// ct.setTId(1);
		// ct.setTDesc("this is a cloud test");
		// ct.setTAttribute("column3");
		// ct.setTDt(new Date());
		// return ct;
		return bltNoLeverageColumnDao.queryById(id);

	}

	public int createCloudTest(Map<String, Object> fieldMap) throws ParseException {
		System.out.print("Get a request to method:");
		System.out.println("createCloudTest");
		System.out.println(fieldMap);
		BltNoLeverageColumn ct = new BltNoLeverageColumn();
		ct.setTDesc(fieldMap.get("t_desc").toString());
		ct.setTAttribute(fieldMap.get("t_attribute").toString());
		Date dt = DateUtil.parse(fieldMap.get("t_dt").toString());
		ct.setTDt(dt);
		return bltNoLeverageColumnDao.insertBltNoLeverageColumn(ct);
	}

	public long updateCloudTestById(int id, Map<String, Object> fieldMap) throws ParseException {
		System.out.print("Get a request to method:");
		System.out.println("updateCloudTestById");
		BltNoLeverageColumn ct = new BltNoLeverageColumn();
		ct.setTId(id);
		ct.setTDesc(fieldMap.get("t_desc").toString());
		ct.setTAttribute(fieldMap.get("t_attribute").toString());
		Date dt = DateUtil.parse(fieldMap.get("t_dt").toString());
		ct.setTDt(dt);
		if (bltNoLeverageColumnDao.updateBltNoLeverageColumn(ct) > 0) {
			return id;
		} else {
			return 0;
		}
	}

	public Response deleteCloudTestById(int id) {
		System.out.print("Get a request to method:");
		System.out.println("deleteCloudTestById");
		if (bltNoLeverageColumnDao.deleteBltNoLeverageColumnById(id) > 0) {
			return Response.ok().build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
}

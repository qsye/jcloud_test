package com.ht.b2attr.b2attr_service.mbean;

import org.apache.cxf.management.annotation.ManagedOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import com.ht.b2attr.b2attr_service.DAO.BltNoLeverageColumnDao;

@Component
@ManagedResource(objectName = "spitter:name=MBeanCenter")
public class MBeanCenter {
	@Autowired
	private BltNoLeverageColumnDao jdbcCloudTestDAO;

	@ManagedOperation
	public String getCloudTestAttribute(int id) {
		return jdbcCloudTestDAO.queryById(id).getTAttribute().toString();
	}

}

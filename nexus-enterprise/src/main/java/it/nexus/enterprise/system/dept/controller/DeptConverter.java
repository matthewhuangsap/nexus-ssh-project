package it.nexus.enterprise.system.dept.controller;

import it.nexus.enterprise.system.dept.model.Dept;
import it.nexus.enterprise.system.dept.service.DeptService;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.struts2.util.StrutsTypeConverter;

@SuppressWarnings("unchecked")
public class DeptConverter extends StrutsTypeConverter  {
	
	@Resource
	DeptService deptService;

    @Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
        String dept_id = values[0];
		Dept dept = deptService.load(dept_id);
		return dept;
	}

    @Override
	public String convertToString(Map arg0, Object arg1) {
		return null;
	}

}

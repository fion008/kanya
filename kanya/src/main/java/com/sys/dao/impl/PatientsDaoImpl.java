package com.sys.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.sys.base.dao.impl.BaseDaoImpl;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.dao.PatientsDao;
import com.sys.entity.Patients;

@Repository("patientsDao")
public class PatientsDaoImpl extends BaseDaoImpl<Patients> implements
		PatientsDao {

	public PageBean findPatientsList(String keywords, Long app_user_id,
			Long doctorId, PageParam pageParam) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(keywords)) {
			paramMap.put("keywords", keywords);
		}
		if (app_user_id != null) {
			paramMap.put("app_user_id", app_user_id);
		}
		if (doctorId != null) {
			paramMap.put("doctorId", doctorId);
		}
		return listPage(pageParam, paramMap, "findPatientsList");
	}

	@Override
	public List<Patients> getPatientsByAppUserId(Long id) {
		return super.getEntityList(id, "app_user_id", Patients.class);
	}

}

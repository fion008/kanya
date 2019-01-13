package com.sys.dao;

import java.util.List;

import com.sys.base.dao.BaseDao;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.Patients;

public interface PatientsDao extends BaseDao<Patients> {

	PageBean findPatientsList(String keywords, Long app_user_id, Long doctorId,
			PageParam pageParam);

	List<Patients> getPatientsByAppUserId(Long id);

}
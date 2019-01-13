package com.sys.service;

import java.util.List;

import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.Patients;

public interface PatientsService {

	/**
	 * 查询小程序用户列表
	 * 
	 * @param keywords
	 * @param pageParam
	 * @return
	 */
	PageBean findPatientsList(String keywords,Long app_user_id,Long doctorId, PageParam pageParam);

	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */
	int updatePatients(Patients patients);

	/**
	 * 通过id获取小程序用户信息
	 * 
	 * @param id
	 * @return
	 */
	Patients getPatients(Long id);

	/**
	 * 查询某个用户的患者
	 * 
	 * @param id
	 * @return
	 */
	List<Patients> getPatientsByAppUserId(Long id);

	/**
	 * 更新医生
	 * @param patientsId
	 * @param doctorId
	 * @return
	 */
	int updateDoctor(Long patientsId, Long doctorId);

}

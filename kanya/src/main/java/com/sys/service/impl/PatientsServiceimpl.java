package com.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.dao.PatientsDao;
import com.sys.entity.Patients;
import com.sys.service.PatientsService;

@Transactional
@Service("patientService")
public class PatientsServiceimpl implements PatientsService {
	@Autowired
	private PatientsDao patientDao;

	@Override
	public PageBean findPatientsList(String keywords, Long app_user_id,
			Long doctorId, PageParam pageParam) {
		return patientDao.findPatientsList(keywords, app_user_id, doctorId,
				pageParam);
	}

	@Override
	public int updatePatients(Patients patients) {
		if (patients.getId() != null) {
			return patientDao.update(patients);
		} else {
			return patientDao.insert(patients);
		}
	}

	@Override
	public Patients getPatients(Long id) {
		return patientDao.getByKey(id, Patients.class);
	}

	@Override
	public List<Patients> getPatientsByAppUserId(Long id) {
		return patientDao.getPatientsByAppUserId(id);
	}

	@Override
	public int updateDoctor(Long patientsId, Long doctorId) {
		Patients p = patientDao.getByKey(patientsId, Patients.class);
		p.setDoctorId(doctorId);
		return patientDao.update(p);
	}

}

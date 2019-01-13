package com.sys.dao;

import java.util.List;

import com.sys.base.dao.BaseDao;
import com.sys.entity.Interrupt_event;
import com.sys.response.InterruptEventResp;

public interface InterruptEventDao extends BaseDao<Interrupt_event> {

	List<InterruptEventResp> getInteEventByPatientsId(Long patientsId,
			Integer status, String date);

	Interrupt_event getEventByStatus(Long patientsId, Integer status);

	List<Interrupt_event> getNotDoInteEvent(Integer status);

}
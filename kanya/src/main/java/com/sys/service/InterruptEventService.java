package com.sys.service;

import java.util.List;

import com.sys.entity.Interrupt_event;
import com.sys.response.InterruptEventResp;

public interface InterruptEventService {

	/**
	 * 新增/修改中断事件
	 * 
	 * @param interrupt_ype
	 * @return
	 */
	int updateInterruptEvent(Interrupt_event interrupt_ype);

	/**
	 * 通过PatientsId获取中断事件信息
	 * 
	 * @param id
	 * @return
	 */
	List<InterruptEventResp> getInteEventByPatientsId(Long patientsId,
			Integer status, String date);

	/**
	 * 通过id获取中断事件信息
	 * 
	 * @param id
	 * @return
	 */
	Interrupt_event getInterruptEvent(Long id);

	/**
	 * 戴回牙套
	 * 
	 * @param patientsId
	 * @return
	 */
	int changeInterruptEvent(Long patientsId, Long type);
    /**
     * 获取所有未执行的任务并且已经到了提醒时间的任务
     * @param status
     * @return
     */
	List<Interrupt_event> getNotDoInteEvent(Integer status);
}

package com.sys.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sys.base.tool.StringUtil;
import com.sys.dao.InterruptEventDao;
import com.sys.entity.App_user;
import com.sys.entity.Cure_plan;
import com.sys.entity.Fixed_event;
import com.sys.entity.Interrupt_event;
import com.sys.entity.Patients;
import com.sys.entity.Plan;
import com.sys.service.AppUserService;
import com.sys.service.CurePlanService;
import com.sys.service.FixedEventService;
import com.sys.service.InterruptEventService;
import com.sys.service.PatientsService;
import com.sys.service.PlanService;

/**
 * 发送短信定时任务
 * 
 * @author dell
 * 
 */
@Component
public class SendMessageTask {
	@Autowired
	private InterruptEventService interruptEventService;
	@Autowired
	private PatientsService patientsService;
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private InterruptEventDao interruptEventDao;
	@Autowired
	private CurePlanService curePlanService;
	@Autowired
	private PlanService planService;
	@Autowired
	private FixedEventService fixedEventService;

	// @Scheduled(cron = "0/10 * * * * ?")
	public void sendMessageByPatinets() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("测试固定事件提醒定时任务" + df.format(new Date()));
		List<Interrupt_event> eventList = interruptEventService
				.getNotDoInteEvent(0);
		Patients p = null;
		App_user user = null;
		String phone = null;
		for (Interrupt_event event : eventList) {
			p = patientsService.getPatients(event.getPatientsId());
			user = appUserService.getAppUser(p.getApp_user_id());
			phone = user.getPhone();
			/*
			 * try { SendCodeUtil.sendSms(phone, "用户", "还没戴回牙套，您不会忘记了吧？"); }
			 * catch (ClientException e) { e.printStackTrace(); }
			 */
			System.out.println("给用户发送提醒--" + phone);
			event.setIsRemind(1);// 设置为已提醒
			interruptEventDao.update(event);
		}
	}

	// @Scheduled(cron = "0/10 * * * * ?")
	public void sendMessageByFixedEvent() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("测试用户提醒定时任务" + df.format(new Date()));
		Patients p = null;
		App_user user = null;
		String phone = null;

		// 查询事件
		List<Fixed_event> events = fixedEventService.getFixedEventList();
		for (int i = 0; i < events.size(); i++) {
			String time = events.get(i).getTime();
			// TODO 比较时间
			if (time != null) {
				//TODO 查询该事件下的治疗方案
				List<Cure_plan> curePlanList = curePlanService.getCurePlanList();
				for (Cure_plan cureplan : curePlanList) {
					List<Plan> planList = planService.getPlanByCurePlan(cureplan
							.getId());
					for (Plan plan : planList) {
						p = patientsService.getPatients(plan.getPatientsId());
						user = appUserService.getAppUser(p.getApp_user_id());
						phone = user.getPhone();
						/*
						 * try { SendCodeUtil.sendSms(phone, "用户", "还没戴回牙套，您不会忘记了吧？"); }
						 * catch (ClientException e) { e.printStackTrace(); }
						 */
						System.out.println("给用户发送提醒--" + phone);
					}
				}
			}
		}

	}

}

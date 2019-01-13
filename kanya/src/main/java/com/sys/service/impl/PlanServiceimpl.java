package com.sys.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.dao.InterruptEventDao;
import com.sys.dao.PlanDao;
import com.sys.entity.Interrupt_event;
import com.sys.entity.Plan;
import com.sys.response.PlanResp;
import com.sys.service.PlanService;

@Transactional
@Service("planService")
public class PlanServiceimpl implements PlanService {
	@Autowired
	private PlanDao planDao;
	@Autowired
	private InterruptEventDao interruptEventDao;

	@Override
	public int updatePlan(Plan plan) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if (plan.getId() != null) {
			return planDao.update(plan);
		} else { // 创建计划 并创建默认中断事件
			Calendar c = Calendar.getInstance();
			try {
				c.setTime(sf.parse(plan.getStart_time()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.println("开始日期:" + sf.format(c.getTime()));
			Long step = plan.getStep();
			Long day = plan.getDay();
			Long total = day * step;
			c.add(Calendar.DATE, Integer.parseInt(total.toString()));
			System.out.println("增加后日期:" + sf.format(c.getTime()));
			plan.setStart_time(sf.format(c.getTime()));
			planDao.insert(plan);
			// 默认中断事件
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			Interrupt_event event = new Interrupt_event();
			event.setPatientsId(plan.getPatientsId());
			event.setLength(sdf.format(new Date()));
			event.setType(0L);// 默认的其他事件
			event.setStatus(1);
			event.setContent("第一次创建计划");
			return interruptEventDao.insert(event);
		}
	}

	@Override
	public Plan getPlan(Long id) {
		return planDao.getByKey(id, Plan.class);
	}

	@Override
	public PlanResp getPlanByPatientsId(Long patientsId) {
		return planDao.getPlanByPatientsId(patientsId);
	}

	@Override
	public int deletePlan(Long patientsId) {
		// 删除计划 将未执行的 事件变为已执行
		Interrupt_event event = interruptEventDao.getEventByStatus(patientsId,
				0);
		if (event != null) {
			Date now = new Date();
			Date start = event.getStart_time();
			long l = now.getTime() - start.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			long hour = (l / (60 * 60 * 1000) - day * 24);
			long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
			event.setType(0L);
			event.setEnd_time(new Date());
			event.setStatus(1);// 已执行事件
			event.setLength(hour + ":" + min + ":" + s);
			interruptEventDao.update(event);
		}
		// 删除计划
		return planDao.deletePlan(patientsId);
	}

	@Override
	public List<Plan> getPlanByCurePlan(Long curePlanId) {
		return planDao.getPlanByCurePlan(curePlanId);
	}

}

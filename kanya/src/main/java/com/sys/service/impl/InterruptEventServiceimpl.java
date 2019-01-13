package com.sys.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.base.exception.BaseException;
import com.sys.dao.InterruptEventDao;
import com.sys.entity.Interrupt_event;
import com.sys.response.InterruptEventResp;
import com.sys.service.InterruptEventService;

@Transactional
@Service("interruptEventService")
public class InterruptEventServiceimpl implements InterruptEventService {
	@Autowired
	private InterruptEventDao interruptEventDao;

	@Override
	public int updateInterruptEvent(Interrupt_event interrupt_event) {
		if (interrupt_event.getId() != null) {
			return interruptEventDao.update(interrupt_event);
		} else {
			if (interrupt_event.getStatus() == 0
					&& interrupt_event.getLength() != null) {// 未执行的件
				interrupt_event.setStart_time(new Date());
				String length[] = interrupt_event.getLength().split(":");
				Calendar calendar = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历。
				calendar.setTime(new Date());
				calendar.set(
						Calendar.HOUR_OF_DAY,
						calendar.get(Calendar.HOUR_OF_DAY)
								+ (Integer.valueOf(length[0])));
				calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)
						+ (Integer.valueOf(length[1])));
				calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND)
						+ (Integer.valueOf(length[2])));
				interrupt_event.setExpected_end_time(calendar.getTime());
			} else if (interrupt_event.getStatus() == 1) { // 已执行事件 校验时间
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				String date = df.format(new Date());
				List<InterruptEventResp> respList = interruptEventDao
						.getInteEventByPatientsId(
								interrupt_event.getPatientsId(), 1, date);
				Long now = null;
				Long addlength = 0L;
				Long totallength = 0L;
				Long length = 0L;
				System.out.println("当前时间"+sdf.format(new Date()));
				String n[]=sdf.format(new Date()).split(":");
				now = Long.valueOf(n[0]) * 3600 + Long.valueOf(n[1]) * 60+Long.valueOf(n[2]);
				String l[]=interrupt_event.getLength().split(":");
				addlength = Long.valueOf(l[0]) * 3600 + Long.valueOf(l[1]) * 60+Long.valueOf(l[2]);
				System.out.println("增加的时间"+addlength);
				for (InterruptEventResp resp : respList) {
					String le[]=resp.getLength().split(":");
					length = Long.valueOf(le[0]) * 3600 + Long.valueOf(le[1]) * 60+Long.valueOf(le[2]);
					totallength = totallength + length;
				}
				if (now - totallength < addlength) {
					throw new BaseException(1011, "摘牙套时长不可以大于当前已佩戴时长");
				}
			}
			return interruptEventDao.insert(interrupt_event);
		}
	}
	
	public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+0"));
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		try {
			System.out.println( sdf.parse("00:30:00").getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Interrupt_event getInterruptEvent(Long id) {
		return interruptEventDao.getByKey(id, Interrupt_event.class);
	}

	@Override
	public List<InterruptEventResp> getInteEventByPatientsId(Long patientsId,
			Integer status, String date) {
		return interruptEventDao.getInteEventByPatientsId(patientsId, status,
				date);
	}

	@Override
	public int changeInterruptEvent(Long patientsId, Long type) {
		Interrupt_event event = interruptEventDao.getEventByStatus(patientsId,
				0);// 未执行的事件
		if (event == null) {
			throw new BaseException(1001, "参数错误");
		} else {
			Date now = new Date();
			Date start = event.getStart_time();
			long l = now.getTime() - start.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			long hour = (l / (60 * 60 * 1000) - day * 24);
			long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
			event.setType(type);
			event.setEnd_time(new Date());
			event.setStatus(1);// 已执行事件
			event.setLength(hour + ":" + min + ":" + s);
			interruptEventDao.update(event);
		}
		return 1;
	}

	@Override
	public List<Interrupt_event> getNotDoInteEvent(Integer status) {
		return interruptEventDao.getNotDoInteEvent(status);
	}
}

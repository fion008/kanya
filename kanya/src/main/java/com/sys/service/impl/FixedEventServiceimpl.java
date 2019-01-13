package com.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.dao.FixedEventDao;
import com.sys.entity.Fixed_event;
import com.sys.service.FixedEventService;

@Transactional
@Service("fixedEventService")
public class FixedEventServiceimpl implements FixedEventService {

	@Autowired
	private FixedEventDao fixedEventDao;

	@Override
	public PageBean findFixedEventList(String keywords, PageParam pageParam) {
		return fixedEventDao.findFixedEventList(keywords, pageParam);
	}

	@Override
	public int updateFixedEvent(Fixed_event fixed_event) {
		if (fixed_event.getId() != null) {
			Fixed_event exist = fixedEventDao.getByKey(fixed_event.getId(),
					Fixed_event.class);
			exist.setName(fixed_event.getName());
			exist.setTime(fixed_event.getTime());
			exist.setContent(fixed_event.getContent());
			return fixedEventDao.update(exist);
		} else {
			fixed_event.setStatus(0L);
			return fixedEventDao.insert(fixed_event);
		}
	}

	@Override
	public Fixed_event getFixedEvent(Long id) {
		return fixedEventDao.getByKey(id, Fixed_event.class);
	}

	@Override
	public List<Fixed_event> getFixedEventList() {
		return fixedEventDao.getFixedEventList();
	}

}

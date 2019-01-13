package com.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.dao.InterruptTypeDao;
import com.sys.entity.Interrupt_type;
import com.sys.service.InterruptTypeService;

@Transactional
@Service("interruptTypeService")
public class InterruptTypeServiceimpl implements InterruptTypeService {
	@Autowired
	private InterruptTypeDao interruptTypeDao;

	@Override
	public PageBean findInterruptTypeList(String keywords, PageParam pageParam) {
		return interruptTypeDao.findInterruptTypeList(keywords, pageParam);
	}

	@Override
	public int updateInterruptType(Interrupt_type interrupt_type) {
		if (interrupt_type.getId() != null) {
			Interrupt_type exist=interruptTypeDao.getByKey(interrupt_type.getId(), Interrupt_type.class);
			exist.setName(interrupt_type.getName());
			exist.setIcon(interrupt_type.getIcon());
			return interruptTypeDao.update(exist);
		} else {
			interrupt_type.setStatus(0L);
			return interruptTypeDao.insert(interrupt_type);
		}
	}

	@Override
	public Interrupt_type getInterruptType(Long id) {
		return interruptTypeDao.getByKey(id, Interrupt_type.class);
	}

}

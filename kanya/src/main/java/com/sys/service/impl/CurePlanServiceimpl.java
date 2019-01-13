package com.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.dao.CurePlanDao;
import com.sys.entity.Cure_plan;
import com.sys.service.CurePlanService;

@Transactional
@Service("curePlanService")
public class CurePlanServiceimpl implements CurePlanService {
	@Autowired
	private CurePlanDao curePlanDao;

	@Override
	public PageBean findCurePlanList(String keywords, PageParam pageParam) {
		return curePlanDao.findCurePlanList(keywords, pageParam);
	}

	@Override
	public int updateCurePlan(Cure_plan cure_plan) {
		if (cure_plan.getId() != null) {
			Cure_plan exist = curePlanDao.getByKey(cure_plan.getId(), Cure_plan.class);
			exist.setName(cure_plan.getName());
			exist.setPhoto(cure_plan.getPhoto());
			return curePlanDao.update(exist);
		} else {
			cure_plan.setStatus(0);
			return curePlanDao.insert(cure_plan);
		}
	}

	@Override
	public Cure_plan getCurePlan(Long id) {
		return curePlanDao.getByKey(id, Cure_plan.class);
	}

	@Override
	public List<Cure_plan> getCurePlanList() {
		return curePlanDao.getCurePlanList();
	}

}

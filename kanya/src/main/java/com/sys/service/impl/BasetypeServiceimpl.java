package com.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.dao.BasetypeDao;
import com.sys.entity.Basetype;
import com.sys.service.BasetypeService;

@Transactional
@Service("basetypeService")
public class BasetypeServiceimpl implements BasetypeService {

	@Autowired
	private BasetypeDao basetypeDao;

	@Override
	public PageBean findBasetypeList(String type, String keywords,
			PageParam pageParam) {
		return basetypeDao.findBasetypeList(type, keywords, pageParam);
	}

	@Override
	public Basetype getBasetypeById(Long id) {
		return basetypeDao.getByKey(id, Basetype.class);
	}

	@Override
	public int updateBasetype(Basetype basetype) {
		if (basetype.getId() != null) {
			Basetype basetypeExist = basetypeDao.getByKey(basetype.getId(),
					Basetype.class);
			basetypeExist.setName(basetype.getName());
			return basetypeDao.update(basetypeExist);
		} else {
			return basetypeDao.insert(basetype);
		}
	}

	@Override
	public List<Basetype> getBasetypeListByType(String type) {
		return basetypeDao.getBasetypeListByType(type);
	}

}

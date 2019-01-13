package com.sys.dao;

import com.sys.base.dao.BaseDao;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.App_user;

public interface AppUserDao extends BaseDao<App_user> {
	/**
	 * 查询小程序用户列表
	 * 
	 * @param keywords
	 * @param pageParam
	 * @return
	 */
	PageBean findAppUserList(String keywords, PageParam pageParam);

	/**
	 * 通过openid获取
	 * 
	 * @param openid
	 * @return
	 */
	App_user getAppUserByOpenId(String openId);

	/**
	 * 通过手机号查询一个用户
	 * 
	 * @param phone
	 * @return
	 */
	App_user getAppUserByPhone(String phone);

}
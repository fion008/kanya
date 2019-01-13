package com.sys.service;

import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.Fa_user;

public interface FaUserService {

	/**
	 * 查询医生列表
	 * 
	 * @param keywords
	 * @param pageParam
	 * @return
	 */
	PageBean findFaUserList(String keywords, PageParam pageParam);

	/**
	 * 新增/修改医生
	 * 
	 * @param Fa_User
	 * @return
	 */
	int updateFaUser(Fa_user fa_User);

	/**
	 * 通过id获取医生信息
	 * 
	 * @param id
	 * @return
	 */
	Fa_user getFaUser(Long id);

	/**
	 * 通过手机号查询医生
	 * 
	 * @param phone
	 * @return
	 */
	Fa_user getFaUserByPhone(String phone);

	/**
	 * 根据小程序用户查询医生信息
	 * 
	 * @param appUserId
	 * @return
	 */
	Fa_user getFaUserByAppUserId(Long appUserId);

}

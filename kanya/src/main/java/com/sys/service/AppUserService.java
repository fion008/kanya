package com.sys.service;

import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.App_user;

public interface AppUserService {

	/**
	 * 查询小程序用户列表
	 * 
	 * @param keywords
	 * @param pageParam
	 * @return
	 */
	PageBean findAppUserList(String keywords, PageParam pageParam);

	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */
	int updateAppUser(App_user app_user);

	int updatePhone(Long id, String phone,String name,String avatar);

	/**
	 * 通过id获取小程序用户信息
	 * 
	 * @param id
	 * @return
	 */
	App_user getAppUser(Long id);

	/**
	 * 通过账号查询小程序用户
	 * 
	 * @param account
	 * @return
	 */
	App_user getAppUserByOpenId(String openId);

	/**
	 * 微信登录
	 * 
	 * @param code
	 * @return
	 */
	App_user wechatLogin(String code);

	/**
	 * 通过手机号查询一个用户
	 * 
	 * @param phone
	 * @return
	 */
	App_user getAppUserByPhone(String phone);
	
	/**
	 * 设为医生
	 * @param id
	 * @return
	 */
	int setAppUserBeDoctor(Long id);

}

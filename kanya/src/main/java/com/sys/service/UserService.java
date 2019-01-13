package com.sys.service;

import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.User;

public interface UserService {

	/**
	 * 通过id获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	User getUser(Long id);

	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */
	int updateUser(User user);

	/**
	 * 更新密码
	 * 
	 * @param id
	 * @param newPwd
	 * @param oldPwd
	 * @return
	 */
	int updatePwd(Long id, String newPwd, String oldPwd);

	/**
	 * 查询用户列表
	 * 
	 * @param keywords
	 * @param pageParam
	 * @return
	 */
	PageBean findUserList(String keywords,PageParam pageParam);

	/**
	 * 通过账号查询用户
	 * 
	 * @param account
	 * @return
	 */
	User getUserByAccount(String account);



}

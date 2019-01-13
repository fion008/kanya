package com.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.base.exception.BaseException;
import com.sys.base.tool.MD5;
import com.sys.base.tool.StringUtil;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.dao.UserDao;
import com.sys.entity.User;
import com.sys.service.UserService;

@Transactional
@Service("userService")
public class UserServiceimpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User getUser(Long id) {
		return userDao.getByKey(id, User.class);
	}

	@Override
	public int updateUser(User user) {
		if (user.getId() != null) {
			User exist = userDao.getByKey(user.getId(), User.class);
			exist.setName(user.getName());
			exist.setLogin_name(user.getLogin_name());
			exist.setRoleId(exist.getRoleId());
			if (!exist.getPassword().equalsIgnoreCase(user.getPassword())) {
				exist.setPassword(MD5.getMD5Str(user.getPassword()));
			}
			exist.setPhone(user.getPhone());
			return userDao.update(exist);
		} else {
			return userDao.insert(user);
		}
	}

	@Override
	public PageBean findUserList(String keywords, PageParam pageParam) {
		return userDao.findUserList(keywords, pageParam);
	}

	@Override
	public User getUserByAccount(String account) {
		String type = "phone";
		if (StringUtil.isPhone(account)) {
			type = "phone";
		} else if (StringUtil.isEmail(account)) {
			type = "email";
		} else if (StringUtil.isUsername(account)) {
			type = "login_name";
		}
		return userDao.findUserByAccount(account, type);
	}

	@Override
	public int updatePwd(Long id, String newPwd, String oldPwd) {
		User userExist = userDao.getByKey(id, User.class);
		System.out.println(MD5.getMD5Str(oldPwd));
		if (MD5.getMD5Str(oldPwd).equalsIgnoreCase(userExist.getPassword())) {
			userExist.setPassword(MD5.getMD5Str(newPwd));
			userDao.update(userExist);
		} else {
			throw new BaseException(1001, "旧密码输入错误");
		}
		return 0;
	}

}

package com.sys.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.sys.service.UserService;

public abstract class BaseController {
	@Autowired
	private UserService userService;

	protected String getAdminId(HttpServletRequest req) {
		return (String) req.getSession().getAttribute("userId");
	}

	protected Long getWechatUserId(String userId) {
		return 0L;
	}
}
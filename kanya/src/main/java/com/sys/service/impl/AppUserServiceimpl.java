package com.sys.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sys.common.Constant;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.common.utils.SendRequest;
import com.sys.dao.AppUserDao;
import com.sys.dao.FaUserDao;
import com.sys.entity.App_user;
import com.sys.entity.Fa_user;
import com.sys.service.AppUserService;
import com.sys.service.SeqService;

@Transactional
@Service("appUserService")
public class AppUserServiceimpl implements AppUserService {
	@Autowired
	private AppUserDao appUserDao;
	@Autowired
	private SeqService seqService;
	@Autowired
	private FaUserDao faUserDao;

	@Override
	public PageBean findAppUserList(String keywords, PageParam pageParam) {
		return appUserDao.findAppUserList(keywords, pageParam);
	}

	@Override
	public int updateAppUser(App_user app_user) {
		if (app_user.getId() != null) {
			return appUserDao.update(app_user);
		} else {
			return appUserDao.insert(app_user);
		}
	}

	@Override
	public App_user getAppUser(Long id) {
		return appUserDao.getByKey(id, App_user.class);
	}

	@Override
	public App_user getAppUserByOpenId(String openId) {
		return appUserDao.getAppUserByOpenId(openId);
	}

	@Override
	public App_user wechatLogin(String code) {
		String requestUrl = Constant.URL;
		Map<String, String> requestUrlParam = new HashMap<String, String>();
		requestUrlParam.put("appid", Constant.APPID); // 开发者设置中的appId
		requestUrlParam.put("secret", Constant.SECRET); // 开发者设置中的appSecret
		requestUrlParam.put("js_code", code); // 小程序调用wx.login返回的code
		requestUrlParam.put("grant_type", "authorization_code"); // 默认参数
		JSONObject jsonObject = JSON.parseObject(SendRequest.sendPost(
				requestUrl, requestUrlParam));
		System.out.println("微信返回信息：" + jsonObject);
		String openid = (String) jsonObject.get("openid");// 获取openid
		App_user user = appUserDao.getAppUserByOpenId(openid);
		if (user == null) {
			user = new App_user();
			Long id = seqService.getSeqByName("USER");
			user.setId(id);
			user.setOpenId(openid);
			user.setSession_key((String) jsonObject.get("session_key"));
			appUserDao.insert(user);
		} else {
			user.setSession_key((String) jsonObject.get("session_key"));
			appUserDao.update(user);
		}
		return user;
	}

	@Override
	public App_user getAppUserByPhone(String phone) {
		return appUserDao.getAppUserByPhone(phone);
	}

	@Override
	public int updatePhone(Long id, String phone, String name, String avatar) {
		App_user exist = appUserDao.getByKey(id, App_user.class);
		exist.setPhone(phone);
		exist.setName(name);
		exist.setAvatar(avatar);
		Fa_user user = faUserDao.getFaUserByPhone(phone);
		if (user != null) {
			exist.setIdentity(0L); // 医生
			user.setApp_user_id(id);
			faUserDao.update(user);
		} else {
			exist.setIdentity(1L); // 患者
		}
		return appUserDao.update(exist);
	}

	public static void main(String[] args) {
		new AppUserServiceimpl()
				.wechatLogin("033uVJyD11cf360LnbzD1l12zD1uVJyQ");
	}

	@Override
	public int setAppUserBeDoctor(Long id) {
		App_user exist = appUserDao.getByKey(id, App_user.class);
		exist.setIdentity(0L);
		appUserDao.update(exist);
		Fa_user user = faUserDao.getFaUserByPhone(exist.getPhone());
		if (user != null) {
			user.setApp_user_id(id);
			faUserDao.update(user);
		}
		return 1;
	}
}

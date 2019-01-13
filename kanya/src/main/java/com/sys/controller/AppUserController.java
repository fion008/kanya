package com.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.exceptions.ClientException;
import com.sys.base.controller.BaseController;
import com.sys.common.MediaType;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.common.utils.SendCodeUtil;
import com.sys.entity.App_user;
import com.sys.response.AppResponse;
import com.sys.service.AppUserService;

@Controller
@RequestMapping("/appuser")
public class AppUserController extends BaseController {

	@Autowired
	private AppUserService appUserService;

	/**
	 * 用户列表分页查询
	 * 
	 * @param keywords
	 * @param pageParam
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pagelist", method = RequestMethod.POST)
	public AppResponse findPageList(
			@RequestParam(required = false) String keywords,
			@ModelAttribute("pageParam") PageParam pageParam,
			HttpServletRequest req) {
		PageBean pb = appUserService.findAppUserList(keywords, pageParam);
		return AppResponse.okList(pb);
	}

	/**
	 * 更新手机号
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePhone", method = RequestMethod.POST)
	public AppResponse update(@RequestParam(required = true) Long id,
			@RequestParam(required = true) String phone, @RequestParam(required = false) String name,
			@RequestParam(required = true) String avatar,HttpServletRequest req) {
		int result = appUserService.updatePhone(id, phone,name,avatar);
		return AppResponse.okData(result);
	}

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public AppResponse updatePhone(@ModelAttribute App_user user,
			HttpServletRequest req) {
		int result = appUserService.updateAppUser(user);
		return AppResponse.okData(result);
	}

	/**
	 * 通过id查询一条记录
	 * 
	 * @param id
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/byid", method = RequestMethod.POST)
	public AppResponse findAppUserById(@RequestParam(required = false) Long id,
			HttpServletRequest req) {
		App_user result = appUserService.getAppUser(id);
		return AppResponse.okData(result);
	}

	/**
	 * 微信登录
	 * 
	 * @param code
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED)
	public AppResponse userWechatLogin(@RequestParam("code") String code,
			HttpServletRequest req) {
		App_user user = appUserService.wechatLogin(code);
		HttpSession session = req.getSession();
		session.setAttribute(user.getId().toString(),
				user.getOpenId() + user.getSession_key());
		session.setAttribute("WechatuserId", user.getId());
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", user.getId().toString());
		map.put("openId", user.getOpenId());
		return AppResponse.okData(user);
	}

	/**
	 * 发送验证码
	 * 
	 * @param phone
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/sendCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED)
	public AppResponse sendCode(@RequestParam("phone") String phone,
			HttpServletRequest req) {
		// 如有验证码,删除
		req.getSession().removeAttribute("code");
		App_user user = appUserService.getAppUserByPhone(phone);
		// 六位数字验证码
		String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
		String result = "";
		System.out.println("验证码是：" + code);
		if (user != null) {
			result = "手机号已注册";
		} else {
			req.getSession().setAttribute("code", code);
			try {
				SendCodeUtil.sendSms(phone, "用户", code);
			} catch (ClientException e) {
				e.printStackTrace();
			}
			result = "发送验证码成功";
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", result);
		map.put("code", code);
		return AppResponse.okData(map);
	}
	
	/**
	 * 设为医生
	 * @param id
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/set", method = RequestMethod.POST)
	public AppResponse setAppUserBeDoctor(@RequestParam(required = false) Long id,
			HttpServletRequest req) {
		int result = appUserService.setAppUserBeDoctor(id);
		return AppResponse.okData(result);
	}
}

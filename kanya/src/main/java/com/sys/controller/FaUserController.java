package com.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.base.controller.BaseController;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.Fa_user;
import com.sys.response.AppResponse;
import com.sys.service.FaUserService;

@Controller
@RequestMapping("/fauser")
public class FaUserController extends BaseController {

	@Autowired
	private FaUserService faUserService;

	/**
	 * 医生列表分页查询
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
		PageBean pb = faUserService.findFaUserList(keywords, pageParam);
		return AppResponse.okList(pb);
	}

	/**
	 * 新增、修改医生
	 * 
	 * @param resource
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public AppResponse update(@ModelAttribute Fa_user fa_user,
			HttpServletRequest req) {
		int result = faUserService.updateFaUser(fa_user);
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
	public AppResponse findFaUserById(@RequestParam(required = false) Long id,
			HttpServletRequest req) {
		Fa_user result = faUserService.getFaUser(id);
		return AppResponse.okData(result);
	}

	/**
	 * 获取医生信息
	 * @param appUserId
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/byAppUserId", method = RequestMethod.POST)
	public AppResponse getFaUserByAppUserId(@RequestParam(required = false) Long app_user_id,
			HttpServletRequest req) {
		Fa_user result = faUserService.getFaUserByAppUserId(app_user_id);
		return AppResponse.okData(result);
	}
}

package com.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.base.controller.BaseController;
import com.sys.base.exception.BaseException;
import com.sys.base.tool.MD5;
import com.sys.common.MediaType;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.User;
import com.sys.response.AppResponse;
import com.sys.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

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
		PageBean pb = userService.findUserList(keywords, pageParam);
		return AppResponse.okList(pb);
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
	public AppResponse update(@ModelAttribute User user, HttpServletRequest req) {
		int result = userService.updateUser(user);
		return AppResponse.okData(result);
	}

	/**
	 * 更新密码
	 * 
	 * @param id
	 * @param newPwd
	 * @param oldPwd
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
	public AppResponse updatePwd(@RequestParam(required = true) Long id,
			@RequestParam(required = true) String newPwd,
			@RequestParam(required = true) String oldPwd, HttpServletRequest req) {
		int result = userService.updatePwd(id, newPwd, oldPwd);
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
	public AppResponse findUserById(@RequestParam(required = false) Long id,
			HttpServletRequest req) {
		User result = userService.getUser(id);
		return AppResponse.okData(result);
	}

	/**
	 * 登录
	 * 
	 * @param account
	 * @param password
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED)
	public AppResponse userLogin(@RequestParam("account") String account,
			@RequestParam("password") String password,
			@RequestParam(required = false) String code, HttpServletRequest req) {
		User user = userService.getUserByAccount(account);
		HttpSession session = req.getSession();
		String codes = (String) session.getAttribute("code");

	/*	if (!codes.equalsIgnoreCase(code)) {
			throw new BaseException(1001, "验证码错误");
		}
*/
		if (user != null) {
			System.out.println("密码" + MD5.getMD5Str(password));
			if (MD5.getMD5Str(password).equalsIgnoreCase(user.getPassword())) {
				session.setAttribute("userId", user.getId());
			} else {
				throw new BaseException(1002, "密码错误");
			}
		} else {
			throw new BaseException(1002, "该用户不存在");
		}
		return AppResponse.okData(user);
	}

}

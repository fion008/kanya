package com.sys.controller;

import java.util.List;

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
import com.sys.entity.Role;
import com.sys.response.AppResponse;
import com.sys.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;

	/**
	 * 角色列表分页查询
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
		PageBean pb = roleService.findRoleList(keywords, pageParam);
		return AppResponse.okList(pb);
	}

	/**
	 * 修改角色
	 * 
	 * @param role
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public AppResponse update(@ModelAttribute Role role, HttpServletRequest req) {
		int result = roleService.updateRole(role);
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
	public AppResponse findRoleById(@RequestParam(required = false) Long id,
			HttpServletRequest req) {
		Role result = roleService.getRole(id);
		return AppResponse.okData(result);
	}

	/**
	 * 角色列表
	 * 
	 * @param id
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public AppResponse getRoleList(@RequestParam(required = false) Long id,
			HttpServletRequest req) {
		List<Role> result = roleService.getRoleList();
		return AppResponse.okList(result);
	}

}

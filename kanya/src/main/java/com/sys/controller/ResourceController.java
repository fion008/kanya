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
import com.sys.entity.Resource;
import com.sys.response.AppResponse;
import com.sys.response.ResourceResp;
import com.sys.response.RoleResourceResp;
import com.sys.service.ResourceService;

@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {

	@Autowired
	private ResourceService resourceService;

	/**
	 * 资源列表分页查询
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
		PageBean pb = resourceService.findResourceList(keywords, pageParam);
		return AppResponse.okList(pb);
	}

	/**
	 * 修改资源
	 * 
	 * @param resource
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public AppResponse update(@ModelAttribute Resource resource,
			HttpServletRequest req) {
		int result = resourceService.updateResource(resource);
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
	public AppResponse findResourceById(
			@RequestParam(required = false) Long id, HttpServletRequest req) {
		Resource result = resourceService.getResource(id);
		return AppResponse.okData(result);
	}

	/**
	 * 资源列表
	 * 
	 * @param type
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public AppResponse getResourceList(
			@RequestParam(required = false) String type, HttpServletRequest req) {
		List<Resource> result = resourceService.getResourceList(type);
		return AppResponse.okList(result);
	}

	/**
	 * 查询菜单列表
	 * 
	 * @param userId
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/byUserId", method = { RequestMethod.POST,
			RequestMethod.GET })
	public AppResponse getResourceList(
			@RequestParam(required = false) Long userId, HttpServletRequest req) {
		List<ResourceResp> result = resourceService.getResourceByUserId(userId);
		return AppResponse.okList(result);
	}

	/**
	 * 通过角色查询列表
	 * 
	 * @param roleId
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/byRoleId", method = { RequestMethod.POST,
			RequestMethod.GET })
	public AppResponse getResourceListByRoleId(
			@RequestParam(required = false) Long roleId, HttpServletRequest req) {
		List<RoleResourceResp> result = resourceService
				.getResourceByRoleId(roleId);
		return AppResponse.okList(result);
	}
}

package com.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.base.controller.BaseController;
import com.sys.response.AppResponse;
import com.sys.response.RoleResourceResp;
import com.sys.service.RoleResourceService;

@Controller
@RequestMapping("/roleResource")
public class RoleResourceController extends BaseController {

	@Autowired
	private RoleResourceService roleResourceService;

	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public AppResponse update(@RequestHeader Long roleId,
			@RequestBody List<RoleResourceResp> roleResourceRespList,
			HttpServletRequest req) {
		int result = roleResourceService.updateRoleResource(
				roleResourceRespList, roleId);
		return AppResponse.okData(result);
	}
}

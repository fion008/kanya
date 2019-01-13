package com.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.base.controller.BaseController;
import com.sys.base.tool.StringUtil;
import com.sys.common.utils.DreamCaptcha;
import com.sys.response.AppResponse;
import com.sys.service.CommonsService;

@Controller
@RequestMapping("/common")
public class CommonsController extends BaseController {
	@Autowired
	private CommonsService commonsService;

	@ResponseBody
	@RequestMapping(value = "/code", method = RequestMethod.GET)
	public AppResponse getCode(HttpServletRequest req, HttpServletResponse res) {
		new DreamCaptcha().generate(req, res);
		return AppResponse.okData("ok");
	}

	/**
	 * 通用的改状态的方法
	 * 
	 * @param tableName
	 * @param field
	 * @param value
	 * @param keyField
	 * @param keyValue
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/change", method = RequestMethod.POST)
	public AppResponse changeStatus(
			@RequestParam(required = true) String tableName,
			@RequestParam(required = false) String field,
			@RequestParam(required = true) String value,
			@RequestParam(required = false) String keyField,
			@RequestParam(required = true) String keyValue,
			HttpServletRequest req) {
		if (StringUtil.isBlank(field)) {
			field = "status";
		}
		if (StringUtil.isBlank(keyField)) {
			keyField = "id";
		}
		commonsService
				.changeStatus(tableName, field, value, keyField, keyValue);
		return AppResponse.okData("ok");
	}

}

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
import com.sys.entity.Basetype;
import com.sys.response.AppResponse;
import com.sys.service.BasetypeService;

@Controller
@RequestMapping("/basetype")
public class BasetypeController extends BaseController {

	@Autowired
	private BasetypeService basetypeService;

	/**
	 * 分页查询基础数据
	 * 
	 * @param keywords
	 * @param type
	 * @param pageParam
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pagelist", method = RequestMethod.POST)
	public AppResponse findPageList(
			@RequestParam(required = false) String keywords,
			@RequestParam(required = false) String type,
			@ModelAttribute("pageParam") PageParam pageParam,
			HttpServletRequest req) {
		PageBean pb = basetypeService.findBasetypeList(type, keywords,
				pageParam);
		return AppResponse.okList(pb);
	}

	/**
	 * 修改或者新增
	 * 
	 * @param basetype
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public AppResponse findPageList(@ModelAttribute Basetype basetype,
			HttpServletRequest req) {
		int result = basetypeService.updateBasetype(basetype);
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
	public AppResponse findBasetypeById(
			@RequestParam(required = false) Long id, HttpServletRequest req) {
		Basetype result = basetypeService.getBasetypeById(id);
		return AppResponse.okData(result);
	}

	/**
	 * 通过type查询
	 * 
	 * @param id
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/byType", method = RequestMethod.POST)
	public AppResponse findBasetypeByType(
			@RequestParam(required = false) String type, HttpServletRequest req) {
		List<Basetype> result = basetypeService.getBasetypeListByType(type);
		return AppResponse.okList(result);
	}
}
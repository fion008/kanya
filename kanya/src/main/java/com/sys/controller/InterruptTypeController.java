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
import com.sys.entity.Interrupt_type;
import com.sys.response.AppResponse;
import com.sys.service.InterruptTypeService;

@Controller
@RequestMapping("/interruptType")
public class InterruptTypeController extends BaseController {

	@Autowired
	private InterruptTypeService interruptTypeService;

	/**
	 * 中断事件类型列表分页查询
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
		PageBean pb = interruptTypeService.findInterruptTypeList(keywords,
				pageParam);
		return AppResponse.okList(pb);
	}

	/**
	 * 新增、修改中断事件类型
	 * 
	 * @param resource
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public AppResponse update(@ModelAttribute Interrupt_type interrupt_ype,
			HttpServletRequest req) {
		int result = interruptTypeService.updateInterruptType(interrupt_ype);
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
	public AppResponse findInterruptTypeById(
			@RequestParam(required = false) Long id, HttpServletRequest req) {
		Interrupt_type result = interruptTypeService.getInterruptType(id);
		return AppResponse.okData(result);
	}

}

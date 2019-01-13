package com.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.base.controller.BaseController;
import com.sys.base.tool.StringUtil;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.dao.CurePlanDao;
import com.sys.entity.Cure_plan;
import com.sys.response.AppResponse;
import com.sys.response.FixedEventResp;
import com.sys.service.CurePlanService;

@Controller
@RequestMapping("/cureplan")
public class CurePlanController extends BaseController {

	@Autowired
	private CurePlanService curePlanService;
	@Autowired
	private CurePlanDao curePlanDao;
	
	/**
	 * 矫正方案列表分页查询
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
		PageBean pb = curePlanService.findCurePlanList(keywords, pageParam);
		return AppResponse.okList(pb);
	}

	/**
	 * 新增、修改治疗方案
	 * 
	 * @param resource
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public AppResponse update(@ModelAttribute Cure_plan cure_plan,
			HttpServletRequest req) {
		int result = curePlanService.updateCurePlan(cure_plan);
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
	public AppResponse findCurePlanById(
			@RequestParam(required = false) Long id, HttpServletRequest req) {
		Cure_plan result = curePlanService.getCurePlan(id);
		return AppResponse.okData(result);
	}

	/**
	 * 更新关联事件
	 * 
	 * @param curePlanId
	 * @param fixedEventRespList
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateEventIds", method = RequestMethod.POST)
	public AppResponse updateEventIds(@RequestHeader Long curePlanId,
			@RequestBody List<FixedEventResp> fixedEventRespList,
			HttpServletRequest req) {
		String ids = "";
		for (FixedEventResp feResp : fixedEventRespList) {
			if (StringUtil.isBlank(ids)) {
				ids = feResp.getId().toString();
			} else {
				ids = ids + "," + feResp.getId().toString();
			}
		}
		Cure_plan cure_plan = curePlanService.getCurePlan(curePlanId);
		cure_plan.setEventIds(ids);
		int result = curePlanDao.update(cure_plan);
		return AppResponse.okData(result);
	}
}

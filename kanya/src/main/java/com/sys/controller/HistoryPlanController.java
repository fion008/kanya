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
import com.sys.entity.History_plan;
import com.sys.response.AppResponse;
import com.sys.service.HistoryPlanService;

@Controller
@RequestMapping("/historyplan")
public class HistoryPlanController extends BaseController {

	@Autowired
	private HistoryPlanService historyPlanService;



	/**
	 * 修改或者新增
	 * 
	 * @param historyPlan
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public AppResponse findPageList(@ModelAttribute History_plan historyPlan,
			HttpServletRequest req) {
		int result = historyPlanService.updateHistoryPlan(historyPlan);
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
		History_plan result = historyPlanService.getHistoryPlanById(id);
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
	@RequestMapping(value = "/byPatientsId", method = RequestMethod.POST)
	public AppResponse findBasetypeByType(
			@RequestParam(required = false) Long patientsId, HttpServletRequest req) {
		List<History_plan> result = historyPlanService.getHistoryPlanByPatientsId(patientsId);
		return AppResponse.okList(result);
	}
}
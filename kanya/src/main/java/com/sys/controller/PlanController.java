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
import com.sys.entity.Plan;
import com.sys.response.AppResponse;
import com.sys.response.PlanResp;
import com.sys.service.PlanService;

@Controller
@RequestMapping("/plan")
public class PlanController extends BaseController {

	@Autowired
	private PlanService planService;

	/**
	 * 更新计划
	 * 
	 * @param plan
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public AppResponse update(@ModelAttribute Plan plan, HttpServletRequest req) {
		int result = planService.updatePlan(plan);
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
	public AppResponse findPlanById(@RequestParam(required = false) Long id,
			HttpServletRequest req) {
		Plan result = planService.getPlan(id);
		return AppResponse.okData(result);
	}

	/**
	 * 获取计划
	 * 
	 * @param app_user_id
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/byPatientsId", method = RequestMethod.POST)
	public AppResponse findPlanByUserId(
			@RequestParam(required = false) Long patientsId,
			HttpServletRequest req) {
		PlanResp result = planService.getPlanByPatientsId(patientsId);
		return AppResponse.okData(result);
	}
	
	/**
	 * 删除计划
	 * @param patientsId
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public AppResponse deletePlan(
			@RequestParam(required = false) Long patientsId,
			HttpServletRequest req) {
		int result = planService.deletePlan(patientsId);
		return AppResponse.okData(result);
	}
}

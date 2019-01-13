package com.sys.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.sys.base.exception.BaseException;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.entity.Cure_plan;
import com.sys.entity.Fixed_event;
import com.sys.response.AppResponse;
import com.sys.response.FixedEventResp;
import com.sys.service.CurePlanService;
import com.sys.service.FixedEventService;

@Controller
@RequestMapping("/fixedEvent")
public class FixedEventController extends BaseController {

	@Autowired
	private FixedEventService fixedEventService;
	@Autowired
	private CurePlanService curePlanService;

	/**
	 * 固定事件列表分页查询
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
		PageBean pb = fixedEventService.findFixedEventList(keywords, pageParam);
		return AppResponse.okList(pb);
	}

	/**
	 * 修改固定事件
	 * 
	 * @param role
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public AppResponse update(@ModelAttribute Fixed_event fixed_event,
			HttpServletRequest req) {
		int result = fixedEventService.updateFixedEvent(fixed_event);
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
	public AppResponse findFixedEventById(
			@RequestParam(required = false) Long id, HttpServletRequest req) {
		Fixed_event result = fixedEventService.getFixedEvent(id);
		return AppResponse.okData(result);
	}

	/**
	 * 固定事件列表
	 * 
	 * @param id
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public AppResponse getFixedEventList(HttpServletRequest req) {
		List<Fixed_event> result = fixedEventService.getFixedEventList();
		return AppResponse.okList(result);
	}
     
	/**
	 * 查询计划下的固定事件
	 * @param curePlanId
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/byCurePlanId", method = RequestMethod.POST)
	public AppResponse getFixedEventByCurePlanId(
			@RequestParam(required = true) Long curePlanId,
			HttpServletRequest req) {
		List<Fixed_event> feList = fixedEventService.getFixedEventList();
		Cure_plan plan = curePlanService.getCurePlan(curePlanId);
		if (plan == null) {
			throw new BaseException(1001, "参数错误");
		}
		List<FixedEventResp> feRespList = new ArrayList<FixedEventResp>();
		FixedEventResp feResp = null;
		String eventId[] = null;
		List<String> eventlist = null;
		if (plan.getEventIds() != null) {
			eventId = plan.getEventIds().split(",");
			eventlist = Arrays.asList(eventId);
		}
		for (Fixed_event fe : feList) {
			feResp = new FixedEventResp();
			feResp.setId(fe.getId());
			feResp.setName(fe.getName());
			if (eventlist != null && eventlist.contains(fe.getId().toString())) {
				feResp.setChecked(true);
			} else {
				feResp.setChecked(false);
			}
			feRespList.add(feResp);
		}
		return AppResponse.okList(feRespList);
	}
}

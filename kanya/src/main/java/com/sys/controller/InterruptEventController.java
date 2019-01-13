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
import com.sys.entity.Interrupt_event;
import com.sys.response.AppResponse;
import com.sys.response.InterruptEventResp;
import com.sys.service.InterruptEventService;

@Controller
@RequestMapping("/interruptEvent")
public class InterruptEventController extends BaseController {

	@Autowired
	private InterruptEventService interruptEventService;

	/**
	 * 事件
	 * 
	 * @param patientsId
	 * @param status
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public AppResponse findPageList(
			@RequestParam(required = false) Long patientsId,
			@RequestParam(required = false) Integer status,
			@RequestParam(required = false) String date,
			HttpServletRequest req) {
		List<InterruptEventResp> pb = interruptEventService
				.getInteEventByPatientsId(patientsId, status,date);
		return AppResponse.okList(pb);
	}

	/**
	 * 新增、修改中断事件
	 * 
	 * @param resource
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public AppResponse update(@ModelAttribute Interrupt_event interrupt_event,
			HttpServletRequest req) {
		int result = interruptEventService
				.updateInterruptEvent(interrupt_event);
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
	public AppResponse findInterruptEventById(
			@RequestParam(required = false) Long id, HttpServletRequest req) {
		Interrupt_event result = interruptEventService.getInterruptEvent(id);
		return AppResponse.okData(result);
	}

	/**
	 * 带回牙套
	 * 
	 * @param patientsId
	 * @param type
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/change", method = RequestMethod.POST)
	public AppResponse changeInterruptEvent(
			@RequestParam(required = false) Long patientsId,
			@RequestParam(required = false) Long type, HttpServletRequest req) {
		int result = interruptEventService.changeInterruptEvent(patientsId,
				type);
		return AppResponse.okData(result);
	}
}

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
import com.sys.entity.Patients;
import com.sys.response.AppResponse;
import com.sys.service.PatientsService;

@Controller
@RequestMapping("/patients")
public class PatientsController extends BaseController {

	@Autowired
	private PatientsService patientsService;

	/**
	 * 患者列表分页查询
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
			@RequestParam(required = false) Long app_user_id,
			@RequestParam(required = false) Long doctorId,
			@ModelAttribute("pageParam") PageParam pageParam,
			HttpServletRequest req) {
		PageBean pb = patientsService.findPatientsList(keywords, app_user_id,
				doctorId, pageParam);
		return AppResponse.okList(pb);
	}

	/**
	 * 更新患者
	 * 
	 * @param patients
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public AppResponse update(@ModelAttribute Patients patients,
			HttpServletRequest req) {
		int result = patientsService.updatePatients(patients);
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
	public AppResponse findPatientsById(
			@RequestParam(required = false) Long id, HttpServletRequest req) {
		Patients result = patientsService.getPatients(id);
		return AppResponse.okData(result);
	}

	/**
	 * 获取某个小程序用户的患者列表
	 * 
	 * @param app_user_id
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/byAppUserId", method = RequestMethod.POST)
	public AppResponse findPatientsByUserId(
			@RequestParam(required = false) Long app_user_id,
			HttpServletRequest req) {
		List<Patients> result = patientsService
				.getPatientsByAppUserId(app_user_id);
		return AppResponse.okList(result);
	}
	
	/**
	 * 更新医生
	 * @param patients
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateDoctor", method = RequestMethod.POST)
	public AppResponse updateDoctor(@RequestParam(required = true) Long patientsId,
			@RequestParam(required = true) Long doctorId,
			HttpServletRequest req) {
		int result = patientsService.updateDoctor(patientsId,doctorId);
		return AppResponse.okData(result);
	}

}

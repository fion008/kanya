package com.sys.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sys.common.PageBean;

public class AppResponse {
	@JsonInclude(Include.NON_NULL)
	// 将该标记放在属性上，如果该属性为NULL则不参与json化
	private Integer code;
	@JsonInclude(Include.NON_NULL)
	private String message;
	@JsonInclude(Include.NON_NULL)
	private Object data;
	@JsonInclude(Include.NON_NULL)
	private List<?> dataList;
	@JsonInclude(Include.NON_NULL)
	private Integer currentPage;
	@JsonInclude(Include.NON_NULL)
	private Integer totalPage;
	@JsonInclude(Include.NON_NULL)
	private Integer numPerPage;
	@JsonInclude(Include.NON_NULL)
	private Integer totalCount;
	@JsonInclude(Include.NON_NULL)
	private Boolean hasNext;

	public AppResponse(int code, String message, Object data, List<?> dataList) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.dataList = dataList;
	}

	public static AppResponse okData() {
		AppResponse a = new AppResponse();
		a.code = 200;
		a.message = "success";
		return a;
	}

	public static AppResponse okData(Object data) {
		AppResponse a = new AppResponse();
		a.data = data;
		a.code = 200;
		a.message = "success";
		return a;
	}

	public static AppResponse okList(List<?> dataList) {
		AppResponse a = new AppResponse();
		a.dataList = dataList;
		a.code = 200;
		a.message = "success";
		return a;
	}

	public static AppResponse okList(PageBean pb) {
		return okList(pb.getCurrentPage(), pb.getNumPerPage(),
				pb.getTotalCount(), pb.getRecordList());
	}

	public static AppResponse okList(int currentPage, int numPerPage,
			int totalCount, List<?> dataList) {
		AppResponse a = okList(dataList);
		a.currentPage = currentPage;
		a.totalCount = totalCount;
		a.numPerPage = numPerPage;
		a.totalPage = (totalCount + numPerPage - 1) / numPerPage;
		a.hasNext = currentPage < a.totalPage;
		return a;
	}

	public AppResponse() {
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(Integer numPerPage) {
		this.numPerPage = numPerPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Boolean getHasNext() {
		return hasNext;
	}

	public void setHasNext(Boolean hasNext) {
		this.hasNext = hasNext;
	}

}

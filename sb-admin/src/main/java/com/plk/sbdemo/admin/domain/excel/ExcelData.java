package com.plk.sbdemo.admin.domain.excel;

import java.io.Serializable;
import java.util.List;

public class ExcelData<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ExcelHeader> headers;
	private List<T> dataList;

	public List<ExcelHeader> getHeaders() {
		return headers;
	}

	public void setHeaders(List<ExcelHeader> headers) {
		this.headers = headers;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
}

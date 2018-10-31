package com.plk.sbdemo.admin.domain.excel;

public class ExcelHeader {

	private String field;
	private String title;
	private String format;

	public ExcelHeader(){}
	
	public ExcelHeader(String field, String title){
		this.field = field;
		this.title = title;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}

/**
 * 
 */
package com.plk.sbdemo.shiro.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 接口访问，返回的数据封装
 * 
 * @author franp
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ApiResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static String FAIL_CODE = "9999";
	public static String SUCC_CODE = "0000";

	private String code; // 错误码
	private String msg;  // 错误信息
	private Object data; // 请求数据
	
	/**
	 * 采用静态方式构建对象
	 */
	private ApiResponse() {
	}
	
	public static ApiResponse sucessResponse() {
		return sucessResponse(null);
	}
	
	public static ApiResponse sucessResponse(Object data) {
		return new ApiResponse()
				.setCode(SUCC_CODE)
				.setMsg("请求成功")
				.setData(data);
	}
	
	public static ApiResponse failResponse(String msg) {
		return new ApiResponse()
				.setCode(FAIL_CODE)
				.setMsg(msg);
	}

	public String getCode() {
		return code;
	}

	public ApiResponse setCode(String code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public ApiResponse setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public ApiResponse setData(Object data) {
		this.data = data;
		return this;
	}
}

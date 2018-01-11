package com.pt.vo;

public class ResultInfo<T> {

	public static final Integer OK = 0;
	public static final Integer ERROR = 100;

	private Integer code = OK;
	private String message;
	private String url;
	
	public ResultInfo() {
		super();
	}
	
	public ResultInfo(T data) {
		super();
		this.data = data;
	}
	private T data;
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}

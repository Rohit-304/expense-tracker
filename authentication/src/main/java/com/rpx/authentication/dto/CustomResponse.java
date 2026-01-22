package com.rpx.authentication.dto;

public class CustomResponse {

	private int code;

	private Object data;

	private String message;

	public CustomResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomResponse(int code, Object data, String message) {
		super();
		this.code = code;
		this.data = data;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

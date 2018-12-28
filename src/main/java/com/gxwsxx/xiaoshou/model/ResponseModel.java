package com.gxwsxx.xiaoshou.model;

public class ResponseModel {

	Integer status = Integer.valueOf(0);
	String msg = "ok";
	Object data;
	
	public ResponseModel() {
	}

	public ResponseModel(Object data) {
		this.data = data;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}

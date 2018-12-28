package com.gxwsxx.xiaoshou.model;

import java.time.LocalDateTime;

public class GoodsModel {

	String id;
	String name;
	Integer remain;
	Status status;
	LocalDateTime addTime;

	public enum Status {
		NORMAL, DELETE
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRemain() {
		return remain;
	}

	public void setRemain(Integer remain) {
		this.remain = remain;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getAddTime() {
		return addTime;
	}

	public void setAddTime(LocalDateTime addTime) {
		this.addTime = addTime;
	}

}

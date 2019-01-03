package com.gxwsxx.xiaoshou.exception;

public class XiaoshouException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8134128289831652430L;
	Type type;

	public Integer getStatus() {
		return this.type.ordinal();
	}

	public XiaoshouException() {
		this.type = Type.SYSTEM;
	}

	public XiaoshouException(Type type) {
		this.type = type;
	}

	@Override
	public String getMessage() {
		return this.type.msg;
	}

	public enum Type {
		NONE("无异常"), SYSTEM("系统异常"), USERNAME_PASSWORD("用户名密码错误"), TOKEN_NOT_FIND("登陆信息没找到");
		String msg;

		private Type(String msg) {
			this.msg = msg;
		}

		public String getMsg() {
			return msg;
		}

	}

}

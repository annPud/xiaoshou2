package com.gxwsxx.xiaoshou.model;

import java.util.List;

public class StartupModel {
	AppModel app;
	UserModel user;
	List<MenuModel> menus;

	public AppModel getApp() {
		return app;
	}

	public void setApp(AppModel app) {
		this.app = app;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public List<MenuModel> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuModel> menus) {
		this.menus = menus;
	}

}

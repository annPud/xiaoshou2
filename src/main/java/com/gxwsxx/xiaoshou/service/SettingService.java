package com.gxwsxx.xiaoshou.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxwsxx.xiaoshou.mapper.SettingMapper;
import com.gxwsxx.xiaoshou.model.AppModel;
import com.gxwsxx.xiaoshou.model.SettingModel;

@Service
public class SettingService {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	SettingMapper sm;

	public List<SettingModel> getByApp() {
		return sm.selectByIdLike("app.%");
	}

	public AppModel getApp() {
		AppModel app = new AppModel();
		SettingModel s = sm.selectById("app.name");
		if (null != s) {
			app.setName(s.getName());
		}
		s = sm.selectById("app.description");
		if (null != s) {
			app.setDescription(s.getName());
		}
		return app;
	}
}

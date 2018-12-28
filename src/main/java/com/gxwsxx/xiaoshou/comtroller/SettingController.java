package com.gxwsxx.xiaoshou.comtroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gxwsxx.xiaoshou.model.AppModel;
import com.gxwsxx.xiaoshou.model.ResponseModel;
import com.gxwsxx.xiaoshou.service.SettingService;

@RestController
@RequestMapping("/setting")
public class SettingController {

	@Autowired
	SettingService ss;

	@GetMapping("/app")
	public ResponseModel app() {
		AppModel am = ss.getApp();
		return new ResponseModel(am);
	}
}

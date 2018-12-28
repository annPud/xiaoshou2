package com.gxwsxx.xiaoshou.comtroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gxwsxx.xiaoshou.exception.XiaoshouException;
import com.gxwsxx.xiaoshou.model.AppModel;
import com.gxwsxx.xiaoshou.model.MenuModel;
import com.gxwsxx.xiaoshou.model.ResponseModel;
import com.gxwsxx.xiaoshou.model.StartupModel;
import com.gxwsxx.xiaoshou.model.TokenModel;
import com.gxwsxx.xiaoshou.model.UserModel;
import com.gxwsxx.xiaoshou.service.MenuService;
import com.gxwsxx.xiaoshou.service.SettingService;
import com.gxwsxx.xiaoshou.service.UserService;

@RequestMapping("/auth")
@RestController
public class AuthController {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService us;

	@Autowired
	SettingService ss;

	@Autowired
	MenuService ms;

	@PostMapping("/token")
	public ResponseModel postToken(@RequestBody UserModel user) throws XiaoshouException {
		String token = us.login(user.getUsername(), user.getPassword());
		TokenModel tm = new TokenModel();
		tm.setToken(token);
		return new ResponseModel(tm);
	}

	@DeleteMapping("/token")
	public ResponseModel delToken(@RequestHeader String token) {
		us.logout(token);
		return new ResponseModel();
	}

	@GetMapping("/startup")
	public ResponseModel startup(@RequestHeader String token) throws XiaoshouException {
		log.debug(token);
		UserModel u = us.getUserByToken(token);
		StartupModel sm = new StartupModel();
		sm.setUser(u);
		AppModel a = ss.getApp();
		sm.setApp(a);
		List<MenuModel> m = ms.getAllByRole(u.getRole());
		sm.setMenus(m);
		return new ResponseModel(sm);
	}
}

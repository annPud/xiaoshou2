package com.gxwsxx.xiaoshou.comtroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gxwsxx.xiaoshou.exception.XiaoshouException;
import com.gxwsxx.xiaoshou.model.MenuModel;
import com.gxwsxx.xiaoshou.model.ResponseModel;
import com.gxwsxx.xiaoshou.model.UserModel;
import com.gxwsxx.xiaoshou.service.MenuService;
import com.gxwsxx.xiaoshou.service.UserService;

@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	UserService us;

	@Autowired
	MenuService ms;

	@GetMapping
	public ResponseModel byToken(@RequestHeader String token) throws XiaoshouException {
		UserModel u = us.getUserByToken(token);
		List<MenuModel> m = ms.getAllByRole(u.getRole());
		return new ResponseModel(m);
	}
}

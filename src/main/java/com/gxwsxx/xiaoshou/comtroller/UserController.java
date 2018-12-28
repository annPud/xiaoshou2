package com.gxwsxx.xiaoshou.comtroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gxwsxx.xiaoshou.exception.XiaoshouException;
import com.gxwsxx.xiaoshou.mapper.UserMapper;
import com.gxwsxx.xiaoshou.model.ResponseModel;
import com.gxwsxx.xiaoshou.model.UserModel;
import com.gxwsxx.xiaoshou.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService us;

	@Autowired
	UserMapper um;

	@GetMapping("")
	public ResponseModel get(UserModel model) {
		List<UserModel> list = um.select(model);
		return new ResponseModel(list);
	}

	@PostMapping("")
	public ResponseModel post(@RequestBody UserModel model) {
		um.insert(model);
		return new ResponseModel();
	}

	@DeleteMapping("/{id}")
	public ResponseModel del(@PathVariable String id) {
		um.delete(id);
		return new ResponseModel();
	}

	@PatchMapping("")
	public ResponseModel patch(@RequestBody UserModel model) {
		um.update(model);
		return new ResponseModel();
	}

	@GetMapping("/{id}")
	public ResponseModel byId(@PathVariable String id) {
		return new ResponseModel(us.getUserById(id));
	}

	@GetMapping("/self")
	public ResponseModel self(@RequestHeader String token) throws XiaoshouException {
		UserModel u = us.getUserByToken(token);
		return new ResponseModel(u);
	}
}

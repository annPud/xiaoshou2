package com.gxwsxx.xiaoshou.comtroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gxwsxx.xiaoshou.exception.XiaoshouException;
import com.gxwsxx.xiaoshou.model.PageModel;
import com.gxwsxx.xiaoshou.model.RecordModel;
import com.gxwsxx.xiaoshou.model.ResponseModel;
import com.gxwsxx.xiaoshou.model.UserModel;
import com.gxwsxx.xiaoshou.service.RecordService;
import com.gxwsxx.xiaoshou.service.UserService;

@RestController
@RequestMapping("/record")
public class RecordController {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	RecordService rs;

	@Autowired
	UserService us;

	@PostMapping
	public ResponseModel post(@RequestBody RecordModel model, @RequestHeader String token) throws XiaoshouException {
		UserModel u = us.getUserByToken(token);
		model.setSalerId(u.getId());
		rs.add(model);
		return new ResponseModel();
	}

	@GetMapping
	public PageModel get(@RequestHeader String token, RecordModel model, @RequestParam(defaultValue = "1") Integer pi,
			@RequestParam(defaultValue = "10") Integer ps) throws XiaoshouException {
		UserModel u = us.getUserByToken(token);
		if (UserModel.Role.SALER == u.getRole()) {
			model.setSalerId(u.getId());
		}
		List<RecordModel> list = rs.get(model, pi, ps);
		Long total = rs.count(model);
		PageModel pm = new PageModel();
		pm.setData(list);
		pm.setTotal(total);
		return pm;
	}

//	@GetMapping("/user/{userId}")
//	public ResponseModel getByUser(@PathVariable String userId) {
//		RecordModel model = new RecordModel();
//		model.setSalerId(userId);
//		List<RecordModel> list = rs.get(model);
//		return new ResponseModel(list);
//	}
//
//	@GetMapping("/user")
//	public ResponseModel getByToken(@RequestHeader String token) throws XiaoshouException {
//		UserModel u = us.getUserByToken(token);
//		RecordModel model = new RecordModel();
//		model.setSalerId(u.getId());
//		List<RecordModel> list = rs.get(model);
//		return new ResponseModel(list);
//	}

}

package com.gxwsxx.xiaoshou.comtroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gxwsxx.xiaoshou.mapper.GoodsMapper;
import com.gxwsxx.xiaoshou.model.GoodsModel;
import com.gxwsxx.xiaoshou.model.ResponseModel;

@RestController
@RequestMapping("/goods")
public class GoodsController {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	GoodsMapper gm;

	@GetMapping("")
	public ResponseModel get(GoodsModel model) {
		List<GoodsModel> list = gm.select(model);
		return new ResponseModel(list);
	}

	@DeleteMapping("/{id}")
	public ResponseModel del(@PathVariable String id) {
		gm.delete(id);
		return new ResponseModel();
	}

	@PostMapping("")
	public ResponseModel post(@RequestBody GoodsModel model) {
		gm.insert(model);
		return new ResponseModel();
	}
}

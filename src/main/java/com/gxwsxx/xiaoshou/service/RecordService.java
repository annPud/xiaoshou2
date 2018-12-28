package com.gxwsxx.xiaoshou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxwsxx.xiaoshou.mapper.GoodsMapper;
import com.gxwsxx.xiaoshou.mapper.RecordMapper;
import com.gxwsxx.xiaoshou.model.RecordModel;

@Service
public class RecordService {

	@Autowired
	GoodsMapper gm;

	@Autowired
	RecordMapper rm;

	public void add(RecordModel model) {
		gm.minus1(model.getGoodsId());
		rm.insert(model);
	}

//	public List<RecordModel> get(RecordModel model) {
//		return rm.select(model, 1, 10);
//	}

	public List<RecordModel> get(RecordModel model, Integer pi, Integer ps) {
		return rm.select(model, pi, ps);
	}

	public Long count(RecordModel model) {
		return rm.count(model);
	}

}

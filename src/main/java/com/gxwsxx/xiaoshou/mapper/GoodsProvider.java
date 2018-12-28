package com.gxwsxx.xiaoshou.mapper;

import org.apache.ibatis.jdbc.SQL;

import com.gxwsxx.xiaoshou.model.GoodsModel;

public class GoodsProvider {

	public String select(GoodsModel model) {
		return new SQL() {
			{
				SELECT("*").FROM("goods").WHERE("status='" + GoodsModel.Status.NORMAL + "'");
				if (null != model.getName()) {
					WHERE("name like '%" + model.getName() + "%'");
				}
			}
		}.toString();
	}

}

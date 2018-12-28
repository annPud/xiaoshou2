package com.gxwsxx.xiaoshou.mapper;

import org.apache.ibatis.jdbc.SQL;

import com.gxwsxx.xiaoshou.model.RecordModel;

public class RecordProvider {

	public String select(RecordModel model, Integer pi, Integer ps) {
		return new SQL() {
			{
				SELECT("r.*,u.name as saler_name,g.name as goods_name").FROM("record r");
				LEFT_OUTER_JOIN("user u on r.saler_id = u.id");
				LEFT_OUTER_JOIN("goods g on r.goods_id = g.id");
				if (null != model.getMobile() && !model.getMobile().isEmpty()) {
					WHERE("r.mobile = #{model.mobile}");
				}
				if (null != model.getSalerId() && !model.getSalerId().isEmpty()) {
					WHERE("u.id = #{model.salerId}");
				}
				ORDER_BY("r.time desc");
			}
		}.toString().concat(" limit " + ((pi - 1) * ps) + ",#{ps}");
	}

	public String count(RecordModel model) {
		return new SQL() {
			{
				SELECT("count(id)").FROM("record");
				if (null != model.getMobile() && !model.getMobile().isEmpty()) {
					WHERE("mobile = #{mobile}");
				}
				if (null != model.getSalerId() && !model.getSalerId().isEmpty()) {
					WHERE("saler_id = #{salerId}");
				}
			}
		}.toString();
	}
}

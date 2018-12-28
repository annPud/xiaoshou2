package com.gxwsxx.xiaoshou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;

import com.gxwsxx.xiaoshou.model.RecordModel;

public interface RecordMapper {

	@Insert("insert into record (id,mobile,goods_id,saler_id) values (#{id},#{mobile},#{goodsId},#{salerId})")
	@SelectKey(before = true, keyProperty = "id", resultType = String.class, statement = {
			"select replace(uuid(),'-','') from dual" })
	void insert(RecordModel model);

	@SelectProvider(method = "select", type = RecordProvider.class)
	List<RecordModel> select(RecordModel model, Integer pi, Integer ps);

	@SelectProvider(method = "count", type = RecordProvider.class)
	Long count(RecordModel model);
}

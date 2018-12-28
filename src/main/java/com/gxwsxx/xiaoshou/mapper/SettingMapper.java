package com.gxwsxx.xiaoshou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.gxwsxx.xiaoshou.model.SettingModel;

public interface SettingMapper {

	@Select("select * from setting")
	List<SettingModel> select();

	@Select("select * from setting where id = #{id}")
	SettingModel selectById(String id);

	@Select("select * from setting where id like #{idLike}")
	List<SettingModel> selectByIdLike(String idLike);
}

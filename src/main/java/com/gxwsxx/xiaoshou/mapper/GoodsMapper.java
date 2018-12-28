package com.gxwsxx.xiaoshou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.gxwsxx.xiaoshou.model.GoodsModel;

public interface GoodsMapper {

//	@Select("select * from goods where status='NORMAL'")
	@SelectProvider(method = "select", type = GoodsProvider.class)
	List<GoodsModel> select(GoodsModel model);

	@Update("update goods set status='DELETE' where id=#{id}")
	void delete(String id);

	@Insert("insert into goods (id,name,remain) values (#{id},#{name},#{remain})")
	@SelectKey(before = true, keyProperty = "id", resultType = String.class, statement = {
			"select replace(uuid(),'-','') from dual" })
	void insert(GoodsModel gm);

	@Update("update goods set remain = (remain - 1) where id = #{id}")
	void minus1(String id);

}

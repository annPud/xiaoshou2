package com.gxwsxx.xiaoshou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.gxwsxx.xiaoshou.model.MenuModel;
import com.gxwsxx.xiaoshou.model.UserModel;

public interface MenuMapper {

	@Select("select * from menu where role is null or role = #{role}")
	List<MenuModel> selectByRole(UserModel.Role role);

	@Select("select * from menu where parent is null and (role is null or role = #{role})")
	List<MenuModel> selectRoot(UserModel.Role role);

	@Select("select * from menu where parent = #{parent} and (role is null or role = #{role})")
	List<MenuModel> selectByParent(@Param("role") UserModel.Role role, @Param("parent") String parent);
}

package com.gxwsxx.xiaoshou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.gxwsxx.xiaoshou.model.UserModel;

public interface UserMapper {

	final static String USER_SECURITY = "id,name,username,role,email";

	@SelectProvider(method = "select", type = UserProvider.class)
	List<UserModel> select(UserModel model);

	@Insert("insert into user (id,name,username,role,email,password) values (#{id},#{name},#{username},#{role},#{email},#{password})")
	@SelectKey(before = true, keyProperty = "id", resultType = String.class, statement = {
			"select replace(uuid(),'-','') from dual" })
	void insert(UserModel model);

	@Update("update user set status = 'DELETE' where id = #{id}")
	void delete(String id);

	@UpdateProvider(method = "update", type = UserProvider.class)
	void update(UserModel model);

	@Select("select * from user where username = #{username} and status = 'NORMAL'")
	UserModel selectByUsername(String username);

	@Select("select " + USER_SECURITY + " from user where id = #{id}")
	UserModel selectById(String id);

	@Select("select " + USER_SECURITY + " from user where token = #{token} and status = 'NORMAL'")
	UserModel selectByToken(String token);

	@Update("update user set token = #{token} where id = #{id} and status = 'NORMAL'")
	void updateToken(UserModel u);
}
